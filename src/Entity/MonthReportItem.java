package Entity;

/*
1. В принципе можно было примитивы, но я хочу попробовать обёртки.
2. Мне кажется, все операции с деньгами должны быть всё же double, но ТЗ есь ТЗ.
====
3. Сделал с гетерами. Но решил оставить объектами, по идее из файла null быть не может, но всё же я не уверен.
 */

public class MonthReportItem {
      private final String itemName;
      private final Boolean isExpense;
      private final Integer quantity;
      private final Integer sumOfOne;

      public MonthReportItem(String itemName, Boolean isExpense, Integer quantity, Integer sumOfOne) {
            this.itemName = itemName;
            this.isExpense = isExpense;
            this.quantity = quantity;
            this.sumOfOne = sumOfOne;
      }

      public String getItemName(){
            return itemName;
      }

      public Integer getSumOfOne() {
            return sumOfOne;
      }

      public Boolean getExpense() {
            return isExpense;
      }

      public Integer getQuantity() {
            return quantity;
      }
}
