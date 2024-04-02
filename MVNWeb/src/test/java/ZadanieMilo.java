import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ZadanieMilo {
    @Test
    public void mailRuLoginTest() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "C:\\ProgiIntelliJ_IDEA\\MVNWeb\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mail.ru/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Находим и нажимаем на кнопку "Вход"
        driver.findElement(By.xpath("//*[@class='resplash-btn resplash-btn_primary resplash-btn_mailbox-big mfflmpk__b85yax']")).click();

        // Находим модальное окно
        WebElement iframeElement = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(iframeElement);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // Ввод логина и нажимаем на кнопку вход
        driver.findElement(By.xpath("//*[@class='input-0-2-71']")).sendKeys("nastya.yfcnzrjirf@bk.ru");
        driver.findElement(By.xpath("//*[@class='base-0-2-79 primary-0-2-93']")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // Вводим пароль и нажимаем на кнопку войти
        driver.findElement(By.xpath("//*[@class='input-0-2-71 withIcon-0-2-72']")).sendKeys("R@hfrfn!wf09");
        driver.findElement(By.xpath("//*[@class='inner-0-2-81 innerTextWrapper-0-2-82']")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // Нажимаем на почту
        driver.findElement(By.xpath("//*[@class='ph-avatar-img svelte-dfhuqc']")).click();

        // Проверяем имя почты, выход из акаунта и проверка элемента "Создать Почту"
        Assert.assertEquals("Настя Чертова",driver.findElement(By.xpath("//*[@class='ph-name svelte-1popff4']")).getText());
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        driver.findElement(By.xpath("//*[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big mfflmpk__b85yax']")).isDisplayed();

        driver.quit();
    }
}
