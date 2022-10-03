package view.tm;

public class PaymentTm {
    private String paymentId;
    private String date;
    private String time;
    private String inquiryId;
    private String customerName;
    private String croCode;
    private String paymentMethod;
    private double total;
    private double discount;

    public PaymentTm() {
    }

    public PaymentTm(String paymentId, String date, String time, String inquiryId, String customerName, String croCode, String paymentMethod, double total, double discount) {
        this.paymentId = paymentId;
        this.date = date;
        this.time = time;
        this.inquiryId = inquiryId;
        this.customerName = customerName;
        this.croCode = croCode;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.discount = discount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCroCode() {
        return croCode;
    }

    public void setCroCode(String croCode) {
        this.croCode = croCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "PaymentTm{" +
                "paymentId='" + paymentId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", inquiryId='" + inquiryId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", croCode='" + croCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
