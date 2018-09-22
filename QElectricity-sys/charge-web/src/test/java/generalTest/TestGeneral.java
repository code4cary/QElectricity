package generalTest;

import com.charge.ChargeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vincent on 21/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class TestGeneral {

    @Test
    public void testMothed() {

        //生成uuid
        String uuid = UUID.randomUUID().toString().trim().replace("-", "");


        // 精确到毫秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(new Date().getTime());
        System.out.println("------------------------------------------------");


        // 精确到秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
        System.out.println(new Date().getTime() / 1000);
        System.out.println("------------------------------------------------");


        // 精确到毫秒
        // 获取指定格式的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        // 输出字符串
        System.out.println(df.format(new Date()));
        System.out.println("------------------------------------------------");


        // 获取指定时间Date对象，参数是时间戳，只能精确到秒
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(df2.format(System.currentTimeMillis() / 1000));
        System.out.println("------------------------------------------------");


        df.getCalendar();
        // 获取指定时间的时间戳
        try {
            System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").parse("2017/11/11 11:11:11:111").getTime());
            System.out.println("------------------------------------------------");

        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println(new Date());
    }
}



