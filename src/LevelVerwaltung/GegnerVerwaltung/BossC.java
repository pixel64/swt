package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 29.09.2016.
 */
public class BossC extends Enemy {

    private int movingticks;
    private int maxmovingticks;
    private boolean walkingright;
    public BossC(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY, String damagesound, int movingticks) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY, damagesound);
        setFlying(true);
        this.movingticks = movingticks;
        this.maxmovingticks = movingticks;
        walkingright = true;
    }

    @Override
    public void update(Level l) {
        if(movingticks < 0 ){
            walkingright =!walkingright;
            movingticks = maxmovingticks;
        }
        double distancex = Math.abs(getX()-l.getPlayer().getX());
        double distancey = Math.abs(getY()-l.getPlayer().getY());
        boolean nearplayer = (distancex < 500 && distancey<600);
        if(walkingright){
            setX(getX()+getSpeedX());
            setAnimationPhase(7-  (((double)shotCDticks/getWeapon().getCooldownTicks())*8));
            if(getAnimationPhase()<3) {
                setAnimationPhase(3.3);
            }
            if(shotCDticks <= 0){
                l.addShot(new Shot(
                        getWeapon().getProjectileImagePath(),
                        getX()+getWidth(), getY() + getHeight() *1/3, 60, 60, 8, +getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                ));
                shotCDticks = getWeapon().getCooldownTicks();
            }

        }else{
            setAnimationPhase(7-  (((double)shotCDticks/getWeapon().getCooldownTicks())*8));
            if(getAnimationPhase()<3) {
                setAnimationPhase(3.3);
            }
            setX(getX()-getSpeedX());
            if(shotCDticks <= 0){
                l.addShot(new Shot(
                        getWeapon().getProjectileImagePath(),
                        getX()-getWidth(), getY() + getHeight() *1/3, 60, 60, 8, -getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                ));
                shotCDticks = getWeapon().getCooldownTicks();
            }
        }
        setAnimationPhase(getMaxAnimPhase()-0.2);
        if(getAnimationPhase() < 0) setAnimationPhase(2.9);
        this.movingticks--;
        super.update(l);
    }
}
