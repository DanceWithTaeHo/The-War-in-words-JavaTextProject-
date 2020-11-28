import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

/* 실제 게임이 진행되는 패널 */
public class GamePanel extends JPanel{
	// 텍스트 40만큼을 입력받을 수 있는 필드 생성
	private JTextField input = new JTextField(40);
	private JLabel startText = new JLabel("타이핑해보세요");
	
	// 점수패널 및 에디트 패널 생성
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	
	private GameGroundPanel gameGroundPanel = new GameGroundPanel();

	private HashMap<JLabel, MoveThread> moveThreadHash = new HashMap<JLabel, MoveThread>();
	private MakeTextThread makeTextThread = new MakeTextThread(gameGroundPanel, moveThreadHash);
	
	/* 실제 게임이 진행되는 패널 */
	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) {
		// 스코어 패널과 에딧패널에 정보를 받음
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;
		
		setLayout(new BorderLayout()); // 중간 아래 배치
		add(gameGroundPanel, BorderLayout.CENTER);
		add(new InputPanel(), BorderLayout.SOUTH);
		
		input.addActionListener(new InputActionListener(scorePanel));
	}
	
	/* 단어가 출력되는 게임그라운드 패널 */
	class GameGroundPanel extends JPanel{
		public GameGroundPanel() {
			// 단어 레이블의 크기 및 위치를 설정하고 추가
			setLayout(null);
			startText.setSize(100, 30);
			startText.setLocation(800, 10);
			add(startText);
		}
	}
	
	
	/* 단어를 입력받는 팬널 */
	class InputPanel extends JPanel{
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(Color.CYAN);
			add(input);
		}		
	}
	
	class InputActionListener implements ActionListener {
		private ScorePanel scorePanel = null;
		private Vector<JLabel> labelVector = new Vector<JLabel>(500);
		
		public InputActionListener(ScorePanel scorePanel) {
			this.scorePanel = scorePanel;
			labelVector = makeTextThread.getLabelVector();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// 이벤트 객체에게 어떤객체가 불렀는지 확인
			JTextField t = (JTextField)(e.getSource());
			
			String inWord = t.getText(); // 입력된 단어를 저장
			
			if(equalsText(inWord)) { // 맞추기 성공			
				scorePanel.increase(); // 점수 올리기				
				t.setText(""); //input 창 지우기			
			}
		}
		
		private boolean equalsText(String inWord) {
			for(int i=0; i<labelVector.size(); i++) {
				JLabel text = labelVector.get(i);
				if(text.getText().equals(inWord)) {
					gameGroundPanel.remove(text);
					gameGroundPanel.repaint();

					MoveThread moveThread = moveThreadHash.get(text);
					moveThread.interrupt();
					return true;
				}
			}
			return false;
		}
	}
	
	public void startGame() {
		gameGroundPanel.remove(startText);
		makeTextThread.start();
		repaint();
	}
}
