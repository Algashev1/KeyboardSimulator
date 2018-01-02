package Entity;

import javax.persistence.*;

/**
 * Created by Gennadiy on 19.11.2017.
 */
@Entity
@Table(name = "user", schema = "keyboardsimulator")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int uId;

    @Column(name = "u_login")
    private String uLogin;

    @Column(name = "u_password")
    private String uPassword;

    @Column(name = "u_role")
    private int uRole;

    @ManyToOne(targetEntity = LevelEntity.class)
    @JoinColumn(name = "l_id")
    private LevelEntity level;

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public String getULogin() {
        return uLogin;
    }

    public void setULogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getURole() {
        return uRole;
    }

    public void setURole(int uRole) {
        this.uRole = uRole;
    }

    public LevelEntity getLId() {
        return level;
    }

    public void setLId(LevelEntity level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (uId != that.uId) return false;
        if (uRole != that.uRole) return false;
        if (uLogin != null ? !uLogin.equals(that.uLogin) : that.uLogin != null) return false;
        if (uPassword != null ? !uPassword.equals(that.uPassword) : that.uPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (uLogin != null ? uLogin.hashCode() : 0);
        result = 31 * result + (uPassword != null ? uPassword.hashCode() : 0);
        result = 31 * result + uRole;
        return result;
    }
}
