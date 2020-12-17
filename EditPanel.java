import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPanel extends JPanel{ // 아랫쪽 에딧패널
	private ImageIcon icon = new ImageIcon("./images/backGround-edit.jpg");
	private Image img = icon.getImage();
	
	GameFrame gameFrame = null;
	
	private JTextField textField = new JTextField(20);
	private JButton addButton = new JButton("단어추가");
	private JButton difficultyButton = new JButton("난이도 설정");
	private JButton rankingButton = new JButton("랭킹 보기");
	private TextSource textSource = new TextSource();
	
	private boolean isEnglish;
	
	// 난이도 정의
	private final int LOW = 700;
	private final int MIDDLE = 500;
	private final int HIGH = 150;
	
	private int difficulty = MIDDLE; // 디폴트 속도
	
	
	public EditPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		this.setLayout(null);
		// 추가 및 생성
		textField.setSize(130, 25);
		textField.setLocation(15, 15);		
		add(textField);

		// 단어 추가 버튼
		addButton.setSize(90, 25);
		addButton.setLocation(150, 15);
		add(addButton);
		addButton.addActionListener(new addBtnActionListener());
		
		// 난이도 설정 버튼
		difficultyButton.setFont(new Font("Gothic",Font.BOLD, 15));
		difficultyButton.setSize(150, 25);
		difficultyButton.setLocation(55, 60);
		add(difficultyButton);
		difficultyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DifficultyDialog();
			}
		});
		
		// 랭킹 확인 버튼
		rankingButton.setFont(new Font("Gothic",Font.BOLD, 15));
		rankingButton.setSize(150, 25);
		rankingButton.setLocation(55, 100);
		add(rankingButton);
		rankingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RankingDialog();
			}			
		});
	}
	
	// 난이도 반환
	public int getDifficulty() {
		return difficulty;
	}
	
	// 내부클래스로 선언된 다이얼로그를 생성해줌
	public DifficultyDialog setDifficulty() {
		return new DifficultyDialog();
	}
	
	// 한/영 모드 확인
	public void setMode(boolean isEnglish){
		this.isEnglish = isEnglish;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 이미지 그리기
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	// addBtn 액션이벤트 리스너
	class addBtnActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			
			
			if(text.equals("")) return; // 텍스트가 공백이면 리턴
			int result = JOptionPane.showConfirmDialog(gameFrame,"영단어입니까? 한글이면 '아니오'", "영단어 확인", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.CLOSED_OPTION) return;
			else if(result == JOptionPane.YES_OPTION) isEnglish = true;
			else isEnglish = false;
			
			
			textSource.saveFile(text, isEnglish);
			textField.setText(""); // clear
		}		
	}
	
	// 내부클래스로 정의한 난이도 설정 다이얼로그
	class DifficultyDialog extends JDialog{
		JLabel text = new JLabel("속도가 달라집니다!");
		JButton lowBtn = new JButton("하");
		JButton middleBtn = new JButton("중");
		JButton highBtn = new JButton("상");
		
		public DifficultyDialog() {
			super(gameFrame, "난이도 설정", true);
			setLayout(null);
			setSize(280,120);
			setLocation(800, 450);
			
			text.setSize(200, 30);
			text.setLocation(35, 10);
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setFont(new Font("Gothic", Font.BOLD, 15));
			add(text);
			
			lowBtn.setSize(50, 30);
			lowBtn.setLocation(50, 40);
			add(lowBtn);
			
			middleBtn.setSize(50, 30);
			middleBtn.setLocation(110, 40);
			add(middleBtn);
			
			highBtn.setSize(50, 30);
			highBtn.setLocation(170, 40);
			add(highBtn);
			
			lowBtn.addActionListener(new DifficultyActionListener());			
			middleBtn.addActionListener(new DifficultyActionListener());
			highBtn.addActionListener(new DifficultyActionListener());
			
			setVisible(true);
		}
		// 난이도를 설정
		class DifficultyActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				String text = btn.getText();
				if(text.equals("하"))
					difficulty = LOW;
				else if(text.equals("중"))
					difficulty = MIDDLE;
				else if(text.equals("상"))
					difficulty = HIGH;
				setVisible(false);
			}
			
		}
	}
}
