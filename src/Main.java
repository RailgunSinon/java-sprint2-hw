import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String userInput = "";



        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();
            userInput = scanner.next();

            switch (userInput){
                case "1":

                    break;
                case "2":
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

