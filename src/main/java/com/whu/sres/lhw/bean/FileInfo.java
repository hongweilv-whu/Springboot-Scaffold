package com.whu.sres.lhw.bean;

import lombok.Data;

/**
 * Description:
 * Created by lvhw on 2019/3/10 22:01.
 */
@Data
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
