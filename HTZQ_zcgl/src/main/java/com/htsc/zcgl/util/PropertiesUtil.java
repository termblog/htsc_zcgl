package com.htsc.zcgl.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by K0180006 on 2017/7/14.
 * 重写Properties部分方法，确保读取的配置文件内容为有序的
 */
public class PropertiesUtil extends Properties {

    /**
     * use serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(PropertiesUtil.class);
    private transient List<Object> keyList = new ArrayList<Object>();

    /**
     * 默认构造方法
     */
    public PropertiesUtil() {

    }

    /**
     * 从指定路径加载信息到Properties
     *
     * @param path
     */
    public PropertiesUtil(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            this.load(is);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("指定文件不存在！");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 从指定路径加载信息到Properties
     *
     * @param reader
     */
    public PropertiesUtil(Reader reader) {
        try {
            this.load(reader);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("指定文件不存在！");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 重写put方法，按照property的存入顺序保存key到keyList，遇到重复的后者将覆盖前者。
     */
    @Override
    public synchronized Object put(Object key, Object value) {
        this.removeKeyIfExists(key);
        keyList.add(key);
        return super.put(key, value);
    }


    /**
     * 重写remove方法，删除属性时清除keyList中对应的key。
     */
    @Override
    public synchronized Object remove(Object key) {
        this.removeKeyIfExists(key);
        return super.remove(key);
    }

    /**
     * keyList中存在指定的key时则将其删除
     */
    private void removeKeyIfExists(Object key) {
        keyList.remove(key);
    }

    /**
     * 获取Properties中key的有序集合
     *
     * @return
     */
    public List<Object> getKeyList() {
        return keyList;
    }

    /**
     * 保存Properties到指定文件，默认使用UTF-8编码
     *
     * @param path 指定文件路径
     */
    public void store(String path) {
        this.store(path, "UTF-8");
    }

    /**
     * 保存Properties到指定文件，并指定对应存放编码
     *
     * @param path    指定路径
     * @param charset 文件编码
     */
    public void store(String path, String charset) {
        if (path != null && !"".equals(path)) {
            OutputStream os = null;
            try {
                os = new FileOutputStream(path);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
                this.store(bw, null);
                bw.close();
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("指定文件不存在！");
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } else {
            throw new RuntimeException("存储路径不能为空!");
        }
    }

    /**
     * 重写keys方法，返回根据keyList适配的Enumeration，且保持HashTable keys()方法的原有语义，
     * 每次都调用返回一个新的Enumeration对象，且和之前的不产生冲突
     */
    @Override
    public synchronized Enumeration<Object> keys() {
        return new EnumerationAdapter<Object>(keyList);
    }

    /**
     * List到Enumeration的适配器
     */
    private class EnumerationAdapter<T> implements Enumeration<T> {
        private final List<T> list;
        private final boolean isEmpty;
        private int index = 0;

        public EnumerationAdapter(List<T> list) {
            this.list = list;
            this.isEmpty = list.isEmpty();
        }

        public boolean hasMoreElements() {
            //isEmpty的引入是为了更贴近HashTable原有的语义，在HashTable中添加元素前调用其keys()方法获得一个Enumeration的引用，
            //之后往HashTable中添加数据后，调用之前获取到的Enumeration的hasMoreElements()将返回false，但如果此时重新获取一个
            //Enumeration的引用，则新Enumeration的hasMoreElements()将返回true，而且之后对HashTable数据的增、删、改都是可以在
            //nextElement中获取到的。
            return !isEmpty && index < list.size();
        }

        public T nextElement() {
            if (this.hasMoreElements()) {
                return list.get(index++);
            }
            return null;
        }

    }
}
