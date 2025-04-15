import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class npc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

    /**
     * Act - do whatever the npc wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
public class NPC extends NpcOrStructures {
    private int moveSpeed = 2;
    private int moveDirection = 1; // 1 = direita, -1 = esquerda
    private int animationDelay = 5;
    private int shootCooldown = 50; // Tempo de espera entre disparos
    private int shootCounter = 0; // Contador para controlar o cooldown do disparo
    private boolean isAttacking = false;
    private GreenfootImage idleImage;
    private GreenfootImage[] animacao_para_direita;
    private GreenfootImage[] animacao_para_esquerda;
    private int currentFrame = 0; // Índice do frame atual
    private int animationCounter = 0; // Contador para controlar a animação
    private int health = 30;
    public NPC() {
        idleImage = new GreenfootImage("images/bird_zombie.png");
        setImage(idleImage);

        // Initialize the animations
        animacao_para_direita = new GreenfootImage[7];
        animacao_para_esquerda = new GreenfootImage[7];
        for (int i = 0; i < animacao_para_direita.length; i++) {
            animacao_para_direita[i] = new GreenfootImage("movimentos/bird_zombie_" + (i + 1) + ".png");
            animacao_para_esquerda[i] = new GreenfootImage(animacao_para_direita[i]);
            animacao_para_esquerda[i].mirrorHorizontally();
        }
    }

    public void act() {
        move();
        animateWalk();
        checkEdge();
        atira();
        is_coliding();
        check_if_it_died();
    }

    private void move() {
        // Check for collision with Barra before moving
        if (!isTouching(Barra.class)) {
            setLocation(getX() + moveSpeed * moveDirection, getY());
        } else {
            // Reverse direction if touching Barra
            moveDirection *= -1;
            setLocation(getX() + moveSpeed * moveDirection, getY());
        }
    }

    private void animateWalk() {
        animationCounter++;
        if (animationCounter >= animationDelay) {
            currentFrame = (currentFrame + 1) % animacao_para_direita.length;
            animationCounter = 0;

            if (moveDirection == 1) {
                setImage(animacao_para_direita[currentFrame]);
            } else {
                setImage(animacao_para_esquerda[currentFrame]);
            }
        }
    }

    private void checkEdge() {
        if (isAtEdge()) {
            // Reverse direction at the edge
            moveDirection *= -1;
            currentFrame = 0; // Reset animation frame
        }
    }
    public void atira() {
        // Decrementa o contador de cooldown do disparo
        if (shootCounter > 0) {
            shootCounter--;
        }

        // Verifica se o cooldown está zerado para disparar
        if (shootCounter == 0) {
            // Dispara o projétil
            Coco_de_passaro Coco = new Coco_de_passaro();
            int x = getX();
            int y = getY();
            getWorld().addObject(Coco, x, y);

            // Reinicia o cooldown
            shootCounter = shootCooldown;
        }
    }
    public void is_coliding(){
        if (isTouching(Bala.class)) {
            health = health -1; // Call loseLife in Vida_Winnie
            removeTouching(Bala.class); // Remove the bullet after collision
        }
        if (isTouching(Gota_de_mel.class)) {
            health = health -1; // Call loseLife in Vida_Winnie
            removeTouching(Gota_de_mel.class); // Remove the bullet after collision
        }
    }
    public void check_if_it_died(){
        if (health == 0){
           Primeiro_nivel primeiro = (Primeiro_nivel)getWorld();
           Score counter = primeiro.getCounter();
           counter.add(50);
           getWorld().removeObject(this);
        }
    }
}



