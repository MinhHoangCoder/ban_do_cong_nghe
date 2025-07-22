/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.QLNVENTITY;

/**
 *
 * @author MSI USER
 */
public class Global {
    private static QLNVENTITY currentUser;
    private static String currentPassword;  // lưu mật khẩu

    // Gọi khi login thành công
    public static void setSession(QLNVENTITY user, String rawPassword) {
        currentUser = user;
        currentPassword = rawPassword;
    }
    
    public static void setCurrentPassword(String PW){
        currentPassword = PW;
    }

    public static QLNVENTITY getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    // Khi logout hoặc đổi user
    public static void clearSession() {
        currentUser = null;
        currentPassword = null;
    }
}
