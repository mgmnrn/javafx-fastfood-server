package application.model;

public class Stock {

    private double unitPrice;
    private double cityTax;
    private double totalAmount;
    private int code;
    private int qty;
    private String name;
    private double vat;
    private String measureUnit;

    public Stock(double unitPrice, double cityTax, double totalAmount, int code, int qty, String name, double vat, String measureUnit) {
        this.unitPrice = unitPrice;
        this.cityTax = cityTax;
        this.totalAmount = totalAmount;
        this.code = code;
        this.qty = qty;
        this.name = name;
        this.vat = vat;
        this.measureUnit = measureUnit;
    }


    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getCityTax() {
        return cityTax;
    }

    public void setCityTax(double cityTax) {
        this.cityTax = cityTax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
