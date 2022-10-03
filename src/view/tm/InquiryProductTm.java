package view.tm;

public class InquiryProductTm {
    private String code;
    private String name;
    private String description;
    private int qty;
    private double listPrice;
    private int discount;
    private double grossTotal;
    private double totalDiscount;
    private double total;

    public InquiryProductTm() {
    }

    public InquiryProductTm(String code, String name, String description, int qty, double listPrice, int discount, double grossTotal, double totalDiscount, double total) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.listPrice = listPrice;
        this.discount = discount;
        this.grossTotal = grossTotal;
        this.totalDiscount = totalDiscount;
        this.total = total;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InquiryProductTm{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                ", listPrice=" + listPrice +
                ", discount=" + discount +
                ", grossTotal=" + grossTotal +
                ", totalDiscount=" + totalDiscount +
                ", total=" + total +
                '}';
    }
}
