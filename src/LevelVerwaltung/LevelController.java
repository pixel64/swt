package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.GegnerVerwaltung.EnemyController;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.ShotController;
import LevelVerwaltung.SpielerVerwaltung.Player;
import LevelVerwaltung.SpielerVerwaltung.PlayerController;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class LevelController {
    private Level level;
    private EnemyController enemyController;
    private ShotController shotController;
    private PlayerController playerController;

    public LevelController(){
        enemyController = new EnemyController();
        shotController = new ShotController();
        playerController = new PlayerController();

    }

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawImage(level.getBackgroundImage(),0,0);
        double offsetX = (level.getPlayer().getX()+ level.getPlayer().getWidth()/2) -400;
        double offsetY = (level.getPlayer().getY()+level.getPlayer().getHeight()/2) -300;
        for(LevelEntity le : level.getTileList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        for(LevelEntity le : level.getEnemyList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        for(LevelEntity le : level.getShotList())
        {
            le.render(gameContainer,graphics, offsetX, offsetY);
        }
        level.getPlayer().render(gameContainer, graphics,offsetX, offsetY);
    }

    public int update(GameContainer gameContainer){

        if (gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)){//Pausiert
            return 1;
        }
        if(enemyController.update(gameContainer, level) == true){ //Level Clear, gegner mit isBoss ist gestorben
            return 3;
        }

        shotController.update(gameContainer, level);

        if(playerController.update(gameContainer, level) == true){ //Player ist gestorben
            return 2;
        }

        //Check Collisions
        for(Tile t:level.getTileList()){
            for (Enemy e:level.getEnemyList()){
                if (e.getHitbox().intersects(t.getHitbox())){
                    t.onCollision(e);
                }
            }
            for(Shot s: level.getShotList()){
                if (s.getHitbox().intersects(t.getHitbox())){
                    t.onCollision(s);
                }
            }
            Player p = level.getPlayer();
            if(p.getHitbox().intersects(t.getHitbox())){
                t.onCollision(p);
            }
        }

        for(Shot s: level.getShotList()){
            for(Enemy e:level.getEnemyList()){
                if (s.getHitbox().intersects(e.getHitbox())){
                    s.onCollision(e);
                }
            }
            Player p = level.getPlayer();
            if(s.getHitbox().intersects(p.getHitbox())){
                s.onCollision(p);
            }
        }

        Player p = level.getPlayer();
        for(Enemy e: level.getEnemyList()){
            if(e.getHitbox().intersects(p.getHitbox())){
                e.onCollision(p);
            }
        }

        return 0;
    }

    public void loadLevel(String path){
        level = LevelFactory.loadLevel(path);
    }
}
