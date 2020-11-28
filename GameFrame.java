import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameFrame extends JFrame{
	// 버튼을 위해이미지 로딩하여 아이콘 만들기
	private ImageIcon normalIcon = new ImageIcon("start.jpg");
	private ImageIcon overIcon = new ImageIcon("overIcon.jpg");
	private ImageIcon pressedIcon = new ImageIcon("pressedIcon.jpg");
	
	private JMenuItem startItem = new JMenuItem("Start");
	private JMenuItem stopItem = new JMenuItem("stop");
	
	private JButton startBtn = new JButton(normalIcon);
	private JButton stopBtn = new JButton("stop");
	
	// 스코어 패널과 에딧패널을 생성하여 게임패널에게 알려줌
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	
	public GameFrame() {
		setTitle("타이핑 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
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
		JMenuBar mBar = new JMenuBar(); // 메뉴바 생성
		this.setJMenuBar(mBar); // 프레임에 메뉴바 부착
		JMenu fileMemu = new JMenu("Game"); // 메뉴바에 들어갈 메뉴 생성
		fileMemu.add(startItem); // 메뉴에 아이템 추가
		fileMemu.add(stopItem);
		fileMemu.addSeparator(); // 메뉴에 구분선 
		fileMemu.add(new JMenuItem("exit"));
		mBar.add(fileMemu); // 메뉴바에 메뉴추가
		
		startItem.addActionListener(new StartAction());
	}
	
	private void makeToolBar() {
		JToolBar tBar = new JToolBar(); // 툴바 생성
		tBar.add(startBtn); //툴바에 버튼을 생성하여 추가
		tBar.add(stopBtn);
		getContentPane().add(tBar, BorderLayout.NORTH); // 툴바는 항상 보더레이아웃 북쪽 조건임
		
		//버튼의 액션 리스너
		startBtn.addActionListener(new StartAction());
		
		// 버튼에 마우스가 올려졌을 때 이미지 변화
		startBtn.setRolloverIcon(overIcon);
		startBtn.setPressedIcon(pressedIcon);		
	}
	
	// 스타트 버튼에 들어갈 액션리스너
	private class StartAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gamePanel.startGame(); // 게임패널의 스타트게임을 부름
		}
	}
}
