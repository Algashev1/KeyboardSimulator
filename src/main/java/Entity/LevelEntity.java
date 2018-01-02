package Entity;

import javax.persistence.*;

/**
 * Created by Gennadiy on 19.11.2017.
 */
@Entity
@Table(name = "level", schema = "keyboardsimulator")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "l_id")
    private int lId;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "l_maxlength")
    private int lMaxlength;

    @Column(name = "l_minlength")
    private int lMinlength;

    @Column(name = "l_maxerrors")
    private int lMaxerrors;

    @Column(name = "l_time")
    private int lTime;

    @ManyToOne(targetEntity = ZoneEntity.class)
    @JoinColumn(name = "z_id")
    private ZoneEntity zone;

    public int getLId() {
        return lId;
    }

    public void setLId(int lId) {
        this.lId = lId;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public int getLMaxlength() {
        return lMaxlength;
    }

    public void setLMaxlength(int lMaxlength) {
        this.lMaxlength = lMaxlength;
    }

    public int getLMinlength() {
        return lMinlength;
    }

    public void setLMinlength(int lMinlength) {
        this.lMinlength = lMinlength;
    }

    public int getLMaxerrors() {
        return lMaxerrors;
    }

    public void setLMaxerrors(int lMaxerrors) {
        this.lMaxerrors = lMaxerrors;
    }

    public int getLTime() {
        return lTime;
    }

    public void setLTime(int lTime) {
        this.lTime = lTime;
    }

    public ZoneEntity getZId()
    {
        return zone;
    }

    public void setZId(ZoneEntity zone)
    {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LevelEntity that = (LevelEntity) o;

        if (lId != that.lId) return false;
        if (lMaxlength != that.lMaxlength) return false;
        if (lMinlength != that.lMinlength) return false;
        if (lMaxerrors != that.lMaxerrors) return false;
        if (lTime != that.lTime) return false;
        if (lName != null ? !lName.equals(that.lName) : that.lName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lId;
        result = 31 * result + (lName != null ? lName.hashCode() : 0);
        result = 31 * result + lMaxlength;
        result = 31 * result + lMinlength;
        result = 31 * result + lMaxerrors;
        result = 31 * result + lTime;
        return result;
    }
}
