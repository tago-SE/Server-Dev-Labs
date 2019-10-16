package models;

import javax.persistence.*;

@Entity
@Table(name = "T_User")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM  User u"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM  User u WHERE u.username = :name"),
        @NamedQuery(name = "User.findByNameContains", query = "SELECT u FROM User u WHERE u.username LIKE :search"),
        @NamedQuery(name = "User.validateCredentials", query = "SELECT u FROM  User u WHERE u.username = :name and u.password = :password")
})
public class User implements EntityInt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String username;

    @Column(name = "pass", nullable = false)
    private String password;

    @Version
    private long version;

    private String email;
    private String country;
    private String occupation;

    public User() { }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    // Query factory methods

    public static Query createFindUserByNameQuery(EntityManager em, String username) {
        return em.createNamedQuery("User.findByName").setParameter("name", username);
    }

    public static Query createValidateCredentialsQuery(EntityManager em, String username, String password) {
        return em.createNamedQuery("User.validateCredentials")
                .setParameter("name", username)
                .setParameter("password", password);
    }

    public static Query createGetAllQuery(EntityManager em) {
        return em.createNamedQuery("User.findAll");
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
