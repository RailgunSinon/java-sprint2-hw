import Entity.MonthReportItem;
import Entity.YearReportItem;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportAnalyzer {

    public int getProfitForMonth(ArrayList<YearReportItem> yearReport,int month){
        int profit = 0;

        for(YearReportItem item : yearReport){
          if(item.getMonth() == month){
            if(item.getExpense() == true){
              profit -= item.getAmount();
            } else {
              profit += item.getAmount();
            }
          }
        }

        return profit;
    }

  public double getExpensesOrIncomeAverage(ArrayList<YearReportItem> yearReport,boolean isExpense){
      double result = 0.0;
      int counter = 0;
      for(YearReportItem item : yearReport){
        if(item.getExpense() == isExpense){
          result += item.getAmount();
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
        if(item.getExpense() == isExpense && (item.getSumOfOne()*item.getQuantity()) > maxValue){
          name = item.getItemName();
          maxValue = item.getSumOfOne()*item.getQuantity();
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
         if(item.getExpense() == false){
           income += item.getSumOfOne()*item.getQuantity();
         } else {
           expenses += item.getSumOfOne()*item.getQuantity();
         }
      }

      for(YearReportItem item : yearReport){
        if(item.getMonth() == key){
          if(item.getExpense() == false && item.getAmount() != income){
            result.add("В месяце " + getMonthName(key) + " по доходам есть несоответствие.");
          }
          if(item.getExpense() ==true && item.getAmount() != expenses){
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
