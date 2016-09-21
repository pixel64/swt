package LevelVerwaltung.SpielerVerwaltung;

import LevelVerwaltung.Level;
import LevelVerwaltung.SchussVerwaltung.Shot;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class PlayerController {
    private static final double jumpPower = 14;
    private static final double climbSpeed = 6;
    private static final double walkSpeed = 7.5;
    private static final double coffeeMultiplier = 1.3;
    public boolean update(GameContainer gameContainer, Level l){
        Player p = l.getPlayer();
        if(p.getHealth() <= 0) return true;
        Input input = gameContainer.getInput();
        p.setSpeedY(p.getSpeedY()+l.getGravitation()); //TODO set animation phases after the inputs
        if(input.isKeyDown(Input.KEY_SPACE)){
            if(p.isOnGround()){
                p.setSpeedY(-jumpPower);
            }
        }
        if(input.isKeyDown(Input.KEY_W)){
            if(p.isOnLadder()){
                p.setSpeedY(-climbSpeed);



            }
        }else if (input.isKeyDown(Input.KEY_S)){
            if(p.isOnLadder()){
                p.setSpeedY(climbSpeed);



            }
        }
        p.setY(p.getY() + p.getSpeedY());
        p.setSpeedX(0);
        if(input.isKeyDown(Input.KEY_A)){
            p.setSpeedX(-walkSpeed);

        }else if(input.isKeyDown(Input.KEY_D)){
            p.setSpeedX(walkSpeed);
        }
        if(p.getCoffeeTicks() > 0){
            p.setSpeedX(p.getSpeedX()*coffeeMultiplier);
        }
        p.setX(p.getX() + p.getSpeedX());

        if(p.getWeaponCooldownTicks() <= 0) {
            if (input.isKeyDown(Input.KEY_LEFT)) {
                l.addShot(new Shot(
                    p.getWeapon().getProjectileImagePath(),
                        p.getX()-4, p.getY() + p.getHeight() *2/3, 20, 5, 1, -p.getWeapon().getSpeed(), 0,p.getWeapon().isGravity()
                ));
                p.setWeaponCooldownTicks(p.getWeapon().getCooldownTicks());
            } else if (input.isKeyDown(Input.KEY_RIGHT)) {
                l.addShot(new Shot(
                        p.getWeapon().getProjectileImagePath(),
                        p.getX()+ p.getWidth() + 4, p.getY() + p.getHeight() *2/3, 20, 5, 1, p.getWeapon().getSpeed(), 0,p.getWeapon().isGravity()
                ));
                p.setWeaponCooldownTicks(p.getWeapon().getCooldownTicks());
            } else if (input.isKeyDown(Input.KEY_UP)) {
                l.addShot(new Shot(
                        p.getWeapon().getProjectileImagePath(),
                        p.getX()+ p.getWidth()/2, p.getY() -4 , 20, 5, 1, 0, -p.getWeapon().getSpeed() ,p.getWeapon().isGravity()
                ));
                p.setWeaponCooldownTicks(p.getWeapon().getCooldownTicks());
            } else if (input.isKeyDown(Input.KEY_DOWN)) {
                l.addShot(new Shot(
                        p.getWeapon().getProjectileImagePath(),
                        p.getX()+p.getWidth()/2 , p.getY() + p.getHeight() +4 , 20, 5, 1, 0, p.getWeapon().getSpeed() ,p.getWeapon().isGravity()
                ));
                p.setWeaponCooldownTicks(p.getWeapon().getCooldownTicks());
            }
        }

        p.setCoffeeTicks(p.getCoffeeTicks()-1);
        if(p.getCoffeeTicks() < 0) p.setCoffeeTicks(0);
        p.setInvulnerabilityTicks(p.getInvulnerabilityTicks()-1);
        if(p.getInvulnerabilityTicks() < 0) p.setInvulnerabilityTicks(0);
        p.setWeaponCooldownTicks(p.getWeaponCooldownTicks()-1);
        if(p.getWeaponCooldownTicks() < 0) p.setWeaponCooldownTicks(0);
        p.setOnGround(false);
        p.setOnLadder(false);
        return false;
    }
}