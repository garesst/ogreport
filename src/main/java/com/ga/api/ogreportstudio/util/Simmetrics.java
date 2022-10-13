package com.ga.api.ogreportstudio.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Simmetrics {
    private SecretKey key;
    private Cipher cipher;
    private String algoritmo= "AES";
    private int keysize=16;

    public void addKey( String value ){
        byte[] valuebytes = value.getBytes();
        key = new SecretKeySpec( Arrays.copyOf( valuebytes, keysize ) , algoritmo );
    }


    public String encriptar( String texto ){
        String value="";
        try {
            cipher = Cipher.getInstance( algoritmo );
            cipher.init( Cipher.ENCRYPT_MODE, key );
            byte[] textobytes = texto.getBytes();
            byte[] cipherbytes = cipher.doFinal( textobytes );
            value = Base64.getEncoder().encodeToString(cipherbytes);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        } catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return value;
    }

    public Integer indexKey(String id){
        char[] num =id.toCharArray();
        for (char c: num
             ) {
            if(Character.isDigit(c)){
                return Integer.parseInt(String.valueOf(c));
            }

        }
        return 1;
    }


    public String desencriptar( String texto ){
        String str="";
        try {
            byte[] value = Base64.getDecoder().decode(texto);
            cipher = Cipher.getInstance( algoritmo );
            cipher.init( Cipher.DECRYPT_MODE, key );
            byte[] cipherbytes = cipher.doFinal( value );
            str = new String( cipherbytes );
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        }  catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return str;
    }
}
