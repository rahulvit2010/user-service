package com.poc.auth.user.controller;

import com.poc.auth.user.util.GenerateKeys;
import com.poc.auth.user.util.RsaEncryptorDecryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/keys")
public class GenerateKeyController {

  @Value("${rsa.encryption.secret-key.public-key}")
  private String publicKey;

  @Value("${rsa.decryption.secret-key.private-key}")
  private String privateKey;

  @Autowired
  private GenerateKeys generateKeys;

  @GetMapping
  public void generateKeys() {
    generateKeys.keyGenerateAndReturnPublicKey();
  }

  @GetMapping("/encrypt-decrypt")
  public ResponseEntity<?> encrypt(
      @RequestParam(value = "message", required = true) String message) {
    try {
      Map<String, String> data = new HashMap<>();
      String encryptedString = RsaEncryptorDecryptor.encrypt(message, publicKey);
      String decryptedString = RsaEncryptorDecryptor.decrypt(encryptedString, privateKey);
      data.put("ENCRYPTED_DATA", encryptedString);
      data.put("DECRYPTED_DATA", decryptedString);
      return ResponseEntity.status(HttpStatus.OK).body(data);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

  }

}
