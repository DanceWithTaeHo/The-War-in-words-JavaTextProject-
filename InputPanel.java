import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class InputPanel extends JPanel{
	public JTextField input = new JTextField(40);

	private ScorePanel scorePanel = null;
	private GaugePanel gaugePanel = null;
	private TextManager textManager = null;
	private StageManager stageManager = null;
	private UnitLabelManager unitLabelManager = null;
	public InputPanel(ScorePanel scorePanel, GaugePanel gaugePanel, TextManager textManager, StageManager stageManager, UnitLabelManager unitLabelManager) {
		
		this.scorePanel = scorePanel;
		this.textManager = textManager;
		this.gaugePanel = gaugePanel;
		this.stageManager = stageManager;
		this.unitLabelManager = unitLabelManager;
		setLayout(new FlowLayout());
		this.setBackground(Color.CYAN);
		add(input);
		
		input.addActionListener(new InputActionListener());
	}
	
	class InputActionListener implements ActionListener {
		public InputActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// �̺�Ʈ ��ü���� ���ü�� �ҷ����� Ȯ��
			SpecialMoveThread specialMoveThread = null;
			JTextField t = (JTextField)(e.getSource());
			
			String inWord = t.getText(); // �Էµ� �ܾ ����
			
			if(textManager.equalsText(inWord)) { // ���߱� ����
				stageManager.checkNextStage();
				scorePanel.increase(); // ���� �ø���
				gaugePanel.decreaseEnemyHP(scorePanel.getUpgrade()); // ��HP ����				
				unitLabelManager.attackEnemy(); // ��������
				if(scorePanel.isSpcialMove()) // �ʻ�� ��� �����̶��
					stageManager.setStageMsg("'�ʻ��!', Ȥ�� 'special!'�� �Է��Ͽ� �ʻ�� ��밡��");
				if(scorePanel.isUpgrade()) { // ���� ���׷��̵� �Ǿ��ٸ�
					unitLabelManager.createMyUnit(scorePanel.getUpgrade()); // ���� ���� ����
				}
				t.setText(""); //input â �����
			}
			else if(inWord.equals("")) { // ����ܾ� �Է½�
				scorePanel.setMsg("����ܾ� �Դϴ�!");
			} // �ʻ�� �Է½�
			else if((inWord.equals("�ʻ��!") || inWord.equals("special!")) && scorePanel.isSpcialMove()){
				specialMoveThread = new SpecialMoveThread();
				specialMoveThread.start();
				stageManager.setStageMsg("");
				t.setText(""); //input â �����
			}
			else {
				scorePanel.hitMiss();
			}
		}
	}
	// �ʻ�� ������, �ʻ�� ������ �����Ͽ� ��� �ܾ� ����
	class SpecialMoveThread extends Thread{
		public SpecialMoveThread() {
		}
		
		@Override
		public void run() {
			scorePanel.specialMove();
			unitLabelManager.specialMove();
			try {
				sleep(10000);
				textManager.deleteAllText();
				this.interrupt();
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
}
