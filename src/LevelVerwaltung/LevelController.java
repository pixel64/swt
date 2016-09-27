package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.GegnerVerwaltung.EnemyController;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SchussVerwaltung.ShotController;
import LevelVerwaltung.SpielerVerwaltung.Player;
import LevelVerwaltung.SpielerVerwaltung.PlayerController;
import org.newdawn.slick.*;

import java.util.ArrayList;

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
        double offsetX = (level.getPlayer().getX()+ level.getPlayer().getWidth()/2) -400;
        double offsetY = (level.getPlayer().getY()+level.getPlayer().getHeight()/2) -300;
        if(offsetY < 0){
            offsetY = 0;
        }
        graphics.drawImage(level.getBackgroundImage(),(float)-offsetX,(float)-offsetY);
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

        //Healthbar und waffenCD anzeigen
        graphics.setColor(Color.red);
        graphics.fillRect(20,20,200,20);
        graphics.setColor(Color.green);
        graphics.fillRect(20,20,level.getPlayer().getHealth()*2,20);
        graphics.setColor(Color.black);
        graphics.drawRect(20,20,200,20);


        //waffencd
        graphics.setColor(Color.white);
        graphics.fillRect(760,20,20,20);
        graphics.setColor(Color.black);
        graphics.drawRect(760,20,20,20);
        graphics.setColor(Color.gray);
        graphics.fillRect(761,(float)(40 - 20 *((double)level.getPlayer().getWeaponCooldownTicks()/(double)level.getPlayer().getWeapon().getCooldownTicks())),19,(float)(20 *((double)level.getPlayer().getWeaponCooldownTicks()/(double)level.getPlayer().getWeapon().getCooldownTicks())));

        //kaffeebar
        if(level.getPlayer().getCoffeeTicks() > 0) {
            graphics.setColor(Color.gray);
            graphics.fillRect(730, 20, 20, 20);
            graphics.setColor(Color.black);
            graphics.drawRect(730, 20, 20, 20);
            graphics.setColor(new Color(127,51,0));
            graphics.fillRect(731, (float) (40 - 20 * ((double) level.getPlayer().getCoffeeTicks() / 600)), 19, (float) (20 * ((double) level.getPlayer().getCoffeeTicks() / 600)));
        }




    }

    public int update(GameContainer gameContainer){

        //Clean up dead Tiles (used up powerups)
        ArrayList<Tile> deadTiles = new ArrayList<Tile>();
        for(Tile t: level.getTileList()){
            if (t.isDead()){
                deadTiles.add(t);
            }
        }
        for(Tile t: deadTiles){
            level.getTileList().remove(t);
        }

        if (gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)){//Pausiert
            return 1;
        }
        if(enemyController.update(gameContainer, level)){ //Level Clear, gegner mit isBoss ist gestorben
            return 3;
        }

        shotController.update(gameContainer, level);

        if(playerController.update(gameContainer, level)){ //Player ist gestorben
            return 2;
        }

        //Tileupdates;
        for(Tile t: level.getTileList()){
            t.update();
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

    public void loadLevel(String path, Player player) {
        level = LevelFactory.loadLevel(path);
        player.setX(level.getPlayer().getX());
        player.setY(level.getPlayer().getY());
        level.setPlayer(player);
    }

    public Level getLevel() {
        return level;
    }
}
