import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TranslationHelper {

    private WebDriver driver;

    public TranslationHelper(WebDriver driver){
        this.driver = driver;
    }

    protected void OpenHomePage() {
        driver.get("https://translate.google.com/");
        driver.manage().window().setSize(new Dimension(1680, 965));
    }

    protected void ChangeLanguage(String language) {
        driver.findElement(By.xpath("//div[contains(@aria-label, 'More')]")).click();
        driver.findElement(By.id("sl_list-search-box")).click();
        driver.findElement(By.id("sl_list-search-box")).sendKeys(language);
        driver.findElement(By.id("sl_list-search-box")).sendKeys(Keys.ENTER);
    }

    protected void ExchangeBetweenLanguages(){
        driver.findElement(By.cssSelector(".swap > .jfk-button-img")).click();
    }

    protected void GetLanguageListBeforeExchange() {
        //  List<WebElement> elements = driver.findElements(By.cssSelector("div[id^='sugg-item-']")); //The list of all 6 languages
        List<WebElement> elements = driver.findElements(By.cssSelector("div[aria-pressed='true']"));
        for (int i=0; i<elements.size(); i++)
        {
            ArrayList<String> languagesBeforeExchange = new ArrayList<>();
            languagesBeforeExchange.add(elements.get(i).getText());
            System.out.println(languagesBeforeExchange);
        }
    }

    protected void GetLanguageListAfterExchange(){
        List<WebElement> elements = driver.findElements(By.cssSelector("div[aria-pressed='true']"));
        for (int i=0; i<elements.size(); i++)
        {
            ArrayList<String> languagesAfterExchange = new ArrayList<>();
            languagesAfterExchange.add(elements.get(i).getText());
            System.out.println(languagesAfterExchange);
        }
    }

    protected void CheckLanguageExchange(){
        //     Assert.assertEquals(languagesBeforeExchange.get(0),languagesAfterExchange.get(1));
    }

    protected void InputText(String someText){

        driver.findElement(By.id("source")).sendKeys(someText);
    }

    protected void DeleteText(){

        driver.findElement(By.cssSelector(".clear > .jfk-button-img")).click();
    }

    protected void AssertTextIsDeleted(){
        Assert.assertEquals(true, driver.findElement(By.id("source")).getText().equals("")); // Checking that the string is empty
    }

    protected void AssertLanguageIsChanged(String language){
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-pressed='true']")).getText().equalsIgnoreCase(language));
    }

    protected void CheckDetectLanguageButtonIsPressed() {
        WebElement detectLanguageButton = driver.findElement(By.cssSelector("div[value='auto']"));
        String isPressed = detectLanguageButton.getAttribute("aria-pressed");
        if (!isPressed.equals("true")) {
            detectLanguageButton.click();
        }
    }

    protected void CheckDetectedLanguage(String languageToDetect){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div[value='auto']")), "DETECTED"));
        Assert.assertTrue(driver.findElement(By.cssSelector("div[value='auto']")).getText().contains(languageToDetect));
    }
}
