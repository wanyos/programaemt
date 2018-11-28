
package entrada_datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import tratamiento_datos.Servicio;

/**
 * Clase que guarda los servicios que se han introducido a la entrada de datos
 * La estructura esta formada por un ArrayList de Year
 * Cada Year contiene un array con los doce meses del año, cada mes se implementa por un 
 * ArrayList de servicios
 * @author wanyos
 */
public class BaseDatosServicios implements Serializable {
    
    
    private static ArrayList<Year> base_year;
    
    
    public BaseDatosServicios(){
        base_year = new ArrayList<>();
    }
    
    
    
    /**
     * Devuelve al año si existe
     * @param id_year
     * @return
     */
    public static Year getYear(int id_year){
      Year y = null;
      int posicion = getPosicionYear(id_year);
      if(posicion > -1){
          y = base_year.get(posicion);
      }
       return y;
    }
    
    
    /**
     * Si el año existe devuelve la lista del mes con los servicios
     * @param month
     * @param id_year
     * @return 
     */
    public static ArrayList<Servicio> getMonth(int month, int id_year){
        ArrayList<Servicio> lista_mes = null;
        int posicion = getPosicionYear(id_year);
        if(posicion > -1){
            lista_mes = base_year.get(posicion).getMonth(month);
        }
        return lista_mes;
    }
    
    
    /**
     * Guarda un servicio en el año y el mes que le indica su fecha
     * Si no existe el dato year en la base de datos se crea un nuevo year con ese dato
     * @param s 
     */
    public static void setServicio(Servicio s){
        LocalDate fecha_servicio = s.getFecha_inicio();
        Year anno;
        int year = fecha_servicio.getYear();
        int month = fecha_servicio.getMonthValue();
        
        int y = getPosicionYear(year);
        if(y > -1){
            //el año existe
            anno = base_year.get(y);
        } else {
            //el año no existe
            anno = new Year(year);
            base_year.add(anno);
        }
        anno.setServicio(month, s);
    }
    
    
    
    
    /**
     * Devuelve el servicio si existe por los datos del parámetro
     * @param dia
     * @param mes
     * @param year
     * @return null si no existe servicio
     */
    public static Servicio getServicio (int dia, int mes, int year){
        Servicio s = null;
        int pos = getPosicionYear(year);
        if(pos > -1){
            //el año existe hay que ir al mes y el dia para saber si existe servicio
            Year y = base_year.get(pos);
            s = y.getServicio(mes, dia);
        }
        return s;
    }
    
    
    /**
     * Elimina el servicio si es que existe
     * @param dia
     * @param mes
     * @param year
     * @return true si se elimina el servicio
     */
    public static boolean deleteServicio(int dia, int mes, int year){
        int pos = getPosicionYear(year);
        if(pos > -1){
            //el año existe se busca el mes y el dia del servicio
            Year y = base_year.get(pos);
            if(y.deleteServicio(mes, dia)){
                //se elimino el servicio
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Busca la posición de un year dentro de la lista de años
     * si no existe devuelve -1
     * @param year
     * @return 
     */
    private static int getPosicionYear(int year){
        int pos = 0;
        while (pos < base_year.size()) {
            int id_year = base_year.get(pos).getIdYear();
            if (id_year == year) {
                return pos;
            } else {
                pos++;
            }
        }
        return -1;
    }
    
    
    
    
    
    
    
    /**
     * Clase que crea un año de servicios
     */
    public static class Year {
        
        private ArrayList<Servicio> [] meses;
        private final int id_year;
        
        public Year(int year){
            meses = new ArrayList[12];
            this.id_year = year;
            this.initMeses();
        }
        
        public int getIdYear(){
            return this.id_year;
        }
        
        /**
         * Inicia la lista de cada mes
         */
        private void initMeses(){
            for(int a = 0; a<12; a++){
                meses[a] = new ArrayList<>();
            }
        }
        
        /**
         * Devuelve el mes del parámetro
         * @param mont
         * @return mes-1 para que coincida con el número del mes
         */
        public ArrayList<Servicio> getMonth (int mont){
            return meses[mont-1];
        }
        
        
        private void setServicio (int month, Servicio s){
            meses[month-1].add(s);
            this.ordenarListaServicios(month);
        }
        
        /**
         * Despues de guardar un servicio en la lista del mes esta se ordena de menor a mayor por día
         * @param month 
         */
        private void ordenarListaServicios(int month){
            Collections.sort(meses[month-1]); 
        }
        
        
        /**
         * Busca un servicio por su fecha de inicio
         * @param month
         * @param day
         * @return si existe devuelve el servicio de lo contrario null
         */
        private Servicio getServicio (int month, int day){
           LocalDate fecha_buscar = LocalDate.of(this.id_year, month, day);
           Servicio s = null;
           ArrayList<Servicio> lista_mes = meses[month-1];
           int posicion = this.getPosicion(lista_mes, fecha_buscar);
           if(posicion > -1){
               s = lista_mes.get(posicion);
           }
           return s;
        }
        
        
        /**
         * Elimina el servicio que ocupa la posición que devuelve el metodo getPosicion()
         * @param month
         * @param day 
         */
        private boolean deleteServicio (int month, int day){
           LocalDate fecha_buscar = LocalDate.of(id_year, month-1, day);
           ArrayList<Servicio> lista_mes = meses[month - 1];
           int posicion = this.getPosicion(lista_mes, fecha_buscar);
           if(posicion > -1){
               //el servicio existe y se elimina
              lista_mes.remove(posicion);
              return true;
           }
           return false;
        }
        
        
        
        /**
         * Devuelve la posición que ocupa un servicio en la lista del mes
         * busca el servicio por un fecha de inicio
         * @return posición del servicio
         */
        private int getPosicion(ArrayList<Servicio> lista, LocalDate fecha_buscar){
            if (lista.isEmpty()) {
                return -1;
            } else {
                int pos = 0;
                while (pos < lista.size()) {
                    Servicio aux = lista.get(pos);
                    if (aux.getFecha_inicio().equals(fecha_buscar)) {
                        return pos;
                    } else {
                        pos++;
                    }
                }
            }
            return -1;
        }
        
        
        
        
        
    }
    
    
    
    
}
