package com.example.learndemo.analogData.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成数据
 */
@Controller
@RequestMapping("/data")
public class GenerateDataController {

    public static void main(String[] args) {
        cs2();
    }


    public static void cs(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(8);
        list.add(4);
        list.add(5);
        list.add(5);

        int i = list.stream().reduce((f, s) -> {
            System.out.println("f:" + f + " s:"+s);
            if (f < s) {
                return s;
            }
            return f;
        }).get();
        System.out.println(i);
    }
    public static void cs2(){
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 1 ;i<6 ;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("key",i);
            list.add(map);
        }
        List<Object> key = list.stream().map(map -> map.get("key")).collect(Collectors.toList());
        Object key1 = list.stream().map(map -> map.get("key")).reduce((f, s) -> {
            System.out.println("f:" + f + " s:" + s);
            int fs = Integer.valueOf(f.toString());
            int ss = Integer.valueOf(s.toString());
            if (fs < ss) {
                return s;
            }
            return f;
        }).get();
        System.out.println(key1);
    }

    public static void xx(){
        String x ="5.0";
        String y ="5.0";
        String z ="0.0";
        //String start = "2023-05-18 15:01:00.100";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            //date = sdf.parse(start);


            for (int i = 0 ; i< 5000 ;i++){
                Date date = new Date();
                String date_Str = sdf.format(date);
                String data = "display:075,1000,0," + date_Str + ",0," + x + "," + y + "," + z;
                sendMessage(data);
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public static void sendMessage(String message) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.137.1", 50000);
        byte[] udpMessage = message.getBytes();
        DatagramPacket datagramPacket = null;
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramPacket = new DatagramPacket(udpMessage, udpMessage.length, inetSocketAddress);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
        }
    }

    @RequestMapping("/cs1")
    public static void cs(@PathVariable(name = "test") String test){

        String path = "F:\\generateData\\";
        long l = System.currentTimeMillis();
        String fileName = "data_" + l + ".txt";
        File f = new File(path + fileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(f);

            String x ="0.0";
            String y ="0.0";
            String z ="0.0";

            String start = "2023-05-18 9:40:23.100";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date date = sdf.parse(start);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            for (int i = 0 ; i< 500 ;i++){
                calendar.add(Calendar.SECOND, 1);
                date = calendar.getTime();
                String date_Str = sdf.format(date);
                String data = "display:075,1000,0," + date_Str + ",0," + x + "," + y + "," + z;
                fileWriter.write(data);
                fileWriter.write(System.getProperty("line.separator"));
            }

            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
