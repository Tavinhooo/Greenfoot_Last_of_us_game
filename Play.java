import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Play here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Play extends Display
{
    public Play() {
        // Carregar a imagem do botão
        GreenfootImage image = new GreenfootImage("play.png");

        // Redimensionar a imagem: (largura,altura)
        image.scale(200, 100);  

        // Definir a imagem redimensionada ao ator
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}
