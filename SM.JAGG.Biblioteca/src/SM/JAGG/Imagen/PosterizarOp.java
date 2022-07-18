package SM.JAGG.Imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase para aplicar un filtro de posterización a una imagen (reducir número de tonos)
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class PosterizarOp extends BufferedImageOpAdapter {

    /**
     * Número de niveles al que se reduce cada banda
     */
    private int niveles;

    /**
     * Constructor con parámetros
     * @param niveles Numero de niveles a los que reducir cada banda
     */
    public PosterizarOp(int niveles) {
        this.niveles = niveles;
    }

    /**
     * Método para aplicar el filtro de posterización
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
        int sample;
        float K = 256.0f/niveles;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < srcRaster.getNumBands(); band++) {
                    sample = srcRaster.getSample(x, y, band);
                    sample = (int)(K * (int)(sample/K));
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }

}
