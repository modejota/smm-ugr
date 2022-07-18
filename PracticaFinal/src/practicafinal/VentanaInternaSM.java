package practicafinal;

import java.awt.image.BufferedImage;

/**
 * Clase padre que representa una ventana interna de nuestra aplicación
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class VentanaInternaSM extends javax.swing.JInternalFrame {
    
    /**
     * Constructor por defecto
     */
    public VentanaInternaSM() {}
    
    /**
     * Constructor con parámetros
     * @param title Título de la ventana
     */
    public VentanaInternaSM (String title) {
        super(title,true,true,true,true);
    }
    
    /**
     * Método para obtener la imagen de la ventana
     * @param bool Si hay que volcar las posibles figuras pintadas
     * @return Imagen de la ventana
     */
    public BufferedImage getImage(boolean bool) {
        return null;
    }
    public void setImage(BufferedImage img){}
}
