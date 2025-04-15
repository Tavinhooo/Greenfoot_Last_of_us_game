import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Quit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quit extends Display
{
    public Quit() {
        // Carregar a imagem do botão
        GreenfootImage image = new GreenfootImage("quit.png");

        // Redimensionar a imagem: (largura,altura)
        image.scale(200, 100);  

        // Definir a imagem redimensionada ao ator
        setImage(image);
        
    }    
}
