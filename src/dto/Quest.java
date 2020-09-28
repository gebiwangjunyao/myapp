package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table(name = "quest")
@NamedQueries({
    @NamedQuery(
            name = "getQuests",
            query = "SELECT q FROM Quest AS q"
            ),
    @NamedQuery(
            name = "getQuest",
            query = "SELECT q FROM Quest AS q WHERE q.rutine_id.id =:rutineId ORDER BY q.startTime"
            ),
    @NamedQuery(
            name = "getTest",
            query = "SELECT q FROM Quest AS q WHERE q.id =:id"
            )
})
@Entity
public class Quest {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rutine_id", nullable = false)
    private Rutine rutine_id;

    @Column(name = "startTime", nullable = false)
    private String startTime;

    @Column(name = "endTime", nullable = false)
    private String endTime;

    @Column(name = "quest", nullable = false)
    private String quest;

    @Column(name = "content", nullable = false)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rutine getRutine_id() {
        return rutine_id;
    }

    public void setRutine_id(Rutine rutine_id) {
        this.rutine_id = rutine_id;
    }



    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
