package Entity;

/*
1. В принципе можно было примитивы, но я хочу попробовать обёртки.
2. Мне кажется, все операции с деньгами должны быть всё же double, но ТЗ есь ТЗ.
 */

public class MonthReportItem {
      public String itemName;
      public Boolean isExpense;
      public Integer quantity;
      public Integer sumOfOne;

      public MonthReportItem(String itemName, Boolean isExpense, Integer quantity, Integer sumOfOne) {
            this.itemName = itemName;
            this.isExpense = isExpense;
            this.quantity = quantity;
            this.sumOfOne = sumOfOne;
      }
}
