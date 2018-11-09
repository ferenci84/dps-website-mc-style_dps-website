package dps.webapplication.entities;

import dps.authentication.AuthenticableUser;
import dps.authentication.utils.PasswordAuthentication;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "ApplicationUser.getAll",
                query = "SELECT m FROM ApplicationUser m"
        ),
        @NamedQuery(
                name = "ApplicationUser.getById",
                query = "SELECT m FROM ApplicationUser m WHERE m.id = :id"
        ),
        @NamedQuery(name = "ApplicationUser.getByUsername", query = "SELECT m FROM ApplicationUser m WHERE m.username = :username"),
        @NamedQuery(name = "ApplicationUser.count", query="SELECT COUNT(m) FROM ApplicationUser m"),
})
public class ApplicationUser implements Serializable, AuthenticableUser {

    private static final long serialVersionUID = 6507165003243550180L;

    private static PasswordAuthentication authentication = new PasswordAuthentication();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5)
    private String username;

    private String passwordHash;

    @Transient
    @Min(5)
    private Integer passwordLength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        passwordLength = password.length();
        this.passwordHash = this.hash(password);
    }

    @Override
    public Boolean checkCredentials(String username, String password) {
        try {
            if (username.equals(this.username) && check(password, this.passwordHash)) return true;
            else return false;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    @JsonbTransient
    public Boolean isAuthorized(String operation) {
        return true;
    }

    private static String hash(String password)
    {
        return authentication.hash(password.toCharArray());
    }

    private static boolean check(String password, String hash)
    {
        return authentication.authenticate(password.toCharArray(),hash);
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
