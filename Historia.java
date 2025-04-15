 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 

  

/** 

* Classe Historia para a transição de fundos de história. 

*/ 

public class Historia extends World { 

  

    private boolean keyPressed;      // Controla se uma tecla foi pressionada 

    private boolean historia1;       // Indica se a primeira história está exibida 

    private boolean historia2;       // Indica se a segunda história está exibida 

    private int enterPressCount;     // Contador de pressões válidas de tecla 

    private int cooldownTimer;       // Temporizador para gerenciar intervalo entre pressões 

    private final int COOLDOWN_DURATION = 30;  // Duração do intervalo em frames 

  

    /** 

     * Construtor para objetos da classe Historia. 

     * Define o fundo inicial e inicializa variáveis de controle. 

     */ 

    public Historia() { 

        super(1200, 700, 1); 

        setBackground(new GreenfootImage("images/nave_na_floresta.png")); 

        historia1 = true; 

        historia2 = false; 

        enterPressCount = 0; 

        cooldownTimer = 0; 

    } 

  

    /** 

     * Método principal da classe Historia. 

     * Executa as verificações de tecla e realiza as trocas de fundo 

     * conforme a contagem de pressões. 

     */ 

    public void act() { 

        // Diminui o cooldown, caso esteja ativo 

        if (cooldownTimer > 0) { 

            cooldownTimer--; 

        } 

  

        // Verifica a tecla pressionada e aplica cooldown 

        if (cooldownTimer == 0 && (Greenfoot.isKeyDown("SPACE") || Greenfoot.isKeyDown("ENTER"))) { 

            enterPressCount++; 

            cooldownTimer = COOLDOWN_DURATION; 

  

            // Muda o fundo ou avança para o próximo nível dependendo da contagem de pressionamentos 

            if (enterPressCount == 2) { 

                TrocaHistoria(); 

            } else if (enterPressCount == 3) { 

                TrocaHistoria(); 

            } else if (enterPressCount == 4) { 

                Greenfoot.setWorld(new Primeiro_nivel());  // Transição para o primeiro nível 

            } 

        } 

    } 

  

    /** 

     * Troca a imagem de fundo para a próxima imagem de história. 

     * Alterna entre 'historia1' e 'historia2' para controlar o estado. 

     */ 

    private void TrocaHistoria() { 

        if (historia1) { 

            setBackground(new GreenfootImage("images/nave_na_floresta2.png"));  // Troca para o segundo fundo 

        } else if (historia2) { 

            setBackground(new GreenfootImage("images/nave_na_floresta3.png"));  // Troca para o terceiro fundo 

        } else { 

            setBackground(new GreenfootImage("images/nave_na_floresta.png"));  // Retorna ao primeiro fundo 

        } 

  

        // Alterna os valores de historia1 e historia2 para rastrear os estados dos fundos 

        historia1 = !historia1; 

        historia2 = !historia2; 

    }
} 