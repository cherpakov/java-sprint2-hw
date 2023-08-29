import java.util.HashMap;

public class Checker {
    HashMap<Integer, MonthlyReport> reportMap;
    YearlyReport yearlyReport;

    public Checker(HashMap<Integer, MonthlyReport> reportMap, YearlyReport yearlyReport) {
        this.reportMap = reportMap;
        this.yearlyReport = yearlyReport;
    }

    public int check() { // сверка отчетов


        for (Integer month : reportMap.keySet()) {
            int expensesFromMap = reportMap.get(month).getExpenses();
            int inComeFormMap = reportMap.get(month).getIncome();
            int expenseFromReport = yearlyReport.getExpenses(month);
            int inComeFormReport = yearlyReport.getIncome(month);
            if (expensesFromMap != expenseFromReport || inComeFormMap != inComeFormReport) {
                return month;

            }
        }
        return 0;
    }
}
