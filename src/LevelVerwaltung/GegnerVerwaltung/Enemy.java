package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.LevelEntity;
import LevelVerwaltung.SpielerVerwaltung.Player;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Enemy extends LevelEntity {
    private int health;
    private boolean isInvulnerable;
    private boolean dead;
    private boolean isBoss;
    private int damage;
    private double speedX;
    private double speedY;
    public Enemy(String path, double x, double y, int width, int height, int maxAnimPhase, int health,boolean isBoss, int damage) {
        super(path, x, y, width, height, maxAnimPhase);
        this.health = health;
        this.isBoss = isBoss;
        dead = false;
        isInvulnerable = false;
        this.damage = damage;
    }




    public void takeDamage(int damage){
        if(!isInvulnerable){
            System.out.println(health + "," + damage);
            health -= damage;
            if(health <= 0) dead = true;
        }
        System.out.println(health);
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void onCollision(Player p){
        p.takeDamage(damage);
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void update(Level l){
        //TODO KI
    }
}
