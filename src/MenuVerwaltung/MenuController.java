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

    public int update(GameContainer gameContainer){
        Input input= gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_ENTER)){
            int option = menu.enterPressed();
            if(option == 3)//EXIT GAME
            {
                gameContainer.exit();
            }
            if(option == 0){
                return 1;
            }
            if(option == 2)//Fullscreen
            {
                return 2;
            }
        } else if (input.isKeyPressed(Input.KEY_DOWN)){
            menu.downPressed();
        }else if (input.isKeyPressed(Input.KEY_UP)){
            menu.upPressed();
        }else if (input.isKeyPressed(Input.KEY_LEFT)){
            menu.leftPressed();
        }else if (input.isKeyPressed(Input.KEY_RIGHT)){
            menu.rightPressed();
        }
        return 0;
    }

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawImage(menu.getBackground(),0,0);
        menu.getTitleEntity().render(gameContainer,graphics);
        ArrayList<MenuEntity> menuEntities= menu.getMenuEntities();
        for(MenuEntity me : menuEntities)
        {
            me.render(gameContainer, graphics);
        }
    }

    public MenuEntity getTitleEntity(){return menu.getTitleEntity();}
}
