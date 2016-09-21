package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.LevelEntity;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Enemy extends LevelEntity {
    public Enemy(String path, double x, double y, int width, int height, int maxAnimPhase) {
        super(path, x, y, width, height, maxAnimPhase);
    }
}
