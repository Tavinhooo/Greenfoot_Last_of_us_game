import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends NpcOrStructures
{
    private int moveSpeed = 2;
    private int moveDirection = 1; // 1 = direita, -1 = esquerda
    private int animationDelay = 5;
    private int attackCooldown = 30; // Tempo de espera entre ataques (em 'acts')
    private int attackCounter = 0;
    private boolean isAttacking = false;
    private GreenfootImage idleImage;
    private GreenfootImage[] animacao_para_direita;
    private GreenfootImage[] animacao_para_esquerda;
    private GreenfootImage[] animacao_de_ataque;
    private GreenfootImage[] animacao_de_ataque_direita;
    private int currentFrame = 0; // Índice do frame atual
    private int animationCounter = 0; // Contador para controlar a animação
    private long lastHitTime = 0; // Time when the last hit was made
    private final long hitCooldownTime = 1000; // Cooldown time in milliseconds (1 second)
    private int health = 120;
    public Boss() {
        idleImage = new GreenfootImage("images/boss_idle.png"); // Imagem de idle
        setImage(idleImage);
 
        // Inicializa as animações para direita e esquerda
        animacao_para_direita = new GreenfootImage[7];
        animacao_para_esquerda = new GreenfootImage[7];
        animacao_de_ataque = new GreenfootImage[7];
        animacao_de_ataque_direita = new GreenfootImage[7];

        // Carrega as imagens para a animação
        for (int i = 0; i < animacao_para_direita.length; i++) {
            // Verifique se você tem imagens numeradas de 1 a 7
            animacao_para_direita[i] = new GreenfootImage("movimentos/boss_andar_" + (i + 1) + ".png");

            // Cria a versão espelhada para a esquerda
            animacao_para_esquerda[i] = new GreenfootImage(animacao_para_direita[i]);
            animacao_para_esquerda[i].mirrorHorizontally();
        }

        // Carrega as imagens para a animação de ataque
        for (int l = 0; l < animacao_de_ataque.length; l++) {
            animacao_de_ataque[l] = new GreenfootImage("animacao_de_bater/boss_ataque_" + (l + 1) + ".png");
            animacao_de_ataque_direita[l] = new GreenfootImage(animacao_de_ataque[l]);
            animacao_de_ataque_direita[l].mirrorHorizontally();
            
            
        }
    }

    public void act() {
        move();
        attack();
        animateWalk();
        checkEdge();
        executeAttack();
        is_coliding();
        check_if_it_died();
    }

    private void move() {
        if (!isAttacking) {
            setLocation(getX() + moveSpeed * moveDirection, getY());
        }
    }

    private void attack() {
        if (attackCounter > 0) {
            attackCounter--; // Reduz o cooldown do ataque
        }

        // Verifica se há um alvo para atacar e o cooldown acabou
        if (detectEnemy() && attackCounter == 0) {
            isAttacking = true;
            attackCounter = attackCooldown; // Reseta o cooldown do ataque
            currentFrame = 0; // Reseta o frame para começar a animação do ataque
        }

        // Se estiver atacando, anima o ataque e tenta executar o ataque
        if (isAttacking) {
            animateAttack();
        }
    }
    private boolean detectEnemy() {
        // Ajuste o valor 50 para o alcance de detecção do ataque
        Actor target = getOneObjectAtOffset(moveDirection * 50, 0, Jogadores.class);
        return target != null;
    }

    private void animateWalk() {
        if (!isAttacking) {
            animationCounter++;

            if (animationCounter >= animationDelay) {
                // Atualiza o frame da animação
                currentFrame = (currentFrame + 1) % animacao_para_esquerda.length;
                animationCounter = 0;

                // Define a imagem de acordo com a direção de movimento
                if (moveDirection == 1) {
                    setImage(animacao_para_esquerda[currentFrame]);
                } else {
                    setImage(animacao_para_direita[currentFrame]);
                }
            }
        }
    }

    private void animateAttack() {
        animationCounter++;

        if (animationCounter >= animationDelay) {
            currentFrame = (currentFrame + 1) % animacao_de_ataque.length;
            animationCounter = 0;

            // Define a imagem de acordo com a direção de ataque
            if (moveDirection == 1) {
                setImage(animacao_de_ataque_direita[currentFrame]); // Ataque para a direita
            } else {
                setImage(animacao_de_ataque[currentFrame]); // Ataque para a esquerda
            }

            // Se chegou ao último frame da animação, termina o ataque
            if (currentFrame == animacao_de_ataque.length - 1) {
                isAttacking = false;
                currentFrame = 0; // Reseta o frame
            }
        }
    }

    private void checkEdge() {
        if (isAtEdge()) {
            // Se o NPC alcançar a borda, muda a direção
            moveDirection *= -1;
            // Reseta a animação quando mudar a direção
            currentFrame = 0;
        }
    }
    private void executeAttack() {
        // Check if there is a player in front to attack
        Actor target = getOneIntersectingObject(Jogadores.class); // Get the object that is a Jogadores

        // Check if the cooldown has expired since the last hit
        long currentTime = System.currentTimeMillis();
        if (target != null && (currentTime - lastHitTime) >= hitCooldownTime) {
            // Update last hit time
            lastHitTime = currentTime;

            // Check if the target is an instance of Crewmate
            if (target instanceof Crewmate) {
                // Call the loseLife method from the Vida_Crewmate class
                ((Crewmate) target).loseLife(); // Handle life loss for Crewmate
            } 
            // Check if the target is an instance of Winnie_Zombie
            else if (target instanceof Winnie_Zombie) {
                // Call the loseLife method for Winnie_Zombie
                ((Winnie_Zombie) target).loseLife(); // Ensure Winnie_Zombie has its own loseLife method
            }
        }
    }
    public void is_coliding(){
        if (isTouching(Bala.class)) {
            health = health -1; // Call loseLife in Vida_Winnie
            removeTouching(Bala.class); // Remove the bullet after collision
        }
        if (isTouching(gota_de_mel_infetada.class)) {
            health = health -1; // Call loseLife in Vida_Winnie
            removeTouching(gota_de_mel_infetada.class); // Remove the bullet after collision
        }
    }
    public int returnHealth(){
        return health;
    }
    public void check_if_it_died(){
        if (health == 0){
           Segundo_nivel primeiro = (Segundo_nivel)getWorld();
           Score counter = primeiro.getCounter();
           counter.add(500);
           getWorld().removeObject(this);
        }
    }
}
