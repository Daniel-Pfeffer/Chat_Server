package entities;

import org.bouncycastle.util.encoders.Hex;

import javax.persistence.*;
import javax.websocket.Session;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Entity
@Table(name = "WS_USER")
@NamedQueries({
        @NamedQuery(name = "User_getUserByEmail", query = "Select u from UserBO u where u.email like :mail")
})
public class UserBO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    private Session session;
    private String username;
    private String email;
    @Transient
    private String password;
    private String passwordHash;
    private String salt;
    @Transient
    private String jwt;


    public UserBO() {
    }

    public UserBO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    private void hashPassword() {
        if (this.passwordHash == null) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(salt);

                byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
                this.passwordHash = new String(Hex.encode(hash));
                this.salt = new String(Hex.encode(salt));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
