package LevelVerwaltung.SchussVerwaltung;

import LevelVerwaltung.Level;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class ShotController {
    public void update(GameContainer gameContainer, Level l){
        ArrayList<Shot> deadShots = new ArrayList<Shot>();
        for(Shot s: l.getShotList()){
            if(s.isDead()){
                deadShots.add(s);
            }
            s.update(gameContainer,l);
        }
        for(Shot s: deadShots){
            l.getShotList().remove(s);
        }
        deadShots.clear();
    }
}
