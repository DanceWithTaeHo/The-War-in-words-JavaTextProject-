import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

/* �ܾ ��µǴ� ���ӱ׶��� �г� */
class GameGroundPanel extends JPanel{
	private JLabel strartText = new JLabel("The War in words");
	private JLabel msg = new JLabel("");
	private ImageIcon icon = new ImageIcon("./images/backGround.jpg");
	private Image img = icon.getImage();
	
	public GameGroundPanel() {
		setLayout(null);
		
		strartText.setSize(600, 60);
		strartText.setLocation(200, 100);
		strartText.setHorizontalAlignment(SwingConstants.CENTER);
		strartText.setFont(new Font("Gothic",Font.CENTER_BASELINE, 60));
		add(strartText);
		
		msg.setSize(600, 40);
		msg.setLocation(200, 50);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setForeground(Color.WHITE);
		msg.setFont(new Font("Gothic", Font.BOLD, 23));
		add(msg);
	}
	
	// ���� �޽��� ����
	public void removeStartText() {
		remove(strartText);
	}
	
	// �޼��� ����
	public void setText(String stageMsg) {
		msg.setText(stageMsg);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}	