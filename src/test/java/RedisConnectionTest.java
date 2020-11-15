import com.tinyplan.exam.common.properties.DBProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RedisConnectionTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DBProperties dbProperties;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("mike", "132");
        System.out.println(dbProperties);
    }

}
