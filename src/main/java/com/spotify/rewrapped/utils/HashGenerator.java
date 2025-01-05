package com.spotify.rewrapped.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

  public static String generateHash(String input) {
    try {
      // Get the MessageDigest instance for SHA-256
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

      // Generate the hash bytes
      byte[] hashBytes = messageDigest.digest(input.getBytes());

      // Convert the byte array into a hexadecimal string
      StringBuilder hexString = new StringBuilder();
      for (byte b : hashBytes) {
        hexString.append(String.format("%02x", b));
      }

      // Return the resulting hash
      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
