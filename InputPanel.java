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
			
			// 이벤트 객체에게 어떤객체가 불렀는지 확인
			SpecialMoveThread specialMoveThread = null;
			JTextField t = (JTextField)(e.getSource());
			
			String inWord = t.getText(); // 입력된 단어를 저장
			
			if(textManager.equalsText(inWord)) { // 맞추기 성공
				stageManager.checkNextStage();
				scorePanel.increase(); // 점수 올리기
				gaugePanel.decreaseEnemyHP(scorePanel.getUpgrade()); // 적HP 공격				
				unitLabelManager.attackEnemy(); // 적군공격
				if(scorePanel.isSpcialMove()) // 필살기 사용 가능이라면
					stageManager.setStageMsg("'필살기!', 혹은 'special!'를 입력하여 필살기 사용가능");
				if(scorePanel.isUpgrade()) { // 만약 업그레이드 되었다면
					unitLabelManager.createMyUnit(scorePanel.getUpgrade()); // 상위 유닛 생성
				}
				t.setText(""); //input 창 지우기
			}
			else if(inWord.equals("")) { // 공백단어 입력시
				scorePanel.setMsg("공백단어 입니다!");
			} // 필살기 입력시
			else if((inWord.equals("필살기!") || inWord.equals("special!")) && scorePanel.isSpcialMove()){
				specialMoveThread = new SpecialMoveThread();
				specialMoveThread.start();
				stageManager.setStageMsg("");
				t.setText(""); //input 창 지우기
			}
			else {
				scorePanel.hitMiss();
			}
		}
	}
	// 필살기 쓰레드, 필살기 유닛을 생성하여 모든 단어 제거
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
