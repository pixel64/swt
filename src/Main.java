
import SpielVerwaltung.GameController;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Main extends BasicGame {

    private boolean fullScreen;
    private static AppGameContainer app;

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try
        {
            app = new AppGameContainer(new Main("Project X-Matrikulation"));
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
        GameController intance = GameController.getInstance();
        fullScreen = intance.getSettings().isFullscreen();
        app.setDisplayMode(800, 600, fullScreen);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        GameController instance = GameController.getInstance();
        boolean switchFullscreen = instance.update(gameContainer);
        if(switchFullscreen){
            fullScreen = !fullScreen;
            instance.getSettings().setFullscreen(fullScreen);
            app.setDisplayMode(800,600,fullScreen);
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        GameController instance = GameController.getInstance();
        instance.render(gameContainer,graphics);
    }
}
