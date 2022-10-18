import Entity.MonthReportItem;
import Entity.YearReportItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String userInput = "";

        HashMap<Integer, ArrayList<MonthReportItem>> monthReports = new HashMap<>(); //Можно было и без них, но хочется закрепить
        ArrayList<YearReportItem> yearReports = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        ReportAnalyzer reportAnalyzer = new ReportAnalyzer();
        FileWorkClass fileWorkClass = new FileWorkClass();

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
                        break;
                    case "4":
                        break;
                    case "5":
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
}

