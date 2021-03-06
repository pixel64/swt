package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.LevelEntity;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Enemy extends LevelEntity {
    private int health;
    protected boolean isInvulnerable;
    private boolean dead;
    private boolean isBoss;
    private Weapon weapon;
    private double speedX;
    private double speedY;
    protected int currentPattern;
    protected int currentPatternTicks;
    protected int shotCDticks;
    protected int jumpCDticks;
    private boolean onGround;
    private boolean flying;
    private int maxhealth;
    private Sound damagetaken;
    public Enemy(String path, double x, double y, int width, int height, int maxAnimPhase, int health,boolean isBoss, Weapon weapon, double speedX, double speedY, String damagesound) {
        super(path, x, y, width, height, maxAnimPhase);
        this.health = health;
        this.isBoss = isBoss;
        dead = false;
        isInvulnerable = false;
        this.weapon = weapon;
        currentPattern=0;
        currentPatternTicks = 0;
        shotCDticks = weapon.getCooldownTicks();
        this.speedX = speedX;
        this.speedY = speedY;
        onGround = false;
        this.jumpCDticks = 0;
        flying = false;
        this.maxhealth = health;
        try {
            this.damagetaken = new Sound("res/sounds/"+damagesound);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }




    public void takeDamage(int damage){
        if(!isInvulnerable){
            //System.out.println(health + "," + damage);
            health -= damage;
            damagetaken.play();
            if(health <= 0) dead = true;
            damagetaken.play();
        }
        //System.out.println(health);
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void onCollision(Player p){
        Shape smallhitbox = new Rectangle((float)this.getX()+10, (float)this.getY()+5, this.getWidth()-20, this.getHeight()-5); //5 oben rechts und links weggenommen
        if(p.getHitbox().intersects(smallhitbox)) p.takeDamage(weapon.getDamage());
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
        this.jumpCDticks--;
        this.shotCDticks--;
        this.currentPatternTicks--;
        if(jumpCDticks < 0) jumpCDticks=0;
        if(shotCDticks < 0) shotCDticks=0;
        if(currentPatternTicks<0) currentPatternTicks=0;
        setOnGround(false);
        if(flying) setSpeedY(0); else setSpeedY(getSpeedY()+l.getGravitation());
        setY(getY()+getSpeedY());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public void heal(int amount){
        health += amount;
        if (health>maxhealth)health = maxhealth;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics, double offsetX, double offsetY) {
        super.render(gameContainer, graphics, offsetX, offsetY);
        //System.out.println("now painting healthbar");
        graphics.setColor(Color.red);
        graphics.fillRect((float)(getX()-offsetX),(float)(getY()-offsetY-10),getWidth(),5);
        graphics.setColor(Color.green);
        graphics.fillRect((float)(getX()-offsetX),(float)(getY()-offsetY-10),(float)(getWidth()*((double)health/(double)maxhealth)),5);
        graphics.setColor(Color.black);
        graphics.drawRect((float)(getX()-offsetX),(float)(getY()-offsetY-10),getWidth(),5);
    }
}
