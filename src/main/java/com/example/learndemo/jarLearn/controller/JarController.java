package com.example.learndemo.jarLearn.controller;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class JarController {
    /**
     * 动态加载Jar包
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //jar所在路径
        //方式1
        String jarPath = "D:/learnDemo-0.0.1-SNAPSHOT.jar";
        URL[] urls1 = new URL[] {
                new URL("file:/" + jarPath )
        };
        //方式2
        File file = new File(jarPath);
        URI uri = file.toURI();
        URL url = uri.toURL();
        URL[] urls2 = new URL[] {url};

        URLClassLoader ul = new URLClassLoader(urls1);
        //类的路径
        Class<?> c = ul.loadClass("com.example.learndemo.jarLearn.controller.TestController");
        Object instance = c.newInstance();

        //方法调用
        c.getMethod("cs3").invoke(instance);
        c.getDeclaredMethod("cs2",String.class).invoke(instance,"lalal");
        c.getMethod("cs1",String.class).invoke(instance,"biubiu");
    }


}
