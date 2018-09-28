
package programaemt;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;



/**
 * Clase que crea el objeto servicio
 * @author wanyos
 */
public class Servicio {
    
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.linea);
        hash = 29 * hash + Objects.hashCode(this.turno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servicio other = (Servicio) obj;
        if (!Objects.equals(this.linea, other.linea)) {
            return false;
        }
        if (!Objects.equals(this.turno, other.turno)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
}
