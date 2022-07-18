package SM.JAGG.Events;


import java.util.EventListener;

/**
 * Interfaz para recibir los eventos que tienen lugar en los lienzos
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public interface LienzoListener extends EventListener {
    /**
     * Método utilizado para notificar de los cambios en la figura seleccionada en el lienzo
     * @param evt Evento generado por el lienzo
     */
    public void selectedShape(LienzoEvent evt);
}
