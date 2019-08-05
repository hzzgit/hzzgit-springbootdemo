package com.hzz.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;


/**
 *
 * @author huangLuSen
 * @date 2018年12月19日
 * 
 */
@Component
public class DefaultJwtDecoder implements JwtDecoder{

	private JWTVerifier verifier;
	private String jwtSecret="1PCiNHPvGxSkJSmc1ga90q6FjU2XW4fd";
	
	public DefaultJwtDecoder() {
	}

	@PostConstruct
	public void init(){
		verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
	}

	@Override
	public String getSubject(String token) throws Exception {
		DecodedJWT jwt = verifier.verify(token);
		Date exp = jwt.getExpiresAt();
		if(exp == null || System.currentTimeMillis() > exp.getTime()) {
			throw new Exception("Token已过期！");
		}
		String sub = jwt.getSubject();
		if(StringUtils.hasText(sub)) {
			return sub.trim();
		}else {
			throw new Exception("Token解析失败！");
		}
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
}
