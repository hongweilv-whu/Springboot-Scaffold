package com.whu.sres.lhw.biz.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Created by lvhw on 2019/3/7 0:06.
 */
@Data
public class User {
    @JSONField(ordinal = 1)
    private Integer id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String email;
    @JSONField(name = "birth", format = "yyyyMMdd HH:mm:ss", ordinal = 4)
    // 通过name对原属性重命名；通过format进行格式化；通过ordinal标识顺序
    private Date birthday;
}
