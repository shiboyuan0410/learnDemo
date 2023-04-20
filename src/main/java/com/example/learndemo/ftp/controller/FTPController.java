package com.example.learndemo.ftp.controller;

import com.example.demo.lx.learn.ftp.utils.FTPTools;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *  FTP 文件上传
 *  windows FTP版
 */
@Controller
@RequestMapping("/ftp")
public class FTPController {

    /**
     * 测试连接ftp文件服务器
     */
    @RequestMapping("/testConnet")
    @ResponseBody
    public String testConnet(){
        FTPClient ftpClient = new FTPClient();
        String hostname = "192.168.21.107";
        int port = 21;
        String username = "Admin";
        String password = "Admin@ht304";
        boolean flag = FTPTools.connect(ftpClient, hostname, port, username, password);
        System.out.println(flag);
        return "ok!";
    }


    /**
     * 测试上传 ftp文件服务器
     */
    @RequestMapping("/testFileUpload")
    @ResponseBody
    public String testFileUpload() throws FileNotFoundException {
        String hostname = "192.168.21.107";
        int port = 21;
        String username = "Admin";
        String password = "Admin@ht304";
        String workingPath = "";
        String str = "C:\\Users\\Admin\\Desktop\\fujianceshi\\cs0901.docx";

        InputStream fileInputStream = new FileInputStream(new File(str));
        String saveName = "cs.docx";
        System.out.println(FTPTools.upload( hostname, port, username, password, workingPath, fileInputStream, saveName));
        return "ok!";
    }

}
