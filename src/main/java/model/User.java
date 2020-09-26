package model;

public class User {
    private int id;
    private String FIO;
    private String email;
    private String password;
    private Status status;

    public int getId() {
        return id;
    }

    public User(int id, String FIO, String email, String password, Status status) {
        this.id = id;
        this.FIO = FIO;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Status {
        USER, UNKNOWN, ADMIN;
    }
}
