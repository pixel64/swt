package LevelVerwaltung.SpielerVerwaltung;

import LevelVerwaltung.LevelEntity;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Player extends LevelEntity{
    private boolean onGround;
    private boolean onLadder;
    private double speedX, speedY;
    private int coffeeTicks;
    private int invulnerabilityTicks;
    private int health;
    private int weaponCooldownTicks;
    private Weapon weapon;
    private Sound damagetaken;

    public Player(String path, double x, double y, int width, int height, int maxAnimPhase) {
        super(path, x, y, width, height, maxAnimPhase);
        onGround = false;
        onLadder = false;
        coffeeTicks = 0;
        invulnerabilityTicks = 30;
        health = 100;
        weapon = new Weapon("kugelschreiber_projectile.png", 20, 20, 30, false, "kugelschreiber_sound.ogg","kugelschreiber.png");
        weaponCooldownTicks = 0;
        try {
            this.damagetaken = new Sound("res/sounds/player_dmg.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics, double offsetX, double offsetY) {
        if(!(invulnerabilityTicks > 0 && ((int)(invulnerabilityTicks/5))%2 == 1)){
            super.render(gameContainer, graphics, offsetX, offsetY);
        }
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
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

    public int getCoffeeTicks() {
        return coffeeTicks;
    }

    public void setCoffeeTicks(int coffeeTicks) {
        this.coffeeTicks = coffeeTicks;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isOnLadder() {
        return onLadder;
    }

    public void setOnLadder(boolean onLadder) {
        this.onLadder = onLadder;
    }

    public int getInvulnerabilityTicks() {
        return invulnerabilityTicks;
    }

    public void setInvulnerabilityTicks(int invulnerabilityTicks) {
        this.invulnerabilityTicks = invulnerabilityTicks;
    }

    public int getWeaponCooldownTicks() {
        return weaponCooldownTicks;
    }

    public void setWeaponCooldownTicks(int weaponCooldownTicks) {
        this.weaponCooldownTicks = weaponCooldownTicks;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void takeDamage(int damage){
        if(invulnerabilityTicks <= 0) {
            health -= damage;
            invulnerabilityTicks = 50;
            damagetaken.play();
        }
    }

    public void addHealth(int health){
        this.health += health;
        if(this.health >100) this.health = 100;
    }
}
