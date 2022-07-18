package SM.JAGG.Imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase para aplicar un filtro de tintado, tal que el grado de la mezcla se calcula automáticamente 
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class TintadoAutoOp extends BufferedImageOpAdapter {

    /**
     * Color con el que tintar la imagen
     */
    private Color color;

    /**
     * Constructor con parámetros
     * @param color Color con el que tintar la imagen
     */
    public TintadoAutoOp(Color color) {
        this.color = color;
    }
    
    /**
     * Método para aplicar el filtro de tintado automático
     * @param src Imagen origen
     * @param dest Imagen destino, resultado de aplicar el filtro a la original
     * @return Imagen resultado de aplicar el filtro a la imagen origen
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        float alpha;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                alpha = ((pixelComp[0] + pixelComp[1] + pixelComp[2]) / 3) / 255.0f;
                pixelCompDest[0] = (int) (alpha * red + (1.0f - alpha) * pixelComp[0]);
                pixelCompDest[1] = (int) (alpha * green + (1.0f - alpha) * pixelComp[1]);
                pixelCompDest[2] = (int) (alpha * blue + (1.0f - alpha) * pixelComp[2]);
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }

}
