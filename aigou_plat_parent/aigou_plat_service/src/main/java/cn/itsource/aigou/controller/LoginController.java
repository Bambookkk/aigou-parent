package cn.itsource.aigou.controller;

import cn.itsource.aigou.commom.AjaxResult;
import cn.itsource.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody User user){
        System.out.println(user);
        if(user!=null&& !StringUtils.isEmpty(user.getName())&&!StringUtils.isEmpty(user.getPassword())){
            if("admin".equals(user.getName())&&"admin".equals(user.getPassword())){
                return AjaxResult.me().setMsg("登录成功");
            }
        }
        return AjaxResult.me().setMsg("登录失败").setSuccess(false);
    }

}
