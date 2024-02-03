
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel; // JFrame 대신 JPanel 임포트
import java.util.ArrayList; // ArrayList 임포트 추가



public class Piece extends JPanel implements KeyListener{
    Image image;
    String state = "default"; // "default", "right", "left", "up", "down", "attack" 중 하나의 상태를 가짐
    int x = 100, y = 100, sel = 1;
    int bgX = 0; //배경 좌우키 이벤트 추가
    
    boolean moved = false; // 변수를 false로 초기화

    private Stage stage; // stage 변수 선언

    public Piece(Stage stage) { // 생성자에서 Stage 객체를 전달받음
        this.stage = stage;
    }// stage 변수에 할당
    
 // 몬스터의 크기 정보 추가
    private int monsterWidth = 50;
    private int monsterHeight = 50;
    
    public int getHpX() {
        return hpX; // hpX 값을 반환
    }
    public int getHpY() {
        return hpY; // hpY 값을 반환
    }


    //캐릭터 정보
    private int currentHP = 5; // 현재 HP
    private int maxHP = 5; // 최대 HP
    //하트 위치
    int hpX = 20; // HP 바의 X 좌표
    int hpY = 40; // HP 바의 Y 좌표
    int hpWidth = 200; // HP 바의 너비
    int hpHeight = 30; // HP 바의 높이
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image heartImage = toolkit.getImage("src/image/pngwing.com.png");
    




    public Piece() {
    	
    	currentHP = maxHP;
    	
           setLayout(null);
           setBounds(100, 100, 700, 400);
           
           addKeyListener(this);  //frame에 KeyListener 장착
           
           x = (int) getWidth() / 2;   // JPanel의 너비 얻기
           y = (int) getHeight() / 2; // JPanel의 높이 얻기
          
    }
    
 // Piece 클래스에 충돌 검사 메서드 추가
    public void checkCollisions() {
        ArrayList<Monster> monsters = stage.getMonsters();
        int heartWidth = 30;
        int heartHeight = 30;

        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            int monsterX = monster.getX();
            int monsterY = monster.getY();

            for (int j = 0; j < getCurrentHP(); j++) {
                int heartX = hpX + j * (heartWidth + 5);
                int heartY = hpY;

                if (monsterX <= heartX + heartWidth && heartX <= monsterX + monsterWidth
                        && monsterY <= heartY + heartHeight && heartY <= monsterY + monsterHeight) {
                    decreaseHP();
                    // monsters.remove(i); // 몬스터와 충돌 시 몬스터 제거 (주석 처리)
                    break;
                }
            }
        }
    }
    
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
           int key = e.getKeyCode();
                  if(key == e.VK_RIGHT || // 오른쪽 키
                        key == e.VK_NUMPAD6 || //숫자키 NUMPAD
                               key == e.VK_KP_RIGHT) {
                  sel = (sel == 1)?2:2;  //삼항연산자
                  x = (x < getWidth())?x + 10 : -image.getWidth(this); //x좌표측으로 이동하는 속도가 5
                  bgX -= 10; //배경도 같이 움직이는 코드 오른쪽으로 이동
                  }
                  else{int key1 = e.getKeyCode();
                               if(key1 == e.VK_LEFT || //왼쪽 키
                                      key1 == e.VK_NUMPAD4 || //숫자키 NUMPAD 
                                             key1 == e.VK_KP_LEFT) {
                               sel = (sel == 1)?3:3;  //삼항연산자 
                               x = (x > 0)?x - 10 :getWidth() + image.getWidth(this);
                               bgX += 10; //배경도 같이 움직이는 코드 왼쪽으로 이동
                  }
                               int key2 = e.getKeyCode();
                               if(key2 == e.VK_UP ||
                                      key2 == e.VK_NUMPAD8 || //숫자키 NUMPAD 
                                             key2 == e.VK_KP_UP) {
                               sel = (sel == 1)?4:1;  //삼항연산자 
                               y = (y > 0)?y - 10 : getHeight() + image.getHeight(this);
                                      
                  }
                               int key3 = e.getKeyCode();
                               if(key3 == e.VK_DOWN ||
                                      key3 == e.VK_NUMPAD2 || //숫자키 NUMPAD 
                                             key3 == e.VK_KP_DOWN) {
                               sel = (sel == 1)?4:1;  //삼항연산자
                               y = (y < getHeight())?y + 10 : image.getWidth(this);
                               
                               }int key4 = e.getKeyCode();
                                if (key == e.VK_SPACE) 
                                sel = (sel == 1)?5:1;
                                //bullet = new Bullet(x, y);
                                { // 스페이스바 키 5:1은 이미지 case 5와 case 1이다.
                                
                               
                               
           
    }
                                if (moved) {
                                    checkCollisions(); // 이동 후 충돌 검사
                                    repaint(); // 화면 갱신
                                }
    }
                  
    repaint(); //한번 실행하면 다시 원래 이미지로 복귀
    //monster.repaint();
    }
           
    public void update() {
        // 게임 로직에 따른 업데이트 작업을 수행 (예: 중력 적용 등)
    }
    
    @Override
    public void paint(Graphics g) {
        // 이전 프레임의 이미지를 지우기 위해 화면을 클리어합니다.
        //g.clearRect(0, 0, getWidth(), getHeight()); //내가 이 그지같은 코드하나떄문에 몇시간을 해맨거야 하...

        switch (sel) {
            case 1:
                image = Toolkit.getDefaultToolkit().getImage("src/image/피스1.png"); // 피스 이미지1
                break;
            case 2:
                image = Toolkit.getDefaultToolkit().getImage("src/image/피스2.png"); // 피스 이미지2
                break;
            case 3:
                image = Toolkit.getDefaultToolkit().getImage("src/image/피스3.png"); // 피스 이미지3
                break;
            case 4:
                image = Toolkit.getDefaultToolkit().getImage("src/image/피스1.png"); // 피스 이미지4
                break;
            case 5:
         	   image = Toolkit.getDefaultToolkit().getImage("src/image/공격.png");// 공격 이미지 경로 잘못 잡아놓고 허둥됨ㅋㅋㅋㅋ
         	   break;
        }
        /*Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/image/map.png"); //맵 이미지 경로
        g.drawImage(backgroundImage, bgX, 0, this);*/
        // 캐릭터 이미지를 그립니다.
        g.drawImage(image, x - image.getWidth(this) / 2, y - image.getHeight(this) / -1, this); //캐릭터가 생성되는 위치 좌표 x,y
        // 배경 이미지를 그립니다. 즉, 창의 배경에 이미지를 넣어줍니다.
        
     // HP 바 그리기
        //g.setColor(Color.BLACK); // HP 바의 색상 설정
        //g.fillRect(hpX, hpY, (int) ((double) currentHP / maxHP * hpWidth), hpHeight); // 현재 HP에 따라 바 그리기

        // 하트 이미지 그리기
        int heartWidth = 30; // 하트 이미지의 너비
        int heartHeight = 30; // 하트 이미지의 높이
        for (int i = 0; i < currentHP; i++) {
            g.drawImage(heartImage, hpX + i * (heartWidth + 5), hpY, heartWidth, heartHeight, this);
        }       
    
    }
    
 // Piece 클래스에 현재 HP 감소 메서드를 추가합니다.
    public void decreaseHP() {
        if (currentHP > 0) {
            currentHP--;
        }
    }

    // Piece 클래스에 현재 HP를 얻는 메서드를 추가합니다.
    public int getCurrentHP() {
        return currentHP;
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent arg0) {}
    @Override
    public void keyTyped(java.awt.event.KeyEvent arg0) {}
    
    public static void main(String[] args) {
           new Piece();
    }
    
}

