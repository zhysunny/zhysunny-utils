/**
 * Copyright 2005 The Apache Software Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhysunny.io.conf;

import com.zhysunny.io.properties.PropertiesConstant;
import com.zhysunny.io.properties.PropertiesReader;
import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.io.xml.reader.XmlToProperties;
import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * 可以读写xml和properties文件配置，分为默认配置与最终配置
 * @author 章云
 * @date 2019/7/30 8:55
 */
public class Configuration {

    private ArrayList<Object> defaultResources = new ArrayList<Object>();
    private ArrayList<Object> finalResources = new ArrayList<Object>();
    /**
     * 配置集合
     */
    private Properties props;
    /**
     * 类加载器
     */
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private static class Inner {

        public static final Configuration INSTANCE = new Configuration();

    }

    /**
     * 单例
     */
    private Configuration() {
    }

    public static Configuration getInstance() {
        return Inner.INSTANCE;
    }

    public Configuration addDefaultResource(String name) {
        addResource(defaultResources, name);
        return this;
    }

    public Configuration addDefaultResource(File file) {
        addResource(defaultResources, file);
        return this;
    }

    public Configuration addDefaultResource(URL url) {
        addResource(defaultResources, url);
        return this;
    }

    public Configuration addFinalResource(String name) {
        addResource(finalResources, name);
        return this;
    }

    public Configuration addFinalResource(File file) {
        addResource(finalResources, file);
        return this;
    }

    public Configuration addFinalResource(URL url) {
        addResource(finalResources, url);
        return this;
    }

    /**
     * 添加资源并初始化配置集合
     * @param resources
     * @param resource
     */
    private synchronized void addResource(ArrayList<Object> resources, Object resource) {
        resources.add(resource);
        props = null;
    }

    public Configuration builder() {
        props = new Properties();
        loadResources(props, defaultResources);
        loadResources(props, finalResources);
        return this;
    }

    /**
     * 加载xml配置到Properties
     * @param props
     * @param resources
     */
    private void loadResources(Properties props, ArrayList<Object> resources) {
        for (Object obj : resources) {
            if (obj.toString().endsWith(".xml")) {
                try {
                    Properties prop = (Properties)new XmlReader(obj).read(new XmlToProperties());
                    props.putAll(prop);
                } catch (Exception e) {
                    throw new RuntimeException("配置文件加载异常：" + obj);
                }
            } else if (obj.toString().endsWith(".props")) {
                try {
                    Properties prop = new PropertiesReader(obj).builder().getProps();
                    props.putAll(prop);
                } catch (Exception e) {
                    throw new RuntimeException("配置文件加载异常：" + obj);
                }
            } else {
                throw new RuntimeException("资源配置文件只支持xml和properties");
            }
        }
    }

    /**
     * 返回name属性的值，如果不存在此类属性，则返回null。
     * @param name
     * @return
     */
    public Object getObject(String name) {
        return getProps().get(name);
    }

    /**
     * 设置name属性的值。
     * @param name
     * @param value
     */
    public void setObject(String name, Object value) {
        getProps().put(name, value);
    }

    /**
     * 返回name属性的值。如果不存在此类属性，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public Object get(String name, Object defaultValue) {
        Object res = getObject(name);
        if (res != null) {
            return res;
        } else {
            return defaultValue;
        }
    }

    /**
     * 设置name属性的值。
     * @param name
     * @param value
     */
    public void set(String name, Object value) {
        getProps().setProperty(name, value.toString());
    }

    /**
     * 返回name属性的值，如果不存在此类属性，则返回null。
     * @param name
     * @return
     */
    public String get(String name) {
        return getProps().getProperty(name);
    }

    /**
     * 返回name属性的值。如果不存在此类属性，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public String get(String name, String defaultValue) {
        return getProps().getProperty(name, defaultValue);
    }

    /**
     * 以整数形式返回name属性的值。如果没有指定此类属性，或者指定的值不是有效整数，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public int getInt(String name, int defaultValue) {
        String valueString = get(name);
        if (valueString == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将name属性的值设置为整数。
     * @param name
     * @param value
     */
    public void setInt(String name, int value) {
        set(name, Integer.toString(value));
    }

    /**
     * 以长整数形式返回name属性的值。如果没有指定此类属性，或者指定的值不是有效长整数，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public long getLong(String name, long defaultValue) {
        String valueString = get(name);
        if (valueString == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将name属性的值设置为长整数。
     * @param name
     * @param value
     */
    public void setLong(String name, long value) {
        set(name, Long.toString(value));
    }

    /**
     * 以单精度浮点型形式返回name属性的值。如果没有指定此类属性，或者指定的值不是有效单精度浮点型，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public float getFloat(String name, float defaultValue) {
        String valueString = get(name);
        if (valueString == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 以布尔型形式返回name属性的值。如果没有指定此类属性，或者指定的值不是有效布尔型，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String valueString = get(name);
        if ("true".equalsIgnoreCase(valueString)) {
            return true;
        } else if ("false".equalsIgnoreCase(valueString)) {
            return false;
        } else {
            return defaultValue;
        }
    }

    /**
     * 将name属性的值设置为布尔型。
     * @param name
     * @param value
     */
    public void setBoolean(String name, boolean value) {
        set(name, Boolean.toString(value));
    }

    /**
     * 以字符串数组的形式返回name属性的值。如果没有指定此类属性，则返回null。值由空格或逗号分隔。
     * @param name
     * @return
     */
    public String[] getStrings(String name) {
        String valueString = get(name);
        if (valueString == null) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(valueString, ", \t\n\r\f");
        List<String> values = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            values.add(tokenizer.nextToken());
        }
        return values.toArray(new String[values.size()]);
    }

    /**
     * 作为类返回name属性的值。如果没有指定此类属性，则返回defaultValue。
     * @param name
     * @param defaultValue
     * @return
     */
    public Class getClass(String name, Class defaultValue) {
        String valueString = get(name);
        if (valueString == null) {
            return defaultValue;
        }
        try {
            return Class.forName(valueString);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 作为类返回name属性的值。如果没有指定此类属性，则返回defaultValue。如果返回的类没有实现指定的接口，则会引发错误。
     * @param name         属性名
     * @param defaultValue 配置不存在时默认的类
     * @param xface        配置类的父类
     * @return
     */
    public Class getClass(String name, Class defaultValue, Class xface) {
        try {
            Class theClass = getClass(name, defaultValue);
            // 如果xface不是theClass的超类，抛出异常
            if (theClass != null && !xface.isAssignableFrom(theClass)) {
                throw new RuntimeException(theClass + " not " + xface.getName());
            }
            return theClass;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得配置集合对象
     * @return
     */
    private synchronized Properties getProps() {
        if (props == null) {
            builder();
        }
        return props;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Configuration: \n\t");
        sb.append("defaults: ");
        toString(defaultResources, sb);
        sb.append("\n\t");
        sb.append("final: ");
        toString(finalResources, sb);
        return sb.toString();
    }

    private void toString(ArrayList<Object> resources, StringBuffer sb) {
        ListIterator<Object> i = resources.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() != 0) {
                sb.append(" , ");
            }
            Object obj = i.next();
            if (obj instanceof File) {
                sb.append(obj);
            } else {
                sb.append((String)obj);
            }
        }
    }

    /**
     * 将配置转入常量类
     * @param clz
     * @throws Exception
     */
    public void toConstant(Class<? extends PropertiesConstant> clz) throws Exception {
        PropertiesReader reader = new PropertiesReader(getProps());
        reader.translate();
        reader.toConstant(clz);
        props = null;
    }

}
