import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ControlsOverlay displays the controls image on top of other objects, scaled to 700 pixels in height.
 */
public class ControlsOverlay extends Display {
    public ControlsOverlay() {
        GreenfootImage image = new GreenfootImage("controlos.png");  // Load the controls image
        
        // Scale image to 700 pixels in height while preserving aspect ratio
        int newHeight = 700;
        int newWidth = (int)(image.getWidth() * ((double)newHeight / image.getHeight()));
        image.scale(newWidth, newHeight);
        
        setImage(image);  // Set the scaled image as this actor's image
    }
}
