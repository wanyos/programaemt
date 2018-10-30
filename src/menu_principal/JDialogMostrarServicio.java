
package menu_principal;

import entrada_datos.Util;
import java.awt.*;
import javax.swing.*;
import tratamiento_datos.Servicio;

/**
 * Muestra el resumen del servicio buscado
 * @author wanyos
 */
public class JDialogMostrarServicio extends JDialogMostrar {
    
    private Servicio s;
    
    public JDialogMostrarServicio (Servicio s) {
        this.s = s;
        this.addPanelServicio();
    }
    
    /**
     * Crea el panel que usa el JDialog y lo manda al método que de la clase JDialogMostrar que 
     * genera el JDialog genérico
     */
    private void addPanelServicio(){
        JPanel pnServicio = new JPanel();
        pnServicio.setLayout(new BorderLayout());
        pnServicio.add(this.getPanelSup(), BorderLayout.NORTH);
        pnServicio.add(this.getPanelCtr(), BorderLayout.CENTER);
        this.initVentana("Datos servicio", pnServicio);
        pnPrincipal.updateUI();
    }
    
    private JPanel getPanelSup(){
        JPanel pnSup = new JPanel();
        JLabel lblLinea = new JLabel(" Resumen datos del servicio...");
        pnSup.add(lblLinea);
        return pnSup;
    }
    
    private JPanel getPanelCtr(){
        JPanel pnCtr = new JPanel();
//        JLabel lblFechaI = new JLabel("FechaI:");
//        JLabel lblHoraI = new JLabel("HoraI:");
//        JLabel lblFechaF = new JLabel("FechaF:");
//        JLabel lblHoraF = new JLabel("HoraF:");
//        JLabel lblLugarI = new JLabel("LugarI:");
//        JLabel lblLugarF = new JLabel("LugarF:");
//        
//        JLabel fechaI = new JLabel(Util.getFechaCadena(s.getFecha_inicio())+"   ");
//        JLabel horaI = new JLabel(Util.getHoraCadena(s.getHora_inicio())+"   ");
//        JLabel fechaF = new JLabel(Util.getFechaCadena(s.getFecha_fin())+"   ");
//        JLabel horaF = new JLabel(Util.getHoraCadena(s.getHora_fin())+"   ");
//        JLabel lugarI = new JLabel(s.getLugar_inicio()+"   ");
//        JLabel lugarF = new JLabel(s.getLugar_fin());
//        
//        pnCtr.add(lblFechaI);
//        pnCtr.add(fechaI);
//        
//        pnCtr.add(lblHoraI);
//        pnCtr.add(horaI);
//        
//        pnCtr.add(lblFechaF);
//        pnCtr.add(fechaF);
//        
//        pnCtr.add(lblHoraF);
//        pnCtr.add(horaF);
//        
//        pnCtr.add(lblLugarI);
//        pnCtr.add(lugarI);
//        
//        pnCtr.add(lblLugarF);
//        pnCtr.add(lugarF);
            JTextArea a = new JTextArea();
            a.setEditable(false);
            a.setText(s.toString());
            pnCtr.add(a);
        
        return pnCtr;
    }
    
}
