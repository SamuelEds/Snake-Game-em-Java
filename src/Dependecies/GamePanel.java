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
    boolean running = false;
    
    Timer timer;
    Random random;
    
    // Confiiguração ao iniciar com o painel.
    public GamePanel(){
        
        random = new Random();
        
        this.setPreferredSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        
        this.addKeyListener(new MyAdapter());
        
        StartGame();
    
    }
    
    public void StartGame(){
        
        newApple();
        running = true;
        
        timer = new Timer(DELAY, this);
        timer.start();
        
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        draw(g);
    
    }
    
    // Desenhar na tela.
    public void draw(Graphics g){
        
        for(int i = 0; i < (SCREEN_HEIGHT / UNIT_SIZE); i++){
            
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDHT, i * UNIT_SIZE);
            
        }
        
    }
    
    public void newApple(){
        
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