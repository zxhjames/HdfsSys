package com.james.imgsys.group.controller;

import com.james.imgsys.group.config.HdfsConn;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.tools.HDFSConcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginAndRegistController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HdfsConn hdfsconn;
    @PostMapping("/login")
    public String UserLogin(@RequestParam(name="username") String username, @RequestParam(name="password") String password
    , Model model) throws InterruptedException, IOException, URISyntaxException {
        String tmp = stringRedisTemplate.opsForValue().get(username);
        if(tmp == null) {
            return "intro/LoginErr";
        }else if(!tmp.equals(password)){
            return "intro/LoginErr";
        }else if(username.equals("root")){
            username = "";
        }
        FileSystem fileSystem = hdfsconn.GetHDFS();
        String root = "/" + username;
        fileSystem.mkdirs(new Path(root));
        FileStatus[] status = fileSystem.listStatus(new Path(root));
        model.addAttribute("homeDir",root);
        return "main";
    }

    @PostMapping("/regist")
    public String UserRegist(@RequestParam(name="username") String username,@RequestParam(name="password") String password){
        String tmp = stringRedisTemplate.opsForValue().get(username);
        if (tmp!=null) {
            return "intro/registErr";
        }
        stringRedisTemplate.opsForValue().set(username,password);
        return "intro/registSuc";
    }
}
