package com.whu.sres.lhw.bean;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Description:
 * Created by lvhw on 2019/3/16 16:02.
 */
public class ResultBeanTest {

    @Test
    public void testNormal() {
        ResultBean r1 = ResultBean.success("测试成功.");
        System.out.println(r1);

        Map<String, Integer> map = Maps.newHashMap();
        map.put("name", 11);
        ResultBean r2 = ResultBean.success(map);
        System.out.println(r2);

        try {
            throw new RuntimeException("空指针异常");
        } catch (Exception e) {
            ResultBean r3 = ResultBean.fail(e);
            System.out.println(r3);
        }
    }
}