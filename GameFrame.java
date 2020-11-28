import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameFrame extends JFrame{
	// ��ư�� �����̹��� �ε��Ͽ� ������ �����
	private ImageIcon normalIcon = new ImageIcon("start.jpg");
	private ImageIcon overIcon = new ImageIcon("overIcon.jpg");
	private ImageIcon pressedIcon = new ImageIcon("pressedIcon.jpg");
	
	private JMenuItem startItem = new JMenuItem("Start");
	private JMenuItem stopItem = new JMenuItem("stop");
	
	private JButton startBtn = new JButton(normalIcon);
	private JButton stopBtn = new JButton("stop");
	
	// ���ھ� �гΰ� �����г��� �����Ͽ� �����гο��� �˷���
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	
	public GameFrame() {
		setTitle("Ÿ���� ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		splitPane(); // JSplitPane�� �����Ͽ� ����Ʈ���� CENTER�� ����
		makeMenu(); // �޴���
		makeToolBar(); //����
		this.setResizable(false);
		setVisible(true);
	}
	
	private void splitPane() { // ���м�
		JSplitPane hPane = new JSplitPane(); // ��Ǯ���� ����
		getContentPane().add(hPane, BorderLayout.CENTER); // ����Ʈ�� �߰��� ��Ǯ���� �߰�
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // ��Ǯ������ ������ ��������
		hPane.setDividerLocation(1000); // ���ʱ��� 600px��ŭ �̵�
		hPane.setEnabled(false); // ����
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setEnabled(false); //����
		
		pPane.setTopComponent(scorePanel); // pPane�� ������ ���ھ� �г� ����
		pPane.setBottomComponent(editPanel); // �Ʒ����� ����Ʈ�г� �߰� 
		hPane.setRightComponent(pPane);
	}

	private void makeMenu() {
		JMenuBar mBar = new JMenuBar(); // �޴��� ����
		this.setJMenuBar(mBar); // �����ӿ� �޴��� ����
		JMenu fileMemu = new JMenu("Game"); // �޴��ٿ� �� �޴� ����
		fileMemu.add(startItem); // �޴��� ������ �߰�
		fileMemu.add(stopItem);
		fileMemu.addSeparator(); // �޴��� ���м� 
		fileMemu.add(new JMenuItem("exit"));
		mBar.add(fileMemu); // �޴��ٿ� �޴��߰�
		
		startItem.addActionListener(new StartAction());
	}
	
	private void makeToolBar() {
		JToolBar tBar = new JToolBar(); // ���� ����
		tBar.add(startBtn); //���ٿ� ��ư�� �����Ͽ� �߰�
		tBar.add(stopBtn);
		getContentPane().add(tBar, BorderLayout.NORTH); // ���ٴ� �׻� �������̾ƿ� ���� ������
		
		//��ư�� �׼� ������
		startBtn.addActionListener(new StartAction());
		
		// ��ư�� ���콺�� �÷����� �� �̹��� ��ȭ
		startBtn.setRolloverIcon(overIcon);
		startBtn.setPressedIcon(pressedIcon);		
	}
	
	// ��ŸƮ ��ư�� �� �׼Ǹ�����
	private class StartAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gamePanel.startGame(); // �����г��� ��ŸƮ������ �θ�
		}
	}
}
