import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GAME extends World
{

    /**
     * Constructor for objects of class Game.
     * 
     */
    public GAME() {
        // Set the initial world to Menu
        super(1200, 700, 1); 
        Greenfoot.setWorld(new Menu());  // Start with Menu
    }
}
