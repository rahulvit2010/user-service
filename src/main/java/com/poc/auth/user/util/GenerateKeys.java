package com.poc.auth.user.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class GenerateKeys {

  private KeyPairGenerator keyGen;
  private PrivateKey privateKey;
  private PublicKey publicKey;
  public GenerateKeys(){}
  private void generateSecureKeys() throws NoSuchAlgorithmException, NoSuchProviderException {
    this.keyGen = KeyPairGenerator.getInstance("RSA");
    this.keyGen.initialize(1024);
  }

  private void createKeys() {
    KeyPair pair = this.keyGen.generateKeyPair();
    this.privateKey = pair.getPrivate();
    this.publicKey = pair.getPublic();
  }

  private PrivateKey getPrivateKey() {
    return this.privateKey;
  }

  private PublicKey getPublicKey() {
    return this.publicKey;
  }

  public void keyGenerateAndReturnPublicKey() {
    String publicKeyPEM = null;
    String privateKeyPEM=null;
    System.out.println("main method of generator");
    try {
      this.generateSecureKeys();
      this.createKeys();

      System.out.println("Public key:" + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
      System.out.println("Private key:" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

}