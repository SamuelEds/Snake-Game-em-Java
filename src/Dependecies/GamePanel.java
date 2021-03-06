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
    
    /**
     * 
     * Confiiguração ao iniciar com o painel.
     * */
    public GamePanel(){
        
        random = new Random();
        
        this.setPreferredSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        
        this.addKeyListener(new MyAdapter());
        
        StartGame();
    
    }
    
    /**
     * Iniciar jogo.
     */
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
    
    /**
     * 
     * Desenhar objetos na tela.
     * */
    public void draw(Graphics g){
        
        if(running){
            
            /*
            for(int i = 0; i < (SCREEN_HEIGHT / UNIT_SIZE); i++){

                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDHT, i * UNIT_SIZE);

            }*/

            // Desenhar a maçã em um quadrado da tela.
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Desenhar a Snake.
            for(int i = 0; i < bodyParts; i++){

                if (i == 0){

                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }else{
                    g.setColor(new Color(45, 180, 0));
                    
                    // Gerar cores aleatórias.
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }

            }
            
            
            // Definir Score na tela.
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));

            FontMetrics metrics = getFontMetrics(g.getFont());

            // Centralizar texto.
            g.drawString("Pontuação: " + applesEaten, (SCREEN_WIDHT - metrics.stringWidth("Pontuação: " + applesEaten)) / 2, g.getFont().getSize());
            
        }else{
            gameOver(g);
        }
    }
    
    /**
     * Gerar uma nova maçã em uma coordenada aleatória.
     * */
    public void newApple(){
        
        // Garantir que será gerada dentro da tela nos quadrados disponíveis.
        appleX = random.nextInt((int) SCREEN_WIDHT / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        
    }
    
    /**
     * Movimentar a Snake.
     */
    public void move(){
        
        for(int i = bodyParts; i > 0; i--){
            
            x[i] = x[i - 1];
            y[i] = y[i - 1];
            
        }
        
        
        // Movimentar a Snake.
        switch(direction){
            
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
                
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
                
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            
        }
        
    }
    
    /** 
     * Checar a colisão com a maçã.
     * */
    public void checkApple(){
        
        if((x[0] == appleX) && (y[0] == appleY)){
            
            // Crescer a parte do corpo da Snake.
            bodyParts++;
            
            // Pontuar.
            applesEaten++;
            
            // Gerar nova maçã.
            newApple();
            
        }
        
    }
    
    /**
     * 
     * Checar colisões.
     * */
    public void checkCollisions(){
        
        for(int i = bodyParts; i > 0; i--){
            
            // Quando a Snake colide com ela mesma.
           if((x[0] == x[i]) && (y[0] == y[i])){
               running = false;
           } 
        }
        
        // Checar se a cabeça da Snake colidiu com a borda da esquerda.
        if(x[0] < 0){
            running = false;
        }
        
        // Checar se a cabeça da Snake colidiu com a borda da direita.
        if(x[0] > SCREEN_WIDHT){
            running = false;
        }
        
        // Checar se colidiu com a borda do topo.
        if(y[0] < 0){
            running = false;
        }
        
        // Checar se colidiu com a borda de baixo.
        if(y[0] > SCREEN_WIDHT){
            running = false;
        }
        
        // Para o jogo quando colidir.
        if(!running){
            timer.stop();
        }
        
    }
    
    /**
     * Processar o GameOver do jogo.
     * @param g Desenhar o GameOver na tela.
     */
    public void gameOver(Graphics g){
        
        // Definir Score na tela.
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));

        FontMetrics metric = getFontMetrics(g.getFont());

        // Centralizar texto.
        g.drawString("Pontuação: " + applesEaten, (SCREEN_WIDHT - metric.stringWidth("Pontuação: " + applesEaten)) / 2, g.getFont().getSize());
        
        // Definir uma mensagem de GameOver.
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        
        FontMetrics metrics = getFontMetrics(g.getFont());
        
        // Centralizar texto.
        g.drawString("Game Over", (SCREEN_WIDHT - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(running){
            
            move();
            checkApple();
            checkCollisions();
            
        }
        
        repaint();
        
    }
    
    public class MyAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent evt){
            
            // Criando teclas de direção.
            switch(evt.getKeyCode()){
                
                // Para a esquerda.
                case KeyEvent.VK_LEFT:
                    
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    
                    break;
                
                case KeyEvent.VK_RIGHT:
                    
                    if (direction != 'L'){
                        direction = 'R';
                    }
                    
                    break;
                    
                case KeyEvent.VK_UP:
                    
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    
                    break;
                
                case KeyEvent.VK_DOWN:
                    
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    
                    break;
                    
            }
            
        }
    }
    
}
