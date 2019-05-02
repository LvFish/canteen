package com.test.controller;

import com.test.dao.AnnounceDao;
import com.test.dao.ClassifyDao;
import com.test.dao.FoodDao;
import com.test.entity.AnnounceEntity;
import com.test.entity.ClassifyEntity;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujiawang on 2019/4/30.
 */
@Controller
public class AnnounceController {
    @Autowired
    private AnnounceDao announceDao;

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private ClassifyDao classifyDao;

    @ResponseBody
    @RequestMapping(value="/user/announce",method = RequestMethod.POST)
    public void getAllAnnounce(HttpServletResponse response) throws IOException {
        List<AnnounceEntity> announceEntityList = announceDao.findAll();
        List<Object> list = new ArrayList();
        for(int i=0;i<announceEntityList.size();i++){
            Map<String,String> map = new HashMap<String,String>();
            map.put("detail",announceEntityList.get(i).getAnnounceDetail());
            FoodEntity food  = foodDao.queryByFoodId(announceEntityList.get(i).getFoodId());
            map.put("foodName",food.getFoodName());
            map.put("foodPrice",food.getFoodPrice().toString());
            map.put("foodId",String.valueOf(food.getFoodId()));
            map.put("foodUrl",food.getFoodUrl());
            list.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("msg",list);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/user/classify",method = RequestMethod.POST)
    public void getAllClassify(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<ClassifyEntity> classifyList = classifyDao.queryByClassifyType(Integer.valueOf(typeId));
        List<FoodEntity> foodList = new ArrayList<FoodEntity>();
        for(int i=0;i<classifyList.size();i++){
            foodList.add(foodDao.queryByFoodId(classifyList.get(i).getFoodId()));
        }
        JSONObject json = new JSONObject();
        json.put("foodList",foodList);
//        json.put("classifyList",classifyList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());

    }

    @ResponseBody
    @RequestMapping(value="/manager/classify",method = RequestMethod.POST)
    public void getAnnounce(HttpServletResponse response) throws IOException {
        List<ClassifyEntity> classes = classifyDao.findAll();
        List<Map> list = new ArrayList<Map>();
        for(int i=0;i<classes.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("food",foodDao.queryByFoodId(classes.get(i).getFoodId()));
            map.put("classes",classes.get(i));
            list.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("rows",list);
        json.put("total",list.size());
//        json.put("classifyList",classifyList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/addClassify",method = RequestMethod.POST)
    public void addClassify(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String foodId = request.getParameter("foodId");
        String classifyId = request.getParameter("classifyId");
        String classs[] = classifyId.split("_");
        String foods[] = foodId.split("_");
        ClassifyEntity classes = classifyDao.findByFoodId(Integer.valueOf(foods[1]));
        JSONObject json = new JSONObject();
        if(classes != null){
            json.put("addClassifyMsg","当前菜品已存在");
            json.put("addClassifyStatus",false);
        }else{
            json.put("addClassifyStatus",true);
            classes = new ClassifyEntity();
            classes.setClassifyType(Integer.valueOf(classs[1]));
            classes.setFoodId(Integer.valueOf(foods[1]));
            classifyDao.save(classes);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }


    @ResponseBody
    @RequestMapping(value="/classify/deleteClassify",method = RequestMethod.POST)
    public void deleteClassify(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        String strs[] = ids.split(",");
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()>0){
                classifyDao.delete(Integer.valueOf(strs[i]));
            }
        }
    }

    @ResponseBody
    @RequestMapping(value="/manager/editClassify",method = RequestMethod.POST)
    public void editClassify(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String classifyId = request.getParameter("classifyId");
        String typeId = request.getParameter("typeId").split("_")[1];
        ClassifyEntity classifyEntity = classifyDao.findByClassifyId(Integer.valueOf(classifyId));
        classifyEntity.setClassifyType(Integer.valueOf(typeId));
        classifyDao.save(classifyEntity);
        JSONObject json = new JSONObject();
        json.put("msg",true);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/manager/getAnnounce",method = RequestMethod.POST)
    public void managerGetAllAnnounce(HttpServletResponse response) throws IOException {
        List<AnnounceEntity> announceEntityList = announceDao.findAll();
        List<Object> list = new ArrayList();
        for(int i=0;i<announceEntityList.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("detail",announceEntityList.get(i).getAnnounceDetail());
            FoodEntity food  = foodDao.queryByFoodId(announceEntityList.get(i).getFoodId());
            map.put("foodName",food.getFoodName());
            map.put("foodPrice",food.getFoodPrice().toString());
            map.put("foodId",String.valueOf(food.getFoodId()));
            map.put("foodUrl",food.getFoodUrl());
            map.put("announceId",announceEntityList.get(i).getAnnounceId());
            list.add(map);
        }
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
    @RequestMapping(value="/manager/editAnnounce",method = RequestMethod.POST)
    public void managerEditAnnounce(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId").split("_")[1];
        String detail = request.getParameter("detail");
        AnnounceEntity announceEntity = announceDao.findByAnnounceDetail(detail);
        announceEntity.setFoodId(Integer.valueOf(typeId));
        announceDao.save(announceEntity);
        JSONObject json = new JSONObject();
        json.put("announceMsg",true);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        // System.out.println(js);
        response.getWriter().write(js.toString());
    }


}
