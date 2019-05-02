package com.test.controller;

import com.test.dao.FoodDao;
import com.test.entity.FoodEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiawang on 2019/4/30.
 */
@Controller
public class SearchFoodController {
    @Autowired
    private FoodDao foodDao;

    @ResponseBody
    @RequestMapping(value="/query/searchFood", method = RequestMethod.POST)
    public void searchFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String foodName = request.getParameter("foodName");
        String canteen = request.getParameter("canteen");
        String type = request.getParameter("type");
        String order = request.getParameter("order");
        FoodEntity food = new FoodEntity();
        if(foodName == null){
            foodName = "";
        }
        food.setFoodName(foodName);
        ExampleMatcher matcher = null;
        if(canteen == null || canteen.isEmpty()){

        }else{
            food.setCanteenId(Integer.valueOf(canteen));
        }
        if(type == null || type.isEmpty()){
        }else{
            food.setTypeId(Integer.valueOf(type));
        }
        if(type == null && canteen == null ){
            System.out.println("2");
            matcher =  ExampleMatcher.matching().withMatcher("foodName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withIgnorePaths("typeId","canteenId","foodId","foodNumber");
        }else if(type == null){
            System.out.println("1.1");
            matcher =  ExampleMatcher.matching().withMatcher("foodName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("canteenId",ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("typeId","foodId","foodNumber");
        }else if(canteen == null){
            System.out.println("1.2");
            matcher =  ExampleMatcher.matching().withMatcher("foodName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("typeId",ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("canteenId","foodId","foodNumber");
        }else{
            System.out.println("0");
            matcher =  ExampleMatcher.matching().withMatcher("foodName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("canteenId",ExampleMatcher.GenericPropertyMatchers.exact())
                    .withMatcher("typeId",ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("foodId","foodNumber");
        }
        Example<FoodEntity> example = Example.of(food,matcher);
        List<FoodEntity> foodList = new ArrayList<FoodEntity>();
        int t = 0;
        if(order != null && !order.isEmpty()){
            t = Integer.valueOf(order);
        }

        Sort sort = new Sort(Sort.Direction.ASC, "foodPrice");
        if (t == 0) {
            foodList = foodDao.findAll(example);
        }else if(t==1){
            foodList = foodDao.findAll(example,sort);
        }else if(t==2){
            sort = new Sort(Sort.Direction.DESC,"foodPrice");
            foodList = foodDao.findAll(example,sort);
        }
        JSONObject json = new JSONObject();
        json.put("foodList",foodList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());

    }
}
