package introwork;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 入門課題その7:「表示された値のチェックをしてみよう」
 */
public class IntroWork7 {
    private WebDriver driver;
    
    private String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac環境の場合
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows環境の場合
        }
        File file = new File(path);
        return file.getAbsolutePath();
    }
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        driver = new ChromeDriver();
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void test() {
        File html = new File("introwork/introWork7.html");
        String url = "file:///" + html.getAbsolutePath();
        driver.get(url);
        
        // TODO 以下を削除して、代わりにプルダウンを選択する処理を記述してください
        try {
            Thread.sleep(1000);
            WebElement textLabel = driver.findElement(By.id("total"));
            assertThat(textLabel.getText(), is("9000"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // TODO ここまで削除してください
    }
}
