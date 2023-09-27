package com.example.learndemo.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * xml 文件修改
 */
@Controller
@RequestMapping("/cs")
public class XmlController {

    @RequestMapping("/xmlTest")
    public String queryCharts(Model model){
        return "xmlTest";
    }


    public static Document loadXml(String path){
        Document document = null;
        try {
            // 1、创建解析器
            SAXReader reader = new SAXReader();
            // 2、读取Document对象
            document = reader.read(path);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public static void save(String destPath,Document document){
        // 1、创建输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 2、XML写入工具
        XMLWriter writer= null;
        try {
            writer = new XMLWriter(new FileWriter(destPath),format);
            // 3、写入文档至XML文件
            writer.write(document);
            // 4、关闭流
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws DocumentException, IOException {

        String path = "src\\main\\resources\\project.xml";
        Document document = loadXml(path);
        //获取根元素
        Element rootElement = document.getRootElement();
        //获取指定元素
        Element taglist = rootElement.element("taglist");
        //添加节点
        Element deviceItem = taglist.addElement("deviceItem");
        deviceItem.addAttribute("id","8001");
        deviceItem.addAttribute("name","8001");
        deviceItem.addAttribute("icon","");
        deviceItem.addAttribute("class","");
        deviceItem.addAttribute("pos",",,");
        deviceItem.addAttribute("more",",");
        deviceItem.addAttribute("flag","");

        save(path,document);
    }

}
