package DAO;

import Entity.ZoneEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public interface ZoneDAO {
    void addZone(ZoneEntity zone);
    void updateZone(ZoneEntity zone);
    List<ZoneEntity> getListZone();
    ZoneEntity getZone(int value);
    public void deleteZone(ZoneEntity zone);
}
