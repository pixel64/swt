package LevelVerwaltung;

import LevelVerwaltung.SpielerVerwaltung.Player;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class Fire extends Tile {

    private int damage;
    public Fire(String path, double x, double y, int width, int height, int maxAnimPhase, int damage) {
        super(path, x, y, width, height, maxAnimPhase);
        this.damage = damage;
    }

    @Override
    public void onCollision(Player p) {
        p.takeDamage(damage);
    }
}
