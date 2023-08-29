public class Sale {
    String name;
    boolean is_expense;
    int quantity;
    int price;

    public Sale(String name, boolean is_expense, int quantity, int price) {
        this.name = name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.price = price;
    }
}
