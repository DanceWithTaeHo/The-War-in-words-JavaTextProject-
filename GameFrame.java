import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameFrame extends JFrame{	
	private JMenuItem startItem = new JMenuItem("게임시작");
	private JMenuItem stopItem = new JMenuItem("중단하기");
	private JMenuItem rePlayItem = new JMenuItem("게임재개");
	private JMenuItem initItem = new JMenuItem("다시하기");
	private JMenuItem exitItem = new JMenuItem("종료하기");
	
	private JButton startBtn = new JButton("게임시작");
	private JButton stopBtn = new JButton("중단하기");
	private JButton rePlayBtn = new JButton("게임재개");
	private JButton initBtn = new JButton("다시하기");
	
	// 스코어 패널과 에딧패널을 생성하여 게임패널에게 알려줌
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel(this);
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	
	private JToolBar tBar = new JToolBar(); // 툴바 생성
	JMenuBar mBar = new JMenuBar(); // 메뉴바 생성
	JMenu fileMemu = new JMenu("Game"); // 메뉴바에 들어갈 메뉴 생성
	
	private boolean isEnglish; 
	
	public GameFrame() {
		setTitle("The War in words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocation(300, 200);
		splitPane(); // JSplitPane을 생성하여 컨텐트팬의 CENTER에 부착
		makeMenu(); // 메뉴바
		makeToolBar(); //툴바
		this.setResizable(false);
		setVisible(true);
		
	}
	
	private void splitPane() { // 구분선
		JSplitPane hPane = new JSplitPane(); // 스풀릿펜 생성
		getContentPane().add(hPane, BorderLayout.CENTER); // 컨텐트펜 중간에 스풀릿펜 추가
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 스풀릿펜의 기준을 수직으로
		hPane.setDividerLocation(1000); // 왼쪽기준 600px만큼 이동
		hPane.setEnabled(false); // 고정
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setEnabled(false); //고정
		
		pPane.setTopComponent(scorePanel); // pPane의 윗쪽의 스코어 패널 생성
		pPane.setBottomComponent(editPanel); // 아랫쪽의 에디트패널 추가 
		hPane.setRightComponent(pPane);
	}

	private void makeMenu() {
		this.setJMenuBar(mBar); // 프레임에 메뉴바 부착		
		fileMemu.add(startItem); // 메뉴에 아이템 추가
		fileMemu.add(initItem);
		fileMemu.addSeparator(); // 메뉴에 구분선 
		fileMemu.add(exitItem);
		mBar.add(fileMemu); // 메뉴바에 메뉴추가
		
		startItem.addActionListener(new StartAction());
		stopItem.addActionListener(new StopAction());
		rePlayItem.addActionListener(new RePlayAction());
		initItem.addActionListener(new InitAction());
		exitItem.addActionListener(new exitAction());
		}
	
	private void makeToolBar() {
		tBar.add(startBtn); //툴바에 버튼을 생성하여 추가
		tBar.add(initBtn);
		getContentPane().add(tBar, BorderLayout.NORTH); // 툴바는 항상 보더레이아웃 북쪽 조건임
		
		//버튼의 액션 리스너
		startBtn.addActionListener(new StartAction());
		stopBtn.addActionListener(new StopAction());
		rePlayBtn.addActionListener(new RePlayAction());
		initBtn.addActionListener(new InitAction());
	}
	
	// 게임 시작 이벤트
	private class StartAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new StartDialog();
			editPanel.setDifficulty();
			
			fileMemu.removeAll();
			fileMemu.add(stopItem); // 메뉴에 아이템 추가
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // 메뉴에 구분선 
			fileMemu.add(exitItem);
			
			tBar.remove(startBtn);
			tBar.add(stopBtn);
			
			gamePanel.startGame(isEnglish); // 게임패널의 스타트게임을 부름
		}
	}
	
	// 게임 중단 이벤트
	private class StopAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fileMemu.removeAll();
			fileMemu.add(rePlayItem); // 메뉴에 아이템 추가
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // 메뉴에 구분선 
			fileMemu.add(exitItem);
			
			tBar.remove(stopBtn);
			tBar.add(rePlayBtn);
			
			gamePanel.stopGame();			
		}
	}

	// 게임 재시작 이벤트
	private class RePlayAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fileMemu.removeAll();
			fileMemu.add(stopItem); // 메뉴에 아이템 추가
			fileMemu.add(initItem);
			fileMemu.addSeparator(); // 메뉴에 구분선 
			fileMemu.add(exitItem);
			
			tBar.remove(rePlayBtn);
			tBar.add(stopBtn);
			
			gamePanel.rePlay();
		}
	}
	
	// 게임 초기화 이벤트
	private class InitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gamePanel.initGame();			
		}
	}
	
	// 게임 종료 이벤트
	private class exitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 System.exit(0);
		}
	}
	
	// 게임 시작 시 다이얼 로그
	class StartDialog extends JDialog{
		JLabel text = new JLabel("한/영 모드를 선택하세요!");
		JButton koBtn = new JButton("한글 단어");
		JButton engBtn = new JButton("영어 단어");
		
		public StartDialog() {
			super(GameFrame.this, "한영모드를 선택하세요!", true);
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
