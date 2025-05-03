package com.example.AkademyTasks.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class OrderDto {
    private String customerName;
    private List<String> products;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private double totalPrice;
    private String orderStatus;

    public OrderDto(String customerName, List<String> products, LocalDateTime orderDate, String shippingAddress, double totalPrice, String orderStatus) {
        this.customerName = customerName;
        this.products = products;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public OrderDto() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        OrderDto orderDto = (OrderDto) object;
        return Double.compare(totalPrice, orderDto.totalPrice) == 0 && Objects.equals(customerName, orderDto.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
               "customerName='" + customerName + '\'' +
               ", products=" + products +
               ", orderDate=" + orderDate +
               ", shippingAddress='" + shippingAddress + '\'' +
               ", totalPrice=" + totalPrice +
               ", orderStatus='" + orderStatus + '\'' +
               '}';
    }
}