package com.test.controller;

import com.test.dao.CanteensDao;
import com.test.dao.FoodDao;
import com.test.dao.FoodDetail;
import com.test.dao.FoodTypeDao;
import com.test.entity.CanteensEntity;
import com.test.entity.FoodEntity;
import com.test.entity.FoodtypeEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Controller
public class FoodController {

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private CanteensDao canteensDao;

    @Autowired
    private FoodTypeDao foodTypeDao;

    @ResponseBody
    @RequestMapping(value = "/food/queryFood",method = RequestMethod.POST)
    public void queryFood( HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<FoodEntity> foodList = foodDao.findAll();
        List<FoodDetail> foodDetails = new ArrayList<FoodDetail>();
        FoodDetail foodDetail = new FoodDetail();
        FoodEntity food = new FoodEntity();
        for(int i=0;i<foodList.size();i++){
            food = foodList.get(i);
            foodDetail = new FoodDetail(food);
            foodDetail.setCanteenName(canteensDao.queryByCanteenId(food.getCanteenId()).getCanteenName());
            foodDetail.setTypeName(foodTypeDao.queryByTypeId(food.getTypeId()).getTypeName());
            foodDetails.add(foodDetail);
        }
        json.put("rows",foodDetails);
        json.put("total",foodDetails.size());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
       // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/queryCanteens" ,method = RequestMethod.POST)
    public void queryCanteens(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<CanteensEntity> canteensEntities = canteensDao.findAll();
        json.put("canteenList",canteensEntities);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/queryTypes" ,method = RequestMethod.POST)
    public void queryTypes(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<FoodtypeEntity> typeList = foodTypeDao.findAll();
        json.put("typeList",typeList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
       // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/editFoodWithFile",method = RequestMethod.POST)
    public void editFoodWithFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        try {
            FoodEntity food = new FoodEntity();
            food.setFoodName(request.getParameter("foodName"));
            food.setFoodId(Integer.valueOf(request.getParameter("foodId")));
            food.setCanteenId(Integer.valueOf(request.getParameter("canteenId").split("_")[1]));
            food.setFoodDetail(request.getParameter("foodDetail"));
            food.setFoodNumber(Integer.valueOf(request.getParameter("foodNumber")));
            food.setFoodPrice(new BigDecimal(request.getParameter("foodPrice")));
            food.setTypeId(Integer.valueOf(request.getParameter("typeId").split("_")[1]));
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;
            File temFile = new File("");
            String filePath = temFile.getAbsolutePath();
            filePath = filePath + File.separatorChar+"src"+File.separatorChar+"main"+File.separatorChar+"resources"
                    +File.separatorChar+ "manager" +File.separatorChar+ fileName;
            System.out.println(filePath);
            file.transferTo(new File(filePath));
            food.setFoodUrl(fileName);
            foodDao.save(food);
            json.put("addFoodMsg",true);
        }catch (Exception e){
            e.printStackTrace();
            json.put("addFoodMsg",false);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
       // System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/editFoodWithOutFile",method = RequestMethod.POST)
    public void editFoodWithOutFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        try {
            FoodEntity food = new FoodEntity();
            food = foodDao.queryByFoodId(Integer.valueOf(request.getParameter("foodId")));
            food.setFoodName(request.getParameter("foodName"));
            food.setFoodId(Integer.valueOf(request.getParameter("foodId")));
            food.setCanteenId(Integer.valueOf(request.getParameter("canteenId").split("_")[1]));
            food.setFoodDetail(request.getParameter("foodDetail"));
            food.setFoodNumber(Integer.valueOf(request.getParameter("foodNumber")));
            food.setFoodPrice(new BigDecimal(request.getParameter("foodPrice")));
            food.setTypeId(Integer.valueOf(request.getParameter("typeId").split("_")[1]));
            foodDao.save(food);
            json.put("addFoodMsg",true);
        }catch (Exception e){
            e.printStackTrace();
            json.put("addFoodMsg",false);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/addFood",method = RequestMethod.POST)
    public void addFood(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        try {
            FoodEntity food = new FoodEntity();
            food.setFoodName(request.getParameter("foodName"));

            food.setCanteenId(Integer.valueOf(request.getParameter("canteenId").split("_")[1]));
            food.setFoodDetail(request.getParameter("foodDetail"));
            food.setFoodNumber(Integer.valueOf(request.getParameter("foodNumber")));
            food.setFoodPrice(new BigDecimal(request.getParameter("foodPrice")));
            food.setTypeId(Integer.valueOf(request.getParameter("typeId").split("_")[1]));
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;
            File temFile = new File("");
            String filePath = temFile.getAbsolutePath();
            filePath = filePath + File.separatorChar+"src"+File.separatorChar+"main"+File.separatorChar+"resources"
                    +File.separatorChar+ "manager" +File.separatorChar+ fileName;
            System.out.println(filePath);
            file.transferTo(new File(filePath));
            food.setFoodUrl(fileName);
            foodDao.save(food);
            json.put("addFoodMsg",true);
        }catch (Exception e){
            e.printStackTrace();
            json.put("addFoodMsg",false);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/food/deleteFood",method = RequestMethod.POST)
    public void deleteFood(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        String strs[] = ids.split(",");
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()>0){
                foodDao.delete(Integer.valueOf(strs[i]));
            }
        }
    }

    @ResponseBody
    @RequestMapping(value="/food/foodDetail" ,method = RequestMethod.POST)
    public void foodDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String  id = request.getParameter("foodId");
        FoodEntity food = foodDao.queryByFoodId(Integer.valueOf(id));
        JSONObject json = new JSONObject();
        json.put("food",food);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

}
