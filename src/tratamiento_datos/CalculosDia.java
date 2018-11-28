
package tratamiento_datos;

import entrada_datos.Archivo;
import entrada_datos.Util;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que contiene todas las funciones que calculan los valores de un dia de servicio
 * @author wanyos
 */
public class CalculosDia {
    
    //recorrer los datos del mes obteniendo de cada dia sus valores
    //hay que reconocer si es sabado, festivo o laboral
    //si es sabado o festivo se añade el valor por trabajar ese dia
    //obtener las horas nocturnas de cada dia
    //obtener si existe turno partido, partido en laboral y partido en festivo
    //obtener si se empieza en calle pues hay que añadir el plus de recaudacion
    
    private int dia_semana;
    private boolean dia_festivo;
    //horas enteras de partido
    private int h_partido_l, h_partido_s, h_partido_f;
    //medias horas de partido
    private int m_h_partido_l, m_h_partido_s, m_h_partido_f;
    private int total_sabados;
    private int total_festivos;
    private float h_nocturnas;
    private int total_entrega_recaudacion;
    private static boolean proxima_entrega_recaudacion;
    
    
    
    public CalculosDia(ArrayList<Servicio> lista_mes){
        if (lista_mes != null && !lista_mes.isEmpty()) {
            for (Servicio s : lista_mes) {
                //primero se comprueba si el dia es festivo y se asigna a su variable 
                this.dia_festivo = this.getDiaFestivo(s);
                //se asigna a su variable el dia de la semana que se presta el servicio
                this.setDiaSemana(s);
                
                //calculos del día
                
                //asignar si el día es sábado o festivo para su contabilidad
                this.setDia();
                
                //calcular el total de horas nocturnas
                this.setHorasNocturnas(s.getHora_inicio(), s.getHora_fin());
                
                //calcular turno partido
                if(s.getFecha_inicio_p() != null){
                    //existe turno partido
                    this.setPartido(s);
                    //calcular las horas nocturnas por si existen en esta parte del turno
                    this.setHorasNocturnas(s.getHora_inicio_p(), s.getHora_fin_p());
                }
                
                //anotar entrega de recaudacion si procede
                this.setEntregaRecaudacion(s);
            }
        }
        //Crear el objeto CalulosMes y le pasamos por parametro los datos aqui recogidos
        //total_festivos, total_sabados, total_horas_nocturnas, total_horas_partido_f, total_horas_partido_s, total_horas_partido_l
        //total_medias_partidos_f, total_medias_partidos_s, total_medias_partido_l
        new CalculosMes(h_partido_l, h_partido_s, h_partido_f, m_h_partido_l, m_h_partido_s, m_h_partido_f, 
                        total_sabados, total_festivos, h_nocturnas, total_entrega_recaudacion);
    }
    
    /**
     * Anota el valor de la variable para la proxima entrega
     * @param anotar 
     */
    public static void setProximaEntregaRecaudacion(boolean anotar){
        proxima_entrega_recaudacion = anotar;
    }
    
    /**
     * Devuelve el valor de proxima_entrega_recaudacion, si es false
     * en esta nueva entrega hay que anotar el plus, si estaba a true, no hay 
     * que anotar el plus y la variable se cambia a false
     * @return 
     */
    public static boolean getProximaEntregaRecaudacion(){
        return proxima_entrega_recaudacion;
    }
    
    /**
     * Comprueba si la fecha de inicio del servicio es un dia festivo
     * este metodo pide la lista de festivos del año del servicio
     * @param s 
     */
    private boolean getDiaFestivo(Servicio s){
        Archivo archivo = new Archivo();
        String year = String.valueOf(s.getYear());
        String nombre_archivo_festivos = "festivos"+year;
        try {
            //usa la ruta donde estan los archivos, mas el nombre del archivo con el año
            ArrayList<String> lista_festivos = archivo.leerArchivo(Archivo.PATH+nombre_archivo_festivos);
            for(String aux: lista_festivos){
               LocalDate fecha_festivo = Util.getObjetoFecha(aux);
               if(fecha_festivo.equals(s.getFecha_inicio())){
                   return true;
               }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, " Error al leer archivo de festivos...", "Error!!!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    
    /**
     * Calcula el día de la semana y si es festivo o sabado añade a las 
     * variables globales el valor de esos dias
     * @param s 
     */
    private void setDiaSemana(Servicio s){
        //lunes = 1, domingo = 7
        int ordinal = s.getFecha_inicio().getDayOfWeek().getValue();
        this.dia_semana = ordinal;
    }
    
    
    /**
     * Aumenta el la contabilidad si el día es sábado o festivo
     */
    private void setDia(){
        if(this.dia_festivo){
            this.total_festivos += 1;
        } else if(this.dia_semana == 6){
            //el día es sábado
            this.total_sabados += 1;
        }
    }
    
    
    /**
     * Recibe el servicio de turno partido, calcula el valor del partido
     * distinguiendo si es laboral, sabado o festivo
     * @param s
     */
    private void setPartido(Servicio s){
        //primero se calculan las horas de partido
        int t_partido = (int) s.getFecha_inicio_p().until(s.getFecha_fin(), ChronoUnit.MINUTES);
        int horas = 0, medias_horas = 0;
        if(t_partido >= 60){
           double total_partido = t_partido/60;
           horas = (int) Math.ceil(total_partido);
           medias_horas = (int) (horas-1/0.5);
           horas = 1;
        } else {
            //implementa si el partido es menor a 60 minutos
            
            
        }
        if(this.dia_semana == 7 || this.dia_festivo){
            //domingo o festivo
            this.h_partido_f += horas;
            this.m_h_partido_f += medias_horas;
            
        } else if(this.dia_semana == 6){
            //sabado
            this.h_partido_s += horas;
            this.m_h_partido_s += medias_horas;
            
        } else {
            //laboral
            this.h_partido_l += horas;
            this.m_h_partido_l += medias_horas;
        }
    }
    
    
    /**
     * Calcula la cantidad de horas nocturnas del turno
     * Calcula si la hora de comienzo es antes de las 6:00 o despues de las 22:00 y lo mismo para la hora de salida
     * @param s 
     */
    private void setHorasNocturnas(LocalTime hora_init_ser, LocalTime hora_fin_ser){
        LocalTime hora_fin_nocturnas = Util.getObjetoHora("06:00");
        LocalTime hora_init_nocturnas = Util.getObjetoHora("22:00");
        int inicio_antes_seis = 0, inicio_despues_veintidos = 0;
        int fin_antes_seis = 0, fin_despues_veintidos = 0;
        
        if(hora_init_ser.isBefore(hora_fin_nocturnas)){
            //init-6:00 el servicio comienza antes de las 6:00
            inicio_antes_seis = (int) hora_init_ser.until(hora_fin_nocturnas, ChronoUnit.MINUTES);
        }
        if(hora_init_ser.isAfter(hora_init_nocturnas)){
            //22:00-init el servicio comienza despues de las 22:00
            inicio_despues_veintidos = (int) hora_init_nocturnas.until(hora_init_ser, ChronoUnit.MINUTES);
        }
        if(hora_fin_ser.isBefore(hora_fin_nocturnas)){
            //fin-6:00 el servicio termina antes de las 6:00
            fin_antes_seis = (int) hora_fin_ser.until(hora_fin_nocturnas, ChronoUnit.MINUTES);
        }
        if(hora_fin_ser.isAfter(hora_init_nocturnas)){
            //22:00-fin el servicio termina despues de las 22:00
            fin_despues_veintidos = (int) hora_init_nocturnas.until(hora_fin_ser, ChronoUnit.MINUTES);
        }
        int total_minutos = inicio_antes_seis + inicio_despues_veintidos + fin_antes_seis + fin_despues_veintidos;
        //lo primero es obtener las horas completas
        int total_horas = total_minutos/60;
        int resto_minutos = total_minutos - total_horas*60;
        if(resto_minutos >= 11 && resto_minutos < 41){
            //equivale a media hora mas
            this.h_nocturnas += 0.5;
        } else if(resto_minutos >= 41){
            //equivale a una hora mas
            this.h_nocturnas += 1;
        }
        this.h_nocturnas += total_horas;
    }
    
    /**
     * Contabiliza si hay que añadir día de entrega de recaudación
     * Esto se contabiliza si el servicio empieza y termina fuera de estación
     * Se contabiliza una vez si y otra no
     * @param s 
     */
    private void setEntregaRecaudacion(Servicio s){
        boolean anotar_plus = false;
        //si no se empieza ni termina en estacion
        if (!s.getLugar_inicio().equalsIgnoreCase("estacion") && !s.getLugar_fin().equalsIgnoreCase("estacion")) {
            anotar_plus = true;
        } else if (s.getFecha_inicio_p() != null && !s.getLugar_inicio_p().equalsIgnoreCase("estacion") && !s.getLugar_fin_p().equalsIgnoreCase("estacion")) {
            //hay que tener en cuenta si es turno partido
            anotar_plus = true;
        }

        //si la variable es false hay que añadir plus y cambiar la variable
        if (anotar_plus && !getProximaEntregaRecaudacion()) {
            this.total_entrega_recaudacion++;
            setProximaEntregaRecaudacion(true);
        } else if (getProximaEntregaRecaudacion()) {
            setProximaEntregaRecaudacion(false);
        }
    }
    
    
    
    //probar funciones
    public static void main(String [] args){
        
    }
    
}
