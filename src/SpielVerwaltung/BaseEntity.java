package SpielVerwaltung;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.net.URL;

/**
 * Created by pixel on 05.09.2016.
 */
public class BaseEntity {
    private double x;
    private double y;
    private int width;
    private int height;
    private double animationPhase;
    private int maxAnimPhase;
    private Shape hitbox;
    private Image img;
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

    public double getY()
    {
        return y;
    }

    public double getAnimationPhase() {
        return animationPhase;
    }

    public void setAnimationPhase(double animationPhase) {
        this.animationPhase = animationPhase;
    }

    public int getMaxAnimPhase() {return maxAnimPhase;}
}
