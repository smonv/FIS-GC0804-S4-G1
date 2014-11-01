/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cu Beo
 */
public class ApplicationHelper {

    private static char[] VALID_CHARACTERS = "123456879".toCharArray();

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static Map<String,String> getRequestParameterMap(){
        return getExternalContext().getRequestParameterMap();
    }

    public static void redirect(String externalPath, boolean keepMessage) {
        try {
            ExternalContext ec = getExternalContext();
            ec.getFlash().setKeepMessages(keepMessage);
            ec.redirect(ec.getRequestContextPath() + externalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isLong(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String secureRandomString(int numChars) {
        SecureRandom srand = new SecureRandom();
        Random rand = new Random();
        char[] buff = new char[numChars];

        for (int i = 0; i < numChars; ++i) {
            // reseed rand once you've used up all available entropy bits
            if ((i % 10) == 0) {
                rand.setSeed(srand.nextLong()); // 64 bits of random!
            }
            buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
        }
        return new String(buff);
    }
    
    public static String formatDate(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String formated_date = format.format(date);
        return formated_date;
    }
}
