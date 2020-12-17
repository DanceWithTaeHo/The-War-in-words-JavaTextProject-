import javax.swing.*;

public class MoveThread extends Thread{ // �ؽ�Ʈ�� �����̰��ϴ� ������
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
				gameGroundPanel.setText("�й��Ͽ����ϴ�");
				gamePanel.gameOver();
			}
			this.interrupt();
		}
	}
}