package com.james.imgsys.group.folderTest;

import com.james.imgsys.group.config.HdfsConn;
import com.james.imgsys.group.controller.OpFileController;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Folder {
    Logger logger = LogManager.getRootLogger();
//    public void ListFile() throws InterruptedException, IOException, URISyntaxException {
//        List<Path> locatedFileStatuses = new OpFileController().GetFileDir("/");
//        if (locatedFileStatuses!=null) {
//            logger.info("get");
//            System.out.println(locatedFileStatuses);
//        }
//        logger.info("error");
//
//    }

    //上传文件
//    @Test
//    public void uploadFile() throws InterruptedException, IOException, URISyntaxException {
//        FileSystem fileSystem = new HdfsConn().GetHDFS();
//        try {
//            fileSystem.copyFromLocalFile(new Path("/Users/mac/bigdata/1.c"), new Path("/"));
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("上传成功");
//        }
//        fileSystem.close();
//        System.out.println("上传失败");
//    }
}
