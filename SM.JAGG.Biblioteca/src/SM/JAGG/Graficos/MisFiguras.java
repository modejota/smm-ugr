package SM.JAGG.Graficos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que representa a una figura genérica
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public abstract class MisFiguras {

    /**
     * Atributos del borde de la figura
     */
    protected MisAtributosBorde atributosBorde;
    
    /**
     * Otros atributos de la figura
     */
    protected MisOtrosAtributos otrosAtributos;
    
    /**
     * Constructor por defecto
     */
    public MisFiguras() {
        this.atributosBorde = new MisAtributosBorde();
        this.otrosAtributos = new MisOtrosAtributos();
    }

    /**
     * Constructor con parámetros
     * @param matribs Objeto con los atributos del borde de la figura
     * @param motros Obejto con los atributos generales de la figura
     */
    public MisFiguras(MisAtributosBorde matribs, MisOtrosAtributos motros) {
        this.atributosBorde = matribs;
        this.otrosAtributos = motros;
    }

    /**
     * Setter de los atributos de borde de la figura
     * @param matribs Objeto con los atributos de borde de la figura
     */
    public void setMisAtributosBorde(MisAtributosBorde matribs) {
        this.atributosBorde = matribs;
    }

    /**
     * Getter de los atributos generales de la figura
     * @return Atributos generales de la figura
     */
    public MisOtrosAtributos getMisOtrosAtributos() {
        return otrosAtributos;
    }
    
    /**
     * Setter de los atributos generales de la figura
     * @param motros Objeto con los atributos generales  de la figura
     */
    public void setMisOtrosAtributos(MisOtrosAtributos motros) {
        this.otrosAtributos = motros;
    }

    /**
     * Getter de los atributos generales y de borde de la figura
     * @return 
     */
    public MisAtributosBorde getMisAtributosBorde() {
        return atributosBorde;
    }

    /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en esta figura
     * @param p Punto a comprobar
     * @return Si el punto está o no contenido en la figura
     */
    public abstract boolean contains(Point2D p);

    /**
     * Método para mover la figura a una determinada posición
     * @param p Posición donde debemos mover la figura
     */
    public abstract void setLocation(Point2D p);

    /**
     * Método para obtener la posición actual de la figura
     * @return Posición actual de la figura
     */
    public abstract Point2D getLocation();

    /**
     * Método para actualizar la posición de una figura
     * @param p Posición para la actualización 
     */
    public abstract void updateShape(Point2D p);

    /**
     * Método para pintar la figura
     * @param g2d Objeto Graphics2D que dibuja la figura
     */
    public abstract void paint(Graphics2D g2d);

    /**
     * Método para obtener el rectangulo que enmarca la figura
     * @return Rectangulo que enmarca la figura
     */
    public abstract Rectangle2D getBounds2D();

    /**
     * Método para pintar 
     * @param g2d Objeto Graphics2D que pintará la figura
     * @param figura Figura a pintar
     */
    public void miPaint(Graphics2D g2d, Shape figura) {
        g2d.setPaint(atributosBorde.getColorBorde());
        g2d.setStroke(atributosBorde.getBorde());
        g2d.setComposite(otrosAtributos.getComposicion());
        g2d.setRenderingHints(otrosAtributos.getRendering());  
                
        g2d.draw(figura);
    }

    /**
     * Setter del color del borde de la figura
     * @param color Color del borde de la figura
     */
    public void setColorBorde(Color color) {
        this.atributosBorde.setColorBorde(color);
    }
    
    /**
     * Setter del grosor del borde de la figura
     * @param grosor Valor del grosor del borde
     */
    public void setGrosorBorde(int grosor) {
        this.atributosBorde.setGrosor(grosor);
    }
    
    /**
     * Setter de si la figura debe ser transparente o no
     * @param transparencia Si la figura debe ser transparente o no
     */
    public void setTransparencia(boolean transparencia){
        this.otrosAtributos.setTransparencia(transparencia);
    }
    
    /**
     * Setter de si la figura debe ser transparente, así como el grado de transparencia
     * @param transparencia Si la figura debe ser transparente
     * @param valor Grado de transparencia a aplicar
     */
    public void setTransparencia(boolean transparencia, float valor) {
        this.otrosAtributos.setComposicion(transparencia, valor);
    }
    
    /**
     * Setter de si la figura debe pintarse con mejoras de antialiasing o no
     * @param alisado Si la figura debe pintarse con mejoras de antialiasing o no
     */
    public void setAlisado(boolean alisado){
        this.otrosAtributos.setRendering(alisado);
    }
    
    /**
     * Setter de si el borde de la figura debe ser discontinuo o no
     * @param disc Si el borde de la figura debe ser discontinuo o no
     */
    public void setEstoyDiscontinuo(boolean disc){
        this.atributosBorde.setEstoyDiscontinuo(disc);
    }
    
    /*
    public boolean getEstoyDiscontinuo(){
        return this.atributosBorde.getEstoyDiscontinuo();
    }
    */
    
}
