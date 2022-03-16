package com.artbyte.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class PBKDF2Encoder  {

    @ConfigProperty(name = "com.artbyte.password.secret")
    private String secret;

    @ConfigProperty(name = "com.artbyte.password.iteration")
    private Integer iteration;

    @ConfigProperty(name = "com.artbyte.password.keylength")
    private Integer keylength;
    
    public String encode(CharSequence cs){
        try{
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(
                    cs.toString().toCharArray(),
                    secret.getBytes(),
                     iteration, keylength))
                    .getEncoded();

            return Base64.getEncoder().encodeToString(result);
            
        }catch(NoSuchAlgorithmException | InvalidKeySpecException ex){
            throw new RuntimeException(ex);
        }
    }
}
