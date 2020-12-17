import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameFrame extends JFrame{	
	private JMenuItem startItem = new JMenuItem("���ӽ���");
	private JMenuItem stopItem = new JMenuItem("�ߴ��ϱ�");
	private JMenuItem rePlayItem = new JMenuItem("�����簳");
	private JMenuItem initItem = new JMenuItem("�ٽ��ϱ�");
	private JMenuItem exitItem = new JMenuItem("�����ϱ�");
	
	private JButton startBtn = new JButton("���ӽ���");
	private JButton stopBtn = new JButton("�ߴ��ϱ�");
	private JButton rePlayBtn = new JButton("�����簳");
	private JButton initBtn = new JButton("�ٽ��ϱ�");
	
	// ���ھ� �гΰ� �����г��� �����Ͽ� �����гο��� �˷���
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel(this);
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	
	private JToolBar tBar = new JToolBar(); // ���� ����
	JMenuBar mBar = new JMenuBar(); // �޴��� ����
	JMenu fileMemu = new JMenu("Game"); // �޴��ٿ� �� �޴� ����
	
	private boolean isEnglish; 
	
	public GameFrame() {
		setTitle("The War in words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocation(300, 200);
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
		this.setJMenuBar(mBar); // �����ӿ� �޴��� ����		
		fileMemu.add(startItem); // �޴��� ������ �߰�
		fileMemu.add(initItem);
		fileMemu.addSeparator(); // �޴��� ���м� 
		fileMemu.add(exitItem);
		mBar.add(fileMemu); // �޴��ٿ� �޴��߰�
		
		startItem.addActionListener(new StartAction());
		stopItem.addActionListener(new StopAction());
		rePlayItem.addActionListener(new RePlayAction());
		initItem.addActionListener(new InitAction());
		exitItem.addActionListener(new exitAction());
		}
	
	private void makeToolBar() {
		tBar.add(startBtn); //���ٿ� ��ư�� �����Ͽ� �߰�
		tBar.add(initBtn);
		getContentPane().add(tBar, BorderLayout.NORTH); // ���ٴ� �׻� �������̾ƿ� ���� ������
		
		//��ư�� �׼� ������
		startBtn.addActionListener(new StartAction());
		stopBtn.addActionListener(new StopAction());
		rePlayBtn.addActionListener(new RePlayAction());
		initBtn.addActionListener(new InitAction());
	}
	
	// ���� ���� �̺�Ʈ
	private class StartAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new StartDialog();
			editPanel.setDifficulty();
			
			fileMemu.removeAll();
			fileMemu.add(stopItem); // �޴��� ������ �߰�
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // �޴��� ���м� 
			fileMemu.add(exitItem);
			
			tBar.remove(startBtn);
			tBar.add(stopBtn);
			
			gamePanel.startGame(isEnglish); // �����г��� ��ŸƮ������ �θ�
		}
	}
	
	// ���� �ߴ� �̺�Ʈ
	private class StopAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fileMemu.removeAll();
			fileMemu.add(rePlayItem); // �޴��� ������ �߰�
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // �޴��� ���м� 
			fileMemu.add(exitItem);
			
			tBar.remove(stopBtn);
			tBar.add(rePlayBtn);
			
			gamePanel.stopGame();			
		}
	}

	// ���� ����� �̺�Ʈ
	private class RePlayAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fileMemu.removeAll();
			fileMemu.add(stopItem); // �޴��� ������ �߰�
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // �޴��� ���м� 
			fileMemu.add(exitItem);
			
			tBar.remove(rePlayBtn);
			tBar.add(stopBtn);
			
			gamePanel.rePlay();
		}
	}
	
	// ���� �ʱ�ȭ �̺�Ʈ
	private class InitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gamePanel.initGame();			
		}
	}
	
	// ���� ���� �̺�Ʈ
	private class exitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 System.exit(0);
		}
	}
	
	// ���� ���� �� ���̾� �α�
	class StartDialog extends JDialog{
		JLabel text = new JLabel("��/�� ��带 �����ϼ���!");
		JButton koBtn = new JButton("�ѱ� �ܾ�");
		JButton engBtn = new JButton("���� �ܾ�");
		
		public StartDialog() {
			super(GameFrame.this, "�ѿ���带 �����ϼ���!", true);
			setLayout(null);
			setSize(280,120);
			setLocation(800, 450);
			
			text.setSize(200, 30);
			text.setLocation(35, 10);
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setFont(new Font("Gothic", Font.BOLD, 15));
			add(text);
			
			koBtn.setSize(100, 30);
			koBtn.setLocation(20, 40);
			add(koBtn);
			
			engBtn.setSize(100, 30);
			engBtn.setLocation(145, 40);
			add(engBtn);
			
			koBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isEnglish = false;
					setVisible(false);
				}
			});
			
			engBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isEnglish = true;
					setVisible(false);
				}
			});

			setVisible(true);
		}
	}
}
