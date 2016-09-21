package LevelVerwaltung;

import SpielVerwaltung.BaseEntity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class LevelEntity extends BaseEntity {
    public LevelEntity(String path, double x, double y, int width, int height, int maxAnimPhase) {
        super(path, x, y, width, height, maxAnimPhase);
    }

    public void render(GameContainer gameContainer, Graphics graphics, double offsetX, double offsetY){
        graphics.drawImage(img,(float)(x -offsetX),(float)(y -offsetY),(float)((x+width)-offsetX),(float)((y+height)-offsetY),((int)animationPhase)* width, 0,(((int)animationPhase)* width)+ width,height);
    }
}
