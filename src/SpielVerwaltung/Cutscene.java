package SpielVerwaltung;

import org.newdawn.slick.*;

/**
 * Created by Kenanja on 05.10.2016.
 */
public class Cutscene {

    private Image background;
    private BaseEntity bellimg;
    private BaseEntity player;
    private Music endingmusic;
    private Sound bell;
    private boolean bellswinging;
    private static Cutscene instance;
    private int phase;
    private int ringing;
    public static Cutscene getInstance(){
        if(instance == null){
            instance = new Cutscene();
        }
        return instance;
    }
    private Cutscene(){
        try {
            background = new Image("res/img/MenuBackground.png");
            endingmusic = new Music("res/sounds/degreesound.ogg");
            bellimg = new BaseEntity("bell.png", 600,300,80,60,4);
            player = new BaseEntity("player.png",0,400,32,68,16);
            bell = new Sound("res/sounds/bell.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        phase= 0;
        bellswinging = false;
    }

    public void playMusic(){
        endingmusic.play();
    }

    public boolean update(){
        if(phase == 0){
            player.setX(player.getX()+2);
            player.setAnimationPhase(player.getAnimationPhase()+0.2);
            if(player.getAnimationPhase() >= 8){
                player.setAnimationPhase(0.1);
            }
            if(player.getX() >= 600){
                phase++;
                ringing = 30;
            }
        }
        if(phase == 1){
            bell.play();
            ringing--;
            if(ringing<=0) phase++;
        }
        if(phase == 2){
            bellimg.setAnimationPhase(bellimg.getAnimationPhase()+0.3);
            player.setX(player.getX()+2);
            player.setAnimationPhase(player.getAnimationPhase()+0.2);
            if(player.getAnimationPhase() >= 8){
                player.setAnimationPhase(0.1);
            }
            if(player.getX() > 800){
                return true;
            }
        }
        return false;
    }

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawImage(background,0,0);
        bellimg.render(gameContainer, graphics);
        player.render(gameContainer, graphics);
    }
}
