
package tratamiento_datos;

import entrada_datos.Archivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Clase que calcula todo lo relacionado con el mes de cobro
 * A esta clasele llegan los datos sacados de CalculosDia, que son todos los datos de cada día
 * y su suma total
 * Esta clase tiene que acceder al archivo con los parámetros de cobro de cada concepto
 * @author wanyos
 */

public class CalculosMes {
    
    
    //horas enteras de partido
    private final int h_partido_l, h_partido_s, h_partido_f;
    //medias horas de partido
    private final int m_h_partido_l, m_h_partido_s, m_h_partido_f;
    private final int total_sabados;
    private final int total_festivos;
    private final float h_nocturnas;
    private final int total_entrega_recaudacion;
    
    private final HashMap<String,Float> valores_nomina = new HashMap();

    
    public CalculosMes(int h_partido_l, int h_partido_s, int h_partido_f, int m_h_partido_l, int m_h_partido_s, int m_h_partido_f, 
                       int total_sabados, int total_festivos, float h_nocturnas, int total_entrega_recaudacion) {
        this.h_partido_l = h_partido_l;
        this.h_partido_s = h_partido_s;
        this.h_partido_f = h_partido_f;
        this.m_h_partido_l = m_h_partido_l;
        this.m_h_partido_s = m_h_partido_s;
        this.m_h_partido_f = m_h_partido_f;
        this.total_sabados = total_sabados;
        this.total_festivos = total_festivos;
        this.h_nocturnas = h_nocturnas;
        this.total_entrega_recaudacion = total_entrega_recaudacion;
        this.leerArchivoDatosNomina();
    }
    
    /**
     * Lee el archivo con los valores de cada concepto de nomina
     * Los guarda en el HashMap, por clave String y valor Float
     */
    private void leerArchivoDatosNomina(){
        //el archivo esta en la carpeta de archivos con el nombre "valores_nomina"
        Archivo ar = new Archivo();
        String ruta_archivo = Archivo.PATH+"valores_nomina.txt";
        try {
            ArrayList<String> valores = ar.leerArchivo(ruta_archivo);
            //los datos de los valores estan en el arrray por lineas
            //se lee cada linea y se separan en String y Float para su almacenamiento en el HashMap
            for(String aux: valores){
                String [] temp = aux.split(";", 2);
                float aux_dos = Float.valueOf(temp[1]);
                this.valores_nomina.put(temp[0], aux_dos);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, " No se ha podido leer el archivo: valores_nomina.txt", "Error!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //------- Se tienen que calcular todos los conceptos de los servicios, para ello se usan los datos del archivo valores_nomina,
    //-------  estos datos han sido cargados en el HashMap para recurrir a ellos.
    
    
    private boolean comprobarClave(String clave){
        return this.valores_nomina.containsKey(clave);
    }
    
    
    private void horasNocturnas(){
        float total_horas_nocturnas;
        if(this.comprobarClave("horas nocturnas")){
            total_horas_nocturnas = this.h_nocturnas * this.valores_nomina.get("horas nocturnas");
        }
    }
    
    private void entregaRecaudacion(){
        
    }
    
    private void sabados(){
        
    }
    
    private void festivos(){
        
    }
    
    private void horasPartidoL(){
        
    }
    
    private void horasPartidoS(){
        
    }
    
    private void horasPartidoF(){
        
    }
    
    private void mediasHorasPartidoL(){
        
    }
    
    private void mediasHorasPartidoS(){
        
    }
    
    private void mediasHorasPartidoF(){
        
    }
    
    
    
    
    
    public static void main(String [] args){
        HashMap<String, Float> mapa = new HashMap();
        
        mapa.put("uno", 1.0f);
        mapa.put("dos", 2.45f);
        mapa.put("tres", 98.456f);
        
        if(mapa.containsKey("uno")){
            System.out.println("Existe el cuatro");
        } else {
            System.out.println("No existe el cuatro");
        }
        
        float k = mapa.get("tres");
        System.out.println(k);
    } 
    
    
    
    
    
    
}
