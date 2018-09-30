
package InicioPrograma;

import java.awt.*;
import javax.swing.*;

/**
 * Ventana principal del programa, ofrece las opciones disponibles
 * @author wanyos
 */
public class VentanaPrincipal extends JFrame {
    
    //buscar un servicio por un dia en concreto
    //ver los datos de un mes, sueldo, minutos, prestaciones
    //ver los datos de un año
    //control de dias tantos pedidos como pendientes
    //
    
    public VentanaPrincipal(){
      Container cp = this.getContentPane(); 
      this.setLayout(new BorderLayout());
      this.setTitle("Menu principal");
      this.setLocation(400, 200);
      this.setSize(800, 650);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setIconImage(new ImageIcon(getClass().getResource("/imagen/power.png")).getImage());
      
      //Crear menus y paneles
      
      
      this.setVisible(true);
    }
    
}
