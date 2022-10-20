import Entity.MonthReportItem;
import Entity.YearReportItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/*
Была идея отталкиваться от первой буквы в имени файла и универсализировать считывание из файла. Но по сути, нет необходимости из-зи того, что просто два метода
попадут в один под простой if. Будет нагромождение, мне показалось это бессмысленным в текущих ТЗ. Но дубликация напрягает, пока нет идей как сделать универсально
для того чтобы куда-то считать данные, а потом уже их распарсить.
 */

public class FileWorkClass {
    private static final int CURRENT_YEAR = 2021;
    private static final int NUMBER_OF_MONTH_REPORTS = 3;

    private String[] inputLines;

    private String[] getDataFromFile(String path){
        try {
            return Files.readString(Path.of(path)).split("\n");
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private ArrayList<MonthReportItem> readMonthReport(String reportName){
        ArrayList<MonthReportItem> resultSet = new ArrayList<>();
        inputLines = getDataFromFile("resources\\" + reportName);

        for(int i=1;i < inputLines.length;i++){
            String[] values = inputLines[i].split(",");
            MonthReportItem monthReportItem = new MonthReportItem(values[0],Boolean.valueOf(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3]));
            resultSet.add(monthReportItem);
        }

        return resultSet;
    }

    public HashMap<Integer, ArrayList<MonthReportItem>> readAllMonthReports() throws IOException {
        HashMap<Integer, ArrayList<MonthReportItem>> resultMap = new HashMap<>();

        for (int i = 1; i<= NUMBER_OF_MONTH_REPORTS;i++){
            ArrayList<MonthReportItem> monthReportItem;
            String fileName = "m." + CURRENT_YEAR + ( i < 10 ? "0" + i : i) + ".csv";
            monthReportItem = readMonthReport(fileName);
            resultMap.put(i,monthReportItem);
        }

        return resultMap;
    }

    public  ArrayList<YearReportItem> readYearReport() throws IOException{
        ArrayList<YearReportItem> resultSet = new ArrayList<>();
        String fileName = "y." + CURRENT_YEAR + ".csv";
        inputLines = getDataFromFile("resources\\" + fileName);

        for(int i=1;i < inputLines.length;i++){
            String[] values = inputLines[i].split(",");
            YearReportItem yearReportItem = new YearReportItem(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Boolean.valueOf(values[2]));
            resultSet.add(yearReportItem);
        }

        return resultSet;
    }

}
