package models;

import javax.persistence.*;

@Entity
@Table(name = "T_User")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM  UserEntity u"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM  UserEntity u WHERE u.name = :name"),
        @NamedQuery(name = "User.findByNameContains", query = "SELECT u FROM UserEntity u WHERE u.name LIKE :search"),
        @NamedQuery(name = "User.validateCredentials", query = "SELECT u FROM  UserEntity u WHERE u.name = :name and u.password = :password")
})
public class User extends EntityInt {

    private long id;
    private String username;
    private String password;
    private String email;
    private String country;
    private String occupation;

    public User() { }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "pass", nullable = false)
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }

}
