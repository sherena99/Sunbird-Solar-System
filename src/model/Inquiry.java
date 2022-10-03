package model;

import java.util.ArrayList;

public class Inquiry {
    private String inquiryCode;
    private String time;
    private String date;
    private String custId;
    private String custName;
    private String siteAddress;
    private String croId;
    private String croCode;
    private String inquiryFrom;
    private String remarks;
    private double total;
    private double discount;
    private String status;
    private ArrayList<ProductDetails> product;

    public Inquiry() {
    }

    public Inquiry(String inquiryCode, String time, String date, String custId, String custName, String siteAddress, String croId, String croCode, String inquiryFrom, String remarks, double total, double discount, String status) {
        this.inquiryCode = inquiryCode;
        this.time = time;
        this.date = date;
        this.custId = custId;
        this.custName = custName;
        this.siteAddress = siteAddress;
        this.croId = croId;
        this.croCode = croCode;
        this.inquiryFrom = inquiryFrom;
        this.remarks = remarks;
        this.total = total;
        this.discount = discount;
        this.status = status;
    }

    public Inquiry(String inquiryCode, String time, String date, String custId, String custName, String siteAddress, String croId, String croCode, String inquiryFrom, String remarks, double total, double discount, String status, ArrayList<ProductDetails> product) {
        this.inquiryCode = inquiryCode;
        this.time = time;
        this.date = date;
        this.custId = custId;
        this.custName = custName;
        this.siteAddress = siteAddress;
        this.croId = croId;
        this.croCode = croCode;
        this.inquiryFrom = inquiryFrom;
        this.remarks = remarks;
        this.total = total;
        this.discount = discount;
        this.status = status;
        this.product = product;
    }

    public String getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(String inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getCroId() {
        return croId;
    }

    public void setCroId(String croId) {
        this.croId = croId;
    }

    public String getCroCode() {
        return croCode;
    }

    public void setCroCode(String croCode) {
        this.croCode = croCode;
    }

    public String getInquiryFrom() {
        return inquiryFrom;
    }

    public void setInquiryFrom(String inquiryFrom) {
        this.inquiryFrom = inquiryFrom;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ProductDetails> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductDetails> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "inquiryCode='" + inquiryCode + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", siteAddress='" + siteAddress + '\'' +
                ", croId='" + croId + '\'' +
                ", croCode='" + croCode + '\'' +
                ", inquiryFrom='" + inquiryFrom + '\'' +
                ", remarks='" + remarks + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                ", status='" + status + '\'' +
                ", product=" + product +
                '}';
    }
}