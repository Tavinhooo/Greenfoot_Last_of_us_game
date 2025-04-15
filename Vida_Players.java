import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Vida_players here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Vida_Players extends Actor {
    protected GreenfootImage[] heartStages; // Heart stages for the player
    protected int lives; // Variable to track the number of lives

    public Vida_Players() {
        heartStages = new GreenfootImage[4]; // Array for heart stages
        lives = 4; // Initialize with 4 lives
        updateLifeDisplay(); // Display full hearts at start
    }

    // Abstract method to be overridden
    public abstract void updateLifeDisplay();

    // Method to reduce lives and update the display
    public void loseLife() {
        if (lives > 0) { // Ensure lives stay within bounds
            lives--; // Decrease lives
            updateLifeDisplay(); // Update heart display
        }
    }

    // Get the current number of lives
    public int getLives() {
        return lives; // Returns current number of lives left
    }

    // Set the image based on current lives
    protected void setHeartImage() {
        if (lives >= 0 && lives < heartStages.length) {
            setImage(heartStages[lives]); // Set the appropriate heart stage based on lives
        }
    }
}