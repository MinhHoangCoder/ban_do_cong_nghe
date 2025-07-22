/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
    
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author MSI USER
 */
public class Validator {
    public static boolean isEmailOrPhoneValid(String username) {
        return isEmailValid(username) || isPhoneValid(username);
    }

    private static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isPhoneValid(String phone) {
        String phoneRegex = "^0\\d{9,10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
