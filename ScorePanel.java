import java.awt.Color;

import javax.swing.*;


public class ScorePanel extends JPanel{
	private int score = 0;
	private JLabel textLabel = new JLabel("점수");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	
	ScorePanel() {
		this.setBackground(Color.YELLOW);
		setLayout(null);
		
		// 텍스트 레이블 설정
		textLabel.setSize(50, 20);
		textLabel.setLocation(10, 10);
		add(textLabel); // 추가
		
		// 스코어 레이블 설정
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(70, 10);
		add(scoreLabel); // 추가
	}
	
	public void increase() { // 스코어 점수 증가
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}
	
}
