package model;

import java.util.Objects;

public class Customer {
    private String id;
    private String title;
    private String name;
    private String address;
    private String nic;
    private String mobile;
    private String email;

    public Customer(String string, String rstString, int anInt, double aDouble, double rstDouble) {
    }

    public Customer(String id, String title, String name, String address, String nic, String mobile, String email) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String
    toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(title, customer.title) && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(nic, customer.nic) && Objects.equals(mobile, customer.mobile) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, name, address, nic, mobile, email);
    }
}
