package com.charge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vincent on 04/09/2018.
 */

//用于标注控制层组件(如struts中的action)，@ResponseBody和@Controller的合集
@RestController
//申明让spring boot自动给程序进行必要的配置，这个配置等同于：
//@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
// springboot默认扫描其同级包名下的所有component
//@ComponentScan(basePackages = {"com.charge"})指定从那个包开始扫描,会往下逐级扫描
//@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
public class ChargeApplication {

//    @RequestMapping("/test")
//    public String home(HttpServletRequest request) {
//        String name = "sb";
//        System.out.println(request.getAttributeNames());
//        return "傻屌 " + name;
//    }

    public static void main(String[] args) {

        SpringApplication.run(ChargeApplication.class, args);
    }
}
