package SpielVerwaltung;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.net.URL;

/**
 * Created by pixel on 05.09.2016.
 */
public class BaseEntity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected double animationPhase;
    protected int maxAnimPhase;
    protected Shape hitbox;
    protected Image img;
	public BaseEntity(String path, double x, double y, int width, int height, int maxAnimPhase)
        {
            this.x = x;
            this.y = y;
            this.maxAnimPhase= maxAnimPhase;
            try {
                System.out.println(path);
                img = new Image("res/img/"+path);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            this.width = width;
            this.height = height;
            hitbox = new Rectangle((int)x,(int)y,(int)width,(int)height);
            this.animationPhase = 0;
        }

    public Image getImage()
    {
        return img;
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        graphics.drawImage(img,(float)x,(float)y,(float)(x+width),(float)(y+height),((int)animationPhase)* width, 0,(((int)animationPhase)* width)+ width,height);
    }


    public Shape getHitbox()
    {
        return hitbox;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x){
        this.x=x;
        hitbox.setX((float)x);
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y){
        this.y=y;
        hitbox.setY((float)y);
    }

    public double getAnimationPhase() {
        return animationPhase;
    }

    public void setAnimationPhase(double animationPhase) {
        this.animationPhase = animationPhase;
        if(this.animationPhase >= maxAnimPhase){
            this.animationPhase -= maxAnimPhase;
        }
    }

    public int getMaxAnimPhase() {return maxAnimPhase;}
}
