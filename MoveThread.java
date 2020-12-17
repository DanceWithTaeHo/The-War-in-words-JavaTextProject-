import javax.swing.*;

public class MoveThread extends Thread{ // 텍스트를 움직이게하는 스레드
	private GamePanel gamePanel = null;
	private GameGroundPanel gameGroundPanel = null;
	private GaugePanel gaugePanel = null;
	private JLabel text = null;
	private int level;
	private int delay;
	
	public MoveThread(GamePanel gamePanel, GameGroundPanel gameGroundPanel, GaugePanel gaugePanel, JLabel text, int delay, int level) {
		this.gamePanel = gamePanel; 
		this.gameGroundPanel = gameGroundPanel;
		this.gaugePanel = gaugePanel;
		this.text = text;
		this.delay = delay;
		this.level = level;
	}
	
	@Override
	public void run() {
		while(true) {
			checkOver();			
			text.setLocation(text.getX()-5, text.getY());
			try {
				Thread.sleep(delay);
			}
			catch (InterruptedException e) {
				return;
			}
		}
	}
	
	synchronized public void checkOver() {
		if(text.getX() < 0) {
			gameGroundPanel.remove(text);
			gameGroundPanel.repaint();
			gaugePanel.decreaseMyHP();
			
			if(gaugePanel.getMyHP() == 0) {
				gameGroundPanel.setText("패배하였습니다");
				gamePanel.gameOver();
			}
			this.interrupt();
		}
	}
}