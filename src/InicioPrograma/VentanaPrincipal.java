
package InicioPrograma;

import java.awt.*;
import java.awt.event.*;
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
    
    JPanel panel_ctr, panel_sub_sup;
    JLabel lblTitulo;
    
    public VentanaPrincipal(){
      Container cp = this.getContentPane(); 
      this.setLayout(new BorderLayout());
      this.setTitle("Menu principal");
      this.setLocation(400, 200);
      this.setSize(700, 450);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setIconImage(new ImageIcon(getClass().getResource("/imagen/power.png")).getImage());
      
      //Crear menus y paneles
      
      //panel superior
      JPanel panel_sup = new JPanel();
      panel_sup.setBackground(Color.GRAY);
      lblTitulo = new JLabel();
      lblTitulo.setText("Escoger una opcion de las disponibles");
      panel_sup.add(lblTitulo);
      
      //panel central
      panel_ctr = new JPanel();
      panel_ctr.setLayout(new BorderLayout());
      
      //Subpanel central
      panel_sub_sup = new JPanel();
      
      JButton btnBuscar = new JButton("Buscar servicio");
      JButton btnResumenMes = new JButton("Resumen mes");
      JButton btnResumenYear = new JButton("Resumen year");
      JButton btnControlDias = new JButton("Control dias");
      
      btnBuscar.addActionListener(new OyenteBotonBuscar());
      btnResumenMes.addActionListener(new OyenteBotonResumenMes());
      btnResumenYear.addActionListener(new OyenteBotonResumenYear());
      btnControlDias.addActionListener(new OyenteBotonControlDias());
      
      panel_sub_sup.add(btnBuscar, BorderLayout.NORTH);            //nos mostrara una ventana con opciones en el panel central
      panel_sub_sup.add(btnResumenMes, BorderLayout.NORTH);        //nos mostrara una ventana pidiendo el mes y año en el panel central
      panel_sub_sup.add(btnResumenYear, BorderLayout.NORTH);       //nos pide en una ventana el año a buscar
      panel_sub_sup.add(btnControlDias, BorderLayout.NORTH);       //nos manda a otra pantalla donde nos muestra todas las opciones de los dias
      
      panel_ctr.add(panel_sub_sup, BorderLayout.NORTH);
      
      //panel inferior
      JPanel panel_inf = new JPanel();
      panel_inf.setBackground(Color.gray);
      JLabel lblMensaje = new JLabel("Mensajes...");
      panel_inf.add(lblMensaje, FlowLayout.LEFT);
      
      cp.add(panel_sup, BorderLayout.NORTH);
      cp.add(panel_ctr, BorderLayout.CENTER);
      cp.add(panel_inf, BorderLayout.SOUTH);
      this.setVisible(true);
    }
    
    /**
     * Muestra en el panel central un pequeño menú para pedir datos
     */
    private void mostrarMenuBuscar(){
        //this.panel_sub_sup.setVisible(false);
        this.lblTitulo.setText("Ingresar los datos de la fecha a buscar...");
        JPanel panel_sub_ctr = new JPanel();    
        panel_sub_ctr.setLayout(new BorderLayout());
        
        JLabel lblDia = new JLabel("Dia:   ");
        lblDia.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField txtDia = new JTextField(3);
        Box caja_dia = Box.createHorizontalBox();
        caja_dia.add(lblDia);
        caja_dia.add(Box.createHorizontalStrut(10));
        caja_dia.add(txtDia);
        
        JLabel lblMes = new JLabel("Mes: ");
        lblMes.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField txtMes = new JTextField(3);
        Box caja_mes = Box.createHorizontalBox();
        caja_mes.add(lblMes);
        caja_mes.add(Box.createHorizontalStrut(10));
        caja_mes.add(txtMes);
        
        JLabel lblYear = new JLabel("Año:  ");
        lblYear.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField txtYear = new JTextField(3);
        Box caja_year = Box.createHorizontalBox();
        caja_year.add(lblYear);
        caja_year.add(Box.createHorizontalStrut(10));
        caja_year.add(txtYear);
        
        Box caja_total = Box.createVerticalBox();
        caja_total.add(Box.createVerticalStrut(20));
        caja_total.add(caja_dia);
        caja_total.add(Box.createVerticalStrut(10));
        caja_total.add(caja_mes);
        caja_total.add(Box.createVerticalStrut(10));
        caja_total.add(caja_year);
        caja_total.add(Box.createVerticalStrut(20));
        caja_total.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos dia"));
        
        JPanel panel_sub_menu = new JPanel();
        panel_sub_menu.add(caja_total);
        
        JButton btnBuscar = new JButton("Buscar");
        JButton btnAtras = new JButton("Atras");
        
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(btnBuscar);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(btnAtras);
        
        btnBuscar.addActionListener(new OyenteBotonBuscarDia());
        btnAtras.addActionListener(new OyenteBotonAtras());
        
        JPanel panel_sub_botones = new JPanel();
        panel_sub_botones.add(buttonBox);
        
        panel_sub_ctr.add(panel_sub_menu, BorderLayout.CENTER);
        panel_sub_ctr.add(panel_sub_botones, BorderLayout.SOUTH);
        
        panel_ctr.add(panel_sub_ctr, BorderLayout.CENTER);
        panel_ctr.updateUI();
    }
    
    
    
    
    
    
    
    //clases oyente de boton, se crea una clase para cada botón del menú principal
    
    class OyenteBotonBuscar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
              mostrarMenuBuscar();  
           }
    }
    
    class OyenteBotonResumenMes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    class OyenteBotonResumenYear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    class OyenteBotonControlDias implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    }
    
    class OyenteBotonBuscarDia implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class OyenteBotonAtras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
    
    
}
    
