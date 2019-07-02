import org.junit.Test;

public class TestLanguagePicker extends TestBase{

    @Test
    public void testChangeLanguage() {

        String language = "Greek"; // The test can be run with any language which needs to be tested
        app.translationHelper.OpenHomePage();
        app.translationHelper.ChangeLanguage(language);
        app.translationHelper.AssertLanguageIsChanged(language);
    }

    @Test
    public void testExchangeLanguage() {
        app.translationHelper.OpenHomePage();
        app.translationHelper.ChangeLanguage("Hindi");
        app.translationHelper.GetLanguageListBeforeExchange();
        app.translationHelper.ExchangeBetweenLanguages();
        app.translationHelper.GetLanguageListAfterExchange();
        app.translationHelper.CheckLanguageExchange();
    }

    @Test
    public void testDeleteText() {
        String textToDelete = "Some test text";
        app.translationHelper.OpenHomePage();
        app.translationHelper.InputText(textToDelete);
        app.translationHelper.DeleteText();
        app.translationHelper.AssertTextIsDeleted();
    }

    @Test
    public void testLanguageRecognition(){
        String languageToDetect = "RUSSIAN";
        String textToRecognize = "Привет";
        app.translationHelper.OpenHomePage();
        app.translationHelper.InputText(textToRecognize);
        app.translationHelper.CheckDetectLanguageButtonIsPressed();
        app.translationHelper.CheckDetectedLanguage(languageToDetect);
    }
}
