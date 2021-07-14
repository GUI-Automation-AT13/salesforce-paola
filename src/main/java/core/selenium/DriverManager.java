package core.selenium;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void setDriver();

    /**
     * Sets the type of web driver to use.
     * @return the web driver configure.
     */
    public WebDriver getDriver() {
        if (driver == null) {
            setDriver();
        }
        return driver;
    }

    /**
     * Removes the value set for driver.
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
