package com.polypro.utils;

import com.polypro.entity.NhanVien;


public class Auth {
    public static NhanVien user = null;//chua login
    public static void clear() {//logout
        Auth.user = null;
    }
    public static boolean isLogin() {//checklogin
        return Auth.user != null;
    }
    public static boolean isManager() {//checktruongphong
        return Auth.isLogin()&& user.isVaiTro();
    }
}
