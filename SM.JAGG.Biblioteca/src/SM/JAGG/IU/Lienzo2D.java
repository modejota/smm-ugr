package SM.JAGG.IU;

import SM.JAGG.Events.LienzoEvent;
import SM.JAGG.Events.LienzoListener;
import SM.JAGG.Graficos.MiCurva;
import SM.JAGG.Graficos.MiElipse;
import SM.JAGG.Graficos.MiLapiz;
import SM.JAGG.Graficos.MiLinea;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import SM.JAGG.Graficos.MiRectangulo;
import SM.JAGG.Graficos.MisAtributosBorde;
import SM.JAGG.Graficos.MisAtributosRelleno;
import SM.JAGG.Graficos.MisFiguras;
import SM.JAGG.Graficos.MisFigurasRellenas;
import SM.JAGG.Graficos.MisOtrosAtributos;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Clase que representará el lienzo donde se mostrarán las figuras e imagenes
 * @author José Alberto Gómez García modej@correo.ugr.es
 */

public class Lienzo2D extends javax.swing.JPanel {

    /**
     * Herramientas de dibujo disponibles
     */
    public enum Herramienta {
        LINEA, RECTANGULO, ELIPSE, CURVA, LAPIZ
    }

    /**
     * Color de borde seleccionado
     */
    private Color colorBorde;
    /**
     * Color de relleno seleccionado
     */
    private Color colorRelleno;
    /**
     * Atributo para saber si se debe rellenar
     */
    private Boolean rellenoColor;
    /**
     * Atributo para saber si estamos moviendo figuars
     */
    private Boolean editarFiguras;
    /**
     * Atributo para saber si debemos poner transparencia
     */
    private Boolean transparencia;
    /**
     * Atributo para saber si debemos aplicar antialiasing
     */
    private Boolean alisado;
    /**
     * Atributo apra saber si debemos aplicar degradado al relleno
     */
    private Boolean degradado;
    /**
     * Atributo para saber si estamos gestionando el punto de control de la curva
     */
    private Boolean controlCurva;
    /**
     * Atributo para saber si el trazo es discontinuo
     */
    private Boolean discontinuidad;
    /**
     * Atribto para conocer la herramienta a usar
     */
    private Herramienta herramienta;
    /**
     * Atributo para saber el grosor del trazo
     */
    private int numero_grosor;
    /**
     * Atributo para gestionar la transparencia
     */
    private Composite composicion;
    /**
     * Atributo para conocer el grado de transparencia
     */
    private float gradoTransparencia;
    /**
     * Atributo para gestionar las mejoras de antialiasing
     */
    private RenderingHints rendering;
    /**
     * Atributo con la imagen mostrada
     */
    private BufferedImage imagen;
    /**
     * Atributo para establecer el patrón de discontinuidad del marco del lienzo
     */
    float discontinuidad_marco[] = {8.0f, 8.0f};
    /**
     * Atributo para controlar el efecto ventana
     */
    private boolean efectoVentana;
    /**
     * Punto auxiliar para mantener las coordendas de donde se ha clickado
     */
    private Point2D pAux;
    /**
     * Vector con las figuras editables del lienzo
     */
    List<MisFiguras> vShape = new ArrayList();
    /**
     * Atributo para conocer la figura seleccionada 
     */
    private MisFiguras figuraAux;
    /**
     * Círculo sobre el que se verá al activar el efecto 
     */
    private Ellipse2D.Double clipVentana;
    /**
     * Rectángulo que define el límite del lienzo
     */
    private Rectangle2D.Double clipImagen;
    /**
     * Borde discontinuo que marca el límite del lienzo
     */
    private BasicStroke bordeImagen;
    /**
     * Vector con los listeners asociados a los eventos del lienzo
     */
    ArrayList<LienzoListener> lienzoEventListeners = new ArrayList();
    
    
    /**
     * Creates new form Lienzo. Constructor por defecto
     */
    public Lienzo2D() {
        initComponents();
        colorBorde = colorRelleno = Color.BLACK;
        rellenoColor = editarFiguras = transparencia = alisado = degradado = controlCurva = discontinuidad = false;
        herramienta = Herramienta.LINEA;
        numero_grosor = 2;
        composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        gradoTransparencia = 1;
        rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF); 
        efectoVentana = false;
        imagen = null;
        clipVentana = new Ellipse2D.Double(0, 0, 100, 100);
        bordeImagen = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, discontinuidad_marco, 0.0f);
    }

    /**
     * Getter del color del borde seleccionado
     * @return Color de borde activo
     */
    public Color getColorBorde() {
        return colorBorde;
    }
    /**
     * Getter del color del relleno seleccionado
     * @return Color de relleno activo
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Seter del color de borde seleccionado
     * @param colorBorde Color de borde a activar
     */
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        if (figuraAux != null) {
            figuraAux.setColorBorde(colorBorde);
        }
        repaint();
    }

    /**
     * Setter del color de relleno seleccionado
     * @param colorRelleno Color de relleno a activar
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
        if (figuraAux != null && figuraAux instanceof MisFigurasRellenas) {
           ((MisFigurasRellenas) figuraAux).setColorRelleno(colorRelleno);
        }
        repaint();
    }

    /**
     * Getter de si se ha de rellenar o no
     * @return Opcion de relleno seleccionada
     */
    public Boolean getRellenoColor() {
        return rellenoColor;
    }

    /**
     * Setter de si se ha de rellenar o no
     * @param rellenoColor Opcion de relleno a activar
     */
    public void setRellenoColor(Boolean rellenoColor) {
        this.rellenoColor = rellenoColor;
        if (figuraAux != null && figuraAux instanceof MisFigurasRellenas) {
            ((MisFigurasRellenas) figuraAux).setEstoyRelleno(rellenoColor);
            ((MisFigurasRellenas) figuraAux).setDegradadoObj(((MisFigurasRellenas) figuraAux).getMisAtributosRelleno().getOpcionRelleno());
        }
        repaint();
    }

    /**
     * Getter de la herramienta seleccionada
     * @return Herramienta seleccionada
     */
    public Herramienta getHerramienta() {
        return herramienta;
    }

    /**
     * Setter de la herramienta seleccionada
     * @param herramienta Herramienta a activar
     */
    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
        figuraAux = null;
    }

    /**
     * Getter del modo de edición
     * @return Si el modo de edición de figuras está activado o no
     */
    public Boolean getEditarFiguras() {
        return editarFiguras;
    }

    /**
     * Setter del modo de edición
     * @param editarFiguras Activación o desactivación del modo
     */
    public void setEditarFiguras(Boolean editarFiguras) {
        this.editarFiguras = editarFiguras;
    }

    /**
     * Setter del borde de la figura
     * @param nuevo Grosor del borde
     */
    public void setStroke(int nuevo) {
        numero_grosor = nuevo;
        if (figuraAux != null) {
            figuraAux.setGrosorBorde(nuevo);
        }
        repaint();
    }
    
    /**
     * Getter del borde de la figura
     * @return Grosor del borde
     */
    public int getStroke() {
        return numero_grosor;
    }

    /**
     * Getter de la opción de transparencia
     * @return Si la transparencia está activa o no
     */
    public Boolean getTransparencia() {
        return transparencia;
    }

    /**
     * Setter de la opción de transparencia
     * @param transparencia Si la transparencia ha de activarse o no
     */
    public void setTransparencia(Boolean transparencia) {
        if (transparencia) {
            composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        } else {
            composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        }
        this.transparencia = transparencia;
        if (figuraAux != null) {
            figuraAux.setTransparencia(transparencia);
        }
        repaint();
    }

    /**
     * Getter del grado de transparencia
     * @return Grado de transparencia activo
     */
    public float getGradoTransparencia() {
        return gradoTransparencia;
    }

    /**
     * Setter del grado de transparencia
     * @param gradoTransparencia Grado de transparencia a activar
     */
    public void setGradoTransparencia(int gradoTransparencia) {
        this.gradoTransparencia = (float) gradoTransparencia / 100.f;
        composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.gradoTransparencia);
        if (figuraAux != null) {
            figuraAux.setTransparencia(transparencia, this.gradoTransparencia);
        }
        repaint();
    }
    
    /**
     * Getter de la opción de alisado
     * @return Si la opción de antialiasing está activa o no
     */
    public Boolean getAlisado() {
        return alisado;
    }

    /**
     * Setter de la opción de alisado
     * @param alisado Si debe aplicarse antialiasing o no
     */
    public void setAlisado(Boolean alisado) {
        if (alisado) {
            rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            rendering = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        this.alisado = alisado;
        if (figuraAux != null) {
            figuraAux.setAlisado(alisado);
        }
        repaint();
    }

    /**
     * Setter de la opción de degradado
     * @param activar Si se debe activar el modo de degradado
     */
    public void setDegradado(boolean activar) {
        this.degradado = activar;
        if (figuraAux != null && figuraAux instanceof MisFigurasRellenas) {
            ((MisFigurasRellenas) figuraAux).setDegradado(activar);
            if(activar) {
                ((MisFigurasRellenas) figuraAux).setDegradadoObj(((MisFigurasRellenas) figuraAux).getMisAtributosRelleno().getOpcionRelleno());
            }
        }
        repaint();
    }
    /**
     * Setter de la opción del modo de degradado
     * @param opcion El modo de degradado que debe aplicarse
     */
    public void setOpcionDegradado(int opcion) {
        if (figuraAux != null && figuraAux instanceof MisFigurasRellenas) {
            ((MisFigurasRellenas) figuraAux).setOpcionDegradado(opcion);
            if(((MisFigurasRellenas) figuraAux).getMisAtributosRelleno().getDegradado()) {
                ((MisFigurasRellenas) figuraAux).setDegradadoObj(((MisFigurasRellenas) figuraAux).getMisAtributosRelleno().getOpcionRelleno());
            }
        }
    }
    
    /**
     * Getter de la opción de degradado
     * @return Si la opción de degradado está activada o no
     */
    public boolean getEstoyDegradado(){
        return this.degradado;
    }
    
    /**
     * Getter de la opción de discontinuidad de borde
     * @return Si la opción de borde discontinuo está activada o no
     */
    public boolean getEstoyDiscontinuo(){
        return this.discontinuidad;
    }
    
    /**
     * Setter de la opción de discontinuidad de borde
     * @param disc Si el borde debe ser discontinuo o no
     */
    public void setEstoyDiscontinuo(Boolean disc) {
        discontinuidad = disc;
        if(figuraAux != null)
            figuraAux.setEstoyDiscontinuo(disc);
    }

    /**
     * Setter de la imagen del lienzo
     * @param img Imagen a colocar en el lienzo
     */
    public void setImage(BufferedImage img) {
        this.imagen = img;
        clipImagen = new Rectangle2D.Double(0, 0, imagen.getWidth(), imagen.getHeight());
        if (imagen != null) {
            //Garantizar barras de desplazamiento si la ventana lo requiere
            setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
        }
    }
    
    /**
     * Getter de la imagen del lienzo.
     * @return Imagen del lienzo sin modificar
     */
    public BufferedImage getImage() {
        return this.imagen;
    }

    /**
     * Getter de la imagen del lienzo
     * @param drawVector Si debemos volcar las figuras pintadas sobre la imagen o no
     * @return Imagen de lienzo, con o sin figuras aplicadas sobre ella.
     */
    public BufferedImage getImage(boolean drawVector) {
        if (drawVector) {
            BufferedImage imgout = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
            this.paint(imgout.createGraphics());
            return imgout;
        } else {
            return this.imagen;
        }
    }

    /**
     * Setter del efecto ventana (visión limitada a un círculo centrado en el ratón)
     * @param efecto Si debemos aplicar efecto ventana
     */
    public void setEfectoVentana(boolean efecto) {
        efectoVentana = efecto;
    }

    /**
     * Getter del efecto ventana
     * @return Si el efecto ventana está activado o no
     */
    public Boolean getEfectoVentana() {
        return this.efectoVentana;
    }

    /**
     * Método para volcar las figuras sobre la imagen del lienzo
     */
    public void realizarVolcado() {
        if (this.imagen != null) {
            imagen = this.getImage(true);
            vShape.clear();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 873, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para capturar el punto sobre el que se hace click
     * @param evt Evento de ratón
     */
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        pAux = evt.getPoint();
    }//GEN-LAST:event_formMouseClicked

    /**
     * Método para gestionar las figuras mientras se mantiene pulsado el ratón
     * @param evt Evento de ratón
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pAux = evt.getPoint();
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        if (editarFiguras) {
            figuraAux = getSelectedShape(evt.getPoint());
            this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            if (figuraAux != null) {
                pAux = new Point2D.Double(figuraAux.getLocation().getX() - evt.getPoint().getX(), figuraAux.getLocation().getY() - evt.getPoint().getY());
                //Cuando seleccionamos una figura generamos un evento para notificar de la selección, para que se actualice la interfaz de la ventana principal
                LienzoEvent evtL = new LienzoEvent(this);
                evtL.setFiguraSeleccionada(figuraAux.getClass().toString());
                evtL.setMisOtrosAtributos(figuraAux.getMisOtrosAtributos());
                evtL.setMisAtributosBorde(figuraAux.getMisAtributosBorde());
                if(figuraAux instanceof MisFigurasRellenas) {
                    evtL.setMisAtributosRelleno(((MisFigurasRellenas) figuraAux).getMisAtributosRelleno());
                    evtL.setTipoFigura(1);
                }
                notifySelectedShapeEvent(evtL);
                
            }
        } else {
            if(figuraAux != null) {
                controlCurva = figuraAux instanceof MiCurva && !controlCurva;
            }
            if(figuraAux == null || !controlCurva)
                createShape(evt);
        }
    }//GEN-LAST:event_formMousePressed

    /**
     * método para gestionar las figuars cuando se suelta el ratón
     * @param evt Evento de ratón
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        formMouseDragged(evt);   //No repetir código
        actualizarEfectoVentana(evt);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_formMouseReleased

    /**
     * Método para gestionar las figuras cuando se arrastra el ratón
     * @param evt Evento de ratón
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (editarFiguras) {
            if (figuraAux != null) {
                Point2D punto = new Point2D.Double(evt.getPoint().getX() + pAux.getX(), evt.getPoint().getY() + pAux.getY());
                figuraAux.setLocation(punto);
            }
        } else if (figuraAux != null) {
            if(figuraAux instanceof MiCurva) {
                if(controlCurva)
                    ((MiCurva) figuraAux).setControl(evt.getPoint());
                else
                    figuraAux.updateShape(evt.getPoint());
            } else
                figuraAux.updateShape(evt.getPoint());
        }
        actualizarEfectoVentana(evt);
        repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * Método para gestionar el efecto ventana mientras que se mueve el ratón
     * @param evt Evento de ratón
     */
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        actualizarEfectoVentana(evt);
        repaint();
    }//GEN-LAST:event_formMouseMoved

    /**
     * Método para llevar a cabo la actualización de la posición de la zona del efecto ventana
     * @param evt Evento de ratón
     */
    private void actualizarEfectoVentana(java.awt.event.MouseEvent evt) {
        if (efectoVentana) {
            clipVentana.setFrame(evt.getPoint().getX() - 100, evt.getPoint().getY() - 100, 200, 200);
        } else {
            clipVentana.setFrame(0, 0, 0, 0);
        }
    }

    /**
     * Método para pintar las figuras del lienzo, así como el propio enmarcado del lienzo.
     * @param g Objeto Graphics con el que pintar las figuras
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (efectoVentana) { 
            g2d.clip(clipVentana);
        }

        if (imagen != null) {
            g2d.setStroke(bordeImagen);
            g2d.drawRect(0, 0, imagen.getWidth(), imagen.getHeight());
            g2d.clip(clipImagen);
            g2d.drawImage(imagen, 0, 0, this);
        }

        for (MisFiguras s : vShape) {
            s.paint(g2d);
        }
    }

    /**
     * Método para crear una figura en el lienzo a partir de los valores activos en el propio lienzo
     * @param evt Evento de ratón
     */
    private void createShape(MouseEvent evt) {
        MisOtrosAtributos motros = new MisOtrosAtributos(transparencia, alisado, gradoTransparencia);
        MisAtributosBorde mbord = new MisAtributosBorde(colorBorde, numero_grosor);
        MisAtributosRelleno mrell = new MisAtributosRelleno(colorRelleno, rellenoColor, degradado);
        switch (herramienta) {
            case LINEA:
                figuraAux = (MisFiguras) new MiLinea(evt.getPoint(), mbord, motros);
                break;
            case RECTANGULO:
                figuraAux = (MisFigurasRellenas) new MiRectangulo(evt.getPoint(), mbord, mrell, motros);
                break;
            case ELIPSE:
                figuraAux = (MisFigurasRellenas) new MiElipse(evt.getPoint(), evt.getPoint(), mbord, mrell, motros);
                break;   
            case CURVA:
                figuraAux = (MisFiguras) new MiCurva(evt.getPoint(),evt.getPoint(),mbord, motros);
                break;
            case LAPIZ:
                figuraAux = (MisFiguras) new MiLapiz(evt.getPoint(), evt.getPoint(), mbord, motros);
                break;
        }
        vShape.add(figuraAux);
    }

    /**
     * Método para obtener la figura que se encuentra en una determinada posición del lienzo
     * @param p Posición del lienzo
     * @return Figura en dicha coordenada, nulo en caso de no haber ninguna figura
     */
    private MisFiguras getSelectedShape(Point2D p) {
        for (int i = vShape.size() - 1; i >= 0; i--) {
            if (vShape.get(i).contains(p)) {
                return vShape.get(i);
            }
        }
        return null;
    }

    /**
     * Método para añadir los listener de los eventos del lienzo
     * @param listener Objeto listener del lienzo
     */
    public void addLienzoListener(LienzoListener listener){
        if(listener != null)
            lienzoEventListeners.add(listener);
    }
    
    /**
     * Método para notificar de un cambio en la forma seleccionada dentro del lienzo
     * @param evt Evento de lienzo.
     */
    private void notifySelectedShapeEvent(LienzoEvent evt) {
        if(!lienzoEventListeners.isEmpty()) {
            for(LienzoListener listener : lienzoEventListeners)
                listener.selectedShape(evt);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
