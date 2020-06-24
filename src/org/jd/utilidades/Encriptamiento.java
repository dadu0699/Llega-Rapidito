package org.jd.utilidades;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;

public class Encriptamiento {

    private String llaveSecreta;

    public Encriptamiento() {
        llaveSecreta = "NuestraContrasenia";
    }

    public String encode(String texto) {
        String textoEncriptado = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyPassword = md5.digest(llaveSecreta.getBytes(StandardCharsets.UTF_8));
            byte[] bytesKey = Arrays.copyOf(keyPassword, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes(StandardCharsets.UTF_8);
            byte[] buff = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buff);
            textoEncriptado = new String(base64Bytes);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return textoEncriptado;
    }

    public String decode(String texto) {
        String textoDesencriptado = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(llaveSecreta.getBytes(StandardCharsets.UTF_8));
            byte[] bytesKey = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] message = Base64.decodeBase64(texto.getBytes(StandardCharsets.UTF_8));
            byte[] plainText = decipher.doFinal(message);
            textoDesencriptado = new String(plainText, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return textoDesencriptado;
    }
}
