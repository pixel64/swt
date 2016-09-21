package MenuVerwaltung;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Menu {

    private Image background;
    private ArrayList<MenuEntity> menuEntities;
    private MenuEntity option0;
    private int currentOption;


    public Menu(String backgroundPath){
        menuEntities = new ArrayList<MenuEntity>();
        option0 = new MenuEntity("test.png",50,50,100,100,2);
        currentOption = 0;
        menuEntities.add(option0);
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

    public void enterPressed(){
        MenuEntity active = menuEntities.get(currentOption);
        active.setAnimationPhase((active.getAnimationPhase() + 1) % active.getMaxAnimPhase());
    }
}
