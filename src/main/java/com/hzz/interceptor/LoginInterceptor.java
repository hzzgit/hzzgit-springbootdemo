//package com.hzz.interceptor;
//
//import net.fxft.cloud.redis.RedisUtil;
//import net.fxft.common.jdbc.JdbcUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class LoginInterceptor  implements HandlerInterceptor {
//	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
//	private static final String[] IGNORE_URI = {"/login.action","/login2.action", "/logout.action", "/randomPicture.action"};
//
//	public static final String ONLINE_USER = "currUserInfo";
//	private static final String LOGIN_URL = "/logout.action";
//	@Autowired
//	private JwtDecoder jwtDecoder;
//
//	private boolean autoLogin = false;
//
//
//	@PostConstruct
//	public void init() {
//		log.error("---jwtSecret---"+jwtDecoder);
//	}
//
//	public boolean isAutoLogin() {
//		return autoLogin;
//	}
//
//	public void setAutoLogin(boolean autoLogin) {
//		this.autoLogin = autoLogin;
//	}
//
//
//	public boolean preHandle(HttpServletRequest request,
//							 HttpServletResponse response, Object handler) throws Exception {
//
//		if(request.getMethod().equalsIgnoreCase("OPTIONS")){
//			return true;
//		}
//
//		String url = request.getRequestURL().toString();
//		for (String s : IGNORE_URI) {
//			if (url.contains(s)) {
//				return true;
//			}
//		}
//		String origin = request.getHeader("Origin");
//		UserInfo user=new UserInfo();
//		if (url.indexOf(".action") > 0) {
//
//
//				String token = request.getHeader("Authorization");
//				if (token == null) {
//					String querystring = request.getQueryString();
//					if (querystring != null) {
//						int i = querystring.indexOf("access_token=");
//						if (i != -1) {
//							int j = querystring.indexOf("&", i);
//							if (j == -1) {
//								token = querystring.substring(i + 13);
//							} else {
//								token = querystring.substring(i + 13, j);
//							}
//						}
//					}
//				}
//				if (token != null) {
//					try {
//						String subject = jwtDecoder.getSubject(token);
//
//						UserInfo userinfo =new UserInfo();
//
//					} catch (Exception e) {
//						log.error("jwt解析异常！", e);
//						response.setHeader("Access-Control-Allow-Origin", origin);
//						response.setHeader("Access-Control-Allow-Headers", "*");
//						response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
//						response.setHeader("Access-Control-Allow-Credentials", "true");
//						throw new SessionTimeoutException();
//					}
//				}
//			}else{
//			String co=request.getParameter("co");
//			UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Threadlocal.LOGINED_KEY);
//
//			if("1".equals(co)){
//				user.setEntityId(1);
//				request.getSession().setAttribute(Threadlocal.LOGINED_KEY,user);
//				Threadlocal.setUserSession(user);
//			}else if ("2".equals(co)){
//				user.setEntityId(2);
//				request.getSession().setAttribute(Threadlocal.LOGINED_KEY,user);
//				Threadlocal.setUserSession(user);
//			}
//		}
//
//			if (user == null) {
//
//				response.setHeader("Access-Control-Allow-Origin", origin);
//				response.setHeader("Access-Control-Allow-Headers", "*");
//				response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
//				response.setHeader("Access-Control-Allow-Credentials", "true");
//
//				//如果判断是 AJAX 请求,直接设置为session超时
//				if (request.getHeader("x-requested-with") != null
//						&& request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
//
////					response.setHeader("sessionstatus", "timeout");
////					response.sendError(518, "session timeout.");
////					return false;
//					throw new SessionTimeoutException();
//				} else {
//					if(origin != null){
////						response.setHeader("sessionstatus", "timeout");
////						response.sendError(518, "session timeout.");
//						throw new SessionTimeoutException();
//					}else {
//					    response.sendRedirect(request.getContextPath() + LOGIN_URL);
//					}
//					return false;
//				}
//			}
//
//		response.setHeader("Access-Control-Allow-Origin", origin);
//		response.setHeader("Access-Control-Allow-Headers", "*");
//		response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		return true;
//	}
//
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//    }
//
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//    }
//
//
//
//}
