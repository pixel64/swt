package LevelVerwaltung.SchussVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.Level;
import LevelVerwaltung.LevelEntity;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
    public Shot(String path, double x, double y, int width, int height, int maxAnimPhase, double speedX, double speedY, boolean influencedByGravity, int damage, boolean player) {
        super(path, x, y, width, height, maxAnimPhase);
        this.speedX = speedX;
        this.speedY = speedY;
        this.influencedByGravity = influencedByGravity;
        this.damage = damage;
        dead = false;
        ownerisplayer = player;
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
        setX(getX()+speedX);
        if(influencedByGravity){
            speedY += l.getGravitation()*3;
        }
        setY(getY()+speedY);

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics, double offsetX, double offsetY) {
        Image renderedImage = img.copy();
        if(speedX > 0){//Projektil fliegt nach rechts

        }
        else if (speedX < 0){//Projektil fliegt nach links
            renderedImage = renderedImage.getFlippedCopy(true, false);
        }else if (speedY < 0){//Projektil fliegt nach oben
            renderedImage.rotate(90);
        }else{//Projektil fliegt nach unten
            renderedImage.rotate(270);
        }
        graphics.drawImage(renderedImage,(float)(x -offsetX),(float)(y -offsetY),(float)((x+width)-offsetX),(float)((y+height)-offsetY),((int)animationPhase)* width, 0,(((int)animationPhase)* width)+ width,height);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
