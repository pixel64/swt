package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;

/**
 * Created by Kenanja on 22.09.2016.
 */
public class EnemyA extends Enemy {

    public EnemyA(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, int damage) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, damage);
    }

    @Override
    public void update(Level l) {
        //TODO override KI
    }
}
