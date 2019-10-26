package com.zhysunny.io.xml;

import com.zhysunny.io.BaseReader;
import com.zhysunny.io.properties.PropertiesConstant;
import com.zhysunny.io.xml.reader.BaseXmlToAny;
import com.zhysunny.io.xml.reader.XmlToBean;
import com.zhysunny.io.xml.reader.XmlToMap;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Xml读取类
 * @author 章云
 * @date 2019/7/24 15:29
 */
public class XmlReader extends BaseReader {

    public static final String ATTRIBUTES = "Attributes";
    public static final String ELEMENT = "Element";

    /**
     * xml文档
     */
    private Document document;

    public XmlReader(File file) {
        super(file);
    }

    public XmlReader(String path) {
        super(path);
    }

    public XmlReader(URL url) {
        super(url);
    }

    /**
     * 加载xml
     * @return
     * @throws Exception
     */
    @Override
    public XmlReader builder() throws Exception {
        if (document == null) {
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                docBuilderFactory.setIgnoringComments(true);
                docBuilderFactory.setNamespaceAware(true);
                try {
                    docBuilderFactory.setXIncludeAware(true);
                } catch (UnsupportedOperationException e) {
                    throw new UnsupportedOperationException(e);
                }
                DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
                if (names[0] instanceof URL) {
                    URL url = (URL) names[0];
                    if (url != null) {
                        document = builder.parse(url.toString());
                    }
                } else if (names[0] instanceof File) {
                    File file = (File) names[0];
                    if (file.exists()) {
                        document = builder.parse(file);
                    }
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        return this;
    }

    public Object read(BaseXmlToAny baseXmlToAny, Object... params) throws Exception {
        if (document == null) {
            builder();
        }
        return baseXmlToAny.read(this, params);
    }

    /**
     * xml转化成Map
     * @return
     * @throws Exception
     */
    public Map<String, Object> read() throws Exception {
        return (Map<String, Object>) read(new XmlToMap());
    }

    /**
     * xml转化成实体类
     * @param clz
     * @return
     * @throws Exception
     */
    public <T> T read(Class<T> clz) throws Exception {
        if (!XmlBean.class.isAssignableFrom(clz)) {
            throw new Exception(clz.getSimpleName() + " 没有实现 " + XmlBean.class);
        }
        return (T) read(new XmlToBean(), clz);
    }

    /**
     * 获取document实例
     * @return
     */
    public Document getDocument() {
        return document;
    }

}
