import java.awt.Color;
import javax.swing.*;

/* �����г� ����� ������ ������ �г� */
public class GaugePanel extends JPanel{
	// ���� ���� HP���α׷����� ����
	private JProgressBar myHP = new JProgressBar(0, 100);
	private JProgressBar enemyHP = new JProgressBar(0, 200);
	
	public GaugePanel() {		
		setLayout(null);
		
		initHP();
		
		myHP.setBounds(0, 0, 500, 20);
		myHP.setForeground(new Color(51, 153, 51));
		myHP.setStringPainted(true);
		
		enemyHP.setBounds(500, 0, 500, 20); // ���� ��ġ �� ũ�� ����
		enemyHP.setForeground(Color.RED);
		enemyHP.setStringPainted(true); // %ǥ��
		
		add(myHP);
		add(enemyHP);
	}
	
	// ü�� �ʱ�ȭ
	public void initHP() {
		myHP.setValue(100);
		enemyHP.setValue(200);
	}
	
	// ���� ü�� ��ȯ
	public int getMyHP() {
		return myHP.getValue();
	}
	
	// �� ü�� ��ȯ
	public int getEnemyHP() {
		return enemyHP.getValue();
	}
	
	// ���� ü�� ����
	public void decreaseMyHP() {
		int HP = myHP.getValue() - 10;
		myHP.setValue(HP);
	}
	
	// �� ü�� ���� ���׷��̵� ��ġ��ŭ �߰� ����
	public void decreaseEnemyHP(int upgrade) {
		int HP = enemyHP.getValue() - (20+(upgrade));
		enemyHP.setValue(HP);
	}

}
