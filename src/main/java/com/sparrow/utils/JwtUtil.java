package com.sparrow.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 21:30
 **/
public class JwtUtil {
	public static final Long JWT_TTL = 60 * 60 * 1000L;
	public static final String JWT_KEY = "sparrow";

	public static String getUUID() {
		String token = UUID.randomUUID().toString().replace("-", "");
		return token;
	}

	/**
	 * 生成jwt
	 * @param subject token这要存放的数据JSON格式
	 * @return
	 */
	public static String createJWT(String subject) {
		JwtBuilder builder = getJWTBuilder(subject, null, getUUID());
		return builder.compact();
	}

	public static String createJWT(String subject, Long ttlMillis) {
		JwtBuilder builder = getJWTBuilder(subject, ttlMillis, getUUID());
		return builder.compact();
	}

	public static JwtBuilder getJWTBuilder(String subject, Long ttlMillis, String uuid) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if (ttlMillis == null) {
			ttlMillis = JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);
		return Jwts.builder()
				.setId(uuid)
				.setSubject(subject)
				.setIssuer("sg") //签发者
				.setIssuedAt(now) //签发时间
				.signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名，第二个参数2为密钥
				.setExpiration(expDate);
	}

	//创建token
	public static String createJWT(String id, String subject, Long ttlMillis) {
		JwtBuilder builder = getJWTBuilder(subject, ttlMillis, id);
		return builder.compact();
	}

	//生成加密后的密钥
	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
		return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	}

	public static Claims parseJWT(String jwt) {
		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
	}
}
