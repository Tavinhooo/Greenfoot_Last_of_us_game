import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Crewmate extends Jogadores {
    private String left = "left";  // Key for left movement
    private String right = "right"; // Key for right movement
    private GreenfootImage[] animacao;  // Array for storing normal movement animation images
    private GreenfootImage[] animacaoDispara; // Array for storing shooting animation images
    private GreenfootImage idleImage;   // Image for idle state without gun
    private GreenfootImage idleDisparaImage; // Image for idle state with gun
    private boolean andandoParaEsquerda; // Track direction for mirroring
    private int indice; // Index to handle animation frames
    private boolean isMoving; // To check if the character is moving
    private int frameCounter; // Counter to slow down the animation
    private  boolean hasGun = false; // Track if the player has picked up a gun
    private boolean facingRight = true; // Track which direction the character is facing
    private boolean ta_a_disparar = false; // Track if the character is currently shooting
    private Vida_Player_2 Vida_Crewmate;
    private Primeiro_nivel previousWorld;
    private Segundo_nivel lastLevel;
    // Constructor
    public Crewmate(Vida_Player_2 Vida_Crewmate) {
        idleImage = new GreenfootImage("images/among_user.png"); // Set idle image
        idleDisparaImage = new GreenfootImage("images/among_dispara_idle.png"); // Set idle shooting image
        setImage(idleImage);
        // Load walking animation without gun
        animacao = new GreenfootImage[4];
        for (int i = 0; i < animacao.length; i++) {
            animacao[i] = new GreenfootImage("movimentos/among_mov_" + (i + 1) + ".png");
        }

        // Load walking animation with gun
        animacaoDispara = new GreenfootImage[4];
        for (int i = 0; i < animacaoDispara.length; i++) {
            animacaoDispara[i] = new GreenfootImage("movimentos/among_dispara_anda_" + (i + 1) + ".png");
        }
        this.Vida_Crewmate = Vida_Crewmate;
        frameCounter = 0; // Initialize frame counter
    }
    public boolean esta_com_a_arma(){
        return hasGun;
    }
    public void tornar_true(){
        this.hasGun = true;
    }
    // Method to handle movement
    protected void move() {
        isMoving = false; // Assume no movement, update if keys are pressed

        // Move left
        if (Greenfoot.isKeyDown(left) && getX() > 17) {
            isMoving = true;
            if (!andandoParaEsquerda) {  // Only mirror if we weren't already facing left
                mirrorImages();
            }
            setLocation(getX() - 3, getY()); // Move left
            andandoParaEsquerda = true;
            facingRight = false;
            animarMove(); // Play the appropriate animation
        }
        // Move right
        else if (Greenfoot.isKeyDown(right)&& getX() < 1180) {
            isMoving = true;
            if (andandoParaEsquerda) {  // Only mirror if we weren't already facing right
                mirrorImages();
            }
            setLocation(getX() + 3, getY()); // Move right
            andandoParaEsquerda = false;
            facingRight = true;
            animarMove(); // Play the appropriate animation
        }

        // Reset to idle state when not moving
        if (!isMoving) {
            indice = 0;
            if (hasGun) {
                setImage(idleDisparaImage); // Set idle shooting image
            } else {
                setImage(idleImage); // Set idle normal image
            }
        }
    }

    // Act method to update movement and falling logic
    public void act() {
        move();
        fall();
        jump("up");
        checkForGunPickup();
        checkCollisions();
        atira();
        pegarChave();
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
    // Method to check if the player picks up a gun
    private void checkForGunPickup() {
        if (Greenfoot.isKeyDown("2") && !hasGun) {
            Actor gun = getOneIntersectingObject(Gun.class); // Check if the player is near a gun
            if (gun != null) {
                hasGun = true; // Mark that the player has the gun
                getWorld().removeObject(gun); // Remove the gun from the world
                if (!facingRight) {
                    mirrorImages(); // Mirror the new shooting animations if facing left
                }
            }
        }
    }

    // Method for shooting logic
    public void atira() {
        if (Greenfoot.isKeyDown("1") && !ta_a_disparar && hasGun) {
            Bala bala = new Bala(facingRight);
            int x = getX();
            int y = getY();
            if (facingRight) {
                getWorld().addObject(bala, x + 50, y); // Shoot right
            } else {
                getWorld().addObject(bala, x - 50, y); // Shoot left
            }
            ta_a_disparar = true;
        }
        if (!Greenfoot.isKeyDown("1")) {
            ta_a_disparar = false; // Reset shooting state when the key is released
        }
    }

    // Method to mirror images for direction changes
    private void mirrorImages() {
        if (hasGun) {
            for (int i = 0; i < animacaoDispara.length; i++) {
                animacaoDispara[i].mirrorHorizontally(); // Mirror shooting animations
            }
            idleDisparaImage.mirrorHorizontally(); // Mirror shooting idle image
        } else {
            for (int i = 0; i < animacao.length; i++) {
                animacao[i].mirrorHorizontally(); // Mirror normal animations
            }
            idleImage.mirrorHorizontally(); // Mirror normal idle image
        }
    }

    // Method to handle animation during movement
    protected void animarMove() {
        frameCounter++;
        if (frameCounter % 5 == 0) { // Update frame every 5 ticks to slow down the animation
            if (hasGun) {
                if (indice < animacaoDispara.length) {
                    setImage(animacaoDispara[indice]); // Use shooting animation
                    indice++;
                } else {
                    indice = 0; // Reset animation index after finishing all frames
                }
            } else {
                if (indice < animacao.length) {
                    setImage(animacao[indice]); // Use normal animation
                    indice++;
                } else {
                    indice = 0; // Reset animation index after finishing all frames
                }
            }
        }
    }
    public void checkCollisions() {
        if (isTouching(gota_de_mel_infetada.class)) {
            loseLife(); // Call loseLife in Vida_Winnie
            removeTouching(gota_de_mel_infetada.class); // Remove the bullet after collision
        }
        if (isTouching(Coco_de_passaro.class)) {
            loseLife(); // Call loseLife in Vida_Winnie
            removeTouching(Coco_de_passaro.class); // Remove the bullet after collision
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
    }
    public void loseLife() {
    if (Vida_Crewmate.getLives() > 0) {
        Vida_Crewmate.loseLife();
        if (Vida_Crewmate.getLives() == 0) {
            gameOver();  // Call game over when lives reach zero
            }
        }
    }

    public void gameOver() {
    Greenfoot.stop();  // Stop the game
    Greenfoot.setWorld(new GAME_OVER());  // Transition to GAME_OVER screen
    if (getWorld().getObjects(Boss.class).isEmpty() && getWorld().getObjects(Winnie.class).isEmpty()){
        Greenfoot.setWorld(new WINNIE_WINS());
    }else{
            Greenfoot.setWorld(new GAME_OVER());
        }
    }
}
