import org.openqa.selenium.firefox.FirefoxDriver;

class BasePage {

    final FirefoxDriver driver;

    final String baseURL;

    BasePage(FirefoxDriver driver, String baseURL) {
        this.driver = driver;
        this.baseURL = baseURL;
    }
}
