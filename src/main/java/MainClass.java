import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class MainClass {

    private  void ByPassTreeSimple(Node node, ElementClass el) {
        if (node.hasChildNodes()) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    ElementClass child = el.addChild(list.item(i).getNodeName());
                    ByPassTreeSimple(list.item(i), child);
                }
            }
        }
    }

    private  void ByPassTreeHard(Node node, ElementTextClass el) {
        if (node.hasChildNodes()) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    String val = "";
                    if (list.item(i).hasChildNodes()) {
                        NodeList sublist = list.item(i).getChildNodes();
                        for (int j = 0; j <sublist.getLength(); j++) {
                            if (sublist.item(j).getNodeType()== Node.TEXT_NODE)
                                val += sublist.item(j).getNodeValue();
                        }
                        if (val == "" || val.matches("[' ''\n']+"))
                            val = null;
                    }
                    else val = null;
                    ElementTextClass child = el.addChild(list.item(i).getNodeName(), val);
                    ByPassTreeHard(list.item(i), child);
                }
            }
        }
    }

    // обработка xml-документа
    public  Document getDocument(String file_name) throws ParserConfigurationException, IOException, SAXException {
        // Получение фабрики, чтобы после получить билдер документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам
        Document doc;
        try {
            File file = new File("src/main/resources/" + file_name);
            doc = builder.parse(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
            doc = null;
        }
        return doc;
    }

    public  Element getRootElement (Document doc) {
        Element root = doc.getDocumentElement();
        return root;
    }

    // получить дерево элементов
    public  ElementClass  getSimleTree(Element root) {
        ElementClass el = new ElementClass(root.getNodeName());
        ByPassTreeSimple(root, el);
        return el;
    }
    // получить дерево элементов с текстом
    public  ElementTextClass  getHardTree(Element root) {
        ElementTextClass el = new ElementTextClass(root.getNodeName(), null);
        ByPassTreeHard(root, el);
        return el;
    }

    // получить objectMapper
    public  ObjectMapper getObjectMapper (String file_name, boolean kind) throws IOException, SAXException, ParserConfigurationException {
        Document doc = getDocument(file_name);
        if (doc != null) {
            Element root = getRootElement(doc);
            ObjectMapper objectMapper = new ObjectMapper();
            // получить дерево без текста
            if (!kind) {
                ElementClass el = getSimleTree(root);
                objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File("src/main/resources/output1.json"), el);
            }
            else {
                ElementTextClass el = getHardTree(root);
                objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File("src/main/resources/output2.json"), el);
            }
            return objectMapper;
        }
        return null;
    }

    public void getResult(String file_name) throws ParserConfigurationException, SAXException, IOException {
        ObjectMapper objmap1 = getObjectMapper(file_name, false);
        ObjectMapper objmap2 = getObjectMapper(file_name, true);
    }



    public static void main(String[] args) throws Exception {
        try {
            String file_name = "example.xml";
            new MainClass().getResult(file_name);
        }
        catch (Exception e) {
            System.out.println("Ошибка! Проверьте корректность файла!");
        }
    }

}
