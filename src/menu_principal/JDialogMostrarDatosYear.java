
package menu_principal;

import entrada_datos.BaseDatosServicios;
import java.awt.*;
import javax.swing.*;

/**
 * Clase que muestra los datos del resumen del año
 * @author wanyos
 */
public class JDialogMostrarDatosYear extends JDialogMostrar {
    
    public JDialogMostrarDatosYear(BaseDatosServicios.Year y) {
        this.addPanelYear();
    }
    
    /**
     * Crea el panel que se usara en el método genérico de la clase JDialogMostrar
     */
     private void addPanelYear(){
        JPanel pnInf = new JPanel();
        JLabel b = new JLabel("Mensaje datos año", (int) JLabel.CENTER_ALIGNMENT);
        pnInf.add(b);
        this.initVentana("Datos año", pnInf);
        pnPrincipal.updateUI();
    }
    
    
}
