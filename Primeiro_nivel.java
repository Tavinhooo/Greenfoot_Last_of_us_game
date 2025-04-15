import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Primeiro_nivel extends World
{
    private Vida_Winnie vidaWinnie; // Single instance to manage lives
    private Vida_Player_2 vidaPlayerAmong;
    private Score contador = new Score();
    private boolean chaveApareceu = false;
    private boolean portalAberto = false;
    private Portal portal;
    private boolean tem_Arma = false;
    private Crewmate player2;
    public Primeiro_nivel() {    
        super(1200, 700, 1);
        setBackground(new GreenfootImage("imagem_background.png"));
        colocaObjetos();
        addObject(new Platform(), 600, 660);  // Add a platform at (200, 350)
        portal = new Portal(this); // Pass the current world instance to Portal
        addObject(portal, 1141, 291); // Add the portal to the world
    }
    public void act(){
        verificaNPCs();
        verificaPortal();
    }
    private void addPlatform(int width, int height, int x, int y) {
        Platform platform = new Platform(width, height);
        addObject(platform, x, y);
    }
    public Score getCounter(){
        return contador;
    }
    private void verificaPortal() {
        if (getObjects(balde_de_mel.class).isEmpty() && !portalAberto) {
            removeObject(portal); // Remove the closed portal
            portal = new Portal(true, this); // Create a new open portal using the overloaded constructor
            addObject(portal, 1141, 291); // Add the open portal to the world
            portalAberto = true;
        }
    }
    private void colocaObjetos() {
        setPaintOrder(Score.class,Equals_to.class,Vida_Player_2.class,Vida_Winnie.class,Platform.class);
        // Create and add the Vida_Winnie instance
        vidaWinnie = new Vida_Winnie(); 
        
        addObject(vidaWinnie, 60, 660); // Add hearts to the world
        
        // Add Winnie and pass the vidaWinnie instance
        Winnie winnie = new Winnie(vidaWinnie);
        addObject(winnie, 100, 562);
        vidaPlayerAmong  = new Vida_Player_2();
        player2 = new Crewmate(vidaPlayerAmong);
        addObject(vidaPlayerAmong, 1140, 660);
        
        

        addObject(player2, 200, 593);
        
        Gun gun = new Gun();
        addObject(gun, 356, 252);
        addObject(contador, 733, 666);
        
        // Add platforms
        addPlatform(150, 30, 200, 500);  // Platform 1
        addPlatform(150, 30, 400, 400);  // Platform 2
        addPlatform(350, 30, 800, 400);  // Long platform
        addPlatform(150, 30, 600, 300);  // Platform 3
        addPlatform(150, 30, 73, 116);   // Platform 4
        addPlatform(150, 30, 253, 160);
        addPlatform(150, 30, 355, 270);
        // Add other game objects
        addPlatform(350, 30, 900, 270);
        addPlatform(150, 30, 1125, 370);
        Barra barra2 = new Barra();
        addObject(barra2, 162, 88);
        addObject(new Equals_to(), 585,665);
        addObject(new Cave(), 1100,575);
        balde_de_mel balde_de_mel = new balde_de_mel();
        addObject(balde_de_mel, 60, 60);
        addObject(new NPC(), 800, 462);
        addObject(new NPC(), 400, 100);
    }
    private void verificaNPCs() {
        // Se não houver mais NPCs
        if (getObjects(NPC.class).isEmpty() && getObjects(NPC_2.class).isEmpty()&&getObjects(Cave.class).isEmpty()&& !chaveApareceu) {
            // Adiciona a chave ao mundo
            addObject(new Key(), 600, 250); // Altere as coordenadas conforme necessário
            chaveApareceu = true; // Atualiza o estado para indicar que a chave já apareceu
        }
    }
    public void troca_nivel(){
        if (player2.esta_com_a_arma()){tem_Arma = player2.esta_com_a_arma();}
         Greenfoot.setWorld(new Historia_parte_2(contador.getValue(),tem_Arma));;
    }
}
