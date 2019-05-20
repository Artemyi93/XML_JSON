import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OnlyRootFile {
    String file_name;
    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    MainClass main = new MainClass();
    @Пусть("на вход программы подан xml-файл с одним корневым элементом {word}")
    public void на_вход_программы_подан_xml_файл_с_одним_корневым_элементом(String file) throws IOException, SAXException, ParserConfigurationException {
        // Write code here that turns the phrase above into concrete actions
        file_name = file;
        main.getResult(file_name);
    }

    @Тогда("на выходе получаем json с 1 элементом")
    public void на_выходе_получаем_json_с_элементом() throws IOException {
        String json = readFile("src/main/resources/output1.json", StandardCharsets.UTF_8);
        boolean correct = false;
        String []split1 = json.split("\\[");
        String []split2 = json.split("\\]");
        // проверяем, что на выходе один пустой children
        if (split1.length+split2.length-2 == 2)
            correct = true;
        Assert.assertTrue("Ошибка! XML не содержит вложенные элементы!",correct);
    }
}
