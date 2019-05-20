import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class GetInvalidFile {
    private MainClass main = new MainClass();
    private String file_name;
    @Пусть("на вход программы подан некорректный {word}")
    public void на_вход_программы_подан_некорректный(String file)  {
        // Write code here that turns the phrase above into concrete actions
        this.file_name = file;
    }

    @Тогда("должна возникнуть ошибка парсинга XML")
    public void должна_возникнуть_ошибка_парсинга_XML() throws IOException, SAXException, ParserConfigurationException {
        try {
            main.getDocument(file_name);
            Assert.assertTrue("Correct xml-file", false);
        }
        catch (SAXParseException e) {
            Assert.assertTrue(true);
        }
        // Write code here that turns the phrase above into concrete actions
    }
}
