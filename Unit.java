import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

class Unit extends JLabel { // �纴
	private Image img = null;
	private Image attackImg = null; // ���� �̹���
	private Image stopImg = null; // ���� �ߴ� �̹���
	private int width; // ����
	private int height; // ����
	private int attackWidth; // �����̹��� ����
	private int attackHeight; // �����̹��� ����
	
	public Unit(Image stopImg, Image attackImg,
			int width, int height, int attackWidth, int attackHeight) {
		this.img = stopImg;
		this.stopImg = stopImg;
		this.attackImg = attackImg;
		this.width = width;
		this.height = height;
		this.attackWidth = attackWidth;
		this.attackHeight = attackHeight;
		
		this.setSize(width, height);		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);			
		// �̹��� �׸���
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	public void attack() {
		img = attackImg;
		this.setSize(attackWidth, attackHeight);
	}
	public void stopAttack() {
		img = stopImg;
		this.setSize(width, height);
	}
}