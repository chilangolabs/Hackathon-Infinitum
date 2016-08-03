package com.chilangolabs.reporteciudadano.singleton;

public class UserSingleton {

    public static String name;
    public static String mail;
    public static String img;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserSingleton.name = name;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        UserSingleton.mail = mail;
    }

    public static String getImg() {
        return img;
    }

    public static void setImg(String img) {
        UserSingleton.img = img;
    }
}
