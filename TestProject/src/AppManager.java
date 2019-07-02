import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class AppManager {

    protected WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    protected TranslationHelper translationHelper;

    public AppManager(){
        driver = new ChromeDriver(); ///Applications/Google Chrome.app
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        translationHelper = new TranslationHelper(driver);
    }

    public TranslationHelper getTranslator(){
        return translationHelper;
    }

    public void Stop(){
        driver.quit();
    }
}
