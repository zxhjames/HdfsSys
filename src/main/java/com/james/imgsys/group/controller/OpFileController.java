package com.james.imgsys.group.controller;

import com.james.imgsys.group.config.HdfsConn;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URISyntaxException;



@Controller
public class OpFileController {
    @Autowired
    private HdfsConn hdfsconn;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /****
     * todo 文件的操作
     */
    //下载单个文件
    @PostMapping("/download")
    public String DownloadSingleFile(@RequestParam(name = "srcpath") String src,@RequestParam(name="dstpath") String dst) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fileSystem = hdfsconn.GetHDFS();
        try {
            fileSystem.copyToLocalFile(new Path(src), new Path(dst));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        fileSystem.close();
        return "success";
    }


    //上传单个文件
    @PostMapping("/upload")
    public String UploadSingleFILE(@RequestParam(name = "srcpath") String src,@RequestParam(name="dstpath") String dst) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fileSystem = new HdfsConn().GetHDFS();
        try {
            fileSystem.copyFromLocalFile(new Path(src), new Path(dst));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        //写入缓存目录
        fileSystem.close();
        return "success";
    }

    /****
     * todo 文件夹的操作类
     * @param foldername
     * @param newfoldername
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    //文件夹的创建
    @PostMapping("/create")
    public String CreateFolder(@RequestParam(name = "foldername") String foldername,@RequestParam(name = "newfoldername") String newfoldername) throws IOException, URISyntaxException, InterruptedException {
        FileSystem fileSystem = hdfsconn.GetHDFS();
        System.out.println(foldername+"...."+newfoldername);
        String newPath = foldername.equals("/")?foldername+newfoldername:foldername+"/"+newfoldername;
        fileSystem.mkdirs(new Path(newPath));
        fileSystem.close();
        return "success";
    }
    //文件夹的删除(递归删除)
    @PostMapping("/delete")
    public String DeleteFolder(@RequestParam(name = "foldername") String foldername) throws IOException, URISyntaxException, InterruptedException {
        FileSystem fileSystem = hdfsconn.GetHDFS();
        fileSystem.delete(new Path(foldername),true);
        fileSystem.close();
        return "success";
    }
    //遍历文件系统所有当前用户目录下的所有文件夹
    @GetMapping("/current")
    public String GetFileDir(@RequestParam(name = "curdir") String curdir, Model model) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fileSystem = hdfsconn.GetHDFS();
        FileStatus[] status = fileSystem.listStatus(new Path(curdir));
        model.addAttribute("list",status);
        model.addAttribute("curDir",curdir);
        return "theme";
    }
}
