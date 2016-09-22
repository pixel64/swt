package MenuVerwaltung;

import SpielVerwaltung.Settings;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class MenuController {

    private int menuCDticks;
    private Menu menu;
    public MenuController(Settings s){
        menu = new Menu("MenuBackground.png", s);
    }

    public int update(GameContainer gameContainer){
        if(menuCDticks<= 0){
            Input input= gameContainer.getInput();
            if (input.isKeyDown(Input.KEY_ENTER)){
                menuCDticks = 10;
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
            } else if (input.isKeyDown(Input.KEY_DOWN)){
                menuCDticks = 10;
                menu.downPressed();
            }else if (input.isKeyDown(Input.KEY_UP)){
                menuCDticks = 10;
                menu.upPressed();
            }else if (input.isKeyDown(Input.KEY_LEFT)){
                menuCDticks = 10;
                menu.leftPressed();
            }else if (input.isKeyDown(Input.KEY_RIGHT)){
                menuCDticks = 10;
                menu.rightPressed();
            }
        }
        menuCDticks--;
        if(menuCDticks<=0)menuCDticks=0;
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
    public void menuCD(){
        menuCDticks=10;
    }
}
