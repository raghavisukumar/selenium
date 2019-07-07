import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CategoryPage extends BasePage {

    CategoryPage(FirefoxDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    void add(List<String> categoryNames) {
        driver.get(getPath());
        for (String cat : categoryNames) {
            add(cat);
        }
    }

    private void add(String categoryName) {
        driver.findElement(By.id("go_add_category")).click();
        driver.findElement(By.id("name")).sendKeys(categoryName);
        driver.findElement(By.id("submit")).click();
        System.out.println("Added category: " + categoryName);
    }

    void verify(List<String> expectedCategoryNames) {
        List<WebElement> rows = getRows();
        List<String> actualCategoryNames = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            actualCategoryNames.add(cols.get(0).getText());
        }
        Collections.sort(expectedCategoryNames);
        Collections.sort(actualCategoryNames);
        Assertions.assertEquals(actualCategoryNames, expectedCategoryNames, "category list not matching");
    }

    private List<WebElement> getRows() {
        driver.get(getPath());
        WebElement categoryList = driver.findElement(By.className("table"));
        return categoryList.findElements(By.xpath("tbody/tr"));
    }

    void deleteAll() {
        int total = getRows().size();
        for (int i = 0; i < total; i++) {
            delete();
            sleep();
        }
    }

    private void delete() {
        driver.get(getPath());
        WebElement firstRow = getRows().get(0);
        WebElement secondColumn = firstRow.findElements(By.tagName("td")).get(1);
        List<WebElement> secondButton = secondColumn.findElements(By.tagName("a"));
        WebElement deleteButton = secondButton.get(1);
        deleteButton.click();
        driver.switchTo().alert().accept();
    }

    private void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    private String getPath() {
        return baseURL + "/listcategories.jsp";
    }
}