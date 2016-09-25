package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class EnemyB extends Enemy {
    private int movingticks;
    private boolean walkingright;

    public EnemyB(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY, int movingticks) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY);
        this.movingticks = movingticks;
        walkingright =false;
    }

    @Override
    public void update(Level l) {

        if(currentPatternTicks <= 0) {
            currentPattern = (currentPattern+1) %2;
            currentPatternTicks = movingticks;
        }
        switch(currentPattern){
            case 0:
                setX(getX() - getSpeedX());
                walkingright = false;
                if(l.getPlayer().getX()+getWidth() < getX() && shotCDticks <= 0)
                {
                    l.addShot(new Shot(
                            getWeapon().getProjectileImagePath(),
                            getX() - 24, getY() + getHeight() * 1 / 3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity() ? -5 : 0), getWeapon().isGravity(), getWeapon().getDamage()
                    ));
                    shotCDticks = getWeapon().getCooldownTicks();
                }
                break;
            case 1:
                setX(getX() + getSpeedX());
                walkingright = true;
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
        if(!walkingright) {
            setAnimationPhase(getAnimationPhase() - 0.4);
            if (getAnimationPhase() < 8) setAnimationPhase(15.9);
        }else
        {
            setAnimationPhase(getAnimationPhase()+0.4);
            if(getAnimationPhase() >= 8) setAnimationPhase(0);
        }
        super.update(l);
    }
}
