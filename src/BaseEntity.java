import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by pixel on 05.09.2016.
 */
public class BaseEntity {
    public BaseEntity() {
        ObjectController controller = ObjectController.getInstance();
        controller.add(this);
    }
    public void onUpdate(){

    }
    public void render(GameContainer container, Graphics graphics) throws SlickException {

    }
}
