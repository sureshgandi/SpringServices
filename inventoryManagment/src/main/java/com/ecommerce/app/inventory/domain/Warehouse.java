package com.ecommerce.app.inventory.domain;

public class Warehouse {

    private String warehouseId;
    private String name;
    private String location;


    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouse_id(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId='" + warehouseId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
