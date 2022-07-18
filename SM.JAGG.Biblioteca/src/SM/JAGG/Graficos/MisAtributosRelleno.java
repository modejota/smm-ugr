package SM.JAGG.Graficos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Point2D;

/**
 *
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class MisAtributosRelleno {
    
    /**
     * Color del relleno de la figura
     */
    private Color colorRelleno;
    /**
     * Booleano que indica si la figura esta rellena o no
     */
    private boolean estoyRelleno;
    /**
     * Booleano que indica si el relleno de la figura es un degradado
     */
    private boolean estoyDegradado;
    /**
     * Valor que indica el tipo de degradado del relleno
     */
    private int opcionRelleno;
    /**
     * Objeto que representa el degradado del relleno
     */
    private GradientPaint degradado;
    
    /**
     * Constructor por defecto. El relleno estará desactivado
     */
    public MisAtributosRelleno() {
        super();
        colorRelleno = Color.BLACK;
        estoyRelleno = estoyDegradado = false;
        opcionRelleno = 0;
    }
    
    /**
     * Constructor con parametros
     * @param color Color del relleno 
     */
    public MisAtributosRelleno(Color color) {
        super();
        colorRelleno = color;
        estoyRelleno = estoyDegradado = false;
        opcionRelleno = 0;
    }
    
    /**
     * Constructor con parámetros
     * @param color Color del relleno
     * @param rell Indica si el relleno está activado o no
     * @param degr Indica si el degradado está activado o no
     */
    public MisAtributosRelleno(Color color, boolean rell, boolean degr) {
        super();
        colorRelleno = color;
        estoyRelleno = rell;
        estoyDegradado = degr;
        opcionRelleno = 0;
    }
    
    /**
     * Getter de todos los atributos del relleno de la figura 
     * @return 
     */
    public MisAtributosRelleno getAtributosRelleno() {
        return this;
    }
    
    /**
     * Getter del color de relleno
     * @return Color del relleno
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Setter del color del relleno
     * @param colorRelleno Color con el que rellenar
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
    
    /**
     * Getter de si la figura está rellena o no
     * @return Si la figura está rellena o no
     */
    public Boolean getEstoyRelleno(){
        return estoyRelleno;
    }
    
    /**
     * Setter de si la figura está rellena o no
     * @param relleno Si la figura debe estar rellena o no
     */
    public void setEstoyRelleno(Boolean relleno) {
        this.estoyRelleno = relleno;
    }
    
    /**
     * Setter de si el relleno debe ser degradado o no
     * @param activar Si el relleno debe ser degradado o no
     */
    public void setDegradado(boolean activar){
        this.estoyDegradado = activar;
    }
    
    /**
     * Getter de si el relleno está degradado o no
     * @return Si el relleno está degradao o no
     */
    public boolean getDegradado(){
        return this.estoyDegradado;
    }
    
    /**
     * Método para actualizar el degradado
     * @param pt1 Punto de inicio del degradado
     * @param c1 Primer color del degrado
     * @param pt2 Punto de fin del degradado
     * @param c2 Segundo punto del degradado
     */
    public void updateDegradado(Point2D pt1, Color c1, Point2D pt2, Color c2) {
        degradado = new GradientPaint(pt1,c1,pt2,c2);
    }
    
    /**
     * Getter del objeto que representa el degradado
     * @return Objeto que representa el degradado
     */
    public GradientPaint getDegradadoObj() {
        return this.degradado;
    }
    
    /**
     * Setter de la opción de degradado a aplicar
     * @param valor Opción de degradado a aplicar
     */
    public void setOpcionRelleno(int valor){
        opcionRelleno = valor;
    }
    
    /**
     * Getter de la opción de degradado
     * @return Opción de degradado
     */
    public int getOpcionRelleno(){
        return opcionRelleno;
    }
}
