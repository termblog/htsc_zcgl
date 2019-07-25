package com.htsc.zcgl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author xj
 * @date 2017/5/10 15:54
 */
public class JarUrlUtil {
    /**
     * log4j
     */
    private static final Logger logger = LoggerFactory.getLogger(JarUrlUtil.class);

    /**
     * 查询jar包运行路径
     *
     * @return
     */
    public static String getJarUrl() {
        URL url = JarUrlUtil.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(url.getPath());
        String filePath = "";
        try {
            // 转化为utf-8编码，支持中文
            filePath = URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        int i = filePath.indexOf(".jar");
        // 可执行jar包运行的结果里包含".jar"
        if (i >= 0) {
            // 获取jar包所在目录
            filePath = filePath.substring(0, filePath.lastIndexOf("/", i) + 1);
            System.out.println(filePath);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();

        i = filePath.indexOf("file:");
        if (i >= 0) {
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separator, i));
        }
//        String classesUrl = File.separator + "classes";
//        i = filePath.indexOf(classesUrl);
//        if (i >= 0) {
//            filePath = filePath.substring(0, i);
//        }

        return filePath;
    }// getJarUrl定义结束

    /**
     * 首先从jar目录下读取文件，如果不存在在当做classpath从jar内部读取。
     *
     * @param path
     * @return
     */
    public static InputStream getFileStream(String path) {
        String jarPath = JarUrlUtil.getJarUrl() + File.separator + path;
        InputStream in = null;
        try {
            in = new FileInputStream(jarPath);
            if (in != null){
                return in;
            }
        } catch (FileNotFoundException e) {
            logger.info(e.toString(),e);
        }

        in = JarUrlUtil.class.getClassLoader().getResourceAsStream(path);
        return in;
    }
}// class结束
