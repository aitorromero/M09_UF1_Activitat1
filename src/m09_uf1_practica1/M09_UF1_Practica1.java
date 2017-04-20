package m09_uf1_practica1;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class M09_UF1_Practica1 {

    public static void main(String[] args) {
        try {
            /*
            int keySize = 0;
            String key;
            while (keySize != 128 && keySize != 192 && keySize != 256) {
                System.out.println("Introdueix el tamany de la clau (128, 192 o 256): ");
                Scanner sc = new Scanner(System.in);
                keySize = sc.nextInt();
            }
            
            System.out.println("Introdueix la clau del algoritme SHA: ");
            Scanner sc = new Scanner(System.in);
            key = sc.next();
            */
            SeguretatAES sg = new SeguretatAES(256);
            System.out.println("");
            
            SecretKey k = sg.generarClau("alejandroelmagno", 256);
            
            sg.cifrarFichero("texto.txt", k);
            sg.descifrarFichero(k);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(M09_UF1_Practica1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
