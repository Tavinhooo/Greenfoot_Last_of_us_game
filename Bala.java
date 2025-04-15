import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bala extends Projeteis {
    private boolean facingRight; // Store the direction

    public Bala(boolean facingRight) {
        this.facingRight = facingRight; // Set the direction
        // Scale the image
        GreenfootImage image = getImage(); // Get the current image
        setImage(image); // Set the scaled image back to the actor

        // Flip the image if the bullet is facing left
        if (!facingRight) {
            flipImage();
        }
    }

    public void act() {
        mexe();
    }

    private void flipImage() {
        GreenfootImage image = getImage();
        image.mirrorHorizontally(); // Flip the image
        setImage(image);
    }

    private void mexe() {
        // Move the bullet based on direction
        if (facingRight) {
            setLocation(getX() + 7, getY());
        } else {
            setLocation(getX() - 7, getY());
        }
        
        // Create an effect at the bullet's current position
        Effect effect = new Effect();
        getWorld().addObject(effect, getX(), getY()); // Add effect at the bullet's location

        // Check if the bullet has reached or passed position (500, 150)
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove the bullet from the world
        }
    }
}



