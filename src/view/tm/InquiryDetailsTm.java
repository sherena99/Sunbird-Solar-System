package view.tm;

public class InquiryDetailsTm {
    private String inquiryCode;
    private String productCode;
    private int qty;
    private double discount;
    private double unitPrice;

    public InquiryDetailsTm() {
    }

    public InquiryDetailsTm(String inquiryCode, String productCode, int qty, double discount, double unitPrice) {
        this.inquiryCode = inquiryCode;
        this.productCode = productCode;
        this.qty = qty;
        this.discount = discount;
        this.unitPrice = unitPrice;
    }

    public String getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(String inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "InquiryDetailsTm{" +
                "inquiryCode='" + inquiryCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
