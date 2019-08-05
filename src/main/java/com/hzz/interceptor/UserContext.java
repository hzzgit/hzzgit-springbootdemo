//package com.hzz.interceptor;
//
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.io.Serializable;
//
//public class UserContext implements Serializable {
//    public static  String  LOGINED_KEY="curuser";
//
//    /**
//     * 获取当前线程绑定的用户登录对象
//     *
//     * @return
//     */
//    public static UserInfo getUserSession() {
//        return (UserInfo) RequestContextHolder.getRequestAttributes().getAttribute(UserContext.LOGINED_KEY,  RequestAttributes.SCOPE_REQUEST);
//    }
//
//    /**
//     * 将用户登录对象绑定到当前线程
//     *
//     * @param loginEntity
//     */
//    public static void setUserSession(UserInfo loginEntity) {
//        RequestContextHolder.getRequestAttributes().setAttribute(UserContext.LOGINED_KEY, loginEntity, RequestAttributes.SCOPE_REQUEST);
//    }
//
//    /**
//     * 将用户登录对象从当前线程销毁
//     */
//    public static void removeUserSession() {
//        RequestContextHolder.getRequestAttributes().removeAttribute(UserContext.LOGINED_KEY,RequestAttributes.SCOPE_REQUEST);
//    }
//
//}
