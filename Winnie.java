//animacao[i] = new GreenfootImage("movimentos/" + "Winnie_zombie_walk_new_" + (i+1) + ".png");
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Winnie extends Jogadores {
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
    private int cooldownDuration = 50; // Duration of cooldown (in 'acts')
    private int cooldownCounter = 0;   // Counter to track cooldown state
    public Winnie(Vida_Winnie vidaWinnie) { // Accept Vida_Winnie as a parameter
        idleImage = new GreenfootImage("images/Winnie_iddle_1.png"); // Set idle image
        setImage(idleImage); 
        animacao = new GreenfootImage[8];
        for (int i = 0; i < animacao.length; i++) {
            animacao[i] = new GreenfootImage("movimentos/" + "Winnie_walk_new_" + (i + 1) + ".png");
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
        pegarPoteDeMel();
        pegarChave();
        if (!isMoving) {
            setImage(idleImage); // Set idle image if not moving
        }
    }
    private void pegarChave() {
    // Verifica se o jogador está colidindo com um objeto do tipo Key
    Key chave = (Key) getOneIntersectingObject(Key.class);
    
    // Se a chave for encontrada, remova-a do mundo
    if (chave != null) {
        // Remove a chave do mundo
        getWorld().removeObject(chave);
        
        // Verifica se existe algum objeto do tipo Barra no mundo
            if (!getWorld().getObjects(Barra.class).isEmpty()) {
            // Pega o primeiro objeto do tipo Barra e o remove
                Barra barra2 = (Barra) getWorld().getObjects(Barra.class).get(0); 
                getWorld().removeObject(barra2);
            }
        }
    }
    private void pegarPoteDeMel() {
    // Get the first intersecting BaldeDeMel object
    balde_de_mel baldeDeMel = (balde_de_mel) getOneIntersectingObject(balde_de_mel.class);
    
    // Check if the object was found
    if (baldeDeMel != null) {
        getWorld().removeObject(baldeDeMel); // Remove the balde_de_mel object
        
        // Check if there are any Portal objects in the world
        if (!getWorld().getObjects(Portal.class).isEmpty()) {
            // Get the first Portal object and remove it
            Portal portal = (Portal) getWorld().getObjects(Portal.class).get(0); 
            getWorld().removeObject(portal); // Remove the portal object
            }
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
            Gota_de_mel gota_mel = new Gota_de_mel(facingRight);
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
            if (cooldownCounter == 0) { // Check if cooldown is inactive
                loseLife(); // Call loseLife in Vida_Winnie
                cooldownCounter = cooldownDuration; // Reset the cooldown
            }
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
        Greenfoot.setWorld(new GAME_OVER()); // Transition to the GAME_OVER screen
    }
}
