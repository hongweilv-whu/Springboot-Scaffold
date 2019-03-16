package com.whu.sres.lhw.controller;

import com.whu.sres.lhw.bean.JdbcBean;
import com.whu.sres.lhw.biz.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Description:
 * Created by lvhw on 2019/3/6 8:42.
 */
@Api(value = "Swagger2测试", tags = { "测试接口" })
@RestController
@Slf4j
@RequestMapping(value = "/swagger")
public class Swagger2Controller {

    @Autowired
    private JdbcBean jdbcBean;

    /*@Value("${jdbc.userName}")
    private String userName;*/

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", paramType = "query")
    @GetMapping(value = "getUserInfo")
    public User getUserInfo(int id) {
        User user = new User();
        user.setId(id);
        user.setName("张三111");
        user.setEmail("zhangsan@163.com");
        user.setBirthday(new Date());
        log.info("user info is : {}", user);
        return user;
    }

    @GetMapping(value = "getUserInfo9")
    public User getUserInfo2(int id) {
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setEmail("zhangsan@163.com");
        return user;
    }
}
