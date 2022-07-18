package SM.JAGG.Graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para representar una curva con un punto de control, hereda de las figuras con sólo borde
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class MiCurva extends MisFiguras {

    /**
     * Atributo interno de la clase QuadCurve2D, representa la curva.
     */
    private QuadCurve2D curva;
    /**
     * Punto auxiliar para mantener la coordenada de inicio de la curva
     */
    private Point2D punto_ini;
    /**
     * Punto auxiliar para mantener la coordenada de fin de la curva
     */
    private Point2D punto_fin;
    /**
     * Punto auxiliar para mantener la coordenada del punto de control de la curva
     */
    private Point2D punto_control;

    /**
     * Constructor con parámetro
     * @param pt Punto de inicio de la curva
     */
    public MiCurva(Point2D pt) {
        punto_ini = punto_fin = punto_control = pt;
        curva = new QuadCurve2D.Double(punto_ini.getX(), punto_ini.getY(), punto_control.getX(), punto_control.getY(), punto_fin.getX(), punto_fin.getY());
    }

    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la curva
     * @param pt2 Punto de fin de la curva
     */
    public MiCurva(Point2D pt1, Point2D pt2) {
        punto_ini = pt1;
        punto_fin = pt2;
        punto_control = new Point2D.Double(pt1.getX(), pt2.getY());
        curva = new QuadCurve2D.Double(punto_ini.getX(), punto_ini.getY(), punto_control.getX(), punto_control.getY(), punto_fin.getX(), punto_fin.getY());
    }

    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la curva
     * @param pt2 Punto de fin de la curva
     * @param mbord Atribtuos del borde de la curva
     */
    public MiCurva(Point2D pt1, Point2D pt2, MisAtributosBorde mbord) {
        punto_ini = pt1;
        punto_fin = pt2;
        punto_control = new Point2D.Double(pt1.getX(), pt2.getY());
        curva = new QuadCurve2D.Double(punto_ini.getX(), punto_ini.getY(), punto_control.getX(), punto_control.getY(), punto_fin.getX(), punto_fin.getY());
        this.atributosBorde = mbord;
    }
    
        /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio de la curva
     * @param pt2 Punto de fin de la curva
     * @param mbord Atribtuos del borde de la curva
     * @param motros Atributos generales de la curva
     */
    public MiCurva(Point2D pt1, Point2D pt2, MisAtributosBorde mbord, MisOtrosAtributos motros) {
        punto_ini = pt1;
        punto_fin = pt2;
        punto_control = new Point2D.Double(pt1.getX(), pt2.getY());
        curva = new QuadCurve2D.Double(punto_ini.getX(), punto_ini.getY(), punto_control.getX(), punto_control.getY(), punto_fin.getX(), punto_fin.getY());
        this.atributosBorde = mbord;
        this.otrosAtributos = motros;
    }

    /**
     * Método para actualizar el punto de control de la curva
     * @param pt Nuevo punto de control
     */
    public void setControl(Point2D pt) {
        curva.setCurve(curva.getP1(), pt, curva.getP2());
    }

    /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en la curva
     * @param p Punto a comprobar
     * @return Si el punto está o no contenido en la curva
     */
    @Override
    public boolean contains(Point2D p) {
        punto_control = p;
        return curva.contains(p);
    }
    
    /**
     * Método para mover la curva a una determinada posición
     * @param p Posición donde debemos mover la curva
     */
    @Override
    public void setLocation(Point2D p) {
        Point2D p2 = new Point2D.Double(curva.getX2() + (p.getX() - curva.getX1()), curva.getY2() + (p.getY() - curva.getY1()));
        Point2D p1 = new Point2D.Double(curva.getCtrlX() + p.getX() - curva.getX1(), curva.getCtrlY() + p.getY() - curva.getY1());
        punto_ini = p;
        curva.setCurve(punto_ini, p1, p2);
    }

    /**
     * Método para obtener el punto de inicio de la curva
     * @return Punto de inicio de la curva
     */
    @Override
    public Point2D getLocation() {
        return punto_ini;
    }

    /**
     * Método para actualizar la posición de una curva
     * @param p Posición para la actualización 
     */
    @Override
    public void updateShape(Point2D p) {
        curva.setCurve(punto_ini, punto_fin, p);
    }
    
    /**
     * Método para pintar la curva
     * @param g2d Objeto Graphics2D que dibuja la curva
     */ 
    @Override
    public void paint(Graphics2D g2d) {
        super.miPaint(g2d, curva);
    }

    /**
     * Método para obtener el rectangulo que enmarca a la curva
     * @return Rectangulo que enmarca a la curva
     */
    @Override
    public Rectangle2D getBounds2D() {
        return curva.getBounds2D();
    }

}
