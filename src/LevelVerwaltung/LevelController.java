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

    }

    public int update(GameContainer gameContainer){





        return 0;
    }

    public void loadLevel(String path){
        level = LevelFactory.loadLevel(path);
    }
}
