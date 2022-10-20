package Entity;

public class YearReportItem {
    private final Integer month;
    private final Integer amount;
    private final Boolean isExpense;

    public YearReportItem(Integer month,Integer amount,Boolean isExpense){
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean getExpense() {
        return isExpense;
    }
}
