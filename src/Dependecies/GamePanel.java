package Dependecies;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    
    // Configurações de janela.
    static final int SCREEN_WIDHT = 600;
    static final int SCREEN_HEIGHT = 600;
    
    // Cálculo para tamanho dos objetos em cena.
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDHT * SCREEN_HEIGHT) / UNIT_SIZE;
    
    // Atrasos.
    static final int DELAY = 75;
    
    // CÁLCULO PARA O CORPO DA COBRA.
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    
    // Variáveis para configuração da lógica do jogo.
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX = 0;
    int appleY = 0;
    
    // Direção (R - Right, U - Up, L - Left, D - Down)
    char direction = 'R';
    
    public GamePanel(){
        
    }
    
    public void StartGame(){
        
    }
    
    public void paintComonent(Graphics g){
        
    }
    
    public void draw(Graphics g){
        
    }
    
    public void move(){
        
    }
    
    public void checkApple(){
        
    }
    
    public void checkCollisions(){
        
    }
    
    public void gameOver(Graphics g){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    public class MyAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent evt){
            
        }
    }
    
}
