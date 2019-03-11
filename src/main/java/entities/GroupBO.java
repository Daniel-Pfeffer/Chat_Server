package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WS_GROUP")
public class GroupBO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @ManyToMany
    private List<UserBO> users = new ArrayList<>();

    @OneToOne
    private UserBO admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserBO> getUsers() {
        return users;
    }

    public void setUsers(List<UserBO> users) {
        this.users = users;
    }

    public UserBO getAdmin() {
        return admin;
    }

    public void setAdmin(UserBO admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupBO() {
    }

    public GroupBO(UserBO admin, String name) {
        this.admin = admin;
        this.name = name;
    }
}
