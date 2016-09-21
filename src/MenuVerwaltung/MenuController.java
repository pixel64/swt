package MenuVerwaltung;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class MenuController {

    private Menu menu;
    public MenuController(){
        menu = new Menu("MenuBackground.png");
    }

    public void update(GameContainer gameContainer){
        Input input= gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_ENTER)){
            menu.enterPressed();
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawImage(menu.getBackground(),0,0);
        ArrayList<MenuEntity> menuEntities= menu.getMenuEntities();
        for(MenuEntity me : menuEntities)
        {
            me.render(gameContainer, graphics);
        }
    }
}
