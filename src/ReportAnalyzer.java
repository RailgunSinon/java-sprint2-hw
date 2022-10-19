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

  public ArrayList<String> reportDataReconciliation(ArrayList<YearReportItem> yearReport,HashMap<Integer, ArrayList<MonthReportItem>> monthReports){
    ArrayList<String> result = new ArrayList<>();
    int income = 0;
    int expenses = 0;
    for (Integer key : monthReports.keySet()){
      income = 0;
      expenses = 0;

      for(MonthReportItem item : monthReports.get(key)){
         if(item.isExpense == false){
           income += item.quantity*item.sumOfOne;
         } else {
           expenses += item.quantity*item.sumOfOne;
         }
      }

      for(YearReportItem item : yearReport){
        if(item.month == key){
          if(item.isExpense == false && item.amount != income){
            result.add("В месяце " + getMonthName(key) + " по доходам есть несоответствие.");
          }
          if(item.isExpense ==true && item.amount != expenses){
            result.add("В месяце " + getMonthName(key) + " по расходам есть несоответствие.");
          }
        }
      }

    }
    return result;
  }

  public String getMonthName(int number){
    String[] monthNames = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
    return monthNames[number-1];
  }

}
