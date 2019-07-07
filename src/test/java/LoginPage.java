import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

class LoginPage extends BasePage {

    LoginPage(FirefoxDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    void register(String username, String password) {
        driver.get(getPath());
        driver.findElement(By.linkText("Register new user")).click();
        driver.findElement(By.name("login")).sendKeys(username);
        driver.findElement(By.name("password1")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    void login(String username, String password) {
        driver.get(getPath());
        driver.findElement(By.name("login")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        System.out.println("Logged in successfully: username" + username);
    }

    private String getPath() {
        String path = "/index.jsp";
        return baseURL + path;
    }
}