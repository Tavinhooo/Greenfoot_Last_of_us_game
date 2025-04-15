import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class plataforma_com_mel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plataforma_com_mel extends Actor {
    public Plataforma_com_mel(){
        this(1200,80);
    }
    public  Plataforma_com_mel(int comprimento, int altura) {
        GreenfootImage image = getImage();
        image.scale(comprimento, altura);
        setImage(image);
    }
    public void act(){
        
    }
}
