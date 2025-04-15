import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Seta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seta extends Display
{
    /**
     * Act - do whatever the seta wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Seta() {
        // Carregar a imagem do botão
        GreenfootImage image = new GreenfootImage("seta.png");

        // Redimensionar a imagem: (largura,altura)
        image.scale(100, 100);  

        // Definir a imagem redimensionada ao ator
        setImage(image);
        
    }    
    
    public void act()
    {
        // Add your action code here.
    }
}

