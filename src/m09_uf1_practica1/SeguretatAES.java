package m09_uf1_practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SeguretatAES {

    private final int keySize;
    private final String ALGORITME = "AES";
    private final String ALGORITME2 = "SHA-256";
    Scanner lector = new Scanner(System.in);

    public SeguretatAES(int keySize) {
        this.keySize = keySize;
    }
/*
    public SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                
                KeyGenerator kgen = KeyGenerator.getInstance(ALGORITME);
                kgen.init(keySize);
                sKey = kgen.generateKey();
                
                String contrasenya = lector.nextLine();
                byte[] data = contrasenya.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance(ALGORITME);
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize / 8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }
*/
    public SecretKey generarClau(String pass, int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                //Convertim la cadena en Array de bytes
                byte[] data = pass.getBytes("UTF-8");
                //Creem objecte MEssageDisgest amb algortime SHA-256
                MessageDigest md = MessageDigest.getInstance(ALGORITME);
                //Generem contrasenya de 32 bytes
                byte[] hash = md.digest(data);
                //Retallem la contrasenya al nombre de bytes solicitats a keySize
                byte[] key = Arrays.copyOf(hash, keySize / 8);
                //creem el SecretKey amb la clau obtinguda i el algoritme AES
                sKey = new SecretKeySpec(key, ALGORITME);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                System.err.println("Error generant la clau:" + ex);
            }
        }
        return sKey;
    }

    public static void cifrarFichero(File fichero, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.ENCRYPT_MODE, clave);
        
        FileInputStream fis = new FileInputStream(fichero);
        byte[] buff= new byte[1000];
        while(fis.read(buff, 0, buff.length)!=-1){
            ciph.update(buff, 0, buff.length);
        }
        FileOutputStream fos = new FileOutputStream("textoCodificado.txt");
        fos.write(ciph.doFinal());
    }
}
