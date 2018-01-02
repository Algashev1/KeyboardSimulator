package Entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Gennadiy on 19.11.2017.
 */
@Entity
@Table(name = "zone", schema = "keyboardsimulator")
public class ZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id")
    private int zId;

    @Column(name = "z_value")
    private String zValue;

    public int getZId() {
        return zId;
    }

    public void setZId(int zId) {
        this.zId = zId;
    }

    public String getZValue() {
        return zValue;
    }

    public void setZValue(String zValue) {
        this.zValue = zValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZoneEntity that = (ZoneEntity) o;

        if (zId != that.zId) return false;
        if (zValue != null ? !zValue.equals(that.zValue) : that.zValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zId;
        result = 31 * result + (zValue != null ? zValue.hashCode() : 0);
        return result;
    }
}
