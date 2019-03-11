package com.whu.sres.lhw.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by lvhw on 2019/3/8 0:19.
 */
@Component
@Data
@ConfigurationProperties(prefix = "jdbc")
public class JdbcBean {

    private String url;

    private String driver;

    private String userName;

    private String passWord;
}
