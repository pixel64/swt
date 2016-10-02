package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
        boolean nearplayer = (distancex < 1000 && distancey<600);
        if(walkingright){
            setX(getX()+getSpeedX());
         /*   if(shotCDticks <= 15){
                setAnimationPhase(7- shotCDticks/5.0);
            } */
            if(shotCDticks <= 0){
                l.addShot(new Shot(
                        getWeapon().getProjectileImagePath(),
                        getX()+getWidth()/2, getY() + getHeight() *1/3,21, 27, 1, +1, (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                ));
                shotCDticks = getWeapon().getCooldownTicks();
            }

        }else{
            setAnimationPhase(7-  (((double)shotCDticks/getWeapon().getCooldownTicks())*8));
            /*if(shotCDticks <= 15){
                setAnimationPhase(7- shotCDticks/5.0);
            }*/
            setX(getX()-getSpeedX());
            if(shotCDticks <= 0){
                l.addShot(new Shot(
                        getWeapon().getProjectileImagePath(),
                        getX()+getWidth()/2, getY() + getHeight() *1/3, 21, 27, 1, -1, (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                ));
                shotCDticks = getWeapon().getCooldownTicks();
            }
        }

        this.movingticks--;
        super.update(l);
        setAnimationPhase(shotCDticks/50.0*4);

    }

}
