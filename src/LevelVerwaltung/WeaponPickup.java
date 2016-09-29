package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class WeaponPickup extends Tile {
    private Weapon weapon;
    private Sound pickupsound;
    public WeaponPickup(String path, double x, double y, int width, int height, int maxAnimPhase, Weapon weapon, String sound) {
        super(path, x, y, width, height, maxAnimPhase);
        this.weapon = weapon;
        try {
            pickupsound = new Sound("res/sounds/"+sound);
        } catch (SlickException e) {
            e.printStackTrace();
        }
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
        pickupsound.play();
    }
}
