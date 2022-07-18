package SM.JAGG.Events;


import SM.JAGG.Graficos.MisAtributosBorde;
import SM.JAGG.Graficos.MisAtributosRelleno;
import SM.JAGG.Graficos.MisOtrosAtributos;
import java.util.EventObject;

/**
 * Clase para representar los eventos que tienen lugar dentro de los lienzos
 * Tiene métodos para conocer información relativa a la figura seleccionada en el lienzo generador del evento
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class LienzoEvent extends EventObject {
    
    String figuraSeleccionada;
    int tipoFigura;
    MisAtributosBorde mbord;
    MisAtributosRelleno mrell;
    MisOtrosAtributos motros;
    
    /**
     * Constructor de la clase LienzoEvent
     * @param o Lienzo donde se genera el evento
     */   
    public LienzoEvent(Object o) {
        super(o);
        this.tipoFigura = 0;
    }
    
    /**
     * Getter del tipo de figura seleccionada en el lienzo
     * @return String con el identificador de la figura
     */
    public String getFiguraSeleccionada() {
        return this.figuraSeleccionada;
    }
    
    /**
     * Setter del tipo de figura seleccionada en lienzo
     * @param f String con el nombre de la clase de la figura seleccionada
     */
    public void setFiguraSeleccionada(String f) {
        switch(f){
            case "class SM.JAGG.Graficos.MiLinea":
                figuraSeleccionada = "linea";
                break;
            case "class SM.JAGG.Graficos.MiRectangulo":
                figuraSeleccionada = "rectangulo";
                break;
            case "class SM.JAGG.Graficos.MiElipse":
                figuraSeleccionada = "elipse";
                break;
            case "class SM.JAGG.Graficos.MiCurva":
                figuraSeleccionada = "curva";
                break;
            case "class SM.JAGG.Graficos.MiLapiz":
                figuraSeleccionada = "trazo libre";
                break;
        }
    }
    
    /**
     * Getter de los atributos del borde de la figura seleccionada en el lienzo
     * @return Atributos del borde de la figura seleccionada
     */
    public MisAtributosBorde getMisAtributosBorde() {
        return this.mbord;
    }
    
    /**
     * Setter de los atributos del borde de la figura seleccionada en el lienzo
     * @param mb Atributos del borde de la figura seleccionada
     */
    public void setMisAtributosBorde(MisAtributosBorde mb) {
        this.mbord = mb;
    }
    
    /**
     * Getter de los atributos generales de la figura seleccionada en el lienzo
     * @return Atributos generales de la figura seleccionada
     */
    public MisOtrosAtributos getMisOtrosAtributos() {
        return this.motros;
    }
    
    /**
     * Setter de los atributos genreales de la figura seleccionada en el lienzo
     * @param motros Atributos generales de la figura seleccionada
     */
    public void setMisOtrosAtributos(MisOtrosAtributos motros) {
        this.motros = motros;
    }
    
    /**
     * Getter de los atributos del relleno de la figura seleccionada en el lienzo
     * @return Atributos del relleno de la figura seleccionada
     */
    public MisAtributosRelleno getMisAtributosRelleno() {
        return this.mrell;
    }
    
    /**
     * Setter de los atributos del relleno de la figura seleccionada en el lienzo
     * @param mr Atributos del relleno de la figura seleccionada
     */
    public void setMisAtributosRelleno(MisAtributosRelleno mr) {
        this.mrell = mr;
    }
    
    /**
     * Setter del tipo de figura seleccionada en el lienzo
     * @param t Entero que representa el tipo de figura. 0 para figura sin relleno, 1 para figuar con relleno
     */
    public void setTipoFigura(int t){
        tipoFigura = t;
    }
    
    /**
     * Getter del tipo de figura seleccionada en el lienzo
     * @return Entero que representa el tipo de figura. 0 para figura sin relleno, 1 para figuar con relleno
     */   
    public int getTipoFigura(){
        return tipoFigura;
    }
    
}
