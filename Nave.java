import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Nave extends Actor {
    /**
     * Act - do whatever the nave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Check if the boss is defeated and both Winnie_Zombie and Crewmate are touching Nave
        if (getWorld().getObjects(Boss.class).isEmpty() && isTouching(Winnie_Zombie.class) && isTouching(Crewmate.class)) {
            // Cast getWorld() to Segundo_Nivel and call fim() method
            ((Segundo_nivel) getWorld()).fim(); // Transitions to TEAM_WINS world
        }
    }
}

