package com.whu.sres.lhw.controller;

import com.whu.sres.lhw.bean.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description:
 * Created by lvhw on 2019/3/10 22:00.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private String path = "d:\\";

    @PostMapping(value = "upload")
    public FileInfo upload(MultipartFile file) throws Exception {

        File localFile = new File(path, file.getOriginalFilename());

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try (InputStream inputStream = new FileInputStream(new File(path, id + ".jpg"));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            throw new RuntimeException("下载文件失败");
        }
    }
}
