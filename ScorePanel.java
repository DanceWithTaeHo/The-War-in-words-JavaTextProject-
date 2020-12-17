import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScorePanel extends JPanel{
	private ImageIcon icon = new ImageIcon("./images/backGround-score.jpg");
	private Image img = icon.getImage();
	
	private final int LOW = 700;
	private final int MIDDLE = 500;
	private final int HIGH = 150;
	private String difficulty;
	
	private int score = 0; // 스코어 점수
	private int upgrade = 1; // 업그레이드 레벨
	private int hitCount = 0; // 히트 카운트
	private int upgradePoint = 0; // 히트 카운트
	private int bonus = 0;
	private String name;
	
	private JLabel textLabel = new JLabel("점수");
	private JLabel difficultyLabel = new JLabel("난이도");
	private JLabel stageLabel = new JLabel("Stage1");
	private JLabel msgLabel = new JLabel("게임을 시작해요!");
	private JLabel nameLabel = new JLabel("Player: ");
	private JLabel upgradeLabel = new JLabel("업그레이드 게이지");
	private JLabel spcialMoveLabel = new JLabel("필살기");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel hitCountLabel = new JLabel(Integer.toString(hitCount));
	
	private JButton saveButton = new JButton("점수저장");

	private TextSource textSource = new TextSource();
	private JProgressBar upgradeGauge = new JProgressBar(0, 20);
	private JProgressBar spcialMoveGauge = new JProgressBar(0, 30);
	private boolean isEnglish = true;
	public ScorePanel() {
		setLayout(null);
		
		// 텍스트 레이블 설정
		textLabel.setSize(50, 20);
		textLabel.setLocation(10, 10);
		textLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(textLabel);
		
		// 스코어 레이블 설정
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(70, 10);
		textLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(scoreLabel);
		
		// 난이도 레이블 설정		
		difficultyLabel.setSize(150, 20);
		difficultyLabel.setLocation(170, 10);
		difficultyLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(difficultyLabel);
		
		// 네임 레이블
		nameLabel.setSize(150, 20);
		nameLabel.setLocation(10, 270);
		nameLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(nameLabel);
		
		// 스테이지 레이블
		stageLabel.setSize(100, 30);
		stageLabel.setLocation(75, 30);
		stageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 20));
		add(stageLabel);
		
		// 메시지 레이블
		msgLabel.setSize(200, 30);
		msgLabel.setLocation(25, 80);
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(msgLabel);
		
		// 저장 버튼
		saveButton.setSize(90, 20);
		saveButton.setLocation(145, 270);
		add(saveButton);
		
		// 히트 포인트 레이블
		hitCountLabel.setSize(100, 30);
		hitCountLabel.setLocation(75, 130);
		hitCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hitCountLabel.setFont(new Font("Gothic", Font.BOLD | Font.ITALIC, 30));
		add(hitCountLabel);
		
		// 업그레이드 라벨
		upgradeLabel.setSize(150, 50);
		upgradeLabel.setLocation(65, 150);
		upgradeLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(upgradeLabel);
		
		// 업그레이드 게이지
		upgradeGauge.setValue(0);
		upgradeGauge.setForeground(Color.yellow);
		upgradeGauge.setBounds(10, 190, 230, 20);
		upgradeGauge.setStringPainted(true); // %표시
		add(upgradeGauge);

		// 필살기 레이블
		spcialMoveLabel.setSize(150, 50);
		spcialMoveLabel.setLocation(10, 215);
		spcialMoveLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(spcialMoveLabel);
		
		// 필살기 게이지		
		spcialMoveGauge.setValue(0);
		spcialMoveGauge.setForeground(Color.GREEN);
		spcialMoveGauge.setBounds(70, 230, 170, 20);
		spcialMoveGauge.setStringPainted(true); // %표시
		add(spcialMoveGauge);
		
		
		// 이벤트 등록
		saveButton.addActionListener(new saveButtonActionListener());
	}
	// 초기화 함수
	public void initScore() {
		score = 0; // 스코어 점수
		upgrade = 1; // 업그레이드 레벨
		hitCount = 0; // 히트 카운트
		upgradePoint = 0; // 업그레이드 포인트
		bonus = 0;

		msgLabel.setText("다시 시작!");
		scoreLabel.setText(Integer.toString(score));
		hitCountLabel.setText(Integer.toString(hitCount));
		upgradeGauge.setValue(0);
		spcialMoveGauge.setValue(0);
	}
	// 스코어 및 게이지 증가 함수
	public void increase() { // 스코어 점수 및 프록그레스 점수 증가
		bonus = setHitBouns();
		score += (10 + bonus); // 점수 증가
		scoreLabel.setText(Integer.toString(score));
		
		hitCount++; // 히트 카운트 증가
		hitCountLabel.setText(Integer.toString(hitCount));
		
		upgradePoint+=10; // 업그레이드 게이지 증가
		upgradeGauge.setValue(upgradePoint + bonus);
		spcialMoveGauge.setValue(spcialMoveGauge.getValue()+5);
		
	}

	// 보너스 히트 점수
	public int setHitBouns() {
		if(hitCount >=6 && hitCount<=10) {
			msgLabel.setText("보너스 포인트! +1");
			msgLabel.setForeground(new Color(204,153,153));
			return 1;
		}
		else if(hitCount>=11 && hitCount<=15) {
			msgLabel.setText("보너스 포인트! +2");
			msgLabel.setForeground(new Color(255,153,153));
			return 2;
		}
		else if(hitCount>=16 && hitCount<=20) {
			msgLabel.setText("보너스 포인트! +3");
			msgLabel.setForeground(new Color(255,102,102));
			return 3;
		}
		else if(hitCount>=21 && hitCount<=25) {
			msgLabel.setText("보너스 포인트! +4");
			msgLabel.setForeground(new Color(255,51,51));
			return 4;			
		}
		else if(hitCount>=26) {
			msgLabel.setText("보너스 포인트! +5");
			msgLabel.setForeground(new Color(255,0,0));
			return 5;			
		}
		else {
			msgLabel.setText("연속으로 맞춰요!");
			msgLabel.setForeground(Color.BLACK);
			return 0;
		}
	}
	
	// 단어 미스
	public void hitMiss() {
		hitCount = 0;
		hitCountLabel.setText(Integer.toString(hitCount));
		msgLabel.setForeground(Color.BLACK);
		msgLabel.setText("앗! 실수하셨네요!");
	}
	
	// 업그레이드 게이지를 리턴
	public int getUpgrade() {
		return upgrade;
	}

	// 업그레이드가 되었는지 확인
	public boolean isUpgrade() {
		if(upgradeGauge.getValue() == 20) {
			upgradePoint = 0;
			
			msgLabel.setForeground(new Color(204,255,0));
			if(upgrade < 4) {
				msgLabel.setText("병기 업그레이드! 추가피해+" + Integer.toString(upgrade));
				msgLabel.setForeground(Color.BLUE);
				upgrade++;
			}
			else {
				upgrade = 4;
			}
			upgradeGauge.setValue(upgradePoint);
			return true;
		} else return false; 
	}
	
	// 메세지 레이블
	public void setMsg(String text) {
		if(text.equals("Clear!")) { //  게임 종료시 메세지
			msgLabel.setText("총점수 " + Integer.toString(score));
		}
		else
			msgLabel.setText(text);
	}
	
	// 스테이지 레이블을 설정
	public void setStageLabel(String text) {
		stageLabel.setText(text);
	}
	
	// 필살기 사용시
	public void specialMove() {
		spcialMoveGauge.setValue(0);
	}
	
	// 현재 필살기 사용가능한지 리턴
	public boolean isSpcialMove() {
		if(spcialMoveGauge.getValue() == 30)
			return true;
		else
			return false;
	}
	// 한/영 모드
	public void setMode(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	
	// 이름 설정
	public void setName(String name) {
		this.name = name;
		nameLabel.setText("Player: " + name);
	}
	
	// 난이도 설정
	public void setDfficulty(int num) {
		if(num == LOW) difficulty = "하";
		else if(num == MIDDLE) difficulty = "중";
		else if(num == HIGH) difficulty = "상";
		difficultyLabel.setText("난이도 : " + difficulty);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 이미지 그리기
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	// saveButton 액션이벤트 리스너
	class saveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameLabel.getText();
			String score = scoreLabel.getText();
			
			if(name.equals("")) return; // 텍스트가 공백이면 리턴
			
			textSource.saveScore(name, score, difficulty, isEnglish);
			
			JOptionPane.showMessageDialog(null, "점수가 저장되었습니다", "점수 저장", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
}
