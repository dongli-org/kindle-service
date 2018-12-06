package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 21:30
 */
@Entity
@Table(name = "tb_group")
public class Group implements Serializable {

    @Id
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tb_user_group",
            joinColumns = {@JoinColumn(name = "ug_group_id")},
            inverseJoinColumns = {@JoinColumn(name = "ug_user_id")})
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tb_group_role",
            joinColumns = {@JoinColumn(name = "gr_group_id")},
            inverseJoinColumns = {@JoinColumn(name = "gr_role_id")})
    private List<Role> roles;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
