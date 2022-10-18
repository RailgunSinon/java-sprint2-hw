import Entity.MonthReportItem;
import Entity.YearReportItem;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportAnalyzer {

    public int getProfitForMonth(ArrayList<YearReportItem> yearReport,int month){
        int profit = 0;

        for(YearReportItem item : yearReport){
          if(item.month == month){
            if(item.isExpense == true){
              profit -= item.amount;
            } else {
              profit += item.amount;
            }
          }
        }

        return profit;
    }

  public double getExpensesOrIncomeAverage(ArrayList<YearReportItem> yearReport,boolean isExpense){
      double result = 0.0;
      int counter = 0;
      for(YearReportItem item : yearReport){
        if(item.isExpense == isExpense){
          result += item.amount;
          counter++;
        }
      }
      return result/counter;
  }

  public String[] mostProfitableUnprofitableProduct(ArrayList<MonthReportItem> monthReport,boolean isExpense){
      String[] result = new String[2];
      double maxValue = 0;
      String name = "";
      for (MonthReportItem item : monthReport){
        if(item.isExpense == isExpense && (item.sumOfOne*item.quantity) > maxValue){
          name = item.itemName;
          maxValue = item.quantity*item.sumOfOne;
        }
      }

      result[0] = name;
      result[1] = String.format("%.3f",maxValue);

      return result;
  }

  

}
