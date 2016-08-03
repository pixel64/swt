import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import lib.Application;

public class Main {

    public static void main(String[] args) {
        try{
            AppGameContainer app = new AppGameContainer(new Application("Prototype Jump and Run"));
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.start();
        }catch (SlickException se) {
            return;
        }
    }
}
