package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class EnemyB extends Enemy {
    private int movingticks;

    public EnemyB(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY, int movingticks) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY);
        this.movingticks = movingticks;
    }

    @Override
    public void update(Level l) {
        super.update(l);
        if(currentPatternTicks <= 0) {
            currentPattern = (currentPattern+1) %2;
            currentPatternTicks = movingticks;
        }
        switch(currentPattern){
            case 0://Vom Gegner weglaufen und Springen
                setX(getX() - getSpeedX());
                if(l.getPlayer().getX()+getWidth() < getX() && shotCDticks <= 0)
                {
                    l.addShot(new Shot(
                            getWeapon().getProjectileImagePath(),
                            getX() - 24, getY() + getHeight() * 1 / 3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity() ? -5 : 0), getWeapon().isGravity(), getWeapon().getDamage()
                    ));
                    shotCDticks = getWeapon().getCooldownTicks();
                }
                break;
            case 1://Zum gegner Laufen und Schießen
                setX(getX() + getSpeedX());
                if(l.getPlayer().getX()> getX()+getWidth() && shotCDticks <= 0)
                {
                    l.addShot(new Shot(
                            getWeapon().getProjectileImagePath(),
                            getX()+ getWidth() + 4, getY() + getHeight() *1/3, 20, 5, 1, getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage()
                    ));
                    shotCDticks = getWeapon().getCooldownTicks();
                }
                break;
            case 2://Vom Gegner Weglaufen

            default: break;
        }

    }
}
