import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  

  

/** 

* Classe Historia_parte_2 gere a transição para o segundo nível com fundo de história. 

*/ 

public class Historia_parte_2 extends World {  

  

    private Score scoreCounter;       // Armazena a exibição da pontuação 

    private int cooldownTimer;        // Temporizador para controlar intervalo entre pressionamentos 

    private final int COOLDOWN_DURATION = 30;  // Número de frames a esperar entre as teclas pressionadas 

    private boolean tem_Arma;         // Indica se o jogador possui arma 

  

    /** 

     * Construtor que inicializa o scoreCounter e exibe a pontuação. 

     * 

     * @param scoreValue Valor da pontuação a ser exibida. 

     * @param tem_Arma Define se o jogador possui arma. 

     */ 

    public Historia_parte_2(int scoreValue, boolean tem_Arma) {      

        super(1200, 700, 1); 

        setBackground(new GreenfootImage("images/Historia_segundo_nivel.png")); 

        this.tem_Arma = tem_Arma; 

  

        // Inicializa o contador de pontuação e define o valor 

        scoreCounter = new Score(); 

        scoreCounter.setValue(scoreValue); 

  

        cooldownTimer = 0;  // Inicializa o temporizador de cooldown 

    }  

  

    /** 

     * Método act gerencia a lógica de transição para o próximo nível. 

     * Ao pressionar ESPAÇO ou ENTER, passa para o segundo nível. 

     */ 

    public void act() { 

        // Reduz o temporizador de cooldown se ele estiver ativo 

        if (cooldownTimer > 0) { 

            cooldownTimer--; 

        } 

  

        // Verifica o pressionamento de tecla se o cooldown estiver inativo 

        if (cooldownTimer == 0 && (Greenfoot.isKeyDown("SPACE") || Greenfoot.isKeyDown("ENTER"))) { 

            // Transição para o próximo nível, passando a pontuação atual 

            Greenfoot.setWorld(new Segundo_nivel(scoreCounter.getValue(), tem_Arma)); 

            cooldownTimer = COOLDOWN_DURATION;  // Reinicia o temporizador de cooldown 

        } 

    } 

} 