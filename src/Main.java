
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Main extends BasicGame {


    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try
        {
            AppGameContainer app = new AppGameContainer(new Main("Titel"));
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Player player1 = new Player(gameContainer);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        ObjectController instance = ObjectController.getInstance();
        instance.update();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        ObjectController instance = ObjectController.getInstance();
        instance.render(gameContainer,graphics);
    }
}
