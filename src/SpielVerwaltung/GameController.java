package SpielVerwaltung;

import LevelVerwaltung.LevelController;
import LevelVerwaltung.SpielerVerwaltung.Player;
import MenuVerwaltung.MenuController;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.SoundStore;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class GameController{
    private static GameController instance;
    private LevelController levelController;
    private MenuController menuController;
    private Settings settings;
    private Player player;
    private Music bgm;
    private int gameState; // 0 = Hochgefahren, Menü startet, 1 = spielend, 2 = pausiert, 3 = game over, 4 = level clear, 5 = you win
    private int levelnumber;
    private static int maxLevelNumber = 3; //Anzahl aller level
    public static GameController getInstance(){
        if(GameController.instance == null){
            GameController.instance = new GameController();
        }
        return GameController.instance;
    }
    private GameController(){
        this.levelController = new LevelController();
        this.settings = new Settings();
        this.menuController = new MenuController(settings);
        this.gameState = 0;
        this.levelnumber = 0;
        this.player = new Player("player.png",0,0,32,68,16);
        try {
            this.bgm = new Music("res/sounds/bgm.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        //System.out.println(((float)settings.getVolume())/100);
        SoundStore.get().setCurrentMusicVolume(((float)settings.getVolume())/100);
        SoundStore.get().setSoundVolume(((float)settings.getVolume())/100);
        bgm.loop();
    }
    public boolean update(GameContainer gameContainer){
        if (gameState == 0) {
            menuController.getTitleEntity().setAnimationPhase(0); //titlephase 0 = Welcome
            int start = menuController.update(gameContainer);
            if(start == 1){
                gameState = 1;
                levelController.loadLevel("level2.xml");
                this.player= levelController.getLevel().getPlayer();
                levelnumber = 1;
            }else if (start == 2) {
                return true;
            }
        }

        else if (gameState == 1){
            menuController.menuCD();
            int exit = levelController.update(gameContainer); // 0 für spielt weiter, 1 für oause, 2 für game over, 3 level clear
            menuController.getTitleEntity().setAnimationPhase(exit+1);
            if(exit == 1){
                gameState = 2;
            } else if(exit ==2){
                gameState = 3;
            } else if (exit == 3){
                gameState = 4;
                levelnumber++;
                if(levelnumber > maxLevelNumber){
                    gameState = 5;
                    menuController.getTitleEntity().setAnimationPhase(exit+2);
                }else {
                    levelController.loadLevel("level" + levelnumber + ".xml", player);
                }
            }
        }

        else if (gameState == 2 || gameState ==4){ //Pausiert oder level clear
            int start = menuController.update(gameContainer);
            if (start ==1){
                gameState =1;
            }else if (start == 2) {
                return true;
            }
        }

        else if (gameState == 3 || gameState == 5){ //Game over oder You win
            int start = menuController.update(gameContainer);
            if(start == 1){ //Spiel starten
                gameState = 1;
                levelController.loadLevel("level1.xml");
                this.player= levelController.getLevel().getPlayer();
                levelnumber = 1;
            }else if (start == 2) { //Fullscreen
                return true;
            }
        }
        return false;
    }
    public void render(GameContainer gameContainer, Graphics graphics)  throws SlickException {
        if (gameState != 1) {
            menuController.render(gameContainer, graphics);
        }
        if (gameState == 1){
            levelController.render(gameContainer, graphics);
        }
    }

    public Settings getSettings() {
        return settings;
    }
}

