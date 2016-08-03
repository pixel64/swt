package lib;

import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.*;

/**
 * Created by pixel on 03.08.16.
 */
public class Application extends BasicGame {
    private Image test;
    public Application(String title){
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            this.test = new Image("res/button1.png");
        } catch (SlickException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.test.draw(20,20);
    }
}
