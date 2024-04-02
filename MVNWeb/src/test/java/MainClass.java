import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClass {
    @Test
    public void FirstTest(){
        System.setProperty("webdriver.chrome.driver","C:\\ProgiIntelliJ_IDEA\\MVNWeb\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://habr.com/ru/all/");
        driver.manage().window().setSize(new Dimension(1280,1025));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']")).click();

        // Пример проверки текста элемента
        String searchInputPlaceholder = driver.findElement(By.xpath("//input[@name='q']")).getAttribute("placeholder");
        assert searchInputPlaceholder.equals("Поиск");

        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium WebDriver");
        driver.findElement(By.xpath("//*[@class='tm-svg-img tm-svg-icon']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.linkText("Что такое Selenium?")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Пример проверки текста элемента
        String expectedDateText = "28 сентября 2012 в 16:14";
        String actualDateText = driver.findElement(By.xpath("//*[@title='2012-09-28, 16:14']")).getText();

        String regex = "^\\d{2} (сен|сентября) \\d{4} в \\d{2}:\\d{2}$"; // Match format with month abbreviation or full name
        Assert.assertTrue("Date doesn't match", actualDateText.matches(regex));


        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[@href='/ru/articles/' and @class='footer-menu__item-link router-link-active']")).click();

        driver.quit();
    }
}
