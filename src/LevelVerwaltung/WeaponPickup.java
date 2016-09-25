package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import LevelVerwaltung.SpielerVerwaltung.Player;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class WeaponPickup extends Tile {
    private Weapon weapon;
    public WeaponPickup(String path, double x, double y, int width, int height, int maxAnimPhase, Weapon weapon) {
        super(path, x, y, width, height, maxAnimPhase);
        this.weapon = weapon;
    }

    @Override
    public void onCollision(Shot s) {

    }

    @Override
    public void onCollision(Enemy e) {

    }

    @Override
    public void onCollision(Player p) {
        p.setWeapon(weapon);
        dead = true;
    }
}
