package com.test.controller;

import com.test.dao.AppointDao;
import com.test.dao.FoodDao;
import com.test.dao.UserDao;
import com.test.entity.AppointEntity;
import com.test.entity.FoodEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by liujiawang on 2019/4/30.
 */
@Controller
public class AppointController {

    @Autowired
    private AppointDao appointDao;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/user/appoint",method = RequestMethod.POST)
    public void userAppoint(HttpServletRequest request, HttpServletResponse response){
        int foodId = Integer.valueOf(request.getParameter("foodId"));
        int userId = Integer.valueOf(request.getParameter("userId"));
        FoodEntity food = foodDao.queryByFoodId(foodId);
        food.setFoodNumber(food.getFoodNumber()-1);
        AppointEntity appoint = new AppointEntity();
        appoint.setCanteenId(food.getCanteenId());
        appoint.setFoodId(foodId);
        appoint.setUserId(userId);
        appoint.setAppointTime(new Timestamp(System.currentTimeMillis()));
        foodDao.save(food);
        appointDao.save(appoint);
    }

    @ResponseBody
    @RequestMapping(value="/user/appointList",method = RequestMethod.POST)
    public void getAppointList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        List<Map> mapList = new ArrayList<Map>();
        List<AppointEntity> list = appointDao.findByUserIdOrderByAppointTimeDesc(Integer.valueOf(userId));
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("appoint",list.get(i));
            map.put("food",foodDao.queryByFoodId(list.get(i).getFoodId()));
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("list",mapList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/appointList")
    public void getManagerAppoint(HttpServletResponse response) throws IOException {
        List<Map> mapList = new ArrayList<Map>();
        List<AppointEntity> list = appointDao.findAll();
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("appoint",list.get(i));
            map.put("food",foodDao.queryByFoodId(list.get(i).getFoodId()));
            map.put("user",userDao.findByUserId(list.get(i).getUserId()));
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("rows",mapList);
        json.put("total",mapList.size());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/showECharts")
    public void getShowEChars(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        Calendar ca = Calendar.getInstance();
        ca.setTimeInMillis(start);
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Timestamp timestamp = new Timestamp(c.getTimeInMillis());
        List<AppointEntity> list = appointDao.findByAppointTimeGreaterThan(timestamp);
        List<Integer> t1 = new ArrayList<Integer>();
        List<Integer> t2 = new ArrayList<Integer>();
        List<Integer> t3 = new ArrayList<Integer>();
        List<Integer> t4 = new ArrayList<Integer>();
        List<String> t = new ArrayList<String>();
        AppointEntity a;
        for(int i=1;i<=12;i++){
            t.add(i+"个月内");
            ca.setTimeInMillis(start);
            ca.add(Calendar.MONTH,-1);
            long end = ca.getTimeInMillis();
            int a1,a2,a3,a4;
            a1 = a2 = a3 = a4 = 0;
            for(int j=0;j<list.size();j++){
                a = list.get(j);
                if(a.getAppointTime().getTime()>=end&&a.getAppointTime().getTime()<=start){
                    FoodEntity food = foodDao.queryByFoodId(a.getFoodId());
                    if(food.getTypeId()==1){
                        a1++;
                    }else if(food.getTypeId()==2){
                        a2++;
                    }else if(food.getTypeId()==3){
                        a3++;
                    }else if(food.getTypeId()==4){
                        a4++;
                    }
                }
            }
            t1.add(a1);
            t2.add(a2);
            t3.add(a3);
            t4.add(a4);
            start = end;
        }
        JSONObject json = new JSONObject();
        json.put("t",t);
        json.put("t1",t1);
        json.put("t2",t2);
        json.put("t3",t3);
        json.put("t4",t4);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

}
