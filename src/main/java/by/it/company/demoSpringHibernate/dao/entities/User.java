package by.it.company.demoSpringHibernate.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 5L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pswd", nullable = false)
    private String pswd;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(){}

    public User( String login, String pswd, Role role) {
        this.login = login;
        this.pswd = pswd;
        this.role = role;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(pswd, user.pswd) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, pswd, role);
    }
}
