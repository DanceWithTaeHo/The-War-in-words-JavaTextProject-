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
	
	private int score = 0; // ���ھ� ����
	private int upgrade = 1; // ���׷��̵� ����
	private int hitCount = 0; // ��Ʈ ī��Ʈ
	private int upgradePoint = 0; // ��Ʈ ī��Ʈ
	private int bonus = 0;
	private String name;
	
	private JLabel textLabel = new JLabel("����");
	private JLabel difficultyLabel = new JLabel("���̵�");
	private JLabel stageLabel = new JLabel("Stage1");
	private JLabel msgLabel = new JLabel("������ �����ؿ�!");
	private JLabel nameLabel = new JLabel("Player: ");
	private JLabel upgradeLabel = new JLabel("���׷��̵� ������");
	private JLabel spcialMoveLabel = new JLabel("�ʻ��");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel hitCountLabel = new JLabel(Integer.toString(hitCount));
	
	private JButton saveButton = new JButton("��������");

	private TextSource textSource = new TextSource();
	private JProgressBar upgradeGauge = new JProgressBar(0, 20);
	private JProgressBar spcialMoveGauge = new JProgressBar(0, 30);
	private boolean isEnglish = true;
	public ScorePanel() {
		setLayout(null);
		
		// �ؽ�Ʈ ���̺� ����
		textLabel.setSize(50, 20);
		textLabel.setLocation(10, 10);
		textLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(textLabel);
		
		// ���ھ� ���̺� ����
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(70, 10);
		textLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(scoreLabel);
		
		// ���̵� ���̺� ����		
		difficultyLabel.setSize(150, 20);
		difficultyLabel.setLocation(170, 10);
		difficultyLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(difficultyLabel);
		
		// ���� ���̺�
		nameLabel.setSize(150, 20);
		nameLabel.setLocation(10, 270);
		nameLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(nameLabel);
		
		// �������� ���̺�
		stageLabel.setSize(100, 30);
		stageLabel.setLocation(75, 30);
		stageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 20));
		add(stageLabel);
		
		// �޽��� ���̺�
		msgLabel.setSize(200, 30);
		msgLabel.setLocation(25, 80);
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(msgLabel);
		
		// ���� ��ư
		saveButton.setSize(90, 20);
		saveButton.setLocation(145, 270);
		add(saveButton);
		
		// ��Ʈ ����Ʈ ���̺�
		hitCountLabel.setSize(100, 30);
		hitCountLabel.setLocation(75, 130);
		hitCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hitCountLabel.setFont(new Font("Gothic", Font.BOLD | Font.ITALIC, 30));
		add(hitCountLabel);
		
		// ���׷��̵� ��
		upgradeLabel.setSize(150, 50);
		upgradeLabel.setLocation(65, 150);
		upgradeLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(upgradeLabel);
		
		// ���׷��̵� ������
		upgradeGauge.setValue(0);
		upgradeGauge.setForeground(Color.yellow);
		upgradeGauge.setBounds(10, 190, 230, 20);
		upgradeGauge.setStringPainted(true); // %ǥ��
		add(upgradeGauge);

		// �ʻ�� ���̺�
		spcialMoveLabel.setSize(150, 50);
		spcialMoveLabel.setLocation(10, 215);
		spcialMoveLabel.setFont(new Font("Gothic",Font.CENTER_BASELINE, 15));
		add(spcialMoveLabel);
		
		// �ʻ�� ������		
		spcialMoveGauge.setValue(0);
		spcialMoveGauge.setForeground(Color.GREEN);
		spcialMoveGauge.setBounds(70, 230, 170, 20);
		spcialMoveGauge.setStringPainted(true); // %ǥ��
		add(spcialMoveGauge);
		
		
		// �̺�Ʈ ���
		saveButton.addActionListener(new saveButtonActionListener());
	}
	// �ʱ�ȭ �Լ�
	public void initScore() {
		score = 0; // ���ھ� ����
		upgrade = 1; // ���׷��̵� ����
		hitCount = 0; // ��Ʈ ī��Ʈ
		upgradePoint = 0; // ���׷��̵� ����Ʈ
		bonus = 0;

		msgLabel.setText("�ٽ� ����!");
		scoreLabel.setText(Integer.toString(score));
		hitCountLabel.setText(Integer.toString(hitCount));
		upgradeGauge.setValue(0);
		spcialMoveGauge.setValue(0);
	}
	// ���ھ� �� ������ ���� �Լ�
	public void increase() { // ���ھ� ���� �� ���ϱ׷��� ���� ����
		bonus = setHitBouns();
		score += (10 + bonus); // ���� ����
		scoreLabel.setText(Integer.toString(score));
		
		hitCount++; // ��Ʈ ī��Ʈ ����
		hitCountLabel.setText(Integer.toString(hitCount));
		
		upgradePoint+=10; // ���׷��̵� ������ ����
		upgradeGauge.setValue(upgradePoint + bonus);
		spcialMoveGauge.setValue(spcialMoveGauge.getValue()+5);
		
	}

	// ���ʽ� ��Ʈ ����
	public int setHitBouns() {
		if(hitCount >=6 && hitCount<=10) {
			msgLabel.setText("���ʽ� ����Ʈ! +1");
			msgLabel.setForeground(new Color(204,153,153));
			return 1;
		}
		else if(hitCount>=11 && hitCount<=15) {
			msgLabel.setText("���ʽ� ����Ʈ! +2");
			msgLabel.setForeground(new Color(255,153,153));
			return 2;
		}
		else if(hitCount>=16 && hitCount<=20) {
			msgLabel.setText("���ʽ� ����Ʈ! +3");
			msgLabel.setForeground(new Color(255,102,102));
			return 3;
		}
		else if(hitCount>=21 && hitCount<=25) {
			msgLabel.setText("���ʽ� ����Ʈ! +4");
			msgLabel.setForeground(new Color(255,51,51));
			return 4;			
		}
		else if(hitCount>=26) {
			msgLabel.setText("���ʽ� ����Ʈ! +5");
			msgLabel.setForeground(new Color(255,0,0));
			return 5;			
		}
		else {
			msgLabel.setText("�������� �����!");
			msgLabel.setForeground(Color.BLACK);
			return 0;
		}
	}
	
	// �ܾ� �̽�
	public void hitMiss() {
		hitCount = 0;
		hitCountLabel.setText(Integer.toString(hitCount));
		msgLabel.setForeground(Color.BLACK);
		msgLabel.setText("��! �Ǽ��ϼ̳׿�!");
	}
	
	// ���׷��̵� �������� ����
	public int getUpgrade() {
		return upgrade;
	}

	// ���׷��̵尡 �Ǿ����� Ȯ��
	public boolean isUpgrade() {
		if(upgradeGauge.getValue() == 20) {
			upgradePoint = 0;
			
			msgLabel.setForeground(new Color(204,255,0));
			if(upgrade < 4) {
				msgLabel.setText("���� ���׷��̵�! �߰�����+" + Integer.toString(upgrade));
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
	
	// �޼��� ���̺�
	public void setMsg(String text) {
		if(text.equals("Clear!")) { //  ���� ����� �޼���
			msgLabel.setText("������ " + Integer.toString(score));
		}
		else
			msgLabel.setText(text);
	}
	
	// �������� ���̺��� ����
	public void setStageLabel(String text) {
		stageLabel.setText(text);
	}
	
	// �ʻ�� ����
	public void specialMove() {
		spcialMoveGauge.setValue(0);
	}
	
	// ���� �ʻ�� ��밡������ ����
	public boolean isSpcialMove() {
		if(spcialMoveGauge.getValue() == 30)
			return true;
		else
			return false;
	}
	// ��/�� ���
	public void setMode(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	
	// �̸� ����
	public void setName(String name) {
		this.name = name;
		nameLabel.setText("Player: " + name);
	}
	
	// ���̵� ����
	public void setDfficulty(int num) {
		if(num == LOW) difficulty = "��";
		else if(num == MIDDLE) difficulty = "��";
		else if(num == HIGH) difficulty = "��";
		difficultyLabel.setText("���̵� : " + difficulty);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// �̹��� �׸���
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	// saveButton �׼��̺�Ʈ ������
	class saveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameLabel.getText();
			String score = scoreLabel.getText();
			
			if(name.equals("")) return; // �ؽ�Ʈ�� �����̸� ����
			
			textSource.saveScore(name, score, difficulty, isEnglish);
			
			JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�", "���� ����", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
}
