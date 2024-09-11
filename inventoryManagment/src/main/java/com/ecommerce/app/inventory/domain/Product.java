package com.ecommerce.app.inventory.domain;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

public class Product {

    private String productId;

    private String productCode;

    private String barCode;
    private String name;
    private String description;
    private double price;
    private int quantityAvailable;
//    private Category category;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

  /*  public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
*/
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
           //     ", category=" + category +
                '}';
    }
}
