import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Segundo_nivel extends World
{
    //private GreenfootSound backgroundMusic;  // Declare a sound variable
    private Vida_Player_2 vidaPlayerAmong;
    private Vida_Winnie vidaWinnie;
    private Score scoreCounter; // Declare a Score variable to hold the score display
    private int contador; // Keep the contador as an int
    private boolean temArma;
    private Crewmate player2;
    public Segundo_nivel(int contador,boolean temArma) {   
        
        super(1200, 700, 1);
        this.temArma = temArma;
        this.contador = contador; // Store the contador value
        scoreCounter = new Score(); // Initialize the Score instance
        scoreCounter.setValue(contador); // Set the score's value
        colocaObjetos(); // Method to place other objects in the world
        addObject(new Plataforma_com_mel(), 600, 660);
        setBackground(new GreenfootImage("background_segundo_nivel.png"));
        addObject(scoreCounter, 733, 666); // Add the score display to the world
        
        // Initialize the background music and start playing it
        //backgroundMusic = new GreenfootSound("bonnie_tyler.mp3");  // Load the music file
        //backgroundMusic.playLoop();  // Start playing in a loop
    }
    public Score getCounter(){
        return scoreCounter;
    }
    public void fim(){
        Greenfoot.setWorld(new TEAM_WINS(contador));
    }

    private void addPlataforma_com_mel(int width, int height, int x, int y) {
        Plataforma_com_mel plataforma_com_mel = new Plataforma_com_mel(width, height);
        addObject(plataforma_com_mel, x, y);
    }
    
    private void colocaObjetos() {
        setPaintOrder(Equals_to.class,Score.class,Vida_Boss.class,Vida_Player_2.class,Vida_Winnie.class, Plataforma_com_mel.class);
        vidaPlayerAmong = new Vida_Player_2();
        addObject(vidaPlayerAmong, 1140, 660);
        vidaWinnie = new Vida_Winnie();
        addObject(vidaWinnie, 60, 660);
        Crewmate player2 = new Crewmate(vidaPlayerAmong);
        if (temArma){player2.tornar_true();}
        //winnie_zombie winnie_zombie_1 = new winnie_zombie();
        //addObject(winnie_zombie_1, 100, 562);
        Winnie_Zombie winnie = new Winnie_Zombie(vidaWinnie);
        addObject(winnie, 100, 562);
        
        addObject(new Equals_to(), 585,665);
        addObject(new Boss(), 600,582);
        addObject(player2, 1100, 562);
        addObject(new Nave(), 823, 94);
        addPlataforma_com_mel(150, 30, 500, 500);  // Platform 1
        addPlataforma_com_mel(150, 30, 1000, 500);  // Platform 2
        addPlataforma_com_mel(350, 30, 750, 370);  // Long platform
        addPlataforma_com_mel(150, 30, 581, 200);  // Platform 4
        addPlataforma_com_mel(150, 30, 1000, 215);
        addPlataforma_com_mel(350, 30, 190, 400);
        addPlataforma_com_mel(150, 30, 820, 140);
        addPlataforma_com_mel(150, 30, 160, 290);
        addPlataforma_com_mel(150, 30, 357, 203);
        addPlataforma_com_mel(150, 30, 1100, 350);
        addObject(new Vida_Boss(),600,40);
        
    }
}
