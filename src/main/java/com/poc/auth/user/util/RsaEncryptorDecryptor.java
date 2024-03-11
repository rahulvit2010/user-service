package com.poc.auth.user.util;

import com.poc.auth.user.enums.UserAuthenticationEnum;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public final class  RsaEncryptorDecryptor {

  private RsaEncryptorDecryptor(){
    }

  public static String encrypt(String message, String publicKeyString) throws Exception {
    byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString.getBytes());
    KeyFactory keyFactory = KeyFactory.getInstance(UserAuthenticationEnum.RSA.getValue());
    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
    PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
    Cipher cipher = Cipher.getInstance(UserAuthenticationEnum.RSA_PADDING.getValue());
    cipher.init(1, publicKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
  }

  public static String decrypt(String encryptedMessage, String privateKeyString) throws Exception {
    byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString.getBytes());
    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(UserAuthenticationEnum.RSA.getValue());
    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
    Cipher cipher = Cipher.getInstance(UserAuthenticationEnum.RSA_PADDING.getValue());
    cipher.init(2, privateKey);
    return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)), StandardCharsets.UTF_8);
  }

}
