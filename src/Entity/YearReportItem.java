package Entity;

public class YearReportItem {
    Integer month;
    Integer amount;
    Boolean isExpense;

    public YearReportItem(Integer month,Integer amount,Boolean isExpense){
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
