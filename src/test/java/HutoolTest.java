import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.Test;

public class HutoolTest {

    @Test
    public void Test(){
        System.out.println(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyy-MM-dd HH:mm:ss"));
    }


}
