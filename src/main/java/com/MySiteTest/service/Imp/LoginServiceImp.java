package com.MySiteTest.service.Imp;

import com.MySiteTest.dao.UserDao;
import com.MySiteTest.pojo.User;
import com.MySiteTest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginServiceImp implements LoginService {
    @Autowired
    private UserDao userDao;
    @Override
    public String LoginValidate(HttpServletRequest request, HttpServletResponse response, String name, String password,String type, Model model,String dirName) {
        boolean flag1 = name==null||password==null||type==null;
        boolean flag2 = name.trim().equals("")||password.trim().equals("")||type.equals("");
        if(flag1||flag2){
            model.addAttribute("msg","用户名或密码不能都为空！");
            return "loginIn";
        }
        User user = userDao.selectUserByName(name);
        if(user==null||!user.getPassword().equals(password)||!user.getType().equals(type)){
            model.addAttribute("msg","用户名或密码错误");
            return "loginIn";
        }
//        System.out.println("forward:repository");
        return "forward:/"+dirName+"/repository";
    }
}
