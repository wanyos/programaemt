
package menu_principal;

import java.awt.*;
import javax.swing.*;

/**
 * Clase que configura el aspecto de los jDialog que se usan para mostrar 
 * servicios, resumen mes y resumen año
 * @author wanyos
 */
public abstract class JDialogMostrar extends JDialog {
    
    protected JPanel pnPrincipal;
    
    /**
     * Inicia el JDialog genérico, este tiene un panel de fondo añadido al container del JDialog
     * sobre este panel se añade el panel del parámetro que usan el resto de JDialog
     * @param titulo
     * @param principal 
     */
    protected void initVentana(String titulo, JPanel principal){
        this.setTitle(titulo+"...");
        this.setBounds(200, 100, 1150, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagen/power.png")).getImage());
        this.setModal(true);
        Container cp = this.getContentPane();
        //cp.setLayout(new BorderLayout());
        pnPrincipal = new JPanel();
        pnPrincipal.add(principal);
        //pnPrincipal.setLayout(new BorderLayout());
        cp.add(pnPrincipal);
        this.setVisible(true);
    }
    
    
    
    
    
    
    
}
