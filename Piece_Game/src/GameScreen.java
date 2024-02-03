import java.awt.Graphics;



import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


public class GameScreen extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private Piece piece;
    private Stage stage;
    
    private Image heartImage;
    private int hpX;
    private int hpY;
    private int heartWidth;
    private int heartHeight;


    private Image background = new ImageIcon(Main.class.getResource("/image/map.png")).getImage();

    public GameScreen() {
        setTitle("Piece의 어드벤처 Time");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
     // 초기값 할당
        heartImage = Toolkit.getDefaultToolkit().getImage("src/image/pngwing.com.png");
        hpX = 20;
        hpY = 40;
        heartWidth = 30;
        heartHeight = 30;

        
     // Piece 클래스의 인스턴스를 생성
        piece = new Piece();
        piece.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(piece);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                piece.keyPressed(e);
                
                
            }
            
        });

        stage = new Stage(piece);
        stage.start();

        Timer timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece.update();
                repaint();
            }
        });

        timer.start();
    }

    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
        
    }

    public void screenDraw(Graphics g) {
        // 배경 이미지 크기 설정
        int bgWidth = background.getWidth(this);
        int bgHeight = background.getHeight(this);
        
        // 배경 이미지가 화면에 가득 차도록 계산
        int bgX = (Main.SCREEN_WIDTH - bgWidth) / 2;
        int bgY = Main.SCREEN_HEIGHT - bgHeight; // 맵 이미지가 아래쪽에 나타나도록 조정
        
        
        g.drawImage(background, bgX, bgY, null);
        
     // stage 객체가 null이 아닌 경우에만 drawMons 메서드 호출
        if (this.stage != null) {
            stage.drawMons(g);
        }
        //stage.drawMons(g);
        piece.paint(g);
        this.repaint();
        
        for (int i = 0; i < piece.getCurrentHP(); i++) {
            g.drawImage(heartImage, hpX + i * (heartWidth + 5), hpY, heartWidth, heartHeight, this);
        }
    }
    public void restartStage() {
 
    }

    public static void main(String[] args) {
        new GameScreen();
    }
}

