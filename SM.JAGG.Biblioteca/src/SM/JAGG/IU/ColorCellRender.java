package SM.JAGG.IU;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 * Clase para poder representar los colores en los combobox de la interfaz
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class ColorCellRender implements ListCellRenderer<Color> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
        PanelColor panel = new PanelColor(value);
        return panel;
    }
    
}
