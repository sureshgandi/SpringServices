package com.ecommerce.app.ecommerceservice.db;

public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDatabase(String databaseName) {
        contextHolder.set(databaseName);
    }

    public static String getCurrentDatabase() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}