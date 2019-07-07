import java.time.LocalDateTime;

public class Expense {
    LocalDateTime date;
    String category;
    Float amount;
    String reason;

    public Expense(LocalDateTime date, String category, Float amount, String reason) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public Float getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }
}
