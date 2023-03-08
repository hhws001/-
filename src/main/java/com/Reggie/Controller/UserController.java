package com.Reggie.Controller;

import com.Reggie.Common.R;
import com.Reggie.Entity.User;
import com.Reggie.Service.UserService;
import com.Reggie.utils.SMSUtils;
import com.Reggie.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        String codeInSession = session.getAttribute(phone).toString();
        //进行验证码的对比（页面提交的验证码和session中保存的验证码）
        if (code != null && code.equals(codeInSession)){
            //如果比对成功，则登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);

            if (user == null){
                //判断当前手机号是否为新用户，如果是新用户则自动完成注入
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            return R.success(user);
        }

        return R.error("登录失败");
    }
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code = String.valueOf(ValidateCodeUtils.generateValidateCode(6));
            log.info("code={}",code);

            //调用阿里云提供的短信服务API完成短信发送
            SMSUtils.sendMessage("瑞吉外卖","SMS_272510865",phone,code);

            //需要将生成的验证码保存到session
            session.setAttribute(phone,code);
            return R.success("短信发送成功");
        }
        return R.error("短信发送失败");
    }


}
