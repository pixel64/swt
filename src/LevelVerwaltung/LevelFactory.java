package LevelVerwaltung;


import LevelVerwaltung.GegnerVerwaltung.*;
import LevelVerwaltung.SchussVerwaltung.Weapon;
import org.newdawn.slick.Image;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * Created by Kenanja on 21.09.2016.
 */
public final class LevelFactory {
    private LevelFactory(){

    }

    public static Level loadLevel(String path){
        Level l = new Level();
        try {
            File inputFile = new File("xml/level/"+path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Element level = doc.getDocumentElement();
            l.setBackgroundImage(new Image("res/img/" +level.getAttribute("backgroundimage")));
            l.setPlayerPos(Double.parseDouble(level.getAttribute("playerposx")), Double.parseDouble(level.getAttribute("playerposy")));
            NodeList levelElements = doc.getElementsByTagName("entity");
            for(int i = 0; i < levelElements.getLength(); i++){
                Node element = levelElements.item(i);
                if (element.getNodeType() == Node.ELEMENT_NODE){
                    Element e = (Element) element;
                    createLevelEntity(e,l);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l;
    }

    private static void createLevelEntity(Element element,Level level){
        switch(element.getAttribute("type")){
            case "tile":
                level.addTile(createTile(element));
                break;
            case "enemyA":
                level.addEnemy(createEnemyA(element));
                break;
            case "enemyB":
                level.addEnemy(createEnemyB(element));
                break;
            case "enemyC":
                level.addEnemy(createEnemyC(element));
                break;
            case "bossA":
                level.addEnemy(createBossA(element));
                break;
            case "bossB":
                level.addEnemy(createBossB(element));
                break;
            case "healthpack":
                level.addTile(createHealthPack(element));
                break;
            case "coffeepack":
                level.addTile(createCoffeePack(element));
                break;
            case "fire":
                level.addTile(createFire(element));
                break;
            case "weapon":
                level.addTile(createWeaponPickup(element));
                break;
            default:
                System.out.println("Falscher type der entity in xml datei");
                break;
        }

    }

    private static Tile createTile(Element e){
        return new Tile(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("animphases").item(0).getTextContent())
        );
    }

    private static EnemyA createEnemyA(Element e){
        return new EnemyA(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                16,
                Integer.parseInt(e.getElementsByTagName("health").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("isboss").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0)),
                Double.parseDouble(e.getElementsByTagName("speedx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speedy").item(0).getTextContent()),
                e.getElementsByTagName("damagesound").item(0).getTextContent()
        );
    }

    private static HealthPack createHealthPack(Element e){
        return new HealthPack(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("animphases").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("heal").item(0).getTextContent())
        );
    }
    private static CoffeePack createCoffeePack(Element e){
        return new CoffeePack(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("animphases").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("heal").item(0).getTextContent())
        );
    }

    private static Weapon createWeapon(Node n){
        Element e = (Element) n;
        return new Weapon(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Integer.parseInt(e.getElementsByTagName("damage").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speed").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("cooldown").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("gravity").item(0).getTextContent()),
                e.getElementsByTagName("sound").item(0).getTextContent(),
                e.getElementsByTagName("image").item(0).getTextContent()
                );
    }

    private static EnemyB createEnemyB(Element e){
        return new EnemyB(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                16,
                Integer.parseInt(e.getElementsByTagName("health").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("isboss").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0)),
                Double.parseDouble(e.getElementsByTagName("speedx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speedy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("moveduration").item(0).getTextContent()),
                e.getElementsByTagName("damagesound").item(0).getTextContent()
        );
    }

    private static Fire createFire(Element e){
        return new Fire(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("animphases").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("damage").item(0).getTextContent())
        );
    }
    private static EnemyC createEnemyC(Element e){
        return new EnemyC(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                16,
                Integer.parseInt(e.getElementsByTagName("health").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("isboss").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0)),
                Double.parseDouble(e.getElementsByTagName("speedx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speedy").item(0).getTextContent()),
                e.getElementsByTagName("damagesound").item(0).getTextContent()
        );
    }

    private static WeaponPickup createWeaponPickup(Element e){
        return new WeaponPickup(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("animphases").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0))
        );
    }
    private static BossA createBossA(Element e){
        return new BossA(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                20,
                Integer.parseInt(e.getElementsByTagName("health").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("isboss").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0)),
                Double.parseDouble(e.getElementsByTagName("speedx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speedy").item(0).getTextContent()),
                e.getElementsByTagName("damagesound").item(0).getTextContent()
        );
    }
    private static BossB createBossB(Element e){
        return new BossB(
                e.getElementsByTagName("path").item(0).getTextContent(),
                Double.parseDouble(e.getElementsByTagName("posx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("posy").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("height").item(0).getTextContent()),
                16,
                Integer.parseInt(e.getElementsByTagName("health").item(0).getTextContent()),
                Boolean.parseBoolean(e.getElementsByTagName("isboss").item(0).getTextContent()),
                createWeapon(e.getElementsByTagName("weapon").item(0)),
                Double.parseDouble(e.getElementsByTagName("speedx").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("speedy").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("boost").item(0).getTextContent()),
                e.getElementsByTagName("damagesound").item(0).getTextContent()
        );
    }

}
