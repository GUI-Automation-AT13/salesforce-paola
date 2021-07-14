package base;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static core.config.EnvConfiguration.envVariables;

public class Login extends BaseTest {

    @Test
    public void accessLogin() {
        driver.get(envVariables("LOGIN_URL"));
        driver.findElement(By.id("username")).sendKeys(envVariables("USER"));
        driver.findElement(By.id("password")).sendKeys(envVariables("PASS"));
        driver.findElement(By.id("Login")).click();
    }
}
