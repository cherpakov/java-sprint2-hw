import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class YearlyReport {
ArrayList<InfoYear> yearsReport = new ArrayList<>(); // элемент один месяц
     public void loadFile (String path) {
         String content = readFileContentsOrNull(path);
         String[] lines = content.split("\r?\n");
         for (int i = 1; i < lines.length; i++) {
             String line = lines[i]; // month,amount,is_expense
             String[] parts = line.split(",");
             int month = Integer.parseInt(parts[0]);
             int amount = Integer.parseInt(parts[1]);
             boolean is_expense = Boolean.parseBoolean(parts[2]);
             InfoYear infoYear = new InfoYear(month,amount,is_expense);
             yearsReport.add(infoYear);
         }
    }

    public int getExpenses ( int month) { // траты отдельного месяца
         int expense = 0;
        for (InfoYear infoYear : yearsReport) {
            if (infoYear.month == month) {
                if (infoYear.is_Expense) {
                    expense = infoYear.amount;
                }
            }

        }
        return expense;
    }
    public int getIncome ( int month) { // доходы отдельного месяца
        int expense = 0;
        for (InfoYear infoYear : yearsReport) {
            if (infoYear.month == month) {
                if (!infoYear.is_Expense) {
                    expense = infoYear.amount;
                }
            }

        }
        return expense;
    }

    public int aveInCome () {
         int sum = 0;
        int count = 0;
        for (InfoYear infoYear : yearsReport) {
            if (!infoYear.is_Expense) {
                sum += infoYear.amount;
                count++;
            }

        }
        return sum/count;
    }

    public int aveExpense () {
        int sum = 0;
        int count = 0;
        for (InfoYear infoYear : yearsReport) {
            if (infoYear.is_Expense) {
                sum += infoYear.amount;
                count++;
            }

        }
        return sum/count;
    }



    public  String readFileContentsOrNull(String path) {

        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return null;
        }
    }
}
