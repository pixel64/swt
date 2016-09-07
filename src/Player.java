import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by pixel on 07.09.16.
 */
public class Player extends BaseEntity {
    private GameContainer gameContainer;
    private float posX;
    private float posY;
    private float height;
    private float width;
    private float speed;
    public Player (GameContainer gameContainer){
        this.gameContainer = gameContainer;
        this.posX = 0;
        this.posY = 0;
        this.height = 40;
        this.width = 20;
        this.speed = 5;
    }
    @Override
    public void onUpdate(){
        Input in = this.gameContainer.getInput();
        if(in.isKeyDown(Input.KEY_LEFT)){
            this.posX -= speed;
        }
        if(in.isKeyDown(Input.KEY_RIGHT)){
            this.posX += speed;
        }
    }
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException{
        Rectangle rect = new Rectangle(0,0,this.width,this.height);
        rect.setX(this.posX);
        rect.setY(this.posY);
        graphics.draw(rect);
        graphics.fill(rect);
    }
}
