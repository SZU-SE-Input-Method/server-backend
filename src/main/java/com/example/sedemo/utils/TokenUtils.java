package com.example.sedemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtils {
    //token到期时间为 10小时
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    //密钥盐
    private static final String TOKEN_SECRET="ljdyaishijin**3nkjnj??";

    /**
     * 生成token
     * @param username
     * @return
     */
    public static String sign(String username){
        String token = null;
        try{
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (IllegalArgumentException | JWTCreationException je){
        }
        return token;
    }

    /**
     * token验证
     * @param token
     * @return
     */


    public static String verify(String token){
        String username = new String();
        try {
            //创建token验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            username = decodedJWT.getClaim("username").asString();
        }catch (IllegalArgumentException | JWTVerificationException je){
            return null;
        }
        return username;
    }
}
