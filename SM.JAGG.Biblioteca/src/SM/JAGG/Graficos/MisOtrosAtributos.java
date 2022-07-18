package SM.JAGG.Graficos;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.RenderingHints;

/**
 * Clase abstracta que representa todos los atributos que tienen en común las figuras
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class MisOtrosAtributos {

    /**
     *  Objeto que representará la transparencia de la figura
     */
    private Composite composicion;
    /**
     * Booleano que representará si la figura es transparente o no
     */
    private Boolean transparencia;
    /**
     * Booleano que indicará si se han de aplicar mejoras de antialiasing o no
     */
    private Boolean alisar;
    /**
     * Nivel de transparencia de la figura
     */
    private float gradoTransparencia = 1f;
    /**
     * Objeto que representará las mejoras de antialiasing
     */
    private RenderingHints rendering;

    /**
     * Constructor por defecto. Transparencia desactivada, mejoras de antialiasing desactivadas.
     */
    public MisOtrosAtributos() {
        this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        this.transparencia = this.alisar = false;
        this.rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    /**
     * Constructor con parametros. 
     * @param transparencia Indica si la figura es transparente o no
     * @param alisar Indica si se aplican mejoras de antialiasing o no
     */
    public MisOtrosAtributos(boolean transparencia, boolean alisar) {
        this.transparencia = transparencia;
        this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        this.alisar = alisar;
        if (alisar) {
            this.rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            this.rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    
    /** 
     * Constructor con parámetros
     * @param transparencia Si la figura debe ser transparente o no
     * @param alisar Si la figura tiene mejoras de antialiasing o no
     * @param valor Grado de transparencia de la figura
     */
    public MisOtrosAtributos(boolean transparencia, boolean alisar, float valor){
        this.transparencia = transparencia;
        this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, valor);
        this.alisar = alisar;
        if (alisar) {
            this.rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            this.rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        this.gradoTransparencia = valor;
    }

    /**
     * Setter de la transparencia de la figura
     * @param valor Indica si la figura es transparente o no 
     */
    public void setTransparencia(boolean valor) {
        this.transparencia = valor;
    }
    
    /**
     * Getter de la transparencia de la figura
     * @return Devuelve si la figura es transparente o no
     */
    public boolean getEstoyTransparente(){
        return this.transparencia;
    }
    
    /**
     * Setter completo de la transparencia de la figura
     * @param transparencia Indica si la figura es transparente o no.
     * @param valor Indica el nivel de transparencia de la figura
     */
    public void setComposicion(boolean transparencia, float valor){
        this.gradoTransparencia = valor;
        if(transparencia)
            composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gradoTransparencia);
        else 
            composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        this.transparencia = transparencia;
    }
    
    /**
     * Getter del objeto que representa la transparencia de la figura
     * @return Representación de la transparencia de la figura
     */
    public Composite getComposicion() {
        return this.composicion;
    }
    
    /**
     * Getter del booleano que representa si la figura tiene o no mejoras de antialiasing
     * @return Si la figura tiene o no mejoras de antialiasing
     */
    public boolean getEstoyAlisado(){
        return this.alisar;
    }

    /**
     * Setter del objeto que representa las mejoras de antialiasing
     * @param alisar Booleano que indica si se deben aplicar mejoras de antialising
     */
    public void setRendering(Boolean alisar){
        if (alisar) {
            rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        this.alisar = alisar;
    }
    
    /**
     * Getter del objeto que representa las mejoras de antialiasing
     * @return Objeto con información sobre las mejoras de antialiasing
     */
    public RenderingHints getRendering(){
        return rendering;
    }
    
    
}
