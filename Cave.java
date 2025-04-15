import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Cave extends NpcOrStructures {
    private int spawnInterval = 300;  // Interval between spawns (in 'acts')
    private int spawnCounter = 0;     // Counter to control spawn timing
    private int health = 50;

    public Cave() {
        setImage("images/caverna.png");  // Replace with the actual cave image
    }

    public void act() {
        spawnCounter++;
        
        // First, check if the cave is still alive
        if (!check_if_it_died()) {  
            is_coliding();  // Check for collisions only if cave is still alive

            // Spawn a new zombie when the counter reaches the interval
            if (spawnCounter >= spawnInterval) {
                gerarZumbi();
                spawnCounter = 0;  // Reset the counter to start the cycle again
            }
        }
    }
    private void gerarZumbi() {
        NPC_2 novoZumbi = new NPC_2();  // Create a new zombie object
        getWorld().addObject(novoZumbi, getX(), getY()); // Add the zombie to the world
    }

    // Check for collisions with Bala or Gota_de_mel and reduce health accordingly
    public void is_coliding() {
        if (isTouching(Bala.class)) {
            health--;  // Decrease health
            removeTouching(Bala.class);  // Remove the bullet after collision
        }
        if (isTouching(Gota_de_mel.class)) {
            health--;  // Decrease health
            removeTouching(Gota_de_mel.class);  // Remove the object after collision
        }
    }

    // Check if health has reached zero to remove the cave
    public boolean check_if_it_died() {
        if (health <= 0) {
           Primeiro_nivel primeiro = (Primeiro_nivel)getWorld();
           Score counter = primeiro.getCounter();
           counter.add(100);
            getWorld().removeObject(this); 
            return true;  // Return true if cave was removed
        }
        return false;  // Return false if cave was not removed
    }
}
