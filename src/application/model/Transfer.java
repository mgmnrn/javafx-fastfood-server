package application.model;

import java.io.Serializable;

class Transfer implements Serializable {
    private String code;
    private String name;
    private Double quantity;
    private Double unitPrice;
    private Double totalAmount;

    Transfer(String code, String name, Double quantity, Double unitPrice, Double totalAmount) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
    }
}
