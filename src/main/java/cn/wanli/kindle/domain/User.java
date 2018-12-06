package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 18:21
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_available")
    private Boolean available;

    @OneToMany(mappedBy = "user")
    private List<Kindle> kindles;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Kindle> getKindles() {
        return kindles;
    }

    public void setKindles(List<Kindle> kindles) {
        this.kindles = kindles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", available=" + available +
                ", kindles=" + kindles +
                ", groups=" + groups +
                '}';
    }
}
