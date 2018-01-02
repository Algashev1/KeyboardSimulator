package Entity;

import javax.persistence.*;

/**
 * Created by Gennadiy on 19.11.2017.
 */
@Entity
@Table(name = "exercise", schema = "keyboardsimulator")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id")
    private int eId;

    @Column(name = "e_text")
    private String eText;

    @ManyToOne(targetEntity = LevelEntity.class)
    @JoinColumn(name = "l_id")
    private LevelEntity level;

    public int getEId() {
        return eId;
    }

    public void setEId(int eId) {
        this.eId = eId;
    }

    public String getEText() {
        return eText;
    }

    public void setEText(String eText) {
        this.eText = eText;
    }

    public LevelEntity getLId() {
        return level;
    }

    public void setLId(LevelEntity level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseEntity that = (ExerciseEntity) o;

        if (eId != that.eId) return false;
        if (eText != null ? !eText.equals(that.eText) : that.eText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eId;
        result = 31 * result + (eText != null ? eText.hashCode() : 0);
        return result;
    }
}
