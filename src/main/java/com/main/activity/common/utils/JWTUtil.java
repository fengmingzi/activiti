package com.main.activity.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Author: fengguang xu
 * @Description: JWT工具类
 * @Date: 2019/4/25 17:18
 */
@Slf4j
public class JWTUtil {

    //过期时间24小时
    private static final long EXPIRE_TIME = 24*60*60*1000;
    //密钥
    private static final String SECRET = "shiro+JWT";

    /**
     * @author fengguang xu
     * @description 生成token
     * @date 2019/4/25 17:44
     * @param username	用户名
     * @return java.lang.String
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username", username)
                    //.withClaim("roles", "[user,admin]")
                    //.withClaim("permission", "[add,create,update]")
                    //设置过期时间
                    .withExpiresAt(date)
                    //指定算法
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("JWT生成token异常：", e);
            return null;
        }
    }

    /**
     * @author fengguang xu
     * @description 验证token
     * @date 2019/4/26 9:18
     * @param token	JWT生成的token
     * @param username	用户名
     * @return boolean
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证token
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("JWT验证token异常：", e);
            return false;
        }
    }

    /**
     * @author fengguang xu
     * @description 获得token中的信息，无需secret解密也能获得
     * @date 2019/4/26 9:28
     * @param token	jwt生成的token，里面有username
     * @return java.lang.String
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("获取token中信息解码异常：", e);
            return null;
        }
    }


}
