package cn.wanli.kindle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wanli
 * @date 2018-12-06 21:11
 */
@Entity
@Table(name = "tb_push")
public class KindlePush implements Serializable {
    @Id
    @Column(name = "push_id")
    private String id;

    @Column(name = "push_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "push_kindle_id", referencedColumnName = "kd_id")
    private Kindle kindle;

    @ManyToOne
    @JoinColumn(name = "push_book_id", referencedColumnName = "book_id")
    private KindleBook kindleBook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Kindle getKindle() {
        return kindle;
    }

    public void setKindle(Kindle kindle) {
        this.kindle = kindle;
    }

    public KindleBook getKindleBook() {
        return kindleBook;
    }

    public void setKindleBook(KindleBook kindleBook) {
        this.kindleBook = kindleBook;
    }

    @Override
    public String toString() {
        return "KindlePush{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", kindle=" + kindle +
                ", kindleBook=" + kindleBook +
                '}';
    }
}
