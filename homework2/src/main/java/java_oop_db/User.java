package java_oop_db;

public class User {

    private Long id;

    private String first_name;

    private String last_name;

    private Integer age;

    private String username;

    public User(Long id, String first_name, String last_name, Integer age) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public String getlast_name() {
        return last_name;
    }

    public Integer getAge() {
        return age;
    }

    public String getusername() {
        return username;
    }

    // Setter method (if needed)
    public void setusername(String username) {
        this.username = username;
    }
}
