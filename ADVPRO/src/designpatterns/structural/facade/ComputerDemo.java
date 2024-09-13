package designpatterns.structural.facade;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class ComputerDemo {

/*

Provide a unified interface to a set of interfaces in a subsystem.
Facade Pattern defines a higher-level interface that makes the subsystem easier to use

    In the facade pattern, facade classes are used to provide a single interface to set of classes.
    The facade simplifies a client’s interaction with a complex system by localizing the interactions into a single interface
    As a result, the client can interact with a single object rather than being required to
    interact directly in complicated ways with the objects that make up the subsystem.
*/
    public static void main(String[] args) {
        ComputerSystemFacade computer = new ComputerSystemFacade();


        // User initiates the computer startup process with a single call
        computer.startComputer();
        System.out.println(decryptPassWord("5S1IEdoCOqm/EXpo6DIoNR/9Oq3BfBYhR+09ZiJB71g="));
    }
    public static String decryptPassWord(String encryptedPwd) {
        try {
            // DECODE encryptedPwd String
            BASE64Decoder base64decoder = new BASE64Decoder();
            SecretKey key = makeKey();
            byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encryptedPwd);
            Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
            return bytes2String(plainTextPwdBytes);
        } catch (Exception e) {
            return "";
        }
    }
    private static SecretKey makeKey() throws Exception{
        String key = "OTNMSTESTKEY";
        DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(keySpec);
    }
    private static String bytes2String(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }
}
