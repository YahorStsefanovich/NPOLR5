package by.bsuir.stephanovich;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {

    private static ChromeDriver driver;

    private void clickButton(String selector){
        WebElement tagButton = driver.findElement(By.cssSelector(selector));
        tagButton.click();
    }

    private void setField(String selector, String arg){
        WebElement tagField = driver.findElement(By.cssSelector(selector));
        tagField.clear();
        tagField.sendKeys(arg);
    }

    private boolean containsText(List<WebElement> children, String value){
        for (WebElement elem : children){
            if (elem.getText().contains(value)) {
                return true;
            }
        }
        return false;
    }

    private void setCheckbox(String selector){
        WebElement tagField = driver.findElement(By.cssSelector(selector));
        if (!tagField.isSelected())
            tagField.click();
    }

    private static void setTimeout(int value){
        driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
    }

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://by.tribuna.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void titleTest(){
        String title = driver.getTitle();
        Assert.assertEquals("Футбол, хоккей, баскетбол, теннис, бокс, Формула-1 – все новости спорта на Tribuna.com", title);
    }

    @Test
    public void addTagTest(){
        final String tagName = "Федерер";

        //set tag
        clickButton("body > div.user-panel.user-panel--new.js-active.user-panel_state_loaded > div > div > div > div.user-panel__tags.js-tags-list > a");
        setField("body > div.popup__overlay.popup__overlay--usertags > div > div > div > div > div > form > input.search-block__input",
                tagName);
        setTimeout(1);
        clickButton("body > div.popup__overlay.popup__overlay--usertags > div > div > div > div > div > ul > li:nth-child(1) > a");

        //find this tag in list of tags
        WebElement element = driver.findElementByCssSelector("body > div.popup__overlay.popup__overlay--usertags > div > div > div > ul");
        Assert.assertTrue(containsText(element.findElements(By.xpath(".//li/a/span")), tagName));
    }

    @Test
    public void registerTest(){
        String email = "jagir-maloi@rambler.ru";
        String password = "KMxEby9z9V";
        String nickName = "Garis";
        String text = "Выберите все квадраты, в которых изображены";

        //click register button
        clickButton("body > div.user-panel.user-panel--new.js-active.user-panel_state_loaded > div > div > div > ul > li:nth-child(2) > a");

        //set login
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(2) > label:nth-child(1) > input",
                email);

        //set password
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(2) > label.auth__last-label > input",
                password);

        //confirm password
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(3) > label.auth__last-label > input",
                password);

        //set nickName
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(3) > label:nth-child(1) > input",
                nickName);

        //accept user's agreement
        setCheckbox("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(4) > label > input");

        //click register
        clickButton("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(5) > button");

        //setTimeout(10);

        //WebElement element = driver.findElementByClassName("rc-imageselect-desc-no-canonical");
        //Assert.assertTrue(element.getText().contains(text));
    }

    @Test
    public void authorizeTest(){
        String email = "jagir-maloi@rambler.ru";
        String password = "KMxEby9z9V";

        //click authorize button
        clickButton("body > div.user-panel.user-panel--new.js-active.user-panel_state_loaded > div > div > div > ul > li:nth-child(1) > a");

        //set login
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(3) > label > input",
                email);

        //set password
        setField("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(4) > label > input",
                password);

        //save user's data
        setCheckbox("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(5) > label > input");

        //click log in
        clickButton("body > div:nth-child(3) > div > div.auth__main > div.tabs.tabs_state_horizontal.auth__tabs.js-active.tabs_animate.tabs_animate_horizontal-slide > div > div.tabs__panel.tabs__panel_active > div > form > div:nth-child(6) > button");

        //setTimeout(10);

        //WebElement element = driver.findElementByClassName("rc-imageselect-desc-no-canonical");
        //Assert.assertTrue(element.getText().contains(text));
    }

    //@AfterClass
    public static void close(){
        setTimeout(5);
        driver.quit();
    }
}
