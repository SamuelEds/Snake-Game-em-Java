package Main;

import Dependecies.GamePanel;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    
    // Construir a tela do Jogo.
    GameFrame(){
        
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
        
    }
    
}
