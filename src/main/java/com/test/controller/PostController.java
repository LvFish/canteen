package com.test.controller;

import com.test.dao.EvaluationDao;
import com.test.dao.PostDao;
import com.test.dao.UserDao;
import com.test.entity.EvaluationEntity;
import com.test.entity.PostEntity;
import com.test.entity.UserEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by liujiawang on 2019/5/1.
 */
@Controller
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EvaluationDao evaluationDao;

    @ResponseBody
    @RequestMapping(value = "/user/postList",method = RequestMethod.POST)
    public void getPostList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sort sort = new Sort(Sort.Direction.DESC, "postTime");
        List<PostEntity> postList = postDao.findAll(sort);
        List<Map> list = new ArrayList<Map>();
        for(int i=0;i<postList.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("post",postList.get(i));
            map.put("user",userDao.findByUserId(postList.get(i).getUserId()));
            list.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("posts",list);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/user/postDetail",method = RequestMethod.POST)
    public void getPostDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String postId = request.getParameter("postId");
        PostEntity post = postDao.findByPostId(Integer.valueOf(postId));
        UserEntity user = userDao.findByUserId(post.getUserId());
        JSONObject json = new JSONObject();
        json.put("post",post);
        json.put("user",user);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/user/myEvaluation",method = RequestMethod.POST)
    public void getMyEvaluation(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        List<EvaluationEntity> list = evaluationDao.findByUserIdOrderByEvaluationTimeDesc(Integer.valueOf(userId));
        List<Map> mapList = new ArrayList<Map>();
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("evaluation",list.get(i));
            map.put("post",postDao.findByPostId(list.get(i).getPostId()));
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("list",mapList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());

    }

    @ResponseBody
    @RequestMapping(value="/user/evaluationList",method = RequestMethod.POST)
    public void getEvaluationList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String postId = request.getParameter("postId");
        List<EvaluationEntity> list = evaluationDao.findByPostIdOrderByEvaluationTimeDesc(Integer.valueOf(postId));
        List<Map> mapList = new ArrayList<Map>();
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("evaluation",list.get(i));
            map.put("user",userDao.findByUserId(list.get(i).getUserId()));
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("list",mapList);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }

    @ResponseBody
    @RequestMapping(value="/user/addEvaluation",method = RequestMethod.POST)
    public void addEvaluation(HttpServletRequest request,HttpServletResponse response){
        String openId = request.getParameter("openId");
        String postId = request.getParameter("postId");
        String input = request.getParameter("input");
        UserEntity user = userDao.findByUserOpenId(openId);
        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setUserId(user.getUserId());
        evaluation.setEvaluationDetail(input);
        evaluation.setPostId(Integer.valueOf(postId));
        evaluation.setEvaluationTime(new Timestamp(System.currentTimeMillis()));
        evaluationDao.save(evaluation);
    }


    @ResponseBody
    @RequestMapping(value="/user/addPost",method = RequestMethod.POST)
    public void addFood(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String houzhu=file.getContentType();
        int one = houzhu.lastIndexOf("/");
        String type=houzhu.substring((one+1),houzhu.length());
        //根据获取到的文件类型截取出图片后缀
        JSONObject json = new JSONObject();
        try {
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
            json.put("filePath",fileName);
            json.put("addFoodMsg",true);
            System.out.println(fileName);
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
    @RequestMapping(value="/user/addPostDetail",method = RequestMethod.POST)
    public void addPostDetail(HttpServletRequest request,HttpServletResponse response){
        String url = request.getParameter("url");
        String openId = request.getParameter("openId");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        UserEntity user = userDao.findByUserOpenId(openId);
        PostEntity post = new PostEntity();
        post.setUserId(user.getUserId());
        post.setPostTitle(title);
        post.setPostUrl(url);
        post.setPostDetail(detail);
        post.setPostTime(new Timestamp(System.currentTimeMillis()));
        postDao.save(post);
    }

    @ResponseBody
    @RequestMapping(value="/user/myPost",method = RequestMethod.POST)
    public void myPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        List<PostEntity> list = postDao.findByUserIdOrderByPostTimeDesc(Integer.valueOf(userId));
        JSONObject json = new JSONObject();
        json.put("list",list);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        //System.out.println(js);
        response.getWriter().write(js.toString());
    }
}
