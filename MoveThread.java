import javax.swing.*;

public class MoveThread extends Thread{ // �ؽ�Ʈ�� �����̰��ϴ� ������
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