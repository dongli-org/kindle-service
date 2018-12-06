package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 17:25
 */
@Entity
@Table(name = "tb_kdbook")
public class KindleBook implements Serializable {
    @Id
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_path")
    private String path;

    @OneToMany(mappedBy = "kindleBook")
    private List<KindlePush> pushes;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<KindlePush> getPushes() {
        return pushes;
    }

    public void setPushes(List<KindlePush> pushes) {
        this.pushes = pushes;
    }

    @Override
    public String toString() {
        return "KindleBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", pushes=" + pushes +
                '}';
    }
}
