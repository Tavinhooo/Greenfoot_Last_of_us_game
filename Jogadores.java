import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Jogadores extends Actor
{
    private int jumpStrength = 15;  // How high the Boneco jumps
    private int velocity = 0;       // Current velocity for jumping
    private int gravity = 1;        // Gravity effect
    private boolean onGround = true; // Whether the Boneco is on the ground

    // Getter methods for private variables
    protected int getJumpStrength() {
        return jumpStrength;
    }

    protected int getVelocity() {
        return velocity;
    }

    protected int getGravity() {
        return gravity;
    }

    protected boolean isOnGround() {
        return onGround;
    }

    // Other methods can manipulate these values internally
    // For example, a method to set the onGround state
    protected void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    // Method to set the velocity, for example
    protected void setVelocity(int velocity) {
        this.velocity = velocity;
    }public void act() { 
        if (!onGround) {
            fall();  // If not on the ground, apply falling logic
        }
    }

    // Method to handle jumping
    protected void jump(String up) {
        if (Greenfoot.isKeyDown(up) && onGround) {
            velocity = -jumpStrength;  // Apply jump force
            onGround = false;          // Set onGround to false (Ellie is in the air)
        }
    }
    // Method to check if Ellie is on a platform
    protected boolean onPlatform() {
    if (getImage() == null) {
        return false;  // Return false if there's no image
        }
    Actor platformBelow = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 5, Platform.class);
    return platformBelow != null;
    }
    protected boolean onPlatform2() {
    if (getImage() == null) {
        return false;  // Return false if there's no image
        }
    Actor platformBelow1 = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 5, Plataforma_com_mel.class);
    return platformBelow1 != null;
    }
    protected void fall() {
    // Apply gravity when in the air
    setLocation(getX(), getY() + velocity);  // Move down by velocity
    velocity += gravity;  // Increase downward velocity (gravity)
    // Check if landed on a platform
    if (onPlatform()) {
        velocity = 0;  // Stop falling
        onGround = true;  // Ellie is now on the ground

        // Adjust Y position to stand on the platform
        Actor platformBelow = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 5, Platform.class);
        if (platformBelow != null) {
            int platformTop = platformBelow.getY() - platformBelow.getImage().getHeight() / 2;
            setLocation(getX(), platformTop - getImage().getHeight() / 2);  // Adjust to stand on top of the platform
        }
    }
    if (onPlatform2()){
        velocity = 0;  // Stop falling
        onGround = true;  // Ellie is now on the ground
        Actor platformBelow = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 5, Plataforma_com_mel.class);
        if (platformBelow != null) {
            int platformTop = platformBelow.getY() - platformBelow.getImage().getHeight() / 2;
            setLocation(getX(), platformTop - getImage().getHeight() / 2);  // Adjust to stand on top of the platform
        }
    }
    // Check if landed on the ground
    else if (getY() >= getWorld().getHeight()-50) {  // Adjust ground level as needed
        velocity = 0;  // Stop falling
        onGround = true;  // Ellie is now on the ground
        setLocation(getX(), getWorld().getHeight()-50);  // Adjust to ground level
        }
    }
}
