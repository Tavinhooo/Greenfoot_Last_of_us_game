import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Portal extends Objetos {
    private boolean isOpen;
    private Primeiro_nivel previousWorld;
    public Portal(Primeiro_nivel previousWorld) {
        this(false, previousWorld); // Call the overloaded constructor with 'false' (closed)
    }
    public Portal(boolean isOpen, Primeiro_nivel previousWorld) {
        this.isOpen = isOpen;
        this.previousWorld = previousWorld; // Store reference to the previous world
        updateImage(); // Set the image based on the isOpen state
    }

    // Method to update the portal image based on its state
    private void updateImage() {
        if (isOpen) {
            setImage(new GreenfootImage("portal_aberto.png"));
        } else {
            setImage(new GreenfootImage("portal_fechado.png"));
        }
    }

    // Method to open the portal
    public void openPortal() {
        if (!isOpen) {
            isOpen = true; // Set open state to true
            updateImage(); // Update the image to open
        }
    }

    public void act() {
        // Only allow access if the portal is open
        if (isOpen && isTouching(Winnie.class) && isTouching(Crewmate.class)) {
            // Notify the previous world to change levels
            if (previousWorld != null) {
                previousWorld.troca_nivel(); // This method sets the new level world
            }
        }
    }
}
