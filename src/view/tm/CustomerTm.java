package view.tm;

public class CustomerTm {
    private String id;
    private String title;
    private String name;
    private String address;
    private String nic;
    private String mobile;
    private String email;

    public CustomerTm() {
    }

    public CustomerTm(String id, String title, String name, String address, String nic, String mobile, String email) {
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
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
