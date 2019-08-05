//package com.hzz.interceptor;
//
//import java.io.Serializable;
//
//public class Threadlocal implements Serializable {
//    public static  String  LOGINED_KEY="curuser";
//
//    private static ThreadLocal<UserInfo> loginEntityThreadLocal=new ThreadLocal<>();
//
//    public static UserInfo getUserSession() {
//        return loginEntityThreadLocal.get();
//    }
//
//    public static void setUserSession(UserInfo entity) {
//        loginEntityThreadLocal.set(entity);
//    }
//
//    public static void removeUserSession() {
//        loginEntityThreadLocal.remove();
//    }
//}
