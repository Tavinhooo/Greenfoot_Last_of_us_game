import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot e MouseInfo)

public class Vida_Player_2 extends Vida_Players {

    public Vida_Player_2() {
        // Carrega as imagens das vidas
        heartStages[3] = new GreenfootImage("Vida_Jogadores/vida_2.png"); // 3/4 de coração
        heartStages[2] = new GreenfootImage("Vida_Jogadores/vida_3.png"); // 1/2 coração
        heartStages[1] = new GreenfootImage("Vida_Jogadores/vida_4.png"); // 1/4 de coração
        // Atualiza a exibição para refletir as vidas iniciais
        updateLifeDisplay();
    }

    @Override
    public void updateLifeDisplay() {
        setHeartImage(); // Usa o método da classe base para definir a imagem das vidas
    }
}
