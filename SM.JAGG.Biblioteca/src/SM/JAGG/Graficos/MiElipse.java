package SM.JAGG.Graficos;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para representar una elipse, hereda de las figuras con relleno
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class MiElipse extends MisFigurasRellenas {

    /**
     * Atributo interno de la clase Ellpise2D, representa la elipse
     */
    private Ellipse2D elipse;
    /**
     * Punto auxiliar para mantener la coordenada de la esquina superior izquierda del rectángulo que enmarca a la elipse
     */
    private Point2D pAux;

    /**
     * Constructor con parámetro
     * @param p Esquina superior izquierda del rectángulo que enmarca a la elipse
     */
    public MiElipse(Point2D p) {
        this.elipse = new Ellipse2D.Double(p.getX(), p.getY(), p.getX(), p.getY());
        pAux = p;
    }

    /**
     * Constructor con parámetros
     * @param p1 Esquina superior izquierda del rectángulo que enmarca a la elipse
     * @param p2 Punto que contiene el ancho y alto del rectángulo que enmarca la elipse
     */
    public MiElipse(Point2D p1, Point2D p2) {
        this.elipse = new Ellipse2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        pAux = p1;
    }

    /**
     * Constructor con parámetros
     * @param p1 Esquina superior izquierda del rectángulo que enmarca a la elipse
     * @param p2 Punto que contiene el ancho y alto del rectángulo que enmarca la elipse
     * @param mbord Atributos del borde de la elipse
     * @param mrell Atributos del relleno de la elipse
     */
    public MiElipse(Point2D p1, Point2D p2, MisAtributosBorde mbord, MisAtributosRelleno mrell) {
        this.elipse = new Ellipse2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        atributosBorde = mbord;
        atributosRelleno = mrell;
        pAux = p1;
    }
    
        /**
     * Constructor con parámetros
     * @param p1 Esquina superior izquierda del rectángulo que enmarca a la elipse
     * @param p2 Punto que contiene el ancho y alto del rectángulo que enmarca la elipse
     * @param mbord Atributos del borde de la elipse
     * @param mrell Atributos del relleno de la elipse
     * @param motros Atributos generales de la elipse
     */
    public MiElipse(Point2D p1, Point2D p2, MisAtributosBorde mbord, MisAtributosRelleno mrell, MisOtrosAtributos motros) {
        this.elipse = new Ellipse2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        atributosBorde = mbord;
        atributosRelleno = mrell;
        otrosAtributos = motros;
        pAux = p1;
    }

    /**
     * Método para pintar la elipse
     * @param g2d Objeto Graphics2D que dibuja la elipse
     */
    @Override
    public void paint(Graphics2D g2d) {
        super.miPaint(g2d, elipse);
    }

    /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en la elipse
     * @param p Punto a comprobar
     * @return Si el punto está o no contenido en la elipse
     */
    @Override
    public boolean contains(Point2D p) {
        return this.elipse.contains(p);
    }
      
    /**
     * Método para mover la elipse a una determinada posición
     * @param p Posición donde debemos mover la elipse
     */
    @Override
    public void setLocation(Point2D p) {
        this.elipse.setFrame(p.getX(), p.getY(), this.elipse.getWidth(), this.elipse.getHeight());
        pAux = p;
        if (this.atributosRelleno.getDegradado()) {
            this.setDegradadoObj(this.atributosRelleno.getOpcionRelleno());
        }
    }
    
    /**
     * Método para obtener la posición de la esquina superior izquierda del rectángulo que enmarca a la elipse
     * @return Esquina superior izquierda del rectángulo que enmarca a la elipse
     */
    @Override
    public Point2D getLocation() {
        return this.elipse.getBounds().getLocation();
    }

    /**
     * Método para actualizar la posición de una elipse
     * @param p Posición para la actualización 
     */
    @Override
    public void updateShape(Point2D p) {
        this.elipse.setFrameFromDiagonal(pAux, p);
        if (this.atributosRelleno.getDegradado()) {
            this.setDegradadoObj(this.atributosRelleno.getOpcionRelleno());
        }
    }
    
    /**
     * Método para obtener el rectangulo que enmarca la elipse
     * @return Rectangulo que enmarca la elipse
     */
    @Override
    public Rectangle2D getBounds2D() {
        return this.elipse.getBounds2D();
    }

}
