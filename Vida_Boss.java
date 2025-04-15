import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // Make sure to import List

public class Vida_Boss extends Actor {
    private GreenfootImage[] heartStages;

    public Vida_Boss() {
        heartStages = new GreenfootImage[12];
        for (int i = 0; i < heartStages.length; i++) {
            heartStages[i] = new GreenfootImage("Vida_Jogadores/Vida_Boss_" + (i + 1) + ".png");
        }
        setImage(heartStages[heartStages.length - 1]);  // Start with a full heart image
    }

    public void change_heart_fase(int vida) {
        int stage = 11 - (vida / 10); // Calculate the stage so that 120 health (full) shows index 0, and 0 health shows index 11
        stage = Math.max(0, Math.min(stage, heartStages.length - 1)); // Ensure the stage is within bounds
        setImage(heartStages[stage]);
    }

    public void act() {
        // Retrieve the list of Boss objects in the current world
        List<Boss> bosses = getWorld().getObjects(Boss.class);
        
        // Check if there is at least one Boss in the world
        if (!bosses.isEmpty()) {
            Boss boss = bosses.get(0); // Safe to access the first element
            change_heart_fase(boss.returnHealth());
        }
    }
}
