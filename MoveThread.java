import javax.swing.*;

public class MoveThread extends Thread{ // 텍스트를 움직이게하는 스레드
	private int delay;
	private JLabel text = null;
	
	public MoveThread(JLabel text, int delay) {
		this.text = text;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		while(true) {
			text.setLocation(text.getX()-5, text.getY());
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					return;
			}
		}
	}
}