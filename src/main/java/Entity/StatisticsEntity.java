package Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gennadiy on 19.11.2017.
 */
@Entity
@Table(name = "statistics", schema = "keyboardsimulator")
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int sId;

    @Column(name = "s_timefull")
    private int sTimefull;

    @Column(name = "s_max")
    private int sMax;

    @Column(name = "s_min")
    private int sMin;

    @Column(name = "s_speed")
    private int sSpeed;

    @Column(name = "s_errors")
    private int sErrors;

    @Column(name = "s_date")
    private Date sDate;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "u_id")
    private UserEntity user;

    @ManyToOne(targetEntity = ExerciseEntity.class)
    @JoinColumn(name = "e_id")
    private ExerciseEntity exercise;

    public int getSId() {
        return sId;
    }

    public void setSId(int sId) {
        this.sId = sId;
    }

    public int getSTimefull() {
        return sTimefull;
    }

    public void setSTimefull(int sTimefull) {
        this.sTimefull = sTimefull;
    }

    public int getSMax() {
        return sMax;
    }

    public void setSMax(int sMax) {
        this.sMax = sMax;
    }

    public int getSMin() {
        return sMin;
    }

    public void setSMin(int sMin) {
        this.sMin = sMin;
    }

    public int getSSpeed() {
        return sSpeed;
    }

    public void setSSpeed(int sSpeed) {
        this.sSpeed = sSpeed;
    }

    public int getSErrors() {
        return sErrors;
    }

    public void setSErrors(int sErrors) {
        this.sErrors = sErrors;
    }

    public Date getSData() {
        return sDate;
    }

    public void setSData(Date sDate) {
        this.sDate = sDate;
    }

    public UserEntity getUId() {
        return user;
    }

    public void setUId(UserEntity uId) {
        this.user = uId;
    }

    public ExerciseEntity getEId() {
        return exercise;
    }

    public void setEId(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticsEntity that = (StatisticsEntity) o;

        if (sId != that.sId) return false;
        if (sTimefull != that.sTimefull) return false;
        if (sMax != that.sMax) return false;
        if (sMin != that.sMin) return false;
        if (sSpeed != that.sSpeed) return false;
        if (sErrors != that.sErrors) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sId;
        result = 31 * result + sTimefull;
        result = 31 * result + sMax;
        result = 31 * result + sMin;
        result = 31 * result + sSpeed;
        result = 31 * result + sErrors;
        return result;
    }
}
