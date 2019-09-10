package cn.membersheep.controller;

import cn.membersheep.entity.ResponseResult;
import cn.membersheep.entity.User;
import cn.membersheep.service.UserService;
import cn.membersheep.service.ex.*;
import cn.membersheep.util.TextValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    //注册
    @RequestMapping(value = "/hand_reg.do",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> handleReg(User user){
        if (!TextValidator.checkUsername(user.getUsername())){
            return new ResponseResult<Void>(301,"用户名格式不正确");
        }
        if (!TextValidator.checkUsername(user.getPassword())){
            return new ResponseResult<Void>(302,"密码格式不正确");
        }
        userService.reg(user);
       return new ResponseResult<Void>();
    }

    //登录
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/hand_login.do",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void>handLogin(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){
        if (!TextValidator.checkUsername(username)){
            return new ResponseResult<Void>(301,"用户名格式不正确");
        }
        if (!TextValidator.checkUsername(password)){
            return new ResponseResult<Void>(302,"密码格式不正确");
        }
        User user=userService.login(username,password);
        session.setAttribute("uid",user.getId());
        session.setAttribute("username",user.getUsername());
        return new ResponseResult<>();
    }
    @RequestMapping(value = "/change_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> handle_changePassword(@RequestParam("old_password") String oldPassword,
                                                      @RequestParam("new_password") String newPassword, HttpSession session) {
        if (!TextValidator.checkPassword(oldPassword) || !TextValidator.checkPassword(newPassword)) {
            return new ResponseResult<Void>(302, "原密码格式错误");
        }
        Integer id=Integer.valueOf(session.getAttribute("uid").toString());
        userService.changePassword(id, oldPassword, newPassword);
        return new ResponseResult<Void>();
    }

    @RequestMapping(value = "/change_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> handle_changeInfo(User user, HttpSession session) {
        user.setId(Integer.valueOf(session.getAttribute("uid").toString()));
        userService.changeInfo(user);
        return new ResponseResult<Void>();
    }


    @RequestMapping(value = "/info.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<User> handle_getInof(HttpSession session) {
        Integer id=Integer.valueOf(session.getAttribute("uid").toString());
        User user = userService.findUserById(id);
        ResponseResult<User> rr = new ResponseResult<User>();
        rr.setData(user);
        return rr;
    }
    // ÎÄ¼þÉÏ´«×î´óÏÞÖÆ
    private static final int AVATAR_MAX_SIZE = 1 * 1024 * 1024;
    // MIME_TYPEÀàÐÍ°×Ãûµ¥
    private static final List<String> CONTENT_TYPE_WHITE = new ArrayList<String>();

    @PostConstruct
    public void init() {
        CONTENT_TYPE_WHITE.add("image/jpeg");
        CONTENT_TYPE_WHITE.add("image/png");
    }

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> handle_changeAvatar(HttpServletRequest request, HttpSession session, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RequestArgumentException();
        }

        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new UploadFileMaxSizeException("ÉÏ´«ÎÄ¼þ³¬³öÁË×î´óÏÞÖÆ,×î´óÖµÎª:" + AVATAR_MAX_SIZE / 1024 + "kb");
        }
        String contentType = file.getContentType();
        if (!CONTENT_TYPE_WHITE.contains(contentType)) {
            throw new UploadFileContentTypeException("ÉÏ´«ÎÄ¼þÀàÐÍ´íÎó,¸ñÊ½Îªjpg/png");
        }

        String uploadDirName = "upload";
        // ÓÃ»§ÉÏ´«µÄÎÄ¼þ´æ´¢µ½µÄÂ·¾¶
        String uploadDirPath = request.getServletContext().getRealPath(uploadDirName);
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // »ñÈ¡µ±Ç°µÇÂ¼ÓÃ»§µÄid
        Integer id = getUidFromSession(session);
        int beginIndex = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(beginIndex);
        // ÓÃ»§ÉÏ´«µÄÎÄ¼þ´æ´¢µ½µÄÎÄ¼þÃû
        String fileName = getFileName(id) + suffix;
        // ÓÃ»§ÎÄ¼þÉÏ´«´æ´¢µ½·þÎñ¶ËµÄÎÄ¼þ¶ÔÏó
        File dest = new File(uploadDir, fileName);
        // ½«ÓÃ»§Êý¾ÝÉÏ´«ÖÁÖ¸¶¨ÎÄ¼þ¼Ð

        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new UploadFileStateExceptiion("ÉÏ´«µÄÎÄ¼þ²»´æÔÚ,Â·¾¶ÒÑ¸Ä±ä");
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadIOException("¶ÁÈ¡ÉÏ´«ÎÄ¼þÊ§°Ü,ÇëÖØÐÂÉÏ´«");
        }

        String avatar = uploadDirName + "/" + fileName;
        // ½«ÓÃ»§µÄÍ·ÏñÊý¾Ý¸üÐÂµ½Êý¾Ý±í
        userService.changeAvatar(id, avatar);
        // ½«Í·ÏñÂ·¾¶·â×°²¢·µ»Ø
        ResponseResult<String> rr = new ResponseResult<String>();
        rr.setData(avatar);
        return rr;

    }

    private String getFileName(Integer id) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return id + "-" + sdf.format(date);
    }


}
