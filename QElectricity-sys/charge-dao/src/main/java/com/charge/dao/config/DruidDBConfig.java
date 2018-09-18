package com.charge.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @since: JDK1.7
 * @description: druid连接池配置
 */

//@Slf4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象、
@Slf4j

//@Data : 注解在类上,自动生成set/get方法，toString方法，equals方法，
// hashCode方法，不带参数的构造方法
@Data

//@Configuration可以注解一个配置类，但是它需要为每个属性再次声明绑定的字段，
// 不像ConfigurationProperties,所以推荐使用@ConfigurationProperties注解。
@Configuration

//PropertySource注解加载指定的属性文件
@PropertySource(value = "classpath:jdbc.properties")

//@ConfigurationProperties注解的作用是可以根据一个前缀将配置文件的属性
// 映射成一个POJO实体类，只要属性名一致就能自动注入进去，使用起来非常方便
@ConfigurationProperties(prefix = "spring.datasource.druid")

//在 SpringBootApplication 上使用@ServletComponentScan 注解后，
// Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、
// @WebListener 注解自动注册，无需其他代码。
//所以@ServletComponentScan在這裡是什麼意思?
@ServletComponentScan
public class DruidDBConfig {

    @Value("${spring.datasource.ams.url}")
    private String dbUrl1;

    @Value("${spring.datasource.ams.username}")
    private String username1;

    @Value("${spring.datasource.ams.password}")
    private String password1;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private int timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    private String filters;
    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    private String connectionProperties;

    @Bean(name = "dataSource")
    @Qualifier("dataSource")
    public DataSource dataSource() {
        log.info("数据库连接属性【 user = {}, passwd = {} ,url = {} 】", username1, password1, dbUrl1);
        return getDruidDataSource(username1, password1, dbUrl1);
    }


    private DruidDataSource getDruidDataSource(String username, String password, String url) {
        DruidDataSource datasource = new DruidDataSource();

        try {
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            //configuration
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

            datasource.setFilters(filters);
            datasource.setConnectionProperties(connectionProperties);
            datasource.init();
        } catch (SQLException e) {
            log.error("druid configuration initialization filter : {0}", e);
        }
        return datasource;
    }
}  