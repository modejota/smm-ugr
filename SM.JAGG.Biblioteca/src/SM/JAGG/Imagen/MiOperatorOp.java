package SM.JAGG.Imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase con un nuevo filtro pixel a pixel
 * @author José Alberto Gómez García modej@correo.ugr.es
 */
public class MiOperatorOp extends BufferedImageOpAdapter{
    /**
     * Parametro que regula el funcionamiento del filtro
     */
    private float parametro;
    
    /**
     * Constructor con parámetros
     * @param parametro Valor para regular el funcionamiento del filtro
     */
    public MiOperatorOp(float parametro){
        //this.parametro = parametro / 1000;
        this.parametro = parametro/10;
    }

    /**
     * Método para aplicar el filtro a una imagen
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
        
        //La idea es obtener una imagen tal que:
        //  Esquina superior izquierda: tonos azules
        //  Esquina superior derecha: tonos rojos/rosas
        //  Esquina inferior izquierda: tonos verdes
        //  Esquina inferior derecha: tonos amarillos
        //  A mayor valor del parametro mayor predominancia tendrán los tonos de la esquina inferior derecha
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                
                /*
                pixelCompDest[0] = Math.min((int) ((pixelComp[0] + pixelComp[1] - pixelComp[2]) * x * parametro),255);
                pixelCompDest[1] = Math.min((int) ((pixelComp[0] - pixelComp[1] + pixelComp[2]) * y * parametro),255);
                pixelCompDest[2] = Math.min((int) ((pixelComp[0] - pixelComp[1] - pixelComp[2]) * x * y * parametro),0);
                */
                
                /*
                pixelCompDest[0] = Math.min((int) (pixelComp[1] * parametro),255);
                pixelCompDest[1] = Math.min((int) (pixelComp[2] * parametro),255);
                pixelCompDest[2] = Math.min((int) (pixelComp[0] * parametro),255);
                */
                
                pixelCompDest[0] = Math.min((int) ((pixelComp[1]+pixelComp[0])/2 * parametro),255);
                pixelCompDest[1] = Math.min((int) ((pixelComp[2]+pixelComp[1])/2 * parametro),255);
                pixelCompDest[2] = Math.min((int) ((pixelComp[0]+pixelComp[2])/2 * parametro),255);

                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }
    
}
