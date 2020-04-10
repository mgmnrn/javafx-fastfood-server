package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class OrderContainer {

    private static OrderContainer orderContainer;
    private ObservableList orders;

    public OrderContainer() {
        orders = FXCollections.observableArrayList();
    }

    public static OrderContainer getOrderContainer() {
        if (orderContainer == null)
            orderContainer = new OrderContainer();
        return orderContainer;
    }

    public void setOrders(ObservableList<Order> orderList) {
        orders = orderList;
    }

    public ObservableList<Order> getOrderList() {
        return orders;
    }

    public void addFoods(Order order) throws SQLException {
        orders.add(order);
        DBAction.addOrder(order);
    }

    public int genId() throws SQLException {
        return DBAction.genId();
    }


    public void updateOrderState(Order order, String state) throws SQLException {
        order.setState(state);
        DBAction.updateOrderState(order);
    }
}

