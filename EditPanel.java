import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPanel extends JPanel{ // �Ʒ��� �����г�
	private ImageIcon icon = new ImageIcon("./images/backGround-edit.jpg");
	private Image img = icon.getImage();
	
	GameFrame gameFrame = null;
	
	private JTextField textField = new JTextField(20);
	private JButton addButton = new JButton("�ܾ��߰�");
	private JButton difficultyButton = new JButton("���̵� ����");
	private JButton rankingButton = new JButton("��ŷ ����");
	private TextSource textSource = new TextSource();
	
	private boolean isEnglish;
	
	// ���̵� ����
	private final int LOW = 700;
	private final int MIDDLE = 500;
	private final int HIGH = 150;
	
	private int difficulty = MIDDLE; // ����Ʈ �ӵ�
	
	
	public EditPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		this.setLayout(null);
		// �߰� �� ����
		textField.setSize(130, 25);
		textField.setLocation(15, 15);		
		add(textField);

		// �ܾ� �߰� ��ư
		addButton.setSize(90, 25);
		addButton.setLocation(150, 15);
		add(addButton);
		addButton.addActionListener(new addBtnActionListener());
		
		// ���̵� ���� ��ư
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
		
		// ��ŷ Ȯ�� ��ư
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
	
	// ���̵� ��ȯ
	public int getDifficulty() {
		return difficulty;
	}
	
	// ����Ŭ������ ����� ���̾�α׸� ��������
	public DifficultyDialog setDifficulty() {
		return new DifficultyDialog();
	}
	
	// ��/�� ��� Ȯ��
	public void setMode(boolean isEnglish){
		this.isEnglish = isEnglish;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// �̹��� �׸���
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	// addBtn �׼��̺�Ʈ ������
	class addBtnActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			
			
			if(text.equals("")) return; // �ؽ�Ʈ�� �����̸� ����
			int result = JOptionPane.showConfirmDialog(gameFrame,"���ܾ��Դϱ�? �ѱ��̸� '�ƴϿ�'", "���ܾ� Ȯ��", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.CLOSED_OPTION) return;
			else if(result == JOptionPane.YES_OPTION) isEnglish = true;
			else isEnglish = false;
			
			
			textSource.saveFile(text, isEnglish);
			textField.setText(""); // clear
		}		
	}
	
	// ����Ŭ������ ������ ���̵� ���� ���̾�α�
	class DifficultyDialog extends JDialog{
		JLabel text = new JLabel("�ӵ��� �޶����ϴ�!");
		JButton lowBtn = new JButton("��");
		JButton middleBtn = new JButton("��");
		JButton highBtn = new JButton("��");
		
		public DifficultyDialog() {
			super(gameFrame, "���̵� ����", true);
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
		// ���̵��� ����
		class DifficultyActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				String text = btn.getText();
				if(text.equals("��"))
					difficulty = LOW;
				else if(text.equals("��"))
					difficulty = MIDDLE;
				else if(text.equals("��"))
					difficulty = HIGH;
				setVisible(false);
			}
			
		}
	}
}
