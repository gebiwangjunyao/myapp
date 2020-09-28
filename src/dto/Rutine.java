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
@Table(name = "Rutine")

@NamedQueries({
    @NamedQuery(
            name = "getRutines",
            query = "SELECT r FROM Rutine AS r "
            ),
    @NamedQuery(
            name = "getMyRutines",
            query = "SELECT r FROM Rutine AS r where r.user_id.id= :id"
            ),
    @NamedQuery(
            name = "getRutine",
            query = "SELECT r FROM Rutine AS r where r.id= :id"
            ),
    @NamedQuery(
            name = "getMyShareRutines",
            query = "SELECT r FROM Rutine AS r where r.shareCheck = 1 AND NOT r.user_id.id= :id"
            ),
    @NamedQuery(
            name = "getShareRutine",
            query = "SELECT r FROM Rutine AS r where r.shareCheck = 1 "
            ),
    @NamedQuery(
            name = "getThisShareRutine",
            query = "SELECT r FROM Rutine AS r where r.shareCheck = 1 AND  r.id= :rutineId"
            ),
    @NamedQuery(
            name = "deleteRutine",
            query = "Delete from Rutine AS r where r.id = :rutineId"
            )
})
@Entity
public class Rutine {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(name = "rutine", nullable = false)
    private String rutine;

    @Column(name = "shareCheck", nullable = false)
    private Integer shareCheck;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getRutine() {
        return rutine;
    }

    public void setRutine(String rutine) {
        this.rutine = rutine;
    }

    public Integer getShareCheck() {
        return shareCheck;
    }

    public void setShareCheck(Integer shareCheck) {
        this.shareCheck = shareCheck;
    }






}
