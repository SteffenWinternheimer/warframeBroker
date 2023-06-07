public class Order {
    String item;
    String order_type;
    int platinum;
    int quantity;
    boolean visible;

    public enum OrderType{
        sell,
        buy
    }

    public Order(String item, OrderType ordertype, int platinum, int quantity, boolean visible){
        this.item = item;
        this.order_type = ordertype.toString();
        this.platinum = platinum;
        this.quantity = quantity;
        this.visible = visible;
    }
}
