package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 27.09.2016.
 */
public class BossB extends Enemy {
    private boolean walkingright;
    private double speedmultiplier;
    private int speedTicks;
    private int speedCD;
    private boolean aggro;
    public BossB(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY, double speedmultiplier,String damagesound) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY,damagesound);
        aggro =false;
        walkingright= false;
        speedCD = (int)(Math.random()*100)+80;
        speedTicks = 0;
        this.speedmultiplier = speedmultiplier;
        setFlying(false);
    }

    @Override
    public void update(Level l) {
        if(!aggro) {
            if (l.getPlayer().getY() < getY() + getHeight() && l.getPlayer().getY() + l.getPlayer().getHeight() > getY()) {
                if (Math.abs(l.getPlayer().getX() - getX()) < 700)
                    aggro = true;
            }
        }
        else{
            if (l.getPlayer().getX() < getX()-5){
                setX(getX() - getSpeedX()*((speedTicks>0?speedmultiplier:1)));
                walkingright = false;
            }else if(l.getPlayer().getX() > getX()+5){
                setX(getX() + getSpeedX()*((speedTicks>0?speedmultiplier:1)));
                walkingright = true;
            }

            if(!walkingright) {
                setAnimationPhase(getAnimationPhase() - 0.4);
                if (getAnimationPhase() < 8) setAnimationPhase(15.9);
            }else {
                setAnimationPhase(getAnimationPhase() + 0.4);
                if (getAnimationPhase() >= 8) setAnimationPhase(0);
            }
            speedCD--;
            speedTicks--;
            if(speedCD <= 0){
                speedTicks = 40;
                speedCD = (int)(Math.random() * 100 +80);
            }
        }
        super.update(l);
    }
}
