package LevelVerwaltung;

import SpielVerwaltung.BaseEntity;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class LevelEntity extends BaseEntity {
    public LevelEntity(String path, double x, double y, int width, int height, int maxAnimPhase) {
        super(path, x, y, width, height, maxAnimPhase);
    }
}
