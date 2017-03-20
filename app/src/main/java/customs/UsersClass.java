package customs;

/**
 * Created by sparsh on 20/3/17.
 */

public class UsersClass {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String age;

    public UsersClass(String id, String name, String email, String phone, String age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }
}
