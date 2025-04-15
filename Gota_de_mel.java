import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gota_de_mel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gota_de_mel extends Projeteis
{
    /**
     * Act - do whatever the gota_de_mel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean facingRight;
    public Gota_de_mel(boolean facingRight) {
        this.facingRight = facingRight; // Set the direction
        // Scale the image
        GreenfootImage image = getImage(); // Get the current image
        setImage(image); // Set the scaled image back to the actor

        // Flip the image if the bullet is facing left
        if (!facingRight) {
            flipImage();
        }
    }
    public void act()
    {
        mexe();
    }
     private void flipImage() {
        GreenfootImage image = getImage();
        image.mirrorHorizontally(); // Flip the image
        setImage(image);
    }
    private void mexe() {
        // Check if the bullet has reached or passed position (500, 150)
        if (facingRight) {
            setLocation(getX() + 7, getY());
        } else {
            setLocation(getX() - 7, getY());
        }
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove the bullet from the world
        }
    }
}
