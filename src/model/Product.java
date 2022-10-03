package model;

import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private String description;
    private int discount;
    private double price;

    public Product() {
    }

    public Product(String code, String name, String description, int discount, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return discount == product.discount && Double.compare(product.price, price) == 0 && Objects.equals(code, product.code) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, description, discount, price);
    }
}
