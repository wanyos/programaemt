
package tratamiento_datos;

import entrada_datos.Util;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;



/**
 * Clase que crea el objeto servicio
 * @author wanyos
 */
public class Servicio implements Comparable<Servicio> {
    
    private String linea, turno, lugar_inicio, lugar_fin, lugar_inicio_p, lugar_fin_p;
    private LocalDate fecha_inicio, fecha_fin;
    private LocalDate fecha_inicio_p, fecha_fin_p;
    private LocalTime hora_inicio, hora_fin;
    private LocalTime hora_inicio_p, hora_fin_p;
    
    
    /**
     * Constructor turno seguido
     */
    public Servicio(String linea, String turno, LocalDate fechaI, LocalTime horaI, LocalDate fechaF, LocalTime horaF, String lugarI, String lugarF){
        this.linea = linea;
        this.turno = turno;
        this.fecha_inicio = fechaI;
        this.hora_inicio = horaI;
        this.fecha_fin = fechaF;
        this.hora_fin = horaF;
        this.lugar_inicio = lugarI;
        this.lugar_fin = lugarF;
    }
    
    /**
     * Constructor turno partido
     */
    public Servicio(String linea, String turno, LocalDate fechaI, LocalTime horaI, LocalDate fechaF, LocalTime horaF, String lugarI, String lugarF,
                    LocalDate fechaIP, LocalTime horaIP, LocalDate fechaFP, LocalTime horaFP, String lugarIP, String lugarFP){
        this(linea, turno, fechaI, horaI, fechaF, horaF, lugarI, lugarF);
        this.fecha_inicio_p = fechaIP;
        this.hora_inicio_p = horaIP;
        this.fecha_fin_p = fechaFP;
        this.hora_fin_p = horaFP;
        this.lugar_inicio_p = lugarIP;
        this.lugar_fin_p = lugarFP;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getLugar_inicio() {
        return lugar_inicio;
    }

    public void setLugar_inicio(String lugar_inicio) {
        this.lugar_inicio = lugar_inicio;
    }

    public String getLugar_fin() {
        return lugar_fin;
    }

    public void setLugar_fin(String lugar_fin) {
        this.lugar_fin = lugar_fin;
    }

    public String getLugar_inicio_p() {
        return lugar_inicio_p;
    }

    public void setLugar_inicio_p(String lugar_inicio_p) {
        this.lugar_inicio_p = lugar_inicio_p;
    }

    public String getLugar_fin_p() {
        return lugar_fin_p;
    }

    public void setLugar_fin_p(String lugar_fin_p) {
        this.lugar_fin_p = lugar_fin_p;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_inicio_p() {
        return fecha_inicio_p;
    }

    public void setFecha_inicio_p(LocalDate fecha_inicio_p) {
        this.fecha_inicio_p = fecha_inicio_p;
    }

    public LocalDate getFecha_fin_p() {
        return fecha_fin_p;
    }

    public void setFecha_fin_p(LocalDate fecha_fin_p) {
        this.fecha_fin_p = fecha_fin_p;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public LocalTime getHora_inicio_p() {
        return hora_inicio_p;
    }

    public void setHora_inicio_p(LocalTime hora_inicio_p) {
        this.hora_inicio_p = hora_inicio_p;
    }

    public LocalTime getHora_fin_p() {
        return hora_fin_p;
    }

    public void setHora_fin_p(LocalTime hora_fin_p) {
        this.hora_fin_p = hora_fin_p;
    }

    public int getDia(){
       return this.fecha_inicio.getDayOfMonth();
    }
    
    public int getMes(){
       return this.fecha_inicio.getMonthValue();
    }
    
    public int getYear(){
        return this.fecha_inicio.getYear();
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.linea);
        hash = 29 * hash + Objects.hashCode(this.turno);
        return hash;
    }

    /**
     * Si las fechas son iguales el servicio es el mismo
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Servicio){
           if((this.getFecha_inicio() == ((Servicio) obj).getFecha_inicio() && this.getFecha_fin() == ((Servicio) obj).getFecha_fin()) &&
                   (this.getFecha_inicio_p() == ((Servicio) obj).getFecha_inicio_p() && this.getFecha_fin_p() == ((Servicio) obj).getFecha_fin_p())){
               return true;
           } 
        }
        return false;
    }
    
    
    @Override
    public String toString(){
        String servicio = null;
            servicio = "\tLinea: " + this.getLinea() + "     " + "Turno: " + this.getTurno() + "\n"
                    + "FechaI:  " + Util.getFechaCadena(this.getFecha_inicio()) + "     " + "HoraI:   " + Util.getHoraCadena(this.getHora_inicio()) + "\n"
                    + "FechaF: " + Util.getFechaCadena(this.getFecha_fin()) + "     " + "HoraF: "+Util.getHoraCadena(this.getHora_fin()) + "\n"
                    + "LugarI: " + this.getLugar_inicio() + "     " + "LugarF: " + this.getLugar_fin()+"\n\n";
        if (this.getFecha_inicio_p() != null) {
            //se imprime turno partido
            String partido = "FechaIP:  " + Util.getFechaCadena(this.getFecha_inicio_p()) + "     " + "HoraIP:   " + Util.getHoraCadena(this.getHora_inicio_p()) + "\n"
                              + "FechaFP: " + Util.getFechaCadena(this.getFecha_fin_p()) + "     " + "HoraFP: " + Util.getHoraCadena(this.getHora_fin_p()) + "\n"
                              + "LugarIP: " + this.getLugar_inicio_p() + "     " + "LugarFP: " + this.getLugar_fin_p()+"\n";

            servicio = servicio+partido;
        }
        return servicio;
    }
    
    
    /**
     * Compara si el valor del dia es mayor, menor o igual
     * @param a
     * @return a>s = -1    a<s = 1   a==s = 0
     */
    @Override
    public int compareTo(Servicio a){
        int valor = Integer.MAX_VALUE;
        int dia_s = this.getDia();
        int dia_a = a.getDia();
        if(a.getMes() == this.getMes() && a.getYear() == this.getYear()){
          if(dia_s < dia_a){
              return -1;
          } else if(dia_s > dia_a){
              return 1;
          } else {
              return 0;
          }
        }
        return valor;
    }
    
    
    
    
}
