import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class EncryptTest {
    // 秘钥, 不要轻易改变
    public static final String SECRET_KEY = SecureUtil.md5("189050934");
    public static final AES ENCRYPT_AES =
            SecureUtil.aes(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    @Test
    public void test(){
        System.out.println(SecureUtil.md5("123456"));

        /*String data = "徐哲扬";
        String s = encryptByAES(data);
        System.out.println(s);
        System.out.println(decryptByAES(s));*/

    }

    /**
     * 对称加密AES
     *
     * @param rawData 原始内容
     * @return 加密后的16进制内容
     */
    public static String encryptByAES(String rawData){
        return ENCRYPT_AES.encryptHex(rawData, CharsetUtil.CHARSET_UTF_8);
    }

    public static String decryptByAES(String secretData){
        return ENCRYPT_AES.decryptStr(secretData, CharsetUtil.CHARSET_UTF_8);
    }

}
