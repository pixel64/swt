package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class HealthPack extends Tile {
    private Sound pickupsound;
    private int healing;
    public HealthPack(String path, double x, double y, int width, int height, int maxAnimPhase, int healing, String sound) {
        super(path, x, y, width, height, maxAnimPhase);
        this.healing = healing;
        try {
            pickupsound = new Sound("res/sounds/"+sound);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCollision(Player p) {
       // System.out.println(healing);
        pickupsound.play();
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
