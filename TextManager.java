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

	// 단어 생성
	public JLabel makeText(Point location, int level) {
		String newWord = textSource.getWord(isEnglish);
		
		// 단어를 받아 레이블로 만들어 반환
		JLabel text = new JLabel(newWord);
		text.setOpaque(true);
		text.setBackground(Color.WHITE);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setSize(120, 30);
		text.setLocation(location);
		text.setFont(new Font("Gothic",Font.CENTER_BASELINE, 17));
		gameGroundPanel.add(text);
		
		// 단어의 움직임 부여
		MoveThread moveThread = new MoveThread(gamePanel, gameGroundPanel, gaugePanel, text, editPanel.getDifficulty(), level);
		moveThread.start();
		
		moveThreadHash.put(text, moveThread);
		
		return text;
	}
	
	// 단어 제거
	public void deleteText(JLabel text, int i) {
		labelVector.remove(i);
		gameGroundPanel.remove(text);
		gameGroundPanel.repaint();
		moveThreadHash.get(text).interrupt();
	}
	
	// 모든 단어 제거
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
	// 단어를 움직이는 moveThread 중지
	public void moveThreadStop() {
		Set<JLabel> keys = moveThreadHash.keySet();
		Iterator<JLabel> it = keys.iterator();
		while(it.hasNext()) {
			JLabel text = it.next();
			MoveThread moveThread = moveThreadHash.get(text);
			moveThread.suspend();
		}
	}
	
	// 단어를 움직이는 moveThread 시작
	public void moveThreadStart() {
		Set<JLabel> keys = moveThreadHash.keySet();
		Iterator<JLabel> it = keys.iterator();
		
		while(it.hasNext()) {
			JLabel text = it.next();
			MoveThread moveThread = moveThreadHash.get(text);
			moveThread.resume();
		}
	}
	
	// 단어벡터와 입력 단어와 비교
	public boolean equalsText(String inWord) {
		for(int i=0; i<labelVector.size(); i++) {
			JLabel text = labelVector.get(i);
			if(text.getText().equals(inWord)) {
				deleteText(text, i); // 해당 단어 삭제
				return true;
			}
		}
		return false;
	}
	
	// 단어 벡터 반환
	public Vector<JLabel> getLabelVector() {
		return labelVector;
	}
	
	// 모드 설정
	public void setMode(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
}
