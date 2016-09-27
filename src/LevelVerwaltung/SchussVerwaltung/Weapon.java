package LevelVerwaltung.SchussVerwaltung;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Weapon {
    private int damage;
    private double speed;
    private int cooldownTicks;
    private String projectileImagePath;
    private boolean gravity;
    private String soundpath;

    public Weapon(String path, int damage, double speed, int cooldownTicks, boolean gravity, String soundpath){
        this.damage = damage;
        this.speed = speed;
        this.cooldownTicks = cooldownTicks;
        this.projectileImagePath = path;
        this.gravity = gravity;
        this.soundpath = soundpath;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = cooldownTicks;
    }

    public String getProjectileImagePath() {
        return projectileImagePath;
    }

    public void setProjectileImagePath(String projectileImagePath) {
        this.projectileImagePath = projectileImagePath;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public String getSound(){return soundpath;}
}
