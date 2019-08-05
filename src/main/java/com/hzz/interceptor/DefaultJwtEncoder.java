package com.hzz.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 *
 * @author huangLuSen
 * @date 2019年1月9日
 *
 */
public class DefaultJwtEncoder implements JwtEncoder {

	private Algorithm algorithm;
	//默认1个小时，60分钟
	private int jwtExpireMinute = 60;
	private String jwtSecret;
	
	public DefaultJwtEncoder() {
	}

	@PostConstruct
	public void init(){
		this.algorithm = Algorithm.HMAC256(jwtSecret);
	}

	@Override
	public String encodeSubject(String subject) {
		String token = JWT.create().withSubject(subject)
				.withExpiresAt(new Date(System.currentTimeMillis() + jwtExpireMinute * 60000))
				.sign(algorithm);
		return token;
	}

	public int getJwtExpireMinute() {
		return jwtExpireMinute;
	}

	public void setJwtExpireMinute(int jwtExpireMinute) {
		this.jwtExpireMinute = jwtExpireMinute;
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
}
