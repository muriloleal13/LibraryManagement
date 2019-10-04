/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author musle
 */
public class CodificaSenha {

    public String CodificaSenha(String senha) {
        String senhaHex = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder();
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            
            senhaHex = sb.toString();
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhaHex;
    }
    
    
}
