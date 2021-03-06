
package entrada_datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Manejador de archivo tanto de lectura como de escritura para un archivo .txt
 * @author wanyos
 */
public class Archivo {
    
    //ruta carpeta donde se ubican los archivos del programa
    public static String PATH = "C:\\archivo\\";
    private File archivo_salida;
    
    
    /**
     * Crea una carpeta con un archivo de extensión .txt en la ruta indicada
     * mkdir --> crea la carpeta y requiere que exista la ruta
     * mkdirs --> crea la carpeta, independientemente que exista el path completo, si no existe crea toda la ruta necesaria
     * si el directorio existe se escriben los nuevos datos y se pierden los que hubiera
     * @param nombre_archivo 
     */
    private void crearArchivo(String nombre_archivo){
        File directorio = new File("C:\\ArchivoSalida");
        archivo_salida = new File("C:\\ArchivoSalida\\"+nombre_archivo);  //instancia que crea la carpeta 
        if(!directorio.exists()){
            try {
                directorio.mkdirs();      
                archivo_salida.createNewFile();
            } catch (IOException ex) {
              //error en la creación del archivo
              System.out.println(" Error!!!   no se ha creado el archivo...");
            }
        } 
    }
    
    /**
     * Comprueba si existe el directorio del parámetro
     * @param ruta donde se quiere buscar el directorio
     * @return 
     */
    public boolean getExisteDirectorio(String ruta){
        boolean existe = false;
        File directorio = new File(ruta);
        if(directorio.exists()){
            return true;
        }
        return existe;
    }
    
    
    /**
      * Comprueba que la extensión .txt es valida para crear el archivo de salida
      * @param s
      * @return 
      */
     public boolean comprobarExtension(String s){
          for(int a = 0; a<s.length(); a++){
              char x = s.charAt(a);
             if(x == '.'){
                 String subString = s.substring(a, s.length());
                 if(subString.equals(".txt")){
                    return true; 
                 }
             } 
          }
          return false;
      }
    

     
     /**
      * Lee el archivo pasado por parámetro y guarda cada linea en una posición del array
      * si el archivo no esta en la ruta del proyecto necesita la ruta relativa --> "C:\\archivos\\nombre_archivo"
      * no tiene en cuenta las lineas en blanco
      * @param archivo
      * @return array con los datos leidos
      * @throws FileNotFoundException
      * @throws IOException 
      */
     public ArrayList<String> leerArchivo(String archivo) throws FileNotFoundException, IOException {
        ArrayList<String> lista_palabras = new ArrayList<>();                      
        String linea;
        
        try {
        FileReader f = new FileReader(archivo);
            //se lee cada linea y se escribe cada una de ellas en una posición del array
            try (BufferedReader b = new BufferedReader(f)) {
                //se lee cada linea y se escribe cada una de ellas en una posición del array
                while((linea = b.readLine()) != null) {
                      lista_palabras.add(linea.trim());          
                }
            }
        } catch (NumberFormatException e){
            System.out.println("\n !!!Error al leer dato fila o columna del archivo...");
        } 
        return lista_palabras;
     }
     
     
         
    /**
     * Escribe un archivo .txt con los datos del ArrayList
     * @param nombre_archivo
     * @param datos
     * @throws IOException 
     */
     public void escribirArchivo(String nombre_archivo, ArrayList<String> datos) throws IOException {
        crearArchivo(nombre_archivo);
        BufferedWriter buffer = null;
        
      try { 
        if(archivo_salida.exists()) {
            buffer = new BufferedWriter(new FileWriter(archivo_salida));
            
               buffer.write("\r\n");
               buffer.write(" Archivo datos.\r\n");
               
               //en el archivo se escribe cada posición del array en una linea
               for(int a = 0; a<datos.size(); a++){
                 buffer.write(datos.get(a));
                 buffer.write("\r\n");
               }
            buffer.write(" Fin de los datos...\n");
            buffer.write("\r\n");
            }
      } catch (IOException e){
            JOptionPane.showMessageDialog(null, "El archivo salida no se ha generado..."+e.getMessage(), "Error!!!", JOptionPane.ERROR_MESSAGE);
      } finally {
            buffer.close();
      }
    } 
    
}
