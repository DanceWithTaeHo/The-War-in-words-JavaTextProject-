import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

class MakeTextThread extends Thread{
	private JPanel gameGroundPanel = null;
	/* TextSource�� �ܾ� ���ͷκ��� �ܾ� 1���� ��ȯ�� */
	private TextSource textSource = new TextSource(); // �ܾ� ���� ����
	private Vector<JLabel> labelVector = new Vector<JLabel>(500);
	HashMap<JLabel, MoveThread> moveThreadHash = null;
	
	public MakeTextThread(JPanel gameGroundPanel, HashMap<JLabel, MoveThread> moveThreadHash) {
		this.gameGroundPanel = gameGroundPanel;
		this.moveThreadHash = moveThreadHash;
	}
	
	@Override
	public void run() {
		int i=0;
		while(true) {
			labelVector.add(makeText());
			gameGroundPanel.repaint();
			i++;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	/* �ܾ ��� */
	private JLabel makeText() {
		// �ܾ� �� �� �޾� ����
		String newWord = textSource.get();

		JLabel text = new JLabel(newWord);
		text.setSize(100, 30);
		text.setLocation(800,(int)(Math.random()*500));
		gameGroundPanel.add(text);
		
		MoveThread moveThread = new MoveThread(text, 300);
		moveThread.start();
		
		moveThreadHash.put(text, moveThread);
		
		return text;
	}
	public Vector<JLabel> getLabelVector() {
		return labelVector;
	}
}