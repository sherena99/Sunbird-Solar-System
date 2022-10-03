package view.tm;

public class ComplainTm {
    private String complainNum;
    private String time;
    private String date;
    private String inquiryCode;
    private String custName;
    private String croCode;
    private String comAbout;
    private String comDetails;
    private String officer;

    public ComplainTm(String complainNum, String time, String date, String inquiryCode, String custName, String croCode, String comAbout) {
    }

    public ComplainTm(String complainNum, String time, String date, String inquiryCode, String custName, String croCode, String comAbout, String comDetails, String officer) {
        this.complainNum = complainNum;
        this.time = time;
        this.date = date;
        this.inquiryCode = inquiryCode;
        this.custName = custName;
        this.croCode = croCode;
        this.comAbout = comAbout;
        this.comDetails = comDetails;
        this.officer = officer;
    }

    public String getComplainNum() {
        return complainNum;
    }

    public void setComplainNum(String complainNum) {
        this.complainNum = complainNum;
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

    public String getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(String inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCroCode() {
        return croCode;
    }

    public void setCroCode(String croCode) {
        this.croCode = croCode;
    }

    public String getComAbout() {
        return comAbout;
    }

    public void setComAbout(String comAbout) {
        this.comAbout = comAbout;
    }

    public String getComDetails() {
        return comDetails;
    }

    public void setComDetails(String comDetails) {
        this.comDetails = comDetails;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    @Override
    public String toString() {
        return "ComplainTm{" +
                "complainNum='" + complainNum + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", inquiryCode='" + inquiryCode + '\'' +
                ", custName='" + custName + '\'' +
                ", croCode='" + croCode + '\'' +
                ", comAbout='" + comAbout + '\'' +
                ", comDetails='" + comDetails + '\'' +
                ", officer='" + officer + '\'' +
                '}';
    }
}


