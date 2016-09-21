package LevelVerwaltung;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.xml.SlickXMLException;
import org.newdawn.slick.util.xml.XMLElement;

/**
 * Created by Kenanja on 21.09.2016.
 */
public final class LevelFactory {
    private LevelFactory(){

    }

    public static Level loadLevel(String path){
        Level l = new Level();
        try {
            l.setBackgroundImage(new Image("res/img/level1background.png"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
        //TODO xml einlesen

        //Standardlevel für testzwecke
        l.setPlayerPos(300,300);
        l.addTile(new Tile("wall.png", 0, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 100, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 200, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 300, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 400, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 500, 560, 100, 40,1));
        l.addTile(new Tile("wall.png", 300, 460, 100, 40,1));
        l.addTile(new Tile("wall.png", 100, 300, 40, 400,1));
        l.addTile(new HealthPack("durstloescher.png", 500, 400,20,20, 2, 30));
        l.getPlayer().setHealth(60);
        return l;
    }

    private LevelEntity createLevelEntity(XMLElement element){
        LevelEntity levelEntity = null;
        try {  //TODO cases für unterklassen einbausen
            levelEntity = new LevelEntity(element.getAttribute("path"),element.getDoubleAttribute("posx"), element.getDoubleAttribute("posy"),element.getIntAttribute("width"), element.getIntAttribute("height"), element.getIntAttribute("animphases"));
        } catch (SlickXMLException e) {
            e.printStackTrace();
        }

        return levelEntity;
    }
}
