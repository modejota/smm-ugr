package SM.JAGG.Imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase para aplicar un filtro de resaltado de tonos rojos a una imagen
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class RedOp extends BufferedImageOpAdapter {

    /**
     * Umbral a partir del cual se considera que un píxel es predominantemente rojo
     */
    private int umbral;

    /**
     * Constructor con parámetros
     * @param umbral Umbral a partir del cual se considera que un píxel es predominantemente rojo
     */
    public RedOp(int umbral) {
        this.umbral = umbral;
    }

    /**
     * Método para aplicar el filtro de resaltado de rojos
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
        int valor, media;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp); 
                valor = pixelComp[0] - pixelComp[1] - pixelComp[2];
                if(valor < umbral) {
                   media = (pixelComp[0] + pixelComp[1] + pixelComp[2])/3;
                   pixelCompDest[0] = pixelCompDest[1] = pixelCompDest[2] = media;
                } else {
                    pixelCompDest = pixelComp;
                }
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }
}
