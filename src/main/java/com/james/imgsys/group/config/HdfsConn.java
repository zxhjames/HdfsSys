package com.james.imgsys.group.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class HdfsConn {
    /**
     * 获取分布式文件系统持久化存储
     */
    public FileSystem GetHDFS() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://node1:9000/");
        return  FileSystem.get(new URI("hdfs://node1:9000/"), conf,"root");
    }
}
