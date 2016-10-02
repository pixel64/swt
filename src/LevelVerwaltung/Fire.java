package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SpielerVerwaltung.Player;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Kenanja on 25.09.2016.
 */
public class Fire extends Tile {

    private int damage;
    public Fire(String path, double x, double y, int width, int height, int maxAnimPhase, int damage) {
        super(path, x, y, width, height, maxAnimPhase);
        this.damage = damage;
        this.setHitbox(new Rectangle((float)getX()+5,(float) getY()+5, getWidth() -10, getHeight()-5));
    }

    @Override
    public void onCollision(Enemy e) {
        double distleft = Math.abs(getHitbox().getX()+ - e.getX() - e.getWidth());
        double distright = Math.abs(e.getX() - getHitbox().getX() - (getWidth()-10));
        double disttop = Math.abs(getHitbox().getY() - e.getY() -e.getHeight());
        double distbot = Math.abs(e.getY() - getHitbox().getY() - (getHeight()-5));

        //Von Oben
        if (disttop <= distbot)
        {
            //Von Links
            if (distleft <= distright)
            {
                if (disttop <= distleft  || disttop <= e.getSpeedY()+0.0001)// Abstand Oben < Abstand links
                {
                    //System.out.println("1");
                    e.setY(getHitbox().getY() - e.getHeight());
                    e.setOnGround(true);
                    e.setSpeedY(0);
                }
                else
                // Abstand links < Abstand Oben
                {
                    //System.out.println("2");
                    e.setX(getX() - e.getWidth());
                }
            }
            else
            //Von Rechts
            {
                if (disttop <= distright || disttop <= e.getSpeedY()+0.0001)// Abstand Oben < Abstand rechts
                {
                    //System.out.println("3");
                    e.setY(getY() - e.getHeight());
                    e.setOnGround(true);
                    e.setSpeedY(0);

                }
                else
                // Abstand rechts < Abstand Oben
                {
                    //System.out.println("4");
                    e.setX(getX() + getWidth());
                }

            }

        }
        else
        //Von Unten
        {
            if (distleft < distright)//Von Links
            {
                if (distbot < distleft || disttop <= Math.abs(e.getSpeedY()+0.0001))// Abstand Unten < Abstand links
                {
                    //System.out.println("5");
                    e.setY(getY() + getHeight());
                    e.setSpeedY(1);

                }
                else
                // Abstand links < Abstand Unten
                {
                    //System.out.println("6");
                    e.setX(getX() - e.getWidth());

                }
            }
            else
            //Von Rechts
            {
                if (distbot < distright  || disttop <= Math.abs(e.getSpeedY()+0.0001))// Abstand Unten < Abstand rechts
                {
                    //System.out.println("7");
                    e.setY(getY() + getHeight());
                    e.setSpeedY(1);
                }
                else
                // Abstand rechts < Abstand Unten
                {
                    //System.out.println("8");
                    e.setX(getX() + getWidth());

                }

            }
        }
    }

    @Override
    public void onCollision(Player p) {
        p.takeDamage(damage);
    }
}
