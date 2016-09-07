import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by pixel on 05.09.2016.
 */
public class ObjectController {
    private static ObjectController instance;
    private ArrayList<BaseEntity> objects;
    public static ObjectController getInstance(){
        if(ObjectController.instance == null){
            ObjectController.instance = new ObjectController();
        }
        return ObjectController.instance;
    }
    private ObjectController(){
        this.objects = new ArrayList<>();
    }
    public void add(BaseEntity baseEntity){
        this.objects.add(baseEntity);
    }
    public void update(){
        for(BaseEntity m: this.objects){
            m.onUpdate();
        }
    }
    public void render(GameContainer gameContainer, Graphics graphics)  throws SlickException {
        for(BaseEntity m: this.objects){
            m.render(gameContainer, graphics);
        }
    }
}
