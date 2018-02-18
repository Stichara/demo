package by.it.company.demoSpringHibernate.models;

import java.util.Objects;

public class UserModel {
    public final String login;
    public final String pswd;
    public final String role;

    public UserModel(String login, String pswd, String role) {
        this.login = login;
        this.pswd = pswd;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPswd() {
        return pswd;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(login, userModel.login) &&
                Objects.equals(pswd, userModel.pswd) &&
                Objects.equals(role, userModel.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, pswd, role);
    }
}
