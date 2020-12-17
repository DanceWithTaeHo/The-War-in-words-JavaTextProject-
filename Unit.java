import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

class Unit extends JLabel { // 사병
	private Image img = null;
	private Image attackImg = null; // 공격 이미지
	private Image stopImg = null; // 공격 중단 이미지
	private int width; // 넓이
	private int height; // 높이
	private int attackWidth; // 공격이미지 넓이
	private int attackHeight; // 공격이미지 높이
	
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
		// 이미지 그리기
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