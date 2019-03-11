package com.whu.sres.lhw.email;

import com.whu.sres.lhw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by lvhw on 2019/3/10 22:50.
 */
public class EmailToolsTest extends BaseTest {
    @Autowired
    private EmailTools emailTools;

    @Test
    public void test() {
        this.emailTools.sendMail("1064237234@qq.com");
    }

}