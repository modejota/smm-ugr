package SM.JAGG.Graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase para representar un rectángulo, hereda de las figuras con relleno
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class MiRectangulo extends MisFigurasRellenas {
    
    /**
     * Atributo interno de la clase Rectangle, representa el rectangulo
     */
    private Rectangle rectangulo;
    /**
     * Punto auxiliar para mantener la coordenada de la esquina superior izquierda
     */
    private Point2D pAux;
    
    /**
     * Constructor con parámetro
     * @param p Esquina superior izquierda del rectangulo
     */
    public MiRectangulo(Point2D p) {
        this.rectangulo = new Rectangle((Point) p);
        pAux = p;
    }
    
    /**
     * Constructor con parámtros
     * @param p Esquina superior izquierda del rectángulo
     * @param mbord Atributos del borde del rectángulo
     * @param mrell Atributos del relleno del rectángulo
     */
    public MiRectangulo(Point2D p, MisAtributosBorde mbord, MisAtributosRelleno mrell){
        this.rectangulo = new Rectangle((Point) p);
        atributosBorde = mbord;
        atributosRelleno = mrell;
        pAux = p;
    }
    
        /**
     * Constructor con parámtros
     * @param p Esquina superior izquierda del rectángulo
     * @param mbord Atributos del borde del rectángulo
     * @param mrell Atributos del relleno del rectángulo
     * @param motros Atributos generales del rectángulo
     */
    public MiRectangulo(Point2D p, MisAtributosBorde mbord, MisAtributosRelleno mrell, MisOtrosAtributos motros){
        this.rectangulo = new Rectangle((Point) p);
        atributosBorde = mbord;
        atributosRelleno = mrell;
        otrosAtributos = motros;
        pAux = p;
    }
    
    /**
     * Método para pintar el rectángulo
     * @param g2d Objeto Graphics2D que dibuja el rectángulo
     */ 
    @Override
    public void paint(Graphics2D g2d){
        super.miPaint(g2d, rectangulo);
    }
    
    /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en el rectángulo
     * @param p Punto a comprobar
     * @return Si el punto está o no contenido en el rectángulo
     */
    @Override
    public boolean contains(Point2D p) {
        return this.rectangulo.contains(p);
    }
    
    /**
     * Método para mover el rectángulo a una determinada posición
     * @param p Posición donde debemos mover el rectángulo
     */
    @Override
    public void setLocation(Point2D p) {
        this.rectangulo.setFrame(p.getX(), p.getY(), rectangulo.getWidth(), rectangulo.getHeight());
        if (this.atributosRelleno.getDegradado()) {
            this.setDegradadoObj(this.atributosRelleno.getOpcionRelleno());
        }
    }
    
    /**
     * Método para obtener la esquina superior izquierda del rectángulo
     * @return Esquina superior izquierda del rectángulo
     */
    @Override
    public Point2D getLocation() {
        return this.rectangulo.getLocation();
    }
    
    /**
     * Método para actualizar la posición de un rectángulo
     * @param p Posición para la actualización 
     */
    @Override
    public void updateShape(Point2D p) {
        this.rectangulo.setFrameFromDiagonal(pAux,p);
        if(this.atributosRelleno.getDegradado()){
            this.setDegradadoObj(this.atributosRelleno.getOpcionRelleno());
        }
    }
    
    /**
     * Método para obtener el rectangulo que enmarca al rectángulo
     * @return Rectangulo que enmarca al rectángulo
     */
    @Override
    public Rectangle2D getBounds2D() {
        return this.rectangulo.getBounds2D();
    }
    
}
