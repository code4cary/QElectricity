package com.charge.dao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @since: JDK1.7
 * @description: 内部数据源，通过sharding插件进行分表
 */

@Configuration
//通过使用@MapperScan可以指定扫描的mapper接口所在包的路径
//basePackages表明mapper接口所在包的路径
//sqlSessionTemplateRef表明接口类使用的SqlSessionTemplate
@MapperScan(basePackages = "com.charge.dao.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {

    /**
     *
     * @param dataSource
     * @return
     * @throws Exception
     *
     * @description  根据数据源生成SqlSessionFactory,值得注意的是，数据源是必须指定的，否则springboot启动不了
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory cmsSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager cmsTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate cmsSqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
