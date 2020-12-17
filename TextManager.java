import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TextManager {
	private TextSource textSource = new TextSource();
	private GamePanel gamePanel = null;
	private EditPanel editPanel = null;
	private GameGroundPanel gameGroundPanel = null;
	private GaugePanel gaugePanel = null;

	private HashMap<JLabel, MoveThread> moveThreadHash = new HashMap<JLabel, MoveThread>();	
	private Vector<JLabel> labelVector = new Vector<JLabel>(500);
	
	private boolean isEnglish = true;
	
	public TextManager(GamePanel gamePanel, GameGroundPanel gameGroundPanel, GaugePanel gaugePanel, EditPanel editPanel) {
		this.gamePanel = gamePanel;
		this.editPanel = editPanel;
		this.gameGroundPanel = gameGroundPanel;
		this.gaugePanel = gaugePanel;
	}

	// �ܾ� ����
	public JLabel makeText(Point location, int level) {
		String newWord = textSource.getWord(isEnglish);
		
		// �ܾ �޾� ���̺�� ����� ��ȯ
		JLabel text = new JLabel(newWord);
		text.setOpaque(true);
		text.setBackground(Color.WHITE);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setSize(120, 30);
		text.setLocation(location);
		text.setFont(new Font("Gothic",Font.CENTER_BASELINE, 17));
		gameGroundPanel.add(text);
		
		// �ܾ��� ������ �ο�
		MoveThread moveThread = new MoveThread(gamePanel, gameGroundPanel, gaugePanel, text, editPanel.getDifficulty(), level);
		moveThread.start();
		
		moveThreadHash.put(text, moveThread);
		
		return text;
	}
	
	// �ܾ� ����
	public void deleteText(JLabel text, int i) {
		labelVector.remove(i);
		gameGroundPanel.remove(text);
		gameGroundPanel.repaint();
		moveThreadHash.get(text).interrupt();
	}
	
	// ��� �ܾ� ����
	public void deleteAllText() {
		Set<JLabel> keys = moveThreadHash.keySet();
		Iterator<JLabel> it = keys.iterator();
		while(it.hasNext()) {
			JLabel text = it.next();
			gameGroundPanel.remove(text);
			gameGroundPanel.repaint();
			moveThreadHash.get(text).interrupt();
		}
		labelVector.clear();
	}
	// �ܾ �����̴� moveThread ����
	public void moveThreadStop() {
		Set<JLabel> keys = moveThreadHash.keySet();
		Iterator<JLabel> it = keys.iterator();
		while(it.hasNext()) {
			JLabel text = it.next();
			MoveThread moveThread = moveThreadHash.get(text);
			moveThread.suspend();
		}
	}
	
	// �ܾ �����̴� moveThread ����
	public void moveThreadStart() {
		Set<JLabel> keys = moveThreadHash.keySet();
		Iterator<JLabel> it = keys.iterator();
		
		while(it.hasNext()) {
			JLabel text = it.next();
			MoveThread moveThread = moveThreadHash.get(text);
			moveThread.resume();
		}
	}
	
	// �ܾ�Ϳ� �Է� �ܾ�� ��
	public boolean equalsText(String inWord) {
		for(int i=0; i<labelVector.size(); i++) {
			JLabel text = labelVector.get(i);
			if(text.getText().equals(inWord)) {
				deleteText(text, i); // �ش� �ܾ� ����
				return true;
			}
		}
		return false;
	}
	
	// �ܾ� ���� ��ȯ
	public Vector<JLabel> getLabelVector() {
		return labelVector;
	}
	
	// ��� ����
	public void setMode(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
}
