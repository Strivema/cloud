package com.ray.cloud.controller;

import com.google.code.kaptcha.Producer;
import com.ray.cloud.core.HttpResult;
import com.ray.cloud.security.JwtAuthenticationToken;
import com.ray.cloud.utils.IOUtils;
import com.ray.cloud.utils.SecurityUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author Ray.Ma
 * @date 2019/8/14 15:08
 */
@RestController
public class LoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("captcha.jpg")
    @SneakyThrows
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute("", text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/login")
    @SneakyThrows
    public HttpResult login(@RequestParam("name") String name,
                            @RequestParam("password") String password,
                            HttpServletRequest request) {
        JwtAuthenticationToken token = SecurityUtils.login(request, name, password, authenticationManager);

        return HttpResult.ok(token);

    }
}
