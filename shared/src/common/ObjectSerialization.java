/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author frederico
 */
public final class ObjectSerialization {
    
    public static String toString(Serializable obj) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            
            return DatatypeConverter.printBase64Binary(baos.toByteArray());
        } catch (Exception ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (Exception ex) {
                Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    
    public static Serializable fromString(String serObj) {
        try {
            byte [] data = DatatypeConverter.parseBase64Binary(serObj);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Serializable o = (Serializable) ois.readObject();
            ois.close();
            
            return o;
        } catch (Exception ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
