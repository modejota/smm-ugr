package SM.JAGG.Graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para representar una línea, hereda de las figuras con sólo borde
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class MiLinea extends MisFiguras {
    /**
     * Atributo interno de la clase Line2D, representa los puntos extremo
     */
    private Line2D linea;
    
    /**
     * Constructor con parámetros
     * @param pt Punto de inicio y de fin de la línea
     */
    public MiLinea(Point2D pt) {
        linea = new Line2D.Double(pt, pt);
    }
    
    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la línea
     * @param pt2 Punto de fin de la línea
     */
    public MiLinea(Point2D pt1, Point2D pt2) {
        linea = new Line2D.Double(pt1, pt2);
    }
    
    /**
     * Constructor con parámetros
     * @param pt Punto de inicio de la línea
     * @param mbord Atributos del borde de la línea
     */
    public MiLinea(Point2D pt, MisAtributosBorde mbord) {
        linea = new Line2D.Double(pt,pt);
        atributosBorde = mbord;
    }
    
    /**
     * Constructor con parámetros
     * @param pt Punto de inicio de la línea
     * @param mbord Atributos del borde de la línea
     * @param motros Atributos generales de la línea
     */
    public MiLinea(Point2D pt, MisAtributosBorde mbord, MisOtrosAtributos motros) {
        linea = new Line2D.Double(pt,pt);
        atributosBorde = mbord;
        otrosAtributos = motros;
    }
       
    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la línea
     * @param pt2 Punto de fin de la línea
     * @param mbord Atributos del borde de la línea
     */
    public MiLinea(Point2D pt1, Point2D pt2, MisAtributosBorde mbord) {
        linea = new Line2D.Double(pt1,pt2);
        atributosBorde = mbord;
    }
    
    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la línea
     * @param pt2 Punto de fin de la línea
     * @param mbord Atributos del borde de la línea
     * @param motros Atributos generales de la línea
     */
    public MiLinea(Point2D pt1, Point2D pt2, MisAtributosBorde mbord, MisOtrosAtributos motros) {
        linea = new Line2D.Double(pt1,pt2);
        atributosBorde = mbord;
        otrosAtributos = motros;
    }

    /**
     * Método para comprobar si la línea en realidad es un punto
     * @param pos Posición a comprobar
     * @return Si la línea es un punto o no
     */
    public boolean isThisAPoint(Point2D pos) {
        double difX = Math.abs(pos.getX() - linea.getX1());
        double difY = Math.abs(pos.getY() - linea.getY1());
        return (difX <= 2.0 && difY <= 2.0);
    }
    
    /**
     * Método para comprobar si un punto está cerca de una línea
     * @param p Punto a comprobar
     * @return Si el punto está cerca de una línea 
     */
    public boolean isNear(Point2D p) {
        return linea.ptLineDist(p) <= 2.0;
    }
    
     /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en la línea
     * @param pos Punto a comprobar
     * @return Si el punto está o no contenido en la línea
     */
    @Override
    public boolean contains(Point2D pos) {
        return isNear(pos) || isThisAPoint(pos);
        
    }
    
    /**
     * Método para mover la línea a una determinada posición
     * @param pos Posición donde debemos mover la línea
     */
    @Override
    public void setLocation(Point2D pos) {
        double dx = pos.getX() - linea.getX1();
        double dy = pos.getY() - linea.getY1();
        Point2D newp2 = new Point2D.Double(linea.getX2() + dx, linea.getY2() + dy);
        linea.setLine(pos, newp2);
    }
    
    /**
     * Método para obtener la posición actual del punto de inicio de la línea
     * @return Punto de inicio de la línea
     */
    @Override
    public Point2D getLocation() {
        return linea.getP1();
    }
    
    /**
     * Método para actualizar la posición de una línea
     * @param p Posición para la actualización 
     */
    @Override
    public void updateShape(Point2D p) {
        linea.setLine(linea.getP1(), p);
    }
    
    /**
     * Método para obtener el rectangulo que enmarca la línea
     * @return Rectangulo que enmarca la línea
     */
    @Override
    public Rectangle2D getBounds2D() {
        return linea.getBounds2D();
    }
    
    /**
     * Método para pintar la línea
     * @param g2d Objeto Graphics2D que dibuja la línea
     */
    @Override
    public void paint(Graphics2D g2d) {
        super.miPaint(g2d, linea);
    }
}
