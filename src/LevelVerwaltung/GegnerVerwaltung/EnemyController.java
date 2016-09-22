package LevelVerwaltung.GegnerVerwaltung;

import LevelVerwaltung.Level;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class EnemyController {

    public boolean update(GameContainer gameContainer, Level l){
        ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
        for (Enemy e: l.getEnemyList()){
            if(e.isDead()){
                if(e.isBoss())
                    return true;
                deadEnemies.add(e);
            }else{
                e.update(l);
            }

        }
        //Tote gegner aus dem Level entfernen;
        for (Enemy e: deadEnemies){
            l.getEnemyList().remove(e);
        }
        deadEnemies.clear();

        return false;

    }
}
