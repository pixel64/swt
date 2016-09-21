package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SpielerVerwaltung.Player;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class HealthPack extends Tile {
    private int healing;
    public HealthPack(String path, double x, double y, int width, int height, int maxAnimPhase, int healing) {
        super(path, x, y, width, height, maxAnimPhase);
        this.healing = healing;
    }

    @Override
    public void onCollision(Player p) {
        System.out.println(healing);
        p.addHealth(healing);
        dead = true;
    }

    @Override
    public void onCollision(Enemy e) {

    }

    @Override
    public void onCollision(Shot s) {

    }
}
