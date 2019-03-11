package com.whu.sres.lhw.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 邮件工具类
 * Created by lvhw on 2019/3/10 22:41.
 */
@Component
@EnableConfigurationProperties(MailProperties.class)
public class EmailTools {

    private static final String template = "mail.ftl";

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    public void sendMail(String email) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        try {
            // 获取内容
            String text = this.getTextByTemplate(template, map);
            // 发送
            this.send(email, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextByTemplate(String template, Map<String, Object> model) throws Exception {
        return FreeMarkerTemplateUtils
                .processTemplateIntoString(this.freeMarkerConfigurer.getConfiguration().getTemplate(template), model);
    }

    private String send(String email, String text) throws UnsupportedEncodingException, javax.mail.MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        InternetAddress from = new InternetAddress();
        from.setAddress(this.mailProperties.getUsername());
        from.setPersonal("大宏", "UTF-8");
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("SpringBoot 继承Java邮件测试");
        helper.setText(text, true);
        this.javaMailSender.send(message);
        return text;
    }
}