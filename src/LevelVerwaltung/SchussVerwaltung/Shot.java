package LevelVerwaltung.SchussVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.Level;
import LevelVerwaltung.LevelEntity;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.*;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Shot extends LevelEntity {
    private int damage;
    private double speedX;
    private double speedY;
    private boolean dead;
    private boolean influencedByGravity;
    private boolean ownerisplayer;
    private Sound shotcreated;
    public Shot(String path, double x, double y, int width, int height, int maxAnimPhase, double speedX, double speedY, boolean influencedByGravity, int damage, boolean player, String soundpath, boolean nearplayer) {
        super(path, x, y, width, height, maxAnimPhase);
        this.speedX = speedX;
        this.speedY = speedY;
        this.influencedByGravity = influencedByGravity;
        this.damage = damage;
        dead = false;
        ownerisplayer = player;
        try {
            shotcreated = new Sound("res/sounds/"+soundpath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        if(nearplayer)
        shotcreated.play();
    }

    public void onCollision(Enemy e){
        if(ownerisplayer){
        //System.out.println("hit enemy");
        e.takeDamage(damage);
        dead = true;
        }
    }

    public void onCollision(Player p){
        if(!ownerisplayer) {
            p.takeDamage(damage);
            dead = true;
        }
    }

    public void update(GameContainer gameContainer, Level l){
        setAnimationPhase(getAnimationPhase()-0.33);
        if(getAnimationPhase()<0)setAnimationPhase(getMaxAnimPhase());
        setX(getX()+speedX);
        if(influencedByGravity){
            speedY += l.getGravitation()*3;
        }
        setY(getY()+speedY);

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics, double offsetX, double offsetY) {
        Image renderedImage = img.copy();
        if(speedX < 0) renderedImage = renderedImage.getFlippedCopy(true,true);
        renderedImage.rotate((float)Math.toDegrees(Math.atan((speedY/speedX))));
        graphics.drawImage(renderedImage,(float)(x -offsetX),(float)(y -offsetY),(float)((x+width)-offsetX),(float)((y+height)-offsetY),((int)animationPhase)* width, 0,(((int)animationPhase)* width)+ width,height);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
