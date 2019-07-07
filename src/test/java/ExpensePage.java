import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

class ExpensePage extends BasePage {

    ExpensePage(FirefoxDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    private String getAddExpensePath() {
        return baseURL + "/addexpense.jsp";
    }

    void add(List<Expense> expenses) {
        for (Expense expense : expenses) {
            driver.get(getAddExpensePath());
            add(expense);
        }
    }

    private void add(Expense expense) {
        driver.findElement(By.id("day")).sendKeys(expense.getDate().getDayOfMonth() + "");
        driver.findElement(By.id("month")).clear();
        driver.findElement(By.id("month")).sendKeys(expense.getDate().getMonthValue() + "");
        driver.findElement(By.id("year")).sendKeys(expense.getDate().getYear() + "");
        Select category = new Select(driver.findElement(By.id("category")));
        category.selectByVisibleText(expense.getCategory());
        driver.findElement(By.id("amount")).sendKeys(expense.getAmount() + "");
        driver.findElement(By.id("reason")).sendKeys(expense.getReason() + "");
        driver.findElement(By.id("submit")).click();
        System.out.println("Added Expense: " + expense.getReason());
    }

    void verify(List<Expense> expenses) {

    }
    private List<WebElement> getRows() {
        WebElement expenseList = driver.findElement(By.className("table"));
        return expenseList.findElements(By.xpath("tbody/tr"));
    }

    void deleteAll() {
        int total = getRows().size();
        for (int i = 0; i < total; i++) {
            delete();
            sleep();
        }
    }
    private void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    private void delete() {
        driver.get(getExpensePath());
        WebElement firstRow = getRows().get(0);
        WebElement secondColumn = firstRow.findElements(By.tagName("td")).get(4);
        List<WebElement> secondButton = secondColumn.findElements(By.tagName("a"));
        WebElement deleteButton = secondButton.get(2);
        deleteButton.click();
        driver.switchTo().alert().accept();
    }
    private String getExpensePath() {
        return baseURL + "/listexpenses";
    }
}
