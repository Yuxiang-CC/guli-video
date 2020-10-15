package com.yuxiang.guli.service.ucenter;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author: Yuxiang
 * @create: 2020-06-28
 **/
public class JWTTest {


    // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxIiwic3ViIjoiZ3VsaS11c2VyIiwiaWF0IjoxNTkzMzIxNjY2LCJleHAiOjE1OTMzMjM0NjYsImlkIjoiMTgzMzMzMzMzMzMiLCJuaWNrTmFtZSI6IuW8oOS4iSIsImF2YXRhciI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9mYXZhZmEuaWNvIn0.haM4XI-J0z0bT1nM2WHwWKaH32Z2xnySlr1bd6pg8RA

    @Test
    public void jew() {

        JwtBuilder builder = Jwts.builder();

        // 第一部分： JWT头 header
        builder.setHeaderParam("alg", "HS256"); // 签名算法
        builder.setHeaderParam("typ", "JWT"); // 令牌类型

        // 第二部分： JWT有效载荷 playod
        // 默认字段 7个
        builder.setId("1"); // 唯一身份标识
        builder.setSubject("guli-user"); // 令牌主题
        builder.setIssuedAt(new Date()); // 令牌签发时间
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)); // 令牌过期时间
        // 定义私有字段 claim()方法
        builder.claim("id", "18333333333");
        builder.claim("nickName", "张三");
        builder.claim("avatar", "http://localhost:8080/favafa.ico");

        // 第三部分： 签名哈希
        builder.signWith(SignatureAlgorithm.HS256, "yuxiang");

        // 将三部分连接起来
        String jwtCompact = builder.compact();

        System.out.println(jwtCompact);

    }

    @Test
    public void checkJWT() {
        JwtParser parser = Jwts.parser();
        parser.setSigningKey("yuxiang");
        Jws<Claims> claimsJws = parser.parseClaimsJws("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxIiwic3ViIjoiZ3VsaS11c2VyIiwiaWF0IjoxNTkzMzIxNjY2LCJleHAiOjE1OTMzMjM0NjYsImlkIjoiMTgzMzMzMzMzMzMiLCJuaWNrTmFtZSI6IuW8oOS4iSIsImF2YXRhciI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9mYXZhZmEuaWNvIn0.haM4XI-J0z0bT1nM2WHwWKaH32Z2xnySlr1bd6pg8RA");

        JwsHeader header = claimsJws.getHeader();
        System.out.println(claimsJws.getBody());

    }
}
