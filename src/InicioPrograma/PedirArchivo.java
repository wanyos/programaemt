
package InicioPrograma;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Clase donde se crea la ventana para pedir el nombre del archivo de datos
 * @author wanyos
 */
public class PedirArchivo {
    
    private JFrame ventana_inicio;
    private JButton btnBuscar;
    private JTextField txtTexto;
    private Main m;
    
    
    
    public PedirArchivo(Main m){
       this.m = m; 
    }
    
    
    /**
     * Crea la ventana inical donde se pide el nombre del archivo de datos
     */
    public void ventanaInicio(){
      ventana_inicio = new JFrame();
      ventana_inicio.setLayout(new BorderLayout());
      ventana_inicio.setTitle("Inicio programa");
      ventana_inicio.setLocation(400, 200);
      ventana_inicio.setSize(400, 150);
      ventana_inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana_inicio.setIconImage(new ImageIcon(getClass().getResource("/imagen/zoom.png")).getImage());
      
      //Panel superior
      JPanel panelSup = new JPanel();
      panelSup.setLayout(new FlowLayout());
      
      JLabel titulo = new JLabel("         ");
      panelSup.add(titulo);
      
      //Panel central
      JPanel panelCen = new JPanel();
      panelCen.setLayout(new FlowLayout());
      
      JLabel label = new JLabel("Nombre archivo:  ");
      txtTexto = new JTextField(15);
      panelCen.add(label);
      panelCen.add(txtTexto);
      txtTexto.addKeyListener(new OyenteTeclado());
      
      //Panel inferior
      JPanel panelInf = new JPanel();
      panelInf.setLayout(new FlowLayout());
      
      btnBuscar = new JButton("Buscar");
      JButton btnSalir = new JButton("Salir");
      btnBuscar.addActionListener(new OyenteBoton());
      
      btnSalir.addActionListener(new OyenteBoton());
      panelInf.add(btnBuscar);
      panelInf.add(btnSalir);
      
      //añadir los paneles al Frame
      Container cp = ventana_inicio.getContentPane();
      cp.add(panelSup, BorderLayout.NORTH);
      cp.add(panelCen, BorderLayout.CENTER);
      cp.add(panelInf,BorderLayout.SOUTH);
      ventana_inicio.setVisible(true);
    }

    
    public void supenderVentana(){
        this.ventana_inicio.dispose();
    }

    
    
    
    class OyenteBoton implements ActionListener {
        
        public OyenteBoton() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == btnBuscar) {
               m.addNombre(txtTexto.getText());   //usa el objeto Main para dar el nombre al archivo
            } else {
                System.exit(0);
            }
        }
    }
    
    
    
    class OyenteTeclado implements KeyListener {

        public OyenteTeclado() {
            super();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //métodos sin implementar
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int codigo = e.getKeyCode();
            if (codigo == 10) {                 //si se pulsa enter se genera el manager
              m.addNombre(txtTexto.getText()); //usa el objeto Main para dar el nombre al archivo
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //método sin implementar
        }
    }
    
    
}
