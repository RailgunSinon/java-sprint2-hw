import Entity.MonthReportItem;
import Entity.YearReportItem;
import Exeptions.DataWasNotReadException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    //Теперь всё одинаково
    private final static ReportAnalyzer reportAnalyzer = new ReportAnalyzer();
    private final static FileWorkClass fileWorkClass = new FileWorkClass();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput = "";

        HashMap<Integer, ArrayList<MonthReportItem>> monthReports = new HashMap<>(); //Можно было и без них, но хочется закрепить
        ArrayList<YearReportItem> yearReports = new ArrayList<>();

        while (true){
            printMenu();
            userInput = scanner.next();
            try {
                switch (userInput){
                    case "1":
                        System.out.println("Читаю информацию о месяцах...");
                        monthReports = fileWorkClass.readAllMonthReports();
                        System.out.println("Готово!");
                        break;
                    case "2":
                        System.out.println("Читаю отчётность за год...");
                        yearReports = fileWorkClass.readYearReport();
                        System.out.println("Готово!");
                        break;
                    case "3":
                        try {
                            System.out.println("Сверка данных по отчётам:");
                            checkIfReportsAreEmpty(yearReports,monthReports); //было желание проверить кастомные исключения, заодно решает проблему
                            dataReconciliation(yearReports,monthReports);
                        } catch (DataWasNotReadException exception){
                            System.out.println(exception.getMessage());
                        } catch (Exception exception){
                            System.out.println("Неизвестная ошибка!");
                        }
                        break;
                    case "4":
                        printMonthReport(monthReports);
                        break;
                    case "5":
                        printYearReport(yearReports);
                        break;
                    case "exit":
                        System.out.println("Выбран выход из приложения. Всего доброго!");
                        return;
                    default:
                        System.out.println("Данная команда не найдена! Повторите ввод");
                        break;

                }
            } catch (IOException exception){
                System.out.println("Ошибка при работе с файлами!");
            } catch (Exception exception){
                System.out.println("Неизвестная ошибка!");
            }
        }

    }

    private static void printMenu(){
        System.out.println("-----Меню-----");
        System.out.println("1. Считать все месячные отчёты.");
        System.out.println("2. Считать годовой отчёт.");
        System.out.println("3. Сверить отчёты.");
        System.out.println("4. Вывести информацию о всех месячных отчётах.");
        System.out.println("5. Вывести информацию о годовом отчёте.");
        System.out.println("Для выхода из программы введите exit.");
        System.out.println("Введите команду:");
    }



    private static void printYearReport(ArrayList<YearReportItem> yearReport){
        System.out.println("Годовой отчет за 2021:");
        System.out.println("Прибыть по месяцам:");
        for(int i=0,count=1;i<yearReport.size();i+=2,count++){
            System.out.println(reportAnalyzer.getMonthName(count) + ": " + reportAnalyzer.getProfitForMonth(yearReport,count));
        }
        System.out.println("Средний доход за год составил: " + String.format("%.3f",reportAnalyzer.getExpensesOrIncomeAverage(yearReport,false)));
        System.out.println("Средний расход за год составил: " + String.format("%.3f",reportAnalyzer.getExpensesOrIncomeAverage(yearReport,true)));
    }

    private static void printMonthReport(HashMap<Integer, ArrayList<MonthReportItem>> monthReports){
        System.out.println("Отчёты за месяцы:");
        for (Integer key : monthReports.keySet()){
            System.out.println(reportAnalyzer.getMonthName(key) + ": ");
            String[] result = reportAnalyzer.mostProfitableUnprofitableProduct(monthReports.get(key),false);
            System.out.println("Самый прибыльный товар " + result[0] + " принёс " + result[1]);
            result = reportAnalyzer.mostProfitableUnprofitableProduct(monthReports.get(key),true);
            System.out.println("Самый убыточный товар " + result[0] + " - растрата " + result[1]);
        }
    }

    private static void dataReconciliation(ArrayList<YearReportItem> yearReport,HashMap<Integer, ArrayList<MonthReportItem>> monthReports){
        System.out.println("Проводится сверка отчётов...");
        ArrayList<String> check = reportAnalyzer.reportDataReconciliation(yearReport,monthReports);
        if(check.isEmpty()){
            System.out.println("Сверка отчётов успешно завершена. Проблем не обнаружено!");
        } else {
            System.out.println("Обнаружены следующие проблемы:");
            for (String item : check){
                System.out.println(item);
            }
            System.out.println("Сверка отчётов успешно завершена.");
        }
    }

    private static void checkIfReportsAreEmpty(ArrayList<YearReportItem> yearReport,HashMap<Integer, ArrayList<MonthReportItem>> monthReports)
        throws DataWasNotReadException {
      if(yearReport.size() == 0 && monthReports.size() == 0){
          throw new DataWasNotReadException("Данные об отчётах месяцев и годов не были считаны!");
      } else if(yearReport.size() == 0) {
          throw new DataWasNotReadException("Данные об отчётах за год не были считаны!");
      } else if(monthReports.size() == 0) {
          throw new DataWasNotReadException("Данные об отчётах за месяцы не были считаны!");
      }
    }

}
