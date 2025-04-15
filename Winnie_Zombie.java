import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Winnie_Zombie extends Jogadores {
    private String left = "a"; // Key for left movement
    private String right = "d"; // Key for right movement
    private GreenfootImage[] animacao;  // Array for storing animation images
    private GreenfootImage idleImage;   // Image for idle state
    private boolean andandoParaEsquerda; // Track direction for mirroring
    private int indice; // Index to handle animation frames
    private boolean isMoving; // To check if the character is moving
    private boolean ta_a_disparar = false;
    private Vida_Winnie vidaWinnie; // Reference to the Vida_Winnie instance
    private boolean facingRight = true;
    public Winnie_Zombie(Vida_Winnie vidaWinnie) { // Accept Vida_Winnie as a parameter
        idleImage = new GreenfootImage("images/Winnie_iddle_2.png"); // Set idle image
        setImage(idleImage); 
        animacao = new GreenfootImage[8];
        for (int i = 0; i < animacao.length; i++) {
            animacao[i] = new GreenfootImage("movimentos/" + "Winnie_zombie_walk_new_" + (i+1) + ".png");
        }
        andandoParaEsquerda = false; // Initialize as not moving left
        indice = 0; // Start with the first frame
        isMoving = false; // Initially, the character is not moving
        this.vidaWinnie = vidaWinnie; // Set the vidaWinnie reference
    }

    public void act() {
        move();
        fall();
        jump("w");
        atira();
        checkCollisions(); // Check for collisions
        if (!isMoving) {
            setImage(idleImage); // Set idle image if not moving
        }
    }

    protected void move() {
        isMoving = false; // Assume no movement, update if keys are pressed
        if (Greenfoot.isKeyDown(left) && getX() > 25) {
            isMoving = true;
            if (!andandoParaEsquerda) {
                mirrorImages(); // Mirror if not already facing left
            }
            setLocation(getX() - 3, getY()); // Move left
            andandoParaEsquerda = true;
            animarMove(animacao); // Play animation
            facingRight = false;
        } else if (Greenfoot.isKeyDown(right) && getX() < 1170) {
            isMoving = true;
            if (andandoParaEsquerda) {
                mirrorImages(); // Mirror if not already facing right
            }
            setLocation(getX() + 3, getY()); // Move right
            andandoParaEsquerda = false;
            animarMove(animacao); // Play animation
            facingRight = true;
        }

        if (!isMoving) {
            indice = 0; // Reset animation index
            setImage(idleImage); // Set idle image
        }
    }

    private void mirrorImages() {
        for (int i = 0; i < animacao.length; i++) {
            animacao[i].mirrorHorizontally(); // Mirror all animation frames
        }
        idleImage.mirrorHorizontally(); // Mirror the idle image
    }

    protected void animarMove(GreenfootImage[] animacao) {
        if (indice < animacao.length) {
            setImage(animacao[indice]);
            indice++;
        } else {
            indice = 0; // Reset animation index
        }
    }
    public void atira() {
        if (Greenfoot.isKeyDown("t") && !ta_a_disparar) {
            gota_de_mel_infetada gota_mel = new gota_de_mel_infetada(facingRight);
            int x = getX();
            int y = getY();
            if (facingRight) {
                getWorld().addObject(gota_mel, x + 50, y); // Shoot right
            } else {
                getWorld().addObject(gota_mel, x - 50, y); // Shoot left
            }
            ta_a_disparar = true; // Prevent multiple shots
        }
        if (!Greenfoot.isKeyDown("t")) {
            ta_a_disparar = false; // Allow shooting again
        }
    }

    public void checkCollisions() {
        if (isTouching(Bala.class)) {
            loseLife(); // Call loseLife in Vida_Winnie
            removeTouching(Bala.class); // Remove the bullet after collision
        }
        if (isTouching(Barra.class)) {
            // Move the player back if they are touching the Barra
            if (Greenfoot.isKeyDown(left)) {
                setLocation(getX() + 6, getY()); // Move right
            }
            if (Greenfoot.isKeyDown("w")) {
                setLocation(getX(), getY() + 5); // Move down
            }
        }
        if (isTouching(Coco_de_passaro.class)) {
            loseLife(); // Call loseLife in Vida_Winnie
            removeTouching(Coco_de_passaro.class); // Remove the bullet after collision
        }
        if (isTouching(NPC_2.class)) {
            loseLife(); // Call loseLife in Vida_Winnie
        }
    }
    public void loseLife() {
        if (vidaWinnie.getLives() > 0) { // Check if lives are above 0
            vidaWinnie.loseLife(); // Call loseLife in Vida_Winnie to decrease the count
            if (vidaWinnie.getLives() <= 0) {
                gameOver(); // Call game over if lives are zero
            }
        }
    }

    public void gameOver() {
        Greenfoot.stop(); // Stop the game
        if (getWorld().getObjects(Boss.class).isEmpty() && getWorld().getObjects(Winnie.class).isEmpty()){
        Greenfoot.setWorld(new CREWMATE_WINS());
        }else{
            Greenfoot.setWorld(new GAME_OVER());
        }
    }
}