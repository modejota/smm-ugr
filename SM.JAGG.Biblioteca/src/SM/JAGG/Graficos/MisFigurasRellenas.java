/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.JAGG.Graficos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que representa a una figura genérica que puede ser rellena
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public abstract class MisFigurasRellenas extends MisFiguras {
    /**
     * Atributos del relleno de la figura
     */
    protected MisAtributosRelleno atributosRelleno;
    
    
    /**
     * Constructor por defecto
     */
    public MisFigurasRellenas(){
        super();
        atributosRelleno = new MisAtributosRelleno();
    }
    
    /** 
     * Constructor con parámetros
     * @param mbord Atributos del borde de la figura
     * @param mrell Atributos del relleno de la figura
     * @param motros Atributos del borde de la figura
     */
    public MisFigurasRellenas(MisAtributosBorde mbord, MisAtributosRelleno mrell, MisOtrosAtributos motros) {
        super(mbord,motros);
        atributosRelleno = mrell;
    }
    
    /**
     * Getter de los atributos del relleno de la figura
     * @return Atributos del relleno de la figura
     */
    public MisAtributosRelleno getMisAtributosRelleno(){
        return atributosRelleno;
    }
    
    /**
     * Setter de los atributos del relleno de la figura
     * @param mrell Atributos de relleno a actualizar
     */
    public void setMisAtributosRellleno(MisAtributosRelleno mrell) {
        this.atributosRelleno = mrell;
    }
    
    /**
     * Método que nos devuelve si un determinado punto se encuentra contenido en esta figura
     * @param p Punto a comprobar
     * @return Si el punto está o no contenido en la figura
     */
    @Override
    public abstract boolean contains(Point2D p);
    
    /**
     * Método para mover la figura a una determinada posición
     * @param p Posición donde debemos mover la figura
     */
    @Override 
    public abstract void setLocation(Point2D p);
        
    /**
     * Método para obtener la posición actual de la figura
     * @return Posición actual de la figura
     */
    @Override
    public abstract Point2D getLocation();
    
    /**
     * Método para actualizar la posición de una figura
     * @param p Posición para la actualización 
     */
    @Override
    public abstract void updateShape(Point2D p);
    
    /**
     * Método para pintar la figura
     * @param g2d Objeto Graphics2D que dibuja la figura
     */
    @Override
    public abstract void paint(Graphics2D g2d);
    
    /**
     * Método para obtener el rectangulo que enmarca la figura
     * @return Rectangulo que enmarca la figura
     */
    @Override
    public abstract Rectangle2D getBounds2D();
    
    /**
     * Setter de si la figura está rellena
     * @param relleno Si la figura está rellena
     */
    public void setEstoyRelleno(Boolean relleno) {
        atributosRelleno.setEstoyRelleno(relleno);
    }
    /**
     * Getter de si la figura está rellena
     * @return Si la figura está rellena
     */
    public boolean getEstoyRelleno() {
        return atributosRelleno.getEstoyRelleno();
    }
    
    /**
     * Setter del color del relleno de la figura
     * @param color Color del relleno
     */
    public void setColorRelleno(Color color) {
        atributosRelleno.setColorRelleno(color);
    }
    
    /**
     * Getter del color de relleno de la figura
     * @return Color del relleno
     */
    public Color getColorRelleno() {
        return atributosRelleno.getColorRelleno();
    }
    
    /**
     * Setter de si el relleno de la figura es degradado
     * @param activar Si el relleno de la figura es degradado 
     */
    public void setDegradado(boolean activar){
        this.atributosRelleno.setDegradado(activar);
    }
    
    /**
     * Setter del degradado de la figura
     * @param opcion Dirección del degradado
     */
    public void setDegradadoObj(int opcion){
        Point2D pt1 = null,pt2 = null;
        switch(opcion){
            case 0: //Horizontal
               pt1 = new Point2D.Double(this.getBounds2D().getMinX(),this.getBounds2D().getMinY());
               pt2 = new Point2D.Double(this.getBounds2D().getMaxX(),this.getBounds2D().getMinY());
               break;
            case 1: //Vertical
               pt1 = new Point2D.Double(this.getBounds2D().getMinX(),this.getBounds2D().getMinY());
               pt2 = new Point2D.Double(this.getBounds2D().getMinX(),this.getBounds2D().getMaxY());
               break;
            case 2: //Diagonal
               pt1 = new Point2D.Double(this.getBounds2D().getMinX(),this.getBounds2D().getMinY());
               pt2 = new Point2D.Double(this.getBounds2D().getMaxX(),this.getBounds2D().getMaxY());
               break;
        }
        this.atributosRelleno.updateDegradado(pt1,this.atributosBorde.getColorBorde(),pt2,this.atributosRelleno.getColorRelleno());
    }
    
    /**
     * Setter de la opción de degradado
     * @param opcion Opción de degradado
     */
    public void setOpcionDegradado(int opcion){
        this.atributosRelleno.setOpcionRelleno(opcion);
    }
    
    /**
     * Método para pintar 
     * @param g2d Objeto Graphics2D que pintará la figura
     * @param figura Figura a pintar
     */
    @Override
    public void miPaint(Graphics2D g2d, Shape figura) {
        g2d.setPaint(atributosBorde.getColorBorde());
        g2d.setStroke(atributosBorde.getBorde());
        g2d.setComposite(otrosAtributos.getComposicion());
        g2d.setRenderingHints(otrosAtributos.getRendering());
         
        g2d.draw(figura);        
        if (atributosRelleno.getEstoyRelleno()) {
            g2d.setPaint(atributosRelleno.getColorRelleno());
                    if (atributosRelleno.getDegradado())
                        g2d.setPaint(atributosRelleno.getDegradadoObj());
            g2d.fill(figura);
        }
    }
    
}
