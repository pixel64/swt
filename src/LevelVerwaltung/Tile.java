package LevelVerwaltung;

import LevelVerwaltung.GegnerVerwaltung.Enemy;
import LevelVerwaltung.SchussVerwaltung.Shot;
import LevelVerwaltung.SpielerVerwaltung.Player;
import SpielVerwaltung.BaseEntity;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Tile extends LevelEntity {
    protected boolean dead;
    public Tile(String path, double x, double y, int width, int height, int maxAnimPhase) {
        super(path, x, y, width, height, maxAnimPhase);
        dead = false;
    }

    public void onCollision(Player p){
        double distleft = Math.abs(getX() - p.getX() - p.getWidth());
        double distright = Math.abs(p.getX() - getX() - getWidth());
        double disttop = Math.abs(getY() - p.getY() - p.getHeight());
        double distbot = Math.abs(p.getY() - getY() - getHeight());

        //Von Oben
        if (disttop < distbot)
        {
            //Von Links
            if (distleft < distright)
            {
                if (disttop < distleft  || disttop <= p.getSpeedY()+0.0001)// Abstand Oben < Abstand links
                {
                    //System.out.println("1");
                    p.setY(getY() - p.getHeight());
                    p.setOnGround(true);
                    p.setSpeedY(01);
                }
                else
                // Abstand links < Abstand Oben
                {
                    //System.out.println("2");
                    p.setSpeedX(0);
                    p.setX(getX() - p.getWidth());
                }
            }
            else
            //Von Rechts
            {
                if (disttop < distright || disttop <= p.getSpeedY()+0.0001)// Abstand Oben < Abstand rechts
                {
                    //System.out.println("3");
                    p.setY(getY() - p.getHeight());
                    p.setSpeedY(01);

                }
                else
                // Abstand rechts < Abstand Oben
                {
                    //System.out.println("4");
                    p.setSpeedX(0);
                    p.setX(getX() + getWidth());
                }

            }

        }
        else
        //Von Unten
        {
            if (distleft < distright)//Von Links
            {
                if (distbot < distleft || disttop <= Math.abs(p.getSpeedY()+0.0001))// Abstand Unten < Abstand links
                {
                    //System.out.println("5");
                    p.setY(getY() + getHeight());
                    p.setSpeedY(0);

                }
                else
                // Abstand links < Abstand Unten
                {
                    //System.out.println("6");
                    p.setSpeedX(0);
                    p.setX(getX() - p.getWidth());

                }
            }
            else
            //Von Rechts
            {
                if (distbot < distright  || disttop <= Math.abs(p.getSpeedY()+0.0001))// Abstand Unten < Abstand rechts
                {
                    //System.out.println("7");
                    p.setY(getY() + getHeight());
                    p.setSpeedY(0);
                }
                else
                // Abstand rechts < Abstand Unten
                {
                    //System.out.println("8");
                    p.setSpeedX(0);
                    p.setX(getX() + getWidth());

                }

            }
        }

    }

    public void onCollision(Enemy e){
        double distleft = Math.abs(getX() - e.getX() - e.getWidth());
        double distright = Math.abs(e.getX() - getX() - getWidth());
        double disttop = Math.abs(getY() - e.getY() -e.getHeight());
        double distbot = Math.abs(e.getY() - getY() - getHeight());

        //Von Oben
        if (disttop < distbot)
        {
            //Von Links
            if (distleft < distright)
            {
                if (disttop < distleft  || disttop <= e.getSpeedY()+0.0001)// Abstand Oben < Abstand links
                {
                    //System.out.println("1");
                    e.setY(getY() - e.getHeight());
                    e.setSpeedY(0);
                }
                else
                // Abstand links < Abstand Oben
                {
                    //System.out.println("2");
                    e.setSpeedX(0);
                    e.setX(getX() - e.getWidth());
                }
            }
            else
            //Von Rechts
            {
                if (disttop < distright || disttop <= e.getSpeedY()+0.0001)// Abstand Oben < Abstand rechts
                {
                    //System.out.println("3");
                    e.setY(getY() - e.getHeight());
                    e.setSpeedY(0);

                }
                else
                // Abstand rechts < Abstand Oben
                {
                    //System.out.println("4");
                    e.setSpeedX(0);
                    e.setX(getX() + getWidth());
                }

            }

        }
        else
        //Von Unten
        {
            if (distleft < distright)//Von Links
            {
                if (distbot < distleft  || disttop <= Math.abs(e.getSpeedY()+0.0001))// Abstand Unten < Abstand links
                {
                    //System.out.println("5");
                    e.setY(getY() + getHeight());
                    e.setSpeedY(0);

                }
                else
                // Abstand links < Abstand Unten
                {
                    //System.out.println("6");
                    e.setSpeedX(0);
                    e.setX(getX() - e.getWidth());

                }
            }
            else
            //Von Rechts
            {
                if (distbot < distright || disttop <= Math.abs(e.getSpeedY()+0.0001))// Abstand Unten < Abstand rechts
                {
                    //System.out.println("7");
                    e.setY(getY() + getHeight());
                    e.setSpeedY(0);
                }
                else
                // Abstand rechts < Abstand Unten
                {
                    //System.out.println("8");
                    e.setSpeedX(0);
                    e.setX(getX() + getWidth());

                }

            }
        }
    }

    public void onCollision(Shot s){
        s.setDead(true);
    }

    public void update(){
        setAnimationPhase(getAnimationPhase()+0.2);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}

