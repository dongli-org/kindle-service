package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-12-06 21:15
 */
@Entity
@Table(name = "tb_kindle")
public class Kindle implements Serializable {
    @Id
    @Column(name = "kd_id")
    private String id;

    @Column(name = "kd_email")
    private String email;

    @Column(name = "kd_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "kd_user_id", referencedColumnName = "user_id")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Kindle{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
