import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class plataforma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)

public class Platform extends Actor {
    public Platform() {
        GreenfootImage platformImage = new GreenfootImage("images/plataforma.png");  // Use your platform image
        setImage(platformImage);
    }
}*/

public class Platform extends Actor {
    public Platform(){
        this(1200,80);
    }
    public  Platform(int comprimento, int altura) {
        GreenfootImage image = getImage();
        image.scale(comprimento, altura);
        setImage(image);
    }
    public void act(){
        
    }
}
