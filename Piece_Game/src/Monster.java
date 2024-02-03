import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask; // TimerTask 임포트 추가

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Monster extends Thread {

    private Image monsterBasic = new ImageIcon(Main.class.getResource("/image/괴물1.png"))
            .getImage();
    private Image monsterGreen = new ImageIcon(Main.class.getResource("/image/괴물1.png"))
            .getImage();

    // 위치
    private int x, y;
    
    

    // 현태상태
    private Image nowState = monsterBasic;
    
    //한계위치
    private int limitLeft_X; // Image -> int로 타입 변경
    private int limitRight_X; // Image -> int로 타입 변경

    // 방향
    private String direction = "right";

    // 생성자
    public Monster(int x, int y, int limitLeft_X, int limitRight_X) {
        this.x = x;
        this.y = y;
        this.limitLeft_X = limitLeft_X;
        this.limitRight_X = limitRight_X;
    }

    public Image getNowState() {
        return nowState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void run() {
        while (!Stage.stageClear) {

            if (direction.equals("left")) {
                x -= 10;
                if (x <= limitLeft_X)
                    direction = "right";

                try {
                    Thread.sleep(50);
                } catch (Exception e) {

                }
            } else if (direction.equals("right")) {
                x += 10;
                if (x >= limitRight_X)
                    direction = "left";

                try {
                    Thread.sleep(50);
                } catch (Exception e) {

                }
            }
        }
    }
}
