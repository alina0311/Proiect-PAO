package entities.persoana;

import java.util.Objects;

public class User extends Persoana {
    protected String username;
    protected String password;
    protected String email;

    public User(String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, String username, String password, String email) {
        super(nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    public void afiseaza(){}

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
