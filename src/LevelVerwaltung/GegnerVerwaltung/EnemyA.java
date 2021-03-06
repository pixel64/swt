package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 22.09.2016.
 */
public class EnemyA extends Enemy {

    private boolean walkingright;
    private static double jumpPower;
    public EnemyA(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY,String damagesound) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY,damagesound);
        jumpPower= 11;
        walkingright = true;
    }

    @Override
    public void update(Level l) {
        if(currentPatternTicks <= 0){
            currentPattern = (int)(Math.random() * 4);
           // System.out.println("AI-Pattern:"+currentPattern);
            switch(currentPattern){
                case 0:
                    currentPatternTicks = 100;
                    break;
                case 1:
                    currentPatternTicks = 120;
                    break;
                case 2:
                    currentPatternTicks = 70;
                    break;
                case 3:
                    currentPatternTicks = 120;
                    break;
                default:
                    currentPatternTicks = 1;
                    break;
            }
        }else{
            double distancex = Math.abs(getX()-l.getPlayer().getX());
            double distancey = Math.abs(getY()-l.getPlayer().getY());
            boolean nearplayer = (distancex < 400 && distancey<300);
            switch(currentPattern){
                case 0://Vom Gegner weglaufen und Springen
                    if (l.getPlayer().getX() < getX()-1){
                        setX(getX()+getSpeedX());
                        walkingright = true;
                    }else if(l.getPlayer().getX() > getX()+1){
                        setX(getX()-getSpeedX());
                        walkingright = false;
                    }
                    //System.out.println(isOnGround()+","+jumpCDticks);
                    if(isOnGround() && jumpCDticks <= 0) {
                        setSpeedY(-jumpPower);
                        jumpCDticks = 75;
                    }
                    break;
                case 1://Zum gegner Laufen und Schießen
                    if (l.getPlayer().getX() < getX()-1){ //Verhinder stottern auf dem Spieler genau
                        setX(getX() - getSpeedX());
                        walkingright = false;
                        if(shotCDticks <= 0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()-24, getY() + getHeight() *1/3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }else if(l.getPlayer().getX() > getX()+1){
                        setX(getX() + getSpeedX());
                        walkingright = true;
                        if(shotCDticks<=0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()+ getWidth() + 4, getY() + getHeight() *1/3, 20, 5, 1, getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }

                    break;
                case 2://Vom Gegner Weglaufen
                    if (l.getPlayer().getX() < getX()-1){
                        setX(getX()+getSpeedX());
                        walkingright = true;
                    }else if(l.getPlayer().getX() > getX()+1){
                        setX(getX()-getSpeedX());
                        walkingright = false;
                    }

                    break;
                case 3://Zum Gegner laufen, springen und schießen.
                    if (l.getPlayer().getX() < getX()-1){
                        setX(getX() - getSpeedX());
                        walkingright = false;
                        if(shotCDticks <= 0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()-24, getY() + getHeight() *1/3, 20, 5, 1, -getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }else if(l.getPlayer().getX() > getX()+1){
                        setX(getX() + getSpeedX());
                        walkingright = true;
                        if(shotCDticks<=0){
                            l.addShot(new Shot(
                                    getWeapon().getProjectileImagePath(),
                                    getX()+ getWidth() + 4, getY() + getHeight() *1/3, 20, 5, 1, getWeapon().getSpeed(), (getWeapon().isGravity()? -5:0),getWeapon().isGravity(), getWeapon().getDamage(),false, getWeapon().getSound(),nearplayer
                            ));
                            shotCDticks = getWeapon().getCooldownTicks();
                        }
                    }
                    if(isOnGround() && jumpCDticks <=0){
                        setSpeedY(-jumpPower);
                        jumpCDticks = 75;
                    }
                    break;
                default: break;
            }
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
