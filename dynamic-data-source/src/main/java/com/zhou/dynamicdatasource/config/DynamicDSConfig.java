package com.zhou.dynamicdatasource.config;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/30 11:19 上午
 */
@Configuration
public class DynamicDSConfig {

    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username;

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider(){
        return new AbstractJdbcDataSourceProvider(driverClassName, url, username, password) {
            @Override
            protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
                Map<String, DataSourceProperty> propertyMap = new HashMap<>();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM db_config");
                while (resultSet.next()){
                    String name = resultSet.getString("name");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String url = resultSet.getString("url");
                    String driver = resultSet.getString("driver");
                    DataSourceProperty property = new DataSourceProperty();
                    property.setUsername(username);
                    property.setPassword(password);
                    property.setUrl(url);
                    property.setDriverClassName(driver);
                    propertyMap.put(name, property);
                }
                return propertyMap;
            }
        };
    }
}
