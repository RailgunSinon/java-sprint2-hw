package Entity;

public class YearReportItem {
    public Integer month;
    public Integer amount;
    public Boolean isExpense;

    public YearReportItem(Integer month,Integer amount,Boolean isExpense){
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
