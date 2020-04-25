package learn.springcommon;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class XmlService {
    private Map<String, String> xmlMap = new HashMap<>();

    public String getPattern(String id) {
        return xmlMap.get(id);
    }

    @PostConstruct
    public void init() {
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        InputStream is = null;
        Document document = null;
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("demo.xml");
//            is = new BufferedInputStream(new FileInputStream("/demo.xml"));
            document = builder.parse(is);
            //第一层
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                //第二层
                if (node instanceof Element) {
                    String id = ((Element) node).getAttribute("id");
                    String content = node.getTextContent();
                    xmlMap.put(id, content);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
