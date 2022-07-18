package SM.JAGG.Graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * Clase que representa los atributos propios del borde de las figuras
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class MisAtributosBorde {

    /**
     * Objeto que representá el borde de la figura
     */
    private Stroke borde;
    /**
     * Grosor del borde de la figura
     */
    private int grosor;
    /**
     * Booleano que indica si el borde es discontinuo
     */
    private boolean discontinuidad;
    /**
     * Patrón de discontinuidad en caso de tener borde discontinuo
     */
    private float patronDiscontinuidad[] = {10.0f, 10.0f};
    /**
     * Objeto que representa el color del borde de la figura
     */
    private Color colorBorde;

    /**
     * Constructor por defecto. El borde será negro, continuo y de grosor 2
     */
    public MisAtributosBorde() {
        colorBorde = Color.BLACK;
        grosor = 2;
        discontinuidad = false;
        this.makeBorder();
    }

    /**
     * Constructor con parámetros
     * @param grosor Nivel de grosor del borde
     * @param disc Indica si el borde es discontinuo o no
     * @param discontinuidad Indica el patrón de discontinuidad a aplicar
     */
    public MisAtributosBorde(int grosor, boolean disc, float[] discontinuidad) {
        this.colorBorde = Color.BLACK;
        this.grosor = grosor;
        this.discontinuidad = disc;
        this.patronDiscontinuidad = discontinuidad;
        this.makeBorder();
    }

    /**
     * Constructor con parámetros
     * @param color Color que tendrá el borde
     */
    public MisAtributosBorde(Color color) {
        colorBorde = color;
        grosor = 2;
        discontinuidad = false;
        this.makeBorder();
    }
    
    /**
     * Constructor con parámetros
     * @param color Color que tendrá el borde
     * @param gross Indica el nivel de grosor del borde
     */
    public MisAtributosBorde(Color color, int gross) {
        colorBorde = color;
        grosor = gross;
        discontinuidad = false;
        this.makeBorder();
    }

    /**
     * Constructor con parametros más completo
     * @param color Color que tendrá el borde
     * @param grosor Indica el nivel de grosor del borde
     * @param disc Indica si el borde es discontinuo o no
     * @param discontinuidad Patrón de discontinuidad del borde
     */
    public MisAtributosBorde(Color color, int grosor, boolean disc, float[] discontinuidad) {
        colorBorde = color;
        this.grosor = grosor;
        this.discontinuidad = disc;
        this.patronDiscontinuidad = discontinuidad;
        this.makeBorder();
    }

    /**
     * Getter del color del borde
     * @return Color del borde
     */
    public Color getColorBorde() {
        return this.colorBorde;
    }

    /**
     * Setter del color del borde
     * @param color Color del borde
     */
    public void setColorBorde(Color color) {
        this.colorBorde = color;
    }

    /**
     * Getter de todos los atributos del borde de la figura (incluye los de la clase padre)
     * @return 
     */
    public MisAtributosBorde getMisAtributosBorde() {
        return this;
    }

    /**
     * Setter del grosor del borde
     * @param grosor Valor del grosor del borde
     */
    public void setGrosor(int grosor) {
        this.grosor = grosor;
        this.makeBorder();
    }

    /**
     * Getter del grosor del borde
     * @return Grosor del borde
     */
    public int getGrosor() {
        return grosor;
    }

    /**
     * Getter de si el borde es discontinuo o no
     * @return Valor de verdad que representa si el borde es discontinuo o no
     */
    public boolean getEstoyDiscontinuo() {
        return this.discontinuidad;
    }

    /**
     * Setter de si el borde es discontinuo o no
     * @param disc Valor de verdad de la discontinuidad del borde
     */
    public void setEstoyDiscontinuo(boolean disc) {
        this.discontinuidad = disc;
        this.makeBorder();
    }

    /**
     * Setter del patrón de discontinuidad del borde
     * @param valor Patrón de discontinuidad a aplicar
     */
    public void setPatronDiscontinuidad(float valor) {
        this.patronDiscontinuidad = new float[]{valor, valor};
        this.makeBorder();
    }

    /**
     * Getter del patrón de discontinuidad del borde
     * @return Patrón de discontinuidad del borde
     */
    public float[] getPatronDiscontinuidad() {
        return this.patronDiscontinuidad;
    }

    /**
     * Setter del objeto borde
     * @param newBorde Objeto borde a aplicar
     */
    public void setBorde(Stroke newBorde) {
        this.borde = newBorde;
    }

    /**
     * Getter del objeto borde
     * @return Borde de la figura
     */
    public Stroke getBorde() {
        return borde;
    }

    /**
     * Método privado para actualizar el borde de la figura cuando se modifica alguna propiedad del mismo.
     */
    private void makeBorder() {
        if (this.discontinuidad) {
            borde = new BasicStroke(grosor, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
        } else {
            borde = new BasicStroke(grosor);
        }
    }

}
