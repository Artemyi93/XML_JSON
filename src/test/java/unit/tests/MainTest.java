package unit.tests;
import main.classes.*;

import main.classes.MainClass;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {
    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Test
    public void testOneElementFile() throws Exception {
        MainClass main = new MainClass();
        main.getResult("only_root.xml");
        String json;
        json = readFile("src/main/resources/output1.json", StandardCharsets.UTF_8);
        boolean correct = false;
        String []split1 = json.split("\\[");
        String []split2 = json.split("\\]");
        // проверяем, что на выходе один пустой children
        if (split1.length+split2.length-2 == 2)
            correct = true;
        Assert.assertTrue("Ошибка! XML не содержит вложенные элементы!",correct);
    }
    @Test
    public void testFiveLevelsFile() throws Exception {
        MainClass main = new MainClass();
        main.getResult("depth_5_levels.xml");
        String json = readFile("src/main/resources/output1.json", StandardCharsets.UTF_8);
        boolean correct = false;
        int max_depth = 0;
        int count = 0;
        // max_depth + 1 - максимальный уровень вложенности элементов (должен быть равен 5)
        for (int i = json.indexOf('[')+1; i < json.length(); i++) {
            count = 0;
            for (int j = i; j < json.length(); j++) {
                if (json.charAt(j) == '[')
                    count++;
                if (json.charAt(j) == ']') {
                    i = j;
                    break;
                }
            }
            if (count > max_depth)
                max_depth = count;
        }
        if (max_depth+1 == 5)
            correct = true;
        Assert.assertTrue("Ошибка! XML c вложенностью из 5 элементов!",correct);
    }
    @Test
    public void testInvalidFile() throws Exception{
        MainClass main = new MainClass();
        try {
            main.getDocument("invalid_file.xml");
            Assert.assertTrue("Correct xml-file", false);
        }
        catch (SAXParseException e) {
            Assert.assertTrue(true);
        }
    }
}
