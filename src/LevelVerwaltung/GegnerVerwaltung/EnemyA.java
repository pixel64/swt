package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 22.09.2016.
 */
public class EnemyA extends Enemy {
    private int currentPattern;
    private int currentPatternTicks;

    public EnemyA(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon);
    }


    @Override
    public void update(Level l) {
        //TODO override KI
    }
}
