package model;

public class ProductDetails {
    private String productCode;
    private int soldQty;
    private double price;
    private double totalDiscount;

    public ProductDetails(String code, int qty, double total, double totalDiscount, double grossTotal) {
    }

    public ProductDetails(String productCode, int soldQty, double price, double totalDiscount) {
        this.productCode = productCode;
        this.soldQty = soldQty;
        this.price = price;
        this.totalDiscount = totalDiscount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "productCode='" + productCode + '\'' +
                ", soldQty=" + soldQty +
                ", price=" + price +
                ", totalDiscount=" + totalDiscount +
                '}';
    }
}
