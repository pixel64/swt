package MenuVerwaltung;

import SpielVerwaltung.Settings;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Menu {

    private Image background;
    private ArrayList<MenuEntity> menuEntities;
    private MenuEntity title;
    private int currentOption;
    private Settings settings;

    public Menu(String backgroundPath, Settings s){
        settings = s;
        menuEntities = new ArrayList<MenuEntity>();
        title = new MenuEntity("title.png",100,120,600,80,1);
        currentOption = 0; // 0 = spielen, 1 = Lautstärke, 2 = exit
        menuEntities.add(new MenuEntity("spielen.png", 100, 210, 600, 80 ,2));
        menuEntities.add(new MenuEntity("volume.png",100, 300, 600, 80, 12));
        menuEntities.add(new MenuEntity("fullscreen.png",100, 390, 600, 80, 2 ));
        menuEntities.add(new MenuEntity("exit.png",100,480,600, 80, 2));
        menuEntities.get(0).setAnimationPhase(1);
        menuEntities.get(1).setAnimationPhase(settings.getVolume()/20);
        try {
            background = new Image("res/img/" + backgroundPath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Image getBackground(){
        return background;
    }

    public ArrayList<MenuEntity> getMenuEntities() {
        return menuEntities;
    }

    public int enterPressed(){

        return currentOption;
    }

    public void upPressed(){
        MenuEntity me = menuEntities.get(currentOption);
        me.setAnimationPhase(me.getAnimationPhase() - me.getMaxAnimPhase()/2); //ACHTUNG, die Menüelemente haben die gleichen dinge 2mal, einmal hinterlegt, einmal nicht hinterlegt.
        currentOption--;
        if (currentOption < 0)
        {
            currentOption += menuEntities.size();
        }
        me = menuEntities.get(currentOption);
        me.setAnimationPhase(me.getAnimationPhase()+ me.getMaxAnimPhase()/2);
    }

    public void downPressed(){
        MenuEntity me = menuEntities.get(currentOption);
        me.setAnimationPhase(me.getAnimationPhase() - me.getMaxAnimPhase()/2); //ACHTUNG, die Menüelemente haben die gleichen dinge 2mal, einmal hinterlegt, einmal nicht hinterlegt.
        currentOption++;
        if (currentOption >= menuEntities.size()) {
            currentOption = 0;
        }
        me = menuEntities.get(currentOption);
        me.setAnimationPhase(me.getAnimationPhase()+ me.getMaxAnimPhase()/2);
    }

    public void leftPressed(){
        if(currentOption == 1){
            MenuEntity me = menuEntities.get(currentOption);
            if (me.getAnimationPhase() > me.getMaxAnimPhase()/2){
                me.setAnimationPhase(me.getAnimationPhase()-1);
                settings.setVolume(settings.getVolume()-20);
            }
        }
    }

    public void rightPressed(){
        if(currentOption == 1){
            MenuEntity me = menuEntities.get(currentOption);
            if (me.getAnimationPhase() < me.getMaxAnimPhase() -1){
                me.setAnimationPhase(me.getAnimationPhase()+1);
                settings.setVolume(settings.getVolume()+20);
            }
        }
    }
    public Settings getSettings(){return settings;}
    public MenuEntity getTitleEntity(){return title;}
}

