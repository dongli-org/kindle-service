package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 21:39
 */
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    private String id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Group> groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", groups=" + groups +
                '}';
    }
}
