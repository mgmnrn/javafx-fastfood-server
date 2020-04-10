package application.model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private double amount;
    private double vat;
    private double cashAmount;
    private double nonCashAmount;
    private double cityTax;
    private int districtCode;
    private String customerNo;
    private int billType;
    private String state;
    private ArrayList<Stock> stocks;

    public Order(String orderId, double amount, double vat, double cashAmount, double nonCashAmount, double cityTax, int districtCode, String customerNo, int billType, String state, ArrayList<Stock> stocks) {
        this.orderId = orderId;
        this.amount = amount;
        this.vat = vat;
        this.cashAmount = cashAmount;
        this.nonCashAmount = nonCashAmount;
        this.cityTax = cityTax;
        this.districtCode = districtCode;
        this.customerNo = customerNo;
        this.billType = billType;
        this.state = state;
        this.stocks = stocks;
    }

    public String  getOrderId() {
        return orderId;
    }

    public void setOrderId(String  orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getNonCashAmount() {
        return nonCashAmount;
    }

    public void setNonCashAmount(double nonCashAmount) {
        this.nonCashAmount = nonCashAmount;
    }

    public double getCityTax() {
        return cityTax;
    }

    public void setCityTax(double cityTax) {
        this.cityTax = cityTax;
    }

    public int getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(int districtCode) {
        this.districtCode = districtCode;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }
}
