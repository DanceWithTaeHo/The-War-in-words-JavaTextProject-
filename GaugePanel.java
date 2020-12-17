import java.awt.Color;
import javax.swing.*;

/* 게임패널 상단의 부착될 게이지 패널 */
public class GaugePanel extends JPanel{
	// 적과 나의 HP프로그레스바 생성
	private JProgressBar myHP = new JProgressBar(0, 100);
	private JProgressBar enemyHP = new JProgressBar(0, 200);
	
	public GaugePanel() {		
		setLayout(null);
		
		initHP();
		
		myHP.setBounds(0, 0, 500, 20);
		myHP.setForeground(new Color(51, 153, 51));
		myHP.setStringPainted(true);
		
		enemyHP.setBounds(500, 0, 500, 20); // 바의 위치 및 크기 설정
		enemyHP.setForeground(Color.RED);
		enemyHP.setStringPainted(true); // %표시
		
		add(myHP);
		add(enemyHP);
	}
	
	// 체력 초기화
	public void initHP() {
		myHP.setValue(100);
		enemyHP.setValue(200);
	}
	
	// 나의 체력 반환
	public int getMyHP() {
		return myHP.getValue();
	}
	
	// 적 체력 반환
	public int getEnemyHP() {
		return enemyHP.getValue();
	}
	
	// 나의 체력 감소
	public void decreaseMyHP() {
		int HP = myHP.getValue() - 10;
		myHP.setValue(HP);
	}
	
	// 적 체력 감소 업그레이드 수치만큼 추가 감소
	public void decreaseEnemyHP(int upgrade) {
		int HP = enemyHP.getValue() - (20+(upgrade));
		enemyHP.setValue(HP);
	}

}
