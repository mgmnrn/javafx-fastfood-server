package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DBAction {
    private OrderContainer orderContainer;
    public DBAction() {
        try {
            orderContainer = OrderContainer.getOrderContainer();
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders WHERE state = 'Хүлээгдэж буй'");
            ResultSet rs = statement.executeQuery();
            ObservableList orders = FXCollections.observableArrayList();
            while (rs.next()) {
                PreparedStatement statement1 = conn.prepareStatement("SELECT * FROM stocks WHERE orderId = " + rs.getInt("orderId"));
                ResultSet rs1 = statement1.executeQuery();
                ArrayList<Stock> stocks = new ArrayList<>();
                while (rs1.next()) {
                    stocks.add(new Stock(rs1.getDouble("unitPrice"), rs1.getDouble("cityTax"), rs1.getDouble("totalAmount"), rs1.getInt("code"), rs1.getInt("qty"), rs1.getString("name"), rs1.getDouble("vat"), rs1.getString("measureUnit")));
                }
                orders.add(new Order(rs.getString("orderId"), rs.getDouble("amount"), rs.getDouble("vat"), rs.getDouble("cashAmount"), rs.getDouble("nonCashAmount"), rs.getDouble("cityTax"), rs.getInt("districtCode"), rs.getString("customerNo"), rs.getInt("billType"), rs.getString("state"), stocks));
                rs1.close();
            }
            orderContainer.setOrders(orders);
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addOrder(Order order) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO orders(orderId, amount, vat, cashAmount, nonCashAmount, cityTax, districtCode, customerNo, billType, state) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order.getOrderId());
        pstmt.setDouble(2, order.getAmount());
        pstmt.setDouble(3, order.getVat());
        pstmt.setDouble(4, order.getCashAmount());
        pstmt.setDouble(5, order.getNonCashAmount());
        pstmt.setDouble(6, order.getCityTax());
        pstmt.setInt(7, order.getDistrictCode());
        pstmt.setString(8, order.getCustomerNo());
        pstmt.setInt(9, order.getBillType());
        pstmt.setString(10, order.getState());
        pstmt.executeUpdate();

        sql = "INSERT INTO stocks(orderId, unitPrice, cityTax, totalAmount, code, qty, name, vat, measureUnit) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt1 = conn.prepareStatement(sql);
        order.getStocks().forEach(i -> {
            try {
                pstmt1.setString(1, order.getOrderId());
                pstmt1.setDouble(2, i.getUnitPrice());
                pstmt1.setDouble(3, i.getCityTax());
                pstmt1.setDouble(4, i.getTotalAmount());
                pstmt1.setInt(5, i.getCode());
                pstmt1.setInt(6, i.getQty());
                pstmt1.setString(7, i.getName());
                pstmt1.setDouble(8, i.getVat());
                pstmt1.setString(9, i.getMeasureUnit());
                pstmt1.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        pstmt.close();
        pstmt1.close();
    }

    public static int genId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT max(orderId) FROM orders");
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("max(orderId)") + 1;
            rs.close();
            statement.close();
            return id;
        }
        rs.close();
        statement.close();
        return 0;
    }

    public static void updateOrderState(Order order) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE orders SET state = ? WHERE orderId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order.getState());
        pstmt.setString(2, order.getOrderId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public int getPort() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM port WHERE portId = 1");
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("port");
            rs.close();
            statement.close();
            return id;
        }
        return 0;
    }

    public void updatePort(int port) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE port SET port = ? WHERE portId = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, port);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
