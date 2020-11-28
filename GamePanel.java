import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

/* ���� ������ ����Ǵ� �г� */
public class GamePanel extends JPanel{
	// �ؽ�Ʈ 40��ŭ�� �Է¹��� �� �ִ� �ʵ� ����
	private JTextField input = new JTextField(40);
	private JLabel startText = new JLabel("Ÿ�����غ�����");
	
	// �����г� �� ����Ʈ �г� ����
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	
	private GameGroundPanel gameGroundPanel = new GameGroundPanel();

	private HashMap<JLabel, MoveThread> moveThreadHash = new HashMap<JLabel, MoveThread>();
	private MakeTextThread makeTextThread = new MakeTextThread(gameGroundPanel, moveThreadHash);
	
	/* ���� ������ ����Ǵ� �г� */
	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) {
		// ���ھ� �гΰ� �����гο� ������ ����
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;
		
		setLayout(new BorderLayout()); // �߰� �Ʒ� ��ġ
		add(gameGroundPanel, BorderLayout.CENTER);
		add(new InputPanel(), BorderLayout.SOUTH);
		
		input.addActionListener(new InputActionListener(scorePanel));
	}
	
	/* �ܾ ��µǴ� ���ӱ׶��� �г� */
	class GameGroundPanel extends JPanel{
		public GameGroundPanel() {
			// �ܾ� ���̺��� ũ�� �� ��ġ�� �����ϰ� �߰�
			setLayout(null);
			startText.setSize(100, 30);
			startText.setLocation(800, 10);
			add(startText);
		}
	}
	
	
	/* �ܾ �Է¹޴� �ҳ� */
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
			// �̺�Ʈ ��ü���� ���ü�� �ҷ����� Ȯ��
			JTextField t = (JTextField)(e.getSource());
			
			String inWord = t.getText(); // �Էµ� �ܾ ����
			
			if(equalsText(inWord)) { // ���߱� ����			
				scorePanel.increase(); // ���� �ø���				
				t.setText(""); //input â �����			
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
