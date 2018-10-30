
package entrada_datos;

import tratamiento_datos.Servicio;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase donde se lee el archivo datos, se separan los datos por lineas y se crea el array con los servicios
 * @author wanyos
 */
public class CrearServicios {
    
    
    private String nombre_archivo_entrada;
    private final Archivo archivo;
    private ArrayList<String> array_datos_entrada;
    private boolean validar;
    
    
    public CrearServicios() {
        archivo = new Archivo();
        array_datos_entrada = new ArrayList<>();
    }
    
    
    /**
     * Inicia el CrearServicios arrancando los métodos necesarios Crea la ruta del
     * archivo a leer Ordena a la clase archivo leer la ruta del archivo por
     * medio de SetDatosArray si la ruta no es válida saltara un error, de lo
     * contrario leera el archivo y guarda los datos en el array.
     * @param nombre_archivo
     * @return 
     */
    public boolean inicioCrearServicios(String nombre_archivo) {
        //crea la ruta del archivo de entrada con los datos del parametro
        this.setNombreArchivoEntrada(nombre_archivo);
        //lee los datos del archivo y los guarda en el array_datos
        this.setDatosArray();
        if(validar){
            //lee los datos del array_datos para crear los servicios y guardarlos en el array_servicios si el archivo es valido
            this.setDatosServicios();
        }
        return validar;
    }
    
    
    
    /**
     * Función que crea el nombre y la ruta del archivo a leer
     * Si el parámetro es solo el nombre del archivo dicho archivo tiene que estar en el mismo directorio que el ejecutable
     * En caso contrario se necesita la ruta relativa del archivo
     * Vamos a usar siempre el directorio C:\\ para la ubicación del archivo
     * @param nombre_archivo 
     */
    private void setNombreArchivoEntrada(String nombre_archivo) {
        String ruta;
        String path = "C:\\archivo\\";
        if(archivo.getExisteDirectorio(path) && archivo.comprobarExtension(nombre_archivo)){
            ruta = "C:\\archivo\\"+nombre_archivo;    //si en C:\ no existe una carpeta llamada archivo, el archivo se encuentra en el directorio del ejecutable
        } else {
            ruta = nombre_archivo;
        }
        this.nombre_archivo_entrada = ruta;
    }
    
    
    /**
     * Llama al método leerArchivo de la clase archivo para leer el archivo con nombre del parámetro
     * Los datos del archivo se guardan en el array_datos con un dato por cada linea del archivo leido
     * @throws IOException 
     */
   private void setDatosArray() {
        try {
            array_datos_entrada = archivo.leerArchivo(nombre_archivo_entrada);
            this.validar = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "!!!Error", JOptionPane.ERROR_MESSAGE);
        }
   }
 
       
    /**
     * Lee los datos del array y separa los datos necesarios para crear un
     * servicio guarda todos los datos de un servicio en un array y se los pasa
     * al método crearServicio
     */
    private void setDatosServicios() {
        ArrayList<String> datos_servicio = new ArrayList<>();
        for (int a = 0; a < array_datos_entrada.size() - 1; a++) {
            String aux = this.array_datos_entrada.get(a);
            if (!aux.equals("")) {
                String[] temp = aux.split(":", 2);
                datos_servicio.add(temp[1]);
            } else {
                this.crearServicio(datos_servicio);
                datos_servicio.clear();
            }
        }
    }


    /**
     * Recibe por párametro todos los datos de un servicio Solo puede crear
     * turno seguido o turno partido Guarda los servicios en el array_servicios
     *
     * @param datos_servicio
     */
    private void crearServicio(ArrayList<String> datos_servicio) {
        String delimiter_palabra = " ";

        String linea = datos_servicio.get(0).trim();
        String turno = datos_servicio.get(1).trim();

        //se usa el espacio vacio para separa el dia de la hora
        String[] temp1 = datos_servicio.get(2).split(delimiter_palabra);
        String fechaI = temp1[0].trim();
        String horaI = temp1[1].trim();

        String[] temp2 = datos_servicio.get(3).split(delimiter_palabra);
        String fechaF = temp2[0].trim();
        String horaF = temp2[1].trim();

        String lugarI = datos_servicio.get(4).trim();
        String lugarF = datos_servicio.get(5).trim();

        //si es turno seguido el tamaño del array es de 6, si es partido sera de 10
        String fechaIP = null, horaIP = null, fechaFP = null, horaFP = null, lugarIP = null, lugarFP = null;

        if (datos_servicio.size() == 10) {
            String[] temp3 = datos_servicio.get(6).split(delimiter_palabra);
            fechaIP = temp3[0].trim();
            horaIP = temp3[1].trim();

            String[] temp4 = datos_servicio.get(7).split(delimiter_palabra);
            fechaFP = temp4[0].trim();
            horaFP = temp4[1].trim();

            lugarIP = datos_servicio.get(8).trim();
            lugarFP = datos_servicio.get(9).trim();
        }

        //pasar los datos fecha y hora antes de crear el objeto servicio
        LocalDate fecha_inicio = Util.getObjetoFecha(fechaI);
        LocalTime hora_inicio = Util.getObjetoHora(horaI);
        LocalDate fecha_fin = Util.getObjetoFecha(fechaF);
        LocalTime hora_fin = Util.getObjetoHora(horaF);

        Servicio servicio = null;
        if (datos_servicio.size() == 6) {
            //crear servicio seguido
            servicio = new Servicio(linea, turno, fecha_inicio, hora_inicio, fecha_fin, hora_fin, lugarI, lugarF);

        } else if (datos_servicio.size() == 10) {
            //turno partido, pasar los datos fecha y hora antes de crear el objeto servicio
            LocalDate fecha_inicio_p = Util.getObjetoFecha(fechaIP);
            LocalTime hora_inicio_p = Util.getObjetoHora(horaIP);
            LocalDate fecha_fin_p = Util.getObjetoFecha(fechaFP);
            LocalTime hora_fin_p = Util.getObjetoHora(horaFP);

            //crear servicio partido
            servicio = new Servicio(linea, turno, fecha_inicio, hora_inicio, fecha_fin, hora_fin, lugarI, lugarF,
                    fecha_inicio_p, hora_inicio_p, fecha_fin_p, hora_fin_p, lugarIP, lugarFP);
        }

        //guardar los datos en una lista o base datos
        if (servicio != null) {
            BaseDatosServicios.setServicio(servicio);
        }
    }
    
    
    
    
      
}
