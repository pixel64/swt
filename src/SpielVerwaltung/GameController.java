package SpielVerwaltung;

import LevelVerwaltung.LevelController;
import MenuVerwaltung.MenuController;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class GameController{
    private static GameController instance;
    private LevelController levelController;
    private MenuController menuController;
    private int gameState; // 0 = Hochgefahren, Menü startet
    public static GameController getInstance(){
        if(GameController.instance == null){
            GameController.instance = new GameController();
        }
        return GameController.instance;
    }
    private GameController(){
        this.levelController = new LevelController();
        this.menuController = new MenuController();
        this.gameState = 0;
    }
    public void update(GameContainer gameContainer){
        if (gameState == 0) {//TODO add Menu states
            menuController.getTitleEntity().setAnimationPhase(0); //titlephase 0 = Welcome
            menuController.update(gameContainer);
        }
    }
    public void render(GameContainer gameContainer, Graphics graphics)  throws SlickException {
        if (gameState == 0) {
            menuController.render(gameContainer, graphics);
        }
    }
}

