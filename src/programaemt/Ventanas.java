
package programaemt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Clase donde esta toda la gráfica del programa
 * @author wanyos
 */
public class Ventanas {
    
    
    private String nombre_archivo;
    
    public String getNombreArchivo(){
        return this.nombre_archivo;
    }
    
    
    public void VentanaInicio(){
      JFrame ventana_inicio = new JFrame();
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
      JTextField txtTexto = new JTextField(15);
      panelCen.add(label);
      panelCen.add(txtTexto);
      txtTexto.addKeyListener(new PruebaOyente(txtTexto));
      
      //Panel inferior
      JPanel panelInf = new JPanel();
      panelInf.setLayout(new FlowLayout());
      
      JButton btnBuscar = new JButton("Buscar");
      JButton btnSalir = new JButton("Salir");
      btnBuscar.addActionListener(new OyenteBoton(btnBuscar, txtTexto));
      
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

    
    
    class OyenteBoton implements ActionListener {

        private JButton btnBuscar;
        private JTextField texto;

        public OyenteBoton() {
            super();
        }

        public OyenteBoton(JButton btnBuscar, JTextField texto) {
            this.btnBuscar = btnBuscar;
            this.texto = texto;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == btnBuscar) {
                nombre_archivo = texto.getText();
                new Manager(nombre_archivo);
            } else {
                System.exit(0);
            }
        }
    }
    
    
    
    class OyenteTeclado implements KeyListener {

        private JTextField txtTexto;

        public OyenteTeclado(JTextField txtTexto) {
            this.txtTexto = txtTexto;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //métodos sin implementar
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int codigo = e.getKeyCode();
            if (codigo == 10) {             //si se pulsa enter se genera el manager
                new Manager(txtTexto.getText());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //método sin implementar
        }
    }
    
    
    /**
     * Probar una clase adaptadora queimplementa la interface KeyListener
     */
    class PruebaOyente extends KeyAdapter {

        private JTextField txtTexto;

        public PruebaOyente(JTextField txtTexto) {
            this.txtTexto = txtTexto;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int codigo = e.getKeyCode();
            if (codigo == 10) {             //si se pulsa enter se genera el manager
                new Manager(txtTexto.getText());
            }
        }
    }
    
}
