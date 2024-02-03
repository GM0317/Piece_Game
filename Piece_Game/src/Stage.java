import java.awt.Graphics;

import java.util.ArrayList;

class Stage extends Thread {
    private ArrayList<Monster> monList = new ArrayList<>();
    public static boolean stageClear = false;
    public static boolean eatGrape = false;

    // Piece 객체를 저장할 변수
    private Piece piece;

    // Stage 클래스의 생성자에 Piece 객체를 전달받도록 수정
    public Stage(Piece piece) {
        this.piece = piece;
    }

    public void makeMons(Monster monster) {
        monList.add(monster);
    }

    public void drawMons(Graphics g) {
        for (int i = 0; i < monList.size(); i++) {
            Monster monster = monList.get(i);
            g.drawImage(monster.getNowState(), monster.getX(), monster.getY(), null);
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monList;
    }

    public void resetMonsters() {
        monList.clear();
        // 초기 위치에 몬스터 생성 및 시작
        Monster m1 = new Monster(109, 270, 109, 485);
        Monster m2 = new Monster(643, 270, 643, 1019);
        Monster m3 = new Monster(856, 270, 856, 1232);
        monList.add(m1);
        monList.add(m2);
        monList.add(m3);
        for (Monster monster : monList) {
            monster.start();
        }
    }

    // Stage 클래스에서 충돌을 검사하는 메서드를 추가합니다.
    public void checkCollisions() {
        ArrayList<Monster> monsters = getMonsters();
        int heartWidth = 30; // 하트 이미지의 너비
        int heartHeight = 30; // 하트 이미지의 높이
        int hpX = piece.getHpX(); // Piece 클래스에서 hpX 정보 가져오기
        int hpY = piece.getHpY(); // Piece 클래스에서 hpY 정보 가져오기
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            int monsterX = monster.getX();
            int monsterY = monster.getY();

            for (int j = 0; j < piece.getCurrentHP(); j++) {
                int heartX = hpX + j * (heartWidth + 5); // hpX 변수 사용
                int heartY = hpY; // hpY 변수 사용

                // 충돌 검사 - 몬스터와 하트 이미지가 겹치면 감소
                if (monsterX <= heartX + heartWidth && heartX <= monsterX + monster.getNowState().getWidth(null)
                        && monsterY <= heartY + heartHeight && heartY <= monsterY + monster.getNowState().getHeight(null)) {
                    piece.decreaseHP(); // 하트 이미지 감소
                }
            }
        }
    }

    @Override
    public void run() {
        // 스테이지 초기 설정
        resetMonsters();
    }
}
