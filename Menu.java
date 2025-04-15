  

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 

  

/** 

* Classe Menu define o ecrã do menu principal do jogo. 

*/ 

public class Menu extends World { 

  

    private Seta seta = new Seta();                  // Objeto do cursor 

    private int opccao = 0;                          // 0 = Jogar, 1 = Controles, 2 = Sair 

    private boolean showingControls = false;         // Indica se a imagem dos controles está a ser exibida 

    private ControlsOverlay controlsOverlay = new ControlsOverlay();  // Sobreposição da imagem dos controles 

  

    /** 

     * Construtor para objetos da classe Menu. 

     * Define o fundo inicial e prepara os elementos do menu. 

     */ 

    public Menu() { 

        super(1200, 700, 1); 

        setBackground(new GreenfootImage("foto.png")); 

        prepararMundo();  // Prepara os elementos do ecrã de menu 

    } 

  

    /** 

     * Método para adicionar itens do menu e o cursor. 

     */ 

    private void prepararMundo() { 

        addObject(new Play(), 600, 233); 

        addObject(new Controls(), 600, 349); 

        addObject(new Quit(), 600, 466); 

        addObject(seta, 400, 233);  // Posiciona o cursor na primeira opção 

    } 

  

    /** 

     * Método act gerencia a navegação e a seleção no menu. 

     * Permite mover o cursor com as teclas para cima e para baixo, 

     * e selecionar uma opção com ESPAÇO ou ENTER. 

     */ 

    public void act() { 

        // Se os controles estão sendo exibidos, permite retorno ao menu com ESC 

        if (showingControls) { 

            if (Greenfoot.isKeyDown("ESCAPE")) { 

                hideControlsOverlay(); 

            } 

            return;  // Evita navegação pelo menu enquanto os controles estão exibidos 

        } 

  

        // Navegação pelo menu com as teclas para cima e para baixo 

        if (Greenfoot.isKeyDown("DOWN") && opccao < 2) { 

            opccao++; 

            Greenfoot.delay(10);  // Adiciona atraso para evitar rolagem rápida 

        } 

        if (Greenfoot.isKeyDown("UP") && opccao > 0) { 

            opccao--; 

            Greenfoot.delay(10);  // Adiciona atraso para evitar rolagem rápida 

        } 

  

        // Move o cursor com base na opção selecionada 

        seta.setLocation(400, 233 + (opccao * 116));  // Posiciona o cursor na opção selecionada 

  

        // Confirma a opção com ESPAÇO ou ENTER 

        if (Greenfoot.isKeyDown("SPACE") || Greenfoot.isKeyDown("ENTER")) { 

            switch (opccao) { 

                case 0: // Jogar 

                    Greenfoot.setWorld(new Historia());  // Troca para o primeiro nível 

                    break; 

                case 1: // Controles 

                    showControlsOverlay();  // Exibe os controles 

                    break; 

                case 2: // Sair 

                    Greenfoot.stop();  // Encerra o jogo 

                    break; 

            } 

        } 

    } 

  

    /** 

     * Exibe a sobreposição dos controles. 

     */ 

    private void showControlsOverlay() { 

        addObject(controlsOverlay, getWidth() / 2, getHeight() / 2);  // Centraliza a sobreposição 

        showingControls = true; 

    } 

  

    /** 

     * Oculta a sobreposição dos controles e retorna ao menu. 

     */ 

    private void hideControlsOverlay() { 

        removeObject(controlsOverlay);  // Remove a sobreposição 

        showingControls = false; 

    } 

} 