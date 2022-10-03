package model;

public class Site {
    private String date;
    private String time;
    private String inquiryCode;
    private String address;
    private String siteType;
    private String roof;
    private String direction;
    private String extraWork;
    private double charges;
    private String note;

    public Site() {
    }

    public Site(String date, String time, String inquiryCode, String address, String siteType, String roof, String direction, String extraWork, double charges, String note) {
        this.date = date;
        this.time = time;
        this.inquiryCode = inquiryCode;
        this.address = address;
        this.siteType = siteType;
        this.roof = roof;
        this.direction = direction;
        this.extraWork = extraWork;
        this.charges = charges;
        this.note = note;
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

    public String getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(String inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getExtraWork() {
        return extraWork;
    }

    public void setExtraWork(String extraWork) {
        this.extraWork = extraWork;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Site{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", inquiryCode='" + inquiryCode + '\'' +
                ", address='" + address + '\'' +
                ", siteType='" + siteType + '\'' +
                ", roof='" + roof + '\'' +
                ", direction='" + direction + '\'' +
                ", extraWork='" + extraWork + '\'' +
                ", charges=" + charges +
                ", note='" + note + '\'' +
                '}';
    }
}