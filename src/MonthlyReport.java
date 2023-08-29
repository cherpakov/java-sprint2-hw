import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class MonthlyReport {
ArrayList<Sale> sales = new ArrayList<>(); // поле со списком из продаж
    public void loadFile(String path) {
       String content = readFileContentsOrNull(path); // считывание контент файла
        String[] lines = content.split("\r\n"); // разбивание по строкам
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");   // разделение на части
            String name = parts [0];
            boolean is_expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int price = Integer.parseInt(parts[3]); // извлечение нужных данных
        Sale sale = new Sale(name,is_expense, quantity,price);
        sales.add(sale); // в каждой строке создается объкт продажи , который складируется в список(сохраненная статистика)
        }
    }
    public int getExpenses () { // сумма трат за месяц
        int sum = 0;
        for (Sale sale : sales) {
           if (sale.is_expense) {
               sum += sale.quantity * sale.price;
           }
        }
        return sum;
    }

    public int getIncome() { // сумма доходов
        int sum =0;
        for (Sale sale : sales) {
            if (!sale.is_expense) {
                sum += sale.quantity * sale.price;
            }
        }
        return sum;
    }


    public  Sale getTopProduct() { // нахождение максимума

        Sale maxSale = null;
        for (Sale sale : sales) {
            if (!sale.is_expense) {
                if (maxSale == null) {
                    maxSale = sale;
                    continue;
                }
                if ((maxSale.price*maxSale.quantity) < (sale.price*sale.quantity)) {
                    maxSale = sale;
                }
            }
        }
        return maxSale;

    }
        public Sale getTopExpense() { // нахождение самой большой траты

            Sale maxExpense = null;
            for (Sale sale : sales) {
                if (sale.is_expense) {
                    if (maxExpense == null) {
                        maxExpense = sale;
                        continue;
                    }
                    if ((maxExpense.price*maxExpense.quantity) < (sale.price*sale.quantity)) {
                        maxExpense = sale;
                    }

                }
            }
            return maxExpense;

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

