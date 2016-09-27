package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Weapon;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class EnemyC extends Enemy{
    private boolean aggro;
    private boolean walkingright;
    public EnemyC(String path, double x, double y, int width, int height, int maxAnimPhase, int health, boolean isBoss, Weapon weapon, double speedX, double speedY,String damagesound) {
        super(path, x, y, width, height, maxAnimPhase, health, isBoss, weapon, speedX, speedY,damagesound);
        aggro =false;
        walkingright= false;
    }

    @Override
    public void update(Level l) {
        if(!aggro) {
            if (l.getPlayer().getY() < getY() + getHeight() && l.getPlayer().getY() + l.getPlayer().getHeight() > getY()) {
                if (Math.abs(l.getPlayer().getX() - getX()) < 200)
                    aggro = true;
            }
        }
        else{
            if (l.getPlayer().getX() < getX()-1){
                setX(getX() - getSpeedX());
                walkingright = false;
            }else if(l.getPlayer().getX() > getX()+1){
                setX(getX() + getSpeedX());
                walkingright = true;
            }

            if(!walkingright) {
                setAnimationPhase(getAnimationPhase() - 0.4);
                if (getAnimationPhase() < 8) setAnimationPhase(15.9);
            }else {
                setAnimationPhase(getAnimationPhase() + 0.4);
                if (getAnimationPhase() >= 8) setAnimationPhase(0);
            }
        }
        super.update(l);
    }
}
