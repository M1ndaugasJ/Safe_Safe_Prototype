package sample.ui.Controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.apache.commons.*;



import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;


public class Cryptography {
    private static SecretKeySpec skeySpec;
    private String algorithm;
    public FileInputStream input;
    Cryptography(FileInputStream File) {
        try {
            ClassPathResource res = new ClassPathResource("key.key");
            if(res != null){
                File file = res.getFile();
                FileInputStream input = new FileInputStream(file);
                byte[] in = new byte[(int)file.length()];
                input.read(in);
                skeySpec = new SecretKeySpec(in, "AES");
                input.close();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlgorithm(String algorythm){
        this.algorithm = algorithm;
    }

    public byte[] encryptFile() throws GeneralSecurityException, NoSuchPaddingException, IOException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] bytes =  new byte[(int)input.read()];
        return cipher.doFinal(bytes);
    }

    public String decryptFile() throws GeneralSecurityException, NoSuchPaddingException, IOException {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte bytes[] = new byte[(int)input.read()];
            return new String(cipher.doFinal(bytes));
        }
}
