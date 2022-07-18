package SM.JAGG.Imagen;

import java.awt.image.ByteLookupTable;
import java.awt.image.LookupTable;
import sm.image.LookupTableProducer;

/**
 * Clase para aplicar una función propia a una imagen. Hereda del generador de LookupTable del paquete sm.image 
 * @author José Alberto Gómez García modej@correo.ugr.es
 */


public class MiLookupOp extends LookupTableProducer {
    
    /**
     * Crea una nueva LookupTable con la función a aplicar sobre la imagen
     * @param parametro Divisor de la arcotangente
     * @return LookupTable con la función a aplicar
     */
    public static LookupTable createLookupTablePropia(int parametro){
        byte lt[] = new byte[256];
        double k = 255f/Math.atan(255f/(float)parametro);
        for(int i = 0; i < 256; i++) {
            float valor = (float) i / (float) parametro;
            lt[i] = (byte) (Math.atan(valor) * k);
        }
        LookupTable tabla = new ByteLookupTable(0,lt);
        return tabla;
    }
}
