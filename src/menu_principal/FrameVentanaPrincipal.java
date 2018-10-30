
package menu_principal;

import entrada_datos.BaseDatosServicios;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import tratamiento_datos.Servicio;

/**
 * Ventana principal del programa, ofrece las opciones disponibles
 * @author wanyos
 */
public class FrameVentanaPrincipal extends JFrame {
    
    //buscar un servicio por un dia en concreto
    //ver los datos de un mes, sueldo, minutos, prestaciones
    //ver los datos de un año
    //control de dias tantos pedidos como pendientes
    
    JPanel panel_ctr, panel_sub_sup, panel_sub_ctr;
    JLabel lblTitulo;
    JTextField txtDia, txtMes, txtYear;
    int valor_dia, valor_mes, valor_year;
    
    public FrameVentanaPrincipal(){
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
      panel_sub_sup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
      
      JButton btnBuscar = new JButton("Buscar servicio");
      JButton btnResumenMes = new JButton("Resumen mes");
      JButton btnResumenYear = new JButton("Resumen year");
      JButton btnControlDias = new JButton("Control dias");
      
      btnBuscar.addActionListener(new OyenteBotonMenu());
      btnResumenMes.addActionListener(new OyenteBotonMenu());
      btnResumenYear.addActionListener(new OyenteBotonMenu());
      btnControlDias.addActionListener(new OyenteBotonMenu());
      
      panel_sub_sup.add(btnBuscar);              //nos mostrara una ventana con opciones en el panel central
      panel_sub_sup.add(btnResumenMes);         //nos mostrara una ventana pidiendo el mes y año en el panel central
      panel_sub_sup.add(btnResumenYear);        //nos pide en una ventana el año a buscar
      panel_sub_sup.add(btnControlDias);       //nos manda a otra pantalla donde nos muestra todas las opciones de los dias
      
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
     * Acciones que hace al pulsar uno de los botones del menú
     * @param texto 
     */
    private void inicioCambioVentana(String texto) {
        //Se llama la método que deshabilita todos los componentes dentro de él.
        this.setEnableContainer(this.panel_sub_sup, false);
        this.lblTitulo.setText(texto + "...");
        panel_sub_ctr = new JPanel();
        panel_sub_ctr.setLayout(new BorderLayout());
    }
    
    private Box getCajaDia() {
        JLabel lblDia = new JLabel("Dia:   ");
        lblDia.setHorizontalAlignment(SwingConstants.LEFT);
        txtDia = new JTextField(3);
        txtDia.addFocusListener(new OyenteFocoTexto());
        txtDia.setHorizontalAlignment(JTextField.CENTER);
        Box caja_dia = Box.createHorizontalBox();
        caja_dia.add(lblDia);
        caja_dia.add(Box.createHorizontalStrut(10));
        caja_dia.add(txtDia);
        return caja_dia;
    }
    
    private Box getCajaMes(){
        JLabel lblMes = new JLabel("Mes: ");
        lblMes.setHorizontalAlignment(SwingConstants.LEFT);
        txtMes = new JTextField(3);
        txtMes.addFocusListener(new OyenteFocoTexto());
        txtMes.setHorizontalAlignment(JTextField.CENTER);
        Box caja_mes = Box.createHorizontalBox();
        caja_mes.add(lblMes);
        caja_mes.add(Box.createHorizontalStrut(10));
        caja_mes.add(txtMes);
        return caja_mes;
    }
    
    private Box getCajaYear(){
        JLabel lblYear = new JLabel("Año:  ");
        lblYear.setHorizontalAlignment(SwingConstants.LEFT);
        txtYear = new JTextField(3);
        txtYear.addFocusListener(new OyenteFocoTexto());
        txtYear.setHorizontalAlignment(JTextField.CENTER);
        Box caja_year = Box.createHorizontalBox();
        caja_year.add(lblYear);
        caja_year.add(Box.createHorizontalStrut(10));
        caja_year.add(txtYear);
        return caja_year;
    }
    
   
    private Box getCajaTotal (Box caja_dia, Box caja_mes){
        Box caja_total = Box.createVerticalBox();
        caja_total.add(Box.createVerticalStrut(20));
        if (caja_dia != null) {
            caja_total.add(getCajaDia());
            caja_total.add(Box.createVerticalStrut(10));
        }
        if (caja_mes != null) {
            caja_total.add(getCajaMes());
            caja_total.add(Box.createVerticalStrut(10));
        }
            caja_total.add(getCajaYear());
        
        return caja_total;
    }
    
    
    
    
    /**
     * Muestra en el panel central un pequeño menú para pedir datos
     */
    private void mostrarMenuBuscar(Box caja_dia, Box caja_mes){
        Box caja_total = getCajaTotal(caja_dia, caja_mes);
        JPanel panel_sub_menu = new JPanel();
        panel_sub_menu.add(caja_total);
        
        JButton btnBuscar = new JButton("Buscar");
        JButton btnAtras = new JButton("Atras");
        
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(btnBuscar);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(btnAtras);
        
        btnBuscar.addActionListener(new OyenteBotonBuscarDatos());
        btnAtras.addActionListener(new OyenteBotonAtras());
        
        JPanel panel_sub_botones = new JPanel();
        panel_sub_botones.add(buttonBox);
        
        panel_sub_ctr.add(panel_sub_menu, BorderLayout.CENTER);
        panel_sub_ctr.add(panel_sub_botones, BorderLayout.SOUTH);
        
        panel_ctr.add(panel_sub_ctr, BorderLayout.CENTER);
        panel_ctr.updateUI();
    }
    
    
   
    
    
    /**
     * Pasa a la ventana donde se controlan los dias generados
     */
    private void mostrarControlDias(){
        
    }
    
    
    
    
    
    /**
     * Deshabilita todos los componentes que tenga el componente c
     * @param c
     * @param band 
     */
    public void setEnableContainer(Container c, boolean band) {
        Component[] components = c.getComponents();
        c.setEnabled(band);
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(band);

            if (components[i] instanceof Container) {
                setEnableContainer((Container) components[i], band);
            }
        }
    }
    
    
    
    //////  CLASES OYENTE DE LAS VENTANAS /////////////////////////////////////////////////////////////////
    
    
    
    
    
    /**
     * Clase oyente de los botones del menú principal
     */
    private class OyenteBotonMenu implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object boton_pulsado = e.getActionCommand();
            if (boton_pulsado.equals("Buscar servicio")) {
                inicioCambioVentana("Ingresar los datos de la fecha a buscar");
                mostrarMenuBuscar(getCajaDia(), getCajaMes());

            } else if (boton_pulsado.equals("Resumen mes")) {
                inicioCambioVentana("Ingresar los datos del mes y el año a buscar");
                mostrarMenuBuscar(null, getCajaMes());

            } else if (boton_pulsado.equals("Resumen year")) {
                inicioCambioVentana("Ingresar el año a buscar");
                mostrarMenuBuscar(null, null);

            } else if (boton_pulsado.equals("Control dias")) {

            }
        }
    }
    
    
    
   
    
    /**
     * Busca los datos que se han introducido en las cajas de datos
     */
    private class OyenteBotonBuscarDatos implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(valor_dia > 0 && valor_mes > 0 && valor_year > 0){
                //buscar el servicio del dia pedido
                Servicio s = BaseDatosServicios.getServicio(valor_dia, valor_mes, valor_year);
                if(s == null){
                    JOptionPane.showMessageDialog(null, " No existe servicio con esa fecha...", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new JDialogMostrarServicio(s);
                }
                this.restablecerValores();
            } else if(valor_dia == 0 && valor_mes > 0 && valor_year > 0){
                //buscar los datos del mes y del año pedido
                ArrayList<Servicio> lista_mes = BaseDatosServicios.getMonth(valor_mes, valor_year);
                if(lista_mes.isEmpty() || lista_mes == null){
                    JOptionPane.showMessageDialog(null, " No existen datos de ese mes...", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new JDialogMostrarDatosMes(lista_mes);
                }
                this.restablecerValores();
            } else if(valor_dia == 0 && valor_mes == 0 && valor_year > 0){
                //buscar los datos del año pedido
                BaseDatosServicios.Year y = BaseDatosServicios.getYear(valor_year);
                if(y == null){
                    JOptionPane.showMessageDialog(null, " No existen datos de ese año...", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new JDialogMostrarDatosYear(y);
                }
                this.restablecerValores();
            }
        }
            
        
        private void restablecerValores() {
            setEnableContainer(panel_sub_sup, true);
            panel_ctr.remove(panel_sub_ctr);
            panel_ctr.updateUI();
            valor_dia = 0;
            valor_mes = 0;
            valor_year = 0;
            if (txtDia != null) {
                txtDia.setText(null);
            }
            if (txtMes != null) {
                txtMes.setText(null);
            }
            txtYear.setText(null);
        }
        
        
    }
    
    
    
    
    /**
     * Habilita el JPanel superior de la opciones y borra el panel central
     */
    private class OyenteBotonAtras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           setEnableContainer(panel_sub_sup, true);
           panel_ctr.remove(panel_sub_ctr);
           panel_ctr.updateUI();
        }
        
    }
    
    
    
    
    
    /**
     * Clase oeyente de las cajas de texto del día, mes y año
     */
    private class OyenteFocoTexto implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (txtDia != null && txtDia.isFocusOwner()) {
                txtDia.setText("");
            } else if (txtMes != null && txtMes.isFocusOwner()) {
                txtMes.setText("");
            } else if (txtYear.isFocusOwner()) {
                txtYear.setText("");
            }
        }

        
        @Override
        public void focusLost(FocusEvent e) {
            if (txtDia != null) {
                validarDia();
            }
            if (txtMes != null) {
                validarMes();
            }
            validarYear();
        }
        
        
        /**
         * Valida que el dato sea un entero y este dentro del rango
         * de los dias de un mes
         */
        private void validarDia(){
            boolean correcto = false;
            while (!correcto) {
                try {
                    String texto_dia = txtDia.getText().trim();
                    if (texto_dia.length() > 0) {
                       valor_dia = Integer.parseInt(texto_dia);
                       if(!(valor_dia > 0 && valor_dia < 32)){
                           txtDia.setText("");
                           JOptionPane.showMessageDialog(null, " El valor del dia tiene que estar entre 1 y 31...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                       }
                    }
                    correcto = true;
                } catch (NumberFormatException ex) {
                    txtDia.setText("");
                    JOptionPane.showMessageDialog(null, " El valor del dia no es correcto...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                    correcto = true;
                }
            }
        }
        
        /**
         * Valida que el dato sea un entero y este dentro del 
         * rango de los meses del año
         */
        private void validarMes(){
            boolean correcto = false;
            while (!correcto) {
                try {
                    String texto_mes = txtMes.getText().trim();
                    if (texto_mes.length() > 0) {
                        valor_mes = Integer.parseInt(texto_mes);
                        if(!(valor_mes > 0 && valor_mes < 13)){
                            txtMes.setText("");
                            JOptionPane.showMessageDialog(null, " El valor del mes tiene que estar entre 1 y 12...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    correcto = true;
                } catch (NumberFormatException ex) {
                    txtMes.setText("");
                    JOptionPane.showMessageDialog(null, " El valor del dia no es correcto...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Valida que el dato sea un entero y sea mayor que cero
         * y menor que el maximo entero posible
         */
        private void validarYear(){
            boolean correcto = false;
            while (!correcto) {
                try {
                    String texto_year = txtYear.getText().trim();
                    if (texto_year.length() > 0) {
                        valor_year = Integer.parseInt(texto_year);
                        if (!(valor_year > 0 && valor_year < Integer.MAX_VALUE)) {
                            txtYear.setText("");
                            JOptionPane.showMessageDialog(null, " El valor del año tienen que estar entre 1 y min_int...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    correcto = true;
                } catch (NumberFormatException e) {
                    txtYear.setText("");
                    JOptionPane.showMessageDialog(null, " El valor del dia no es correcto...", "Error!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        
        
        
        
    }
        
    
    
    
    
    
    
    
    
    
    
    
    
}
    
