package introwork;
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
 * 入門課題その4:「ラジオボタンを選択してみよう」
 */
public class IntroWork4 {
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
        File html = new File("introwork/introWork4.html");
        String url = "file:///" + html.getAbsolutePath();
        driver.get(url);
        
        // TODO 以下を削除して、代わりにラジオボタンを選択する処理を記述してください
        try {
        	Thread.sleep(1000);
        	WebElement radioButton = driver.findElement(By.id("on_radio"));
            radioButton.click();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // TODO ここまで削除してください
    }
}
