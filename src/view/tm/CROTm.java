package view.tm;

import java.util.Objects;

public class CROTm {
    private String id;
    private String name;
    private String address;
    private String code;
    private String mobile;
    private Double salary;

    public CROTm() {
    }

    public CROTm(String id, String name, String address, String code, String mobile, Double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.code = code;
        this.mobile = mobile;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CROTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", code='" + code + '\'' +
                ", mobile='" + mobile + '\'' +
                ", salary=" + salary +
                '}';
    }
}
