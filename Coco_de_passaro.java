import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class merda_de_passaro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coco_de_passaro extends Projeteis
{
    /**
     * Act - do whatever the merda_de_passaro wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mexe();
    }
    private void mexe() {
        // Check if the bullet has reached or passed position (500, 150)
        setLocation(getX(), getY()+5);
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove the bullet from the world
        }
    }
    

}
