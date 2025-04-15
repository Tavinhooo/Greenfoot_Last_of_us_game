import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/
public class Effect extends Projeteis {
    private int lifetime = 2; // Duration of the effect

    public Effect() {
        GreenfootImage image = new GreenfootImage("images/effect.png"); // Load your effect image
        image.scale(10, 30); // Adjust size as necessary
        setImage(image);
    }

    public void act() {
        // Handle any animation or changes to the effect here
        lifetime--; // Decrease the lifetime
        if (lifetime <= 0) {
            getWorld().removeObject(this); // Remove the effect after its lifetime
        }
    }
}