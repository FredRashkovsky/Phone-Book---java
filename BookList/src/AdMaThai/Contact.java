package AdMaThai;

public class Contact {
    private String name;
    private String phone;

    Contact(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    void setName(String name) {
        this.name = name.trim();
    }

    void setPhone(String phone) {
        this.phone = phone.trim();
    }

    String getName() {
        return this.name;
    }

    String getPhone() {
        return this.phone;
    }
}
