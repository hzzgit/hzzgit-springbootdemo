package com.hzz.interceptor;

/**
 *
 * @author huangLuSen
 * @date 2018年12月19日
 * 
 */
public interface JwtDecoder {

	String getSubject(String token) throws Exception;
	
}
