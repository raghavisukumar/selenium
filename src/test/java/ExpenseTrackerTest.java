import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseTrackerTest {

    private static ResourceBundle rb = ResourceBundle.getBundle("configuration");

    private static FirefoxDriver driver;

    private static ExpensePage expensePage;

    private static LoginPage loginPage;

    private static CategoryPage categoryPage;

    @BeforeAll
    static void configure() {
        System.setProperty("webdriver.gecko.driver", rb.getString("webdriver.gecko.driver.path"));
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver, rb.getString("url"));
        categoryPage = new CategoryPage(driver, rb.getString("url"));
        expensePage = new ExpensePage(driver, rb.getString("url"));
    }

    @AfterAll
    static void tearDown() {
        expensePage.deleteAll();
        categoryPage.deleteAll();
        driver.quit();
    }

    @Test
    @Order(1)
    void verifyLogin() {
        loginPage.register(rb.getString("username"), rb.getString("password"));
        loginPage.login(rb.getString("username"), rb.getString("password"));
    }

    @Test
    @Order(2)
    void verifyCategory() {
        List<String> categories = Arrays.asList("food", "personal", "medical");
        categoryPage.add(categories);
        categoryPage.verify(categories);
    }

    @Test
    @Order(3)
    void verifyExpense() {
        Expense foodExpense = new Expense(LocalDateTime.now(), "food", 4.0f, "Food expenses");
        Expense personalExpense = new Expense(LocalDateTime.now(), "personal", 10.0f, "Personal expenses");
        Expense medicalExpense = new Expense(LocalDateTime.now(), "medical", 5.0f, "Medical expenses");
        List<Expense> expenses = Arrays.asList(foodExpense, personalExpense, medicalExpense);
        expensePage.add(expenses);
        expensePage.verify(expenses);
    }
}