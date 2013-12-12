package practicework;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import practicework.pages.ReserveConfirmPage;
import practicework.pages.ReserveInputPage;

public class PracticeWork3 {
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
        // ページ遷移の際に少し待機するため
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void test() {
        //File html = new File("reserveApp/index.html");
        File html = new File("reserveApp_Renewal/index.html");
        String url = "file:///" + html.getAbsolutePath();
        driver.get(url);
        
        ReserveInputPage inputPage = new ReserveInputPage(driver);



        
        try{

        	Thread.sleep(1000);
        	inputPage.setReserveDate("2013", "12", "8");
        	inputPage.setReserveTerm("3");

        	// TODO 残りの処理を記述してください
        	inputPage.setHeadCount("1");
        	inputPage.setBreakfast(false);
        	inputPage.setPlanA(false);
        	inputPage.setPlanB(false);
        	inputPage.setGuestName("abcd");

        	Thread.sleep(1000);
        	ReserveConfirmPage confirmPage = inputPage.goToNext();

        	// 2ページ目入力画面
        	assertThat(confirmPage.getPrice(), is("22750"));
        	assertThat(confirmPage.getDateFrom(), is("2013年12月8日"));
        	assertThat(confirmPage.getDateTo(), is("2013年12月11日"));
        	assertThat(confirmPage.getDaysCount(), is("3"));

        	// TODO 残りの処理を記述してください
        	assertThat(confirmPage.getHeadCount(), is("1"));
        	assertThat(confirmPage.getBreakfast(), is("なし"));
        	assertThat(confirmPage.existsPlanA(), is(false));
        	assertThat(confirmPage.existsPlanB(), is(false));
        	assertThat(confirmPage.getGuestName(), is("abcd"));
        	
        	Thread.sleep(1000);
        	
        	confirmPage.commit();  
        	Thread.sleep(1000);

        } catch (InterruptedException e) {
        	throw new RuntimeException(e);
        }

    }
    
}
