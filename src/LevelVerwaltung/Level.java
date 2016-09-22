package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Level {
    private ArrayList<Tile> tileList;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Shot> shotList;
    private Player player;
    private Image backgroundImage;
    private double gravitation;

    public Level(){
        tileList = new ArrayList<Tile>();
        enemyList = new ArrayList<Enemy>();
        shotList = new ArrayList<Shot>();
        player = new Player("player.png",0,0,50,100,1);//TODO an Bild anpassen
        gravitation = 0.6;
    }

    public void setBackgroundImage(Image i){
        backgroundImage = i;
    }

    public void setPlayerPos(double x, double y){
        player.setX(x);
        player.setY(y);
    }

    public void addTile(Tile t){
        tileList.add(t);
    }

    public void addEnemy(Enemy e){
        enemyList.add(e);
    }

    public void addShot(Shot s){
        shotList.add(s);
    }

    public void removeTile(Tile t){
        tileList.remove(t);
    }

    public void removeEnemy(Enemy e){
        enemyList.remove(e);
    }

    public void removeShot(Shot s){
        shotList.remove(s);
    }

    public ArrayList<Tile> getTileList(){return tileList;}

    public ArrayList<Enemy> getEnemyList(){return enemyList;}

    public ArrayList<Shot> getShotList(){return shotList;}

    public Player getPlayer(){return player;}

    public Image getBackgroundImage(){return backgroundImage;}

    public double getGravitation() {
        return gravitation;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
