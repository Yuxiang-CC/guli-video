package com.yuxiang.guli.common.base.util;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author: Yuxiang
 * @create: 2020-06-28
 **/
public class JWTUtils {

    public static final String APP_SECRETKEY = "yuxiang";
    // 令牌有效时间
//    public static final Long EXPIRATIONDATE = 1000 * 60 * 60 * 6L;

    public static String genJwt(String id, String nickName, String avatar, int secret) {

        JwtBuilder builder = Jwts.builder();

        // 第一部分： JWT头 header
        builder.setHeaderParam("alg", "HS256"); // 签名算法
        builder.setHeaderParam("typ", "JWT"); // 令牌类型

        // 第二部分： JWT有效载荷 playod
        // 默认字段 7个
        builder.setId(UUID.randomUUID().toString()); // 唯一身份标识
        builder.setSubject("guli-user"); // 令牌主题
        builder.setIssuedAt(new Date()); // 令牌签发时间
        builder.setExpiration(DateTime.now().plusSeconds(secret).toDate()); // 令牌过期时间
        // 定义私有字段 claim()方法
        builder.claim("id", id);
        builder.claim("nickName", nickName);
        builder.claim("avatar", avatar);

        // 第三部分： 签名哈希
        builder.signWith(SignatureAlgorithm.HS256, JWTUtils.APP_SECRETKEY);

        // 将三部分连接起来
        String jwtCompact = builder.compact();

        return jwtCompact;
    }

    public static boolean checkJWT(String jwtToken) {

        if (StringUtils.isEmpty(jwtToken)) return false;

        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(JWTUtils.APP_SECRETKEY);
            parser.parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean checkJWT(HttpServletRequest request) {

        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) return false;
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(JWTUtils.APP_SECRETKEY);
            parser.parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static JwtInfo getInfoFromJWT(HttpServletRequest request) {

        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return null;
        JwtParser parser = Jwts.parser();
        parser.setSigningKey(JWTUtils.APP_SECRETKEY);
        Claims claims = parser.parseClaimsJws(jwtToken).getBody();

        JwtInfo jwtInfo = new JwtInfo(claims.get("id").toString(), claims.get("nickName").toString(), claims.get("avatar").toString());
        return jwtInfo;
    }

}
