package LevelVerwaltung;

import LevelVerwaltung.SpielerVerwaltung.Player;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class CoffeePack extends HealthPack{
    public CoffeePack(String path, double x, double y, int width, int height, int maxAnimPhase, int healing) {
        super(path, x, y, width, height, maxAnimPhase, healing);
    }

    @Override
    public void onCollision(Player p) {
        super.onCollision(p);
        p.setCoffeeTicks(600);
    }
}
