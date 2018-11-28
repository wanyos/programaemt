
package menu_principal;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import tratamiento_datos.CalculosDia;
import tratamiento_datos.Servicio;

/**
 * Muestra el resumen de los datos del mes
 * @author wanyos
 */
public class JDialogMostrarDatosMes extends JDialogMostrar {
    
    public JDialogMostrarDatosMes(ArrayList<Servicio> lista_mes) {
        this.addPanelMes();
        //Pasar la lista de servicios del mes a la clase CalculosDia para los calculos
        CalculosDia calculos_dia = new CalculosDia(lista_mes);
    }
    
    /**
     * Crea el panel que muestra los datos del resumen, este panel lo manda al método genérico
     * de la clase JDialogMostrar que inicia el JDialog
     */
    private void addPanelMes(){
        JPanel pnInf = new JPanel();
        JLabel b = new JLabel("Mensaje datos mes", (int) JLabel.CENTER_ALIGNMENT);
        pnInf.add(b);
        this.initVentana("Datos mes", pnInf);
        pnPrincipal.updateUI();
    }
    
}
