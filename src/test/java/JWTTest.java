import cn.hutool.core.date.LocalDateTimeUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.util.*;

public class JWTTest {

    public static final String SECRET = "123456";
    public static final String EXP = "exp";

    @Test
    public void test() throws UnsupportedEncodingException {
        String token = JWTTest.sign("123", 3600);
        System.out.println(token);
        Map<String, Claim> claimMap = JWTTest.verifyToken(token);
        System.out.println(claimMap);
    }

    public static String sign(String userId, long expire) throws UnsupportedEncodingException {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Date signDate = new Date();
        Date expireDate = Date.from(LocalDateTimeUtil.of(signDate).plusMinutes(expire).atZone(ZoneId.systemDefault()).toInstant());

        JWTCreator.Builder builder = JWT.create()
                .withHeader(header)
                .withClaim("sub", "Access Token")
                .withClaim("userId", userId)
                .withIssuedAt(signDate)
                .withExpiresAt(expireDate);

        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }

}
