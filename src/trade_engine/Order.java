package trade_engine;

import java.util.PriorityQueue;
import java.util.Comparator;

public class Order {
    String type; // "buy" or "sell"
    double price;
    int quantity;
    long timestamp; // thời gian đặt lệnh

    public Order(String type, double price, int quantity, long timestamp) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

// So sánh lệnh mua ưu tiên giá cao hơn trước, sau đó đến thời gian
Comparator<Order> buyOrderComparator = (o1, o2) -> {
    if (o1.price != o2.price) {
        return Double.compare(o2.price, o1.price); // Giá cao hơn ưu tiên trước
    } else {
        return Long.compare(o1.timestamp, o2.timestamp); // Thời gian đặt trước ưu tiên trước
    }
};

// So sánh lệnh bán ưu tiên giá thấp hơn trước, sau đó đến thời gian
Comparator<Order> sellOrderComparator = (o1, o2) -> {
    if (o1.price != o2.price) {
        return Double.compare(o1.price, o2.price); // Giá thấp hơn ưu tiên trước
    } else {
        return Long.compare(o1.timestamp, o2.timestamp); // Thời gian đặt trước ưu tiên trước
    }
};

// Hàng đợi lưu lệnh mua và bán
PriorityQueue<Order> buyOrders = new PriorityQueue<>(buyOrderComparator);
PriorityQueue<Order> sellOrders = new PriorityQueue<>(sellOrderComparator);

// Thêm lệnh vào hàng đợi
public void addOrder(String type, double price, int quantity, long timestamp) {
    Order newOrder = new Order(type, price, quantity, timestamp);
    if (type.equals("buy")) {
        buyOrders.offer(newOrder);
    } else if (type.equals("sell")) {
        sellOrders.offer(newOrder);
    }
}

// Khớp lệnh
public void matchOrders() {
    while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
        Order buyOrder = buyOrders.peek();
        Order sellOrder = sellOrders.peek();

        // Kiểm tra giá có phù hợp để khớp không
        if (buyOrder.price >= sellOrder.price) {
            // Khớp lệnh theo số lượng nhỏ hơn
            int matchedQuantity = Math.min(buyOrder.quantity, sellOrder.quantity);

            System.out.println("Khớp lệnh: Mua " + matchedQuantity + " với giá " + sellOrder.price);

            // Cập nhật số lượng còn lại sau khi khớp
            buyOrder.quantity -= matchedQuantity;
            sellOrder.quantity -= matchedQuantity;

            // Xóa lệnh khỏi hàng đợi nếu đã khớp hết
            if (buyOrder.quantity == 0) buyOrders.poll();
            if (sellOrder.quantity == 0) sellOrders.poll();
        } else {
            break; // Không có lệnh nào khớp được, dừng lại
        }
    }
}
}


