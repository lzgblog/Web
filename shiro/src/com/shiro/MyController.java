package com.shiro;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@Autowired
	ServiceTest serviceTest;
	@RequestMapping("/")
	public String login(){
		
		return "login";
	}
	//注解的方式使用权限认证
	@RequestMapping("/test")
	public String test(){
		serviceTest.test();
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam("username") String username,
			@RequestParam("password") String password){
		
        Subject currentUser = SecurityUtils.getSubject();
        //判断是否授权
        if (!currentUser.isAuthenticated()) {
        	UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);//开启记住我
            try {
                currentUser.login(token);//登入验证  token中的数据会在AuthorizingRealm类的子类中进行授权和认证
            }
            catch (AuthenticationException ae) {
                System.out.println("用户："+ae.getMessage());
            }
        }

		return "redirect:/list.jsp";
	}
}
