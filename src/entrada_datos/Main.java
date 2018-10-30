
package entrada_datos;

import menu_principal.FrameVentanaPrincipal;
import javax.swing.JOptionPane;


/**
 * Clase principal del programa, crea los objetos necesarios para su inicioooooo
 * @author wanyos
 */
public class Main {

    
    //lo primero es pedir al usuario un nombre del archivo a leer
    //este nombre solo sera el String del archivo
    //el programa se encarga de buscarlo o en la raiz del ejecutable o en la ubicación C:\archivo
    private String nombre;
    private boolean disponible;
    
    
   /**
    * Se queda a la espera hasta que el nombre esta disponible
    * cuando el usuario lo introduce en la caja de texto continua la ejecución del hilo
    * @return 
    */
   public synchronized String getNombre(){
    while(!disponible){
        try{
            wait();
        }catch(InterruptedException e){
           JOptionPane.showMessageDialog(null, "Problema en la devolución del nombre del archivo..."+e.getMessage(), "Error!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
     disponible = false;
     notify();
     return this.nombre;
  }

   /**
    * Asigna el nombre del archivo que el usuario a introducido en la caja de texto
    * @param valor 
    */
  public synchronized void addNombre(String valor){
     while(disponible){
        try{
            wait();
        }catch(InterruptedException e){
           JOptionPane.showMessageDialog(null, "Problema en el hilo de añadir nombre de archivo..."+e.getMessage(), "Error!!!", JOptionPane.ERROR_MESSAGE); 
        }
    }
     this.nombre = valor;
     disponible = true;
     notify();
  }
    
    
  
    public static void main(String[] args) throws InterruptedException {
        
        //Crea el objeto main y quedara a la espera del nombre del archivo
        Main m = new Main();
        //Presenta en pantalla la caja de texto para pedir el nombre del archivo
        PedirArchivoEntrada v = new PedirArchivoEntrada(m);
        BaseDatosServicios base_servicios = new BaseDatosServicios();
        CrearServicios crear = new CrearServicios();
        String nombre_archivo;
        
        do {
        //Se lee el archivo y se crean los servicios si el nombre de archivo es valido
        v.ventanaInicio();
        //Recoge el nombre del archivo que ha introducido el usuario
         nombre_archivo = m.getNombre();
        } while(!crear.inicioCrearServicios(nombre_archivo));
           
        //Mostrar la ventana de menus de la aplicacion y cerrar la inicial
          v.supenderVentana();
          FrameVentanaPrincipal ven = new FrameVentanaPrincipal();
        
        
        
        
        
    }
    
}
