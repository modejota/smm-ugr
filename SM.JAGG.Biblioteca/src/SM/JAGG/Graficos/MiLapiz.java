package SM.JAGG.Graficos;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para representar un trazo libre, hereda de las figuras con sólo borde
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class MiLapiz extends MisFiguras {
   
    /**
     * Atributo interno de la clase GeneralPath, representa el trazo del lápiz
     */
    private GeneralPath dibujo;
    
    /**
     * Punto auxiliar para mantener la coordenada de inicio del trazo libre
     */
    private Point2D pAux;

    /**
     * Constructor con parámetro
     * @param pt Punto de inicio del trazo libre
     */
    public MiLapiz(Point2D pt) {
        pAux = pt;
        dibujo = new GeneralPath();
        dibujo.moveTo(pAux.getX(), pAux.getY());
        dibujo.lineTo(pAux.getX(), pAux.getY());
    }

    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio del trazo libre
     * @param pt2 Punto de fin del trazo libre
     */
    public MiLapiz(Point2D pt1, Point2D pt2) {
        pAux = pt1;
        dibujo = new GeneralPath();
        dibujo.moveTo(pAux.getX(), pt2.getY());
        dibujo.lineTo(pAux.getX(), pt2.getY());
    }
    
    /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio del trazo libre
     * @param pt2 Punto de fin del trazo libre
     * @param mbord Atributos del borde del trazo libre
     */
    public MiLapiz(Point2D pt1, Point2D pt2, MisAtributosBorde mbord) {
        pAux = pt1;
        dibujo = new GeneralPath();
        dibujo.moveTo(pAux.getX(), pt2.getY());
        dibujo.lineTo(pAux.getX(), pt2.getY());
        this.atributosBorde = mbord;
    }
    
        /**
     * Constructor con parámetros
     * @param pt1 Punto de inicio del trazo libre
     * @param pt2 Punto de fin del trazo libre
     * @param mbord Atributos del borde del trazo libre
     * @param motros Atributos generales del trazo libre
     */
    public MiLapiz(Point2D pt1, Point2D pt2, MisAtributosBorde mbord, MisOtrosAtributos motros) {
        pAux = pt1;
        dibujo = new GeneralPath();
        dibujo.moveTo(pAux.getX(), pt2.getY());
        dibujo.lineTo(pAux.getX(), pt2.getY());
        this.atributosBorde = mbord;
        this.otrosAtributos = motros;
    }

    /**
     * Método que comprueba si un determinado punto se encuentra dentro del trazo libre
     * @param p Punto a comprobar
     * @return Si el punto se encuentra dentro del trazo libre
     */
    @Override
    public boolean contains(Point2D p) {
        return dibujo.contains(p);
    }

    /**
     * Método que actualiza el trazo libre actual a un determinado punto
     * @param p El punto con la nueva localización del trazo
     */
    @Override
    public void setLocation(Point2D p) {
        double new_x = p.getX() - pAux.getX();
        double new_y = p.getY() - pAux.getY();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(new_x, new_y);
        dibujo.transform(at);
        pAux = p;
    }

    /**
     * Método para obtener la posición actual del trazo
     * @return Posición actual del trazo
     */
    @Override
    public Point2D getLocation() {
        return pAux;    
    }
    
    /**
     * Método para actualizar la posición del trazo libre
     * @param p Posición para la actualización
     */
    @Override
    public void updateShape(Point2D p) {
        dibujo.lineTo(p.getX(), p.getY());
    }

    /**
     * Método para pintar el trazo libre
     * @param g2d Objeto Graphics2D que dibuja el trazo libre
     */ 
    @Override
    public void paint(Graphics2D g2d) {
        super.miPaint(g2d, dibujo);
    }
    
    /**
     * Método para obtener el rectangulo que enmarca al trazo libre
     * @return Rectangulo que enmarca al trazo libre
     */
    @Override
    public Rectangle2D getBounds2D() {
        return dibujo.getBounds2D();
    }
}
