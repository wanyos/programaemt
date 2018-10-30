
package entrada_datos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que contiene metodos estaticos para usar desde cualquier clase
 * @author wanyos
 */
public class Util {
    
    
    
    /**
     * Pasa de String a LocalTime
     * @param hora
     * @return
     */
    public static LocalTime getObjetoHora(String hora) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hour = LocalTime.parse(hora, formato);
        return hour;
    }

    /**
     * Pasa de String a LocalDate
     * @param fecha
     * @return
     */
    public static LocalDate getObjetoFecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fech = LocalDate.parse(fecha, formato);
        return fech;
    }
    
    /**
     * Devuelve el objeto LocalDate como String
     * @param fecha
     * @return 
     */
    public static String getFechaCadena(LocalDate fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha_cadena = fecha.format(formato);
        return fecha_cadena;
    }
    
    /**
     * Devuelve el objeto LocalTime
     * @param hora
     * @return 
     */
    public static String getHoraCadena(LocalTime hora){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String hora_cadena = hora.format(formato);
        return hora_cadena;
    }
    
}
