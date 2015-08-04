package zafu.edu.cn.coreutil;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 * BASE64是为了避免：javax.crypto.IllegalBlockSizeException: last block incomplete in decryption
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-08-04
 * Time: 12:00
 */
public class AESUtil {
    private SecretKeySpec skeySpec;
    private Cipher cipher;

    public AESUtil(byte[] keyraw) throws Exception {
        if (keyraw == null) {
            byte[] bytesOfMessage = "".getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(bytesOfMessage);

            skeySpec = new SecretKeySpec(bytes, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } else {

            skeySpec = new SecretKeySpec(keyraw, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        }
    }

    public AESUtil(String passphrase) throws Exception {
        byte[] bytesOfMessage = passphrase.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        skeySpec = new SecretKeySpec(thedigest, "AES");

        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public AESUtil() throws Exception {
        byte[] bytesOfMessage = "".getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        skeySpec = new SecretKeySpec(thedigest, "AES");

        skeySpec = new SecretKeySpec(new byte[16], "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    /**
     * 加密:先AES再base64
     *
     * @param plaintext
     * @return
     * @throws Exception
     */
    public String encrypt(String plaintext) throws Exception {

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] ciphertext = cipher.doFinal(plaintext.getBytes("UTF-8"));

        return Base64.encodeToString(ciphertext, Base64.DEFAULT);
    }

    /**
     * 解密:先base64再AES
     *
     * @param ciphertext
     * @return
     * @throws Exception
     */
    public String decrypt(String ciphertext) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] plain64text = Base64.decode(ciphertext, Base64.DEFAULT);

        byte[] plaintext = cipher.doFinal(plain64text);

        return new String(plaintext, "UTF-8");
    }

}
