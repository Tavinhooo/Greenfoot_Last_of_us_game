import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TEAM_WINS extends World
{

    /**
     * Constructor for objects of class TEAM_WINS.
     * 
     */
    private Score scoreCounter; // Declare a Score variable to hold the score display

    public TEAM_WINS(int contador)
    {    
        super(1200, 700, 1);
        scoreCounter = new Score(); // Initialize the Score instance
        scoreCounter.setValue(contador);
        setBackground(new GreenfootImage("TEAM_WINS.png")); // Set the scaled image as the background
        addObject(scoreCounter, 733, 666);
        addObject(new Equals_to(), 585,665);
    }
}
