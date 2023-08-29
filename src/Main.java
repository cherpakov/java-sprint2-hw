
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        YearlyReport yearRep = new YearlyReport();
        MonthlyReport monthRep = new MonthlyReport();
        MonthlyReport monthRepTwo = new MonthlyReport();
        MonthlyReport monthRepThree = new MonthlyReport();
        HashMap<Integer, MonthlyReport> reportMap = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {// Вывод доступных пунктов меню в консоль


            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчёт");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию обо всех отчётах");
            System.out.println("0 - выход");
            // Вызов соответствующего метода
            int userInput = scanner.nextInt();
            if (userInput == 0) {
                System.out.println("Выход из программы");
                break;
            }
           else if (userInput == 1) {
                monthRep.loadFile("resources/m.202101.csv");
                monthRepTwo.loadFile("resources/m.202102.csv");
                monthRepThree.loadFile("resources/m.202103.csv");
                reportMap.put(1, monthRep);
                reportMap.put(2, monthRepTwo);
                reportMap.put(3, monthRepThree);
            } else if (userInput == 2) {
                yearRep.loadFile("resources/y.2021.csv");
            } else if (userInput == 3) {
                Checker checker = new Checker(reportMap, yearRep);
                if (reportMap.isEmpty() || yearRep.yearsReport.isEmpty()) {
                    System.out.println("Сначала считайте месячные и годовые отчеты");
                } else {

                    int valuedId = checker.check();
                    if (valuedId == 0) {
                        System.out.println("Проверка пройдена.");
                    } else {
                        System.out.println("Ошибка в месяце №" + valuedId);
                    }
                }
            } else if (userInput == 4) {
                if (reportMap.isEmpty() || yearRep.yearsReport.isEmpty()) {
                    System.out.println("Сначала считайте месячные и годовые отчеты");
                } else {
                    for (Integer month : reportMap.keySet()) {
                        System.out.println("Месяц №" + month);
                        MonthlyReport rep = reportMap.get(month);
                        Sale sale = rep.getTopProduct();
                       int sum =  sale.price * sale.quantity;
                        System.out.println("Самый прибыльный товар " + sale.name + " на сумму " + sum);
                        Sale sale2 = rep.getTopExpense();
                        int sum2 = sale2.price * sale2.quantity;
                        System.out.println("Самая большая трата "+sale2.name + " на сумму " + sum2);
                    }
                    System.out.println("Год 2021");
                    for (int i = 0; i < yearRep.yearsReport.size(); i++) {
                        InfoYear info = yearRep.yearsReport.get(i);
                        if (!info.is_Expense) {
                            System.out.println("Прибыль за "+ info.month + " месяц " + yearRep.getIncome(info.month));
                        }
                    }
                    int inCome = yearRep.aveInCome();
                    int expense = yearRep.aveExpense();
                    System.out.println("Средний доход " + inCome);
                    System.out.println("Средний расход " + expense);
                     }
                } else {
                System.out.println("Команда не поддерживается!");

                }
            }

        }
    }

