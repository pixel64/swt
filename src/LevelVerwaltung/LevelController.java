package LevelVerwaltung;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class LevelController {
    private Level level;

    public LevelController(){
    }

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawImage(level.getBackgroundImage(),0,0);
        double offsetX = (level.getPlayer().getX()+ level.getPlayer().getWidth()/2) -400;
        double offsetY = (level.getPlayer().getY()+level.getPlayer().getHeight()/2) -300;
        for(LevelEntity le : level.getTileList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        for(LevelEntity le : level.getEnemyList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        for(LevelEntity le : level.getShotList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        level.getPlayer().render(gameContainer, graphics,offsetX, offsetY);
    }

    public int update(GameContainer gameContainer){





        return 0;
    }

    public void loadLevel(String path){
        level = LevelFactory.loadLevel(path);
    }
}
