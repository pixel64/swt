package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 22.09.2016.
 */
public class EnemyA extends Enemy {

    private static double jumpPower;
    public EnemyA(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY);
        jumpPower= 10;
    }

    @Override
    public void update(Level l) {
        //TODO override KI
        super.update(l);
        if(currentPatternTicks <= 0){
            currentPattern = (int)(Math.random() * 4);
            System.out.println("AI-Pattern:"+currentPattern);
            currentPatternTicks = 140;
        }else{
            switch(currentPattern){
                case 1:
                    if (l.getPlayer().getX() < getX()){
                        setX(getX() - getSpeedX());
                        if(shotCDticks <= 0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()-24, getY() + getHeight() *1/3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage()
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }else{
                        setX(getX() + getSpeedX());
                        if(shotCDticks<=0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()+ getWidth() + 4, getY() + getHeight() *1/3, 20, 5, 1, getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage()
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }

                    break;
                case 2:
                    if(l.getPlayer().getX() < getX()) {
                        setX(getX()+getSpeedX());
                    }
                    else{
                        setX(getX()-getSpeedX());
                    }

                    break;
                case 3:
                    if (l.getPlayer().getX() < getX()){
                        setX(getX() - getSpeedX());
                        if(shotCDticks <= 0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()-24, getY() + getHeight() *1/3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage()
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }else{
                        setX(getX() + getSpeedX());
                        if(shotCDticks<=0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()+ getWidth() + 4, getY() + getHeight() *1/3, 20, 5, 1, getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage()
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }
                    if(isOnGround() && jumpCDticks <=0){
                        setSpeedY(-jumpPower);
                        jumpCDticks = 75;
                    }


                    break;
                case 0:
                    if(l.getPlayer().getX() < getX()) {
                        setX(getX()+getSpeedX());
                    }
                    else{
                        setX(getX()-getSpeedX());
                    }
                    System.out.println(isOnGround()+","+jumpCDticks);
                    if(isOnGround() && jumpCDticks <= 0){
                        setSpeedY(-jumpPower);
                        jumpCDticks=75;
                    }


                    break;
                default: break;
            }
        }
        setOnGround(false);
        setY(getY()+getSpeedY());
    }
}
