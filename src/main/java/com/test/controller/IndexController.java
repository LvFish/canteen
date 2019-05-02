package com.test.controller;

import com.test.dao.AppointDao;
import com.test.dao.ManagerDao;
import com.test.dao.UserDao;
import com.test.entity.AppointEntity;
import com.test.entity.ManagerEntity;
import com.test.entity.UserEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Controller
public class IndexController {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppointDao appointDao;

    @RequestMapping(value = "index")
    public String index() {
        ManagerEntity managerEntity = managerDao.findByManagerName("root");
        List<ManagerEntity> list = managerDao.findAll();
        System.out.println(list.size());
        return "Hello World!!!"+managerEntity.getManagerPassword();
    }

    @RequestMapping(value = "/manager/index",method = RequestMethod.POST)
    public String managerIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String managerName = request.getParameter("managerName");
        String managerPassword = request.getParameter("managerPassword");
        ManagerEntity managerEntity = managerDao.findByManagerName(managerName);
        if(managerEntity!=null){
            if(managerEntity.getManagerPassword().equals(managerPassword)){
                return "index";
            }
        }
        session.setAttribute("loginMsg","用户名或密码错误");
        return "login";
    }

    @RequestMapping(value = "/manager/index",method = RequestMethod.GET)
    public String managerIndex(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "index";
    }

    @RequestMapping(value = "/manager/managerUser",method = RequestMethod.GET)
    public String managerUser(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "userManager";
    }

    @RequestMapping(value = "/manager/managerAppoint",method = RequestMethod.GET)
    public String managerAppoint(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "managerAppoint";
    }

    @RequestMapping(value = "/manager/managerAnnounce",method = RequestMethod.GET)
    public String managerAnnounce(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "managerAnnounce";
    }

    @RequestMapping(value = "/manager/showECharts",method = RequestMethod.GET)
    public String showECharts(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "showECharts";
    }


    @RequestMapping(value = "/manager/managerHotType",method = RequestMethod.GET)
    public String managerHotType(HttpSession session){
//        session.removeAttribute("loginMsg");
        return "managerHotType";
    }

    @RequestMapping(value = "/manager/login",method = RequestMethod.GET)
    public String managerLogin(HttpSession session){
        session.removeAttribute("loginMsg");
        return "login";
    }


    @ResponseBody
    @RequestMapping(value = "/user/getUserId",method = RequestMethod.POST)
    public void getUserId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String openId = request.getParameter("openId");
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        UserEntity user = userDao.findByUserOpenId(openId);
        if(user==null){
            user = new UserEntity();
            user.setUserOpenId(openId);
            user.setUserInfo("");
            user.setUserNickName(nickName);
            user.setUserAvatarUrl(avatarUrl);
            userDao.save(user);
        }
        user = userDao.findByUserOpenId(openId);
        JSONObject json = new JSONObject();
        json.put("userId",user.getUserId());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/userList",method = RequestMethod.POST)
    public void getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<UserEntity> list = userDao.findAll();
        JSONObject json = new JSONObject();
        json.put("rows",list);
        json.put("total",list.size());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/" +
            "",method = RequestMethod.POST)
    public void getAppointList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<AppointEntity> list = appointDao.findAll();
        JSONObject json = new JSONObject();
        json.put("rows",list);
        json.put("total",list.size());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }
}
