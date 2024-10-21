package designpatterns.structural.facade;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class ComputerDemo {
/*

    The goal of the Facade Pattern is to make complex systems easier to use by providing a single,
    high-level interface to perform tasks.

    Clients interact with the facade rather than interacting with individual classes of the subsystem directly.
    The facade coordinates and delegates the requests to the appropriate subsystem classes.

    Simplifies Usage: It simplifies the client’s interaction with a complex system by exposing only the necessary functionality.
    Decoupling: It decouples clients from the implementation details of the subsystem. Clients don’t need to know how the system works internally.

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
