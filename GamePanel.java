import javax.swing.*;

public class GamePanel extends JPanel{
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	
	// 게임패널에 부착될 게임 그라운드 패널과 인풋 패널
	private GameGroundPanel gameGroundPanel = new GameGroundPanel();
	private InputPanel inputPanel = null;
	private GaugePanel gaugePanel = new GaugePanel();
		
	private TextManager textManager = null; // text들을 관리하는 매니저
	private StageManager stageManager = null; // 스테이지 매니저
	private UnitLabelManager unitLabelManager = new UnitLabelManager(gameGroundPanel); // 유닛 매니저
	
	private String name;
	
	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) {
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;
		textManager = new TextManager(this, gameGroundPanel, gaugePanel, editPanel);
		stageManager = new StageManager(scorePanel, gameGroundPanel, gaugePanel, textManager, unitLabelManager);
		inputPanel = new InputPanel(scorePanel, gaugePanel, textManager, stageManager, unitLabelManager);
		
		setLayout(null);
		
		gaugePanel.setSize(1000, 20);
		gaugePanel.setLocation(0, 0);
		add(gaugePanel);

		gameGroundPanel.setSize(1000, 570);
		gameGroundPanel.setLocation(0, 20);
		add(gameGroundPanel);
		
		inputPanel.setSize(1000, 30);
		inputPanel.setLocation(0, 590);
		add(inputPanel);
	}
	
	// 게임 시작 메소드
	public void startGame(boolean isEnglish) {
		gameGroundPanel.removeStartText();
		
		// 이름 및 한영 모드 난이도 설정
		name = JOptionPane.showInputDialog("이름을 입력하세요!");
		textManager.setMode(isEnglish);
		scorePanel.setName(name);
		scorePanel.setMode(isEnglish);				
		scorePanel.setDfficulty(editPanel.getDifficulty());
		
		// 유닛 생성 및 스테이지 시작
		unitLabelManager.createMyUnit(1);
		stageManager.start();
	}
	
	// 게임 초기화 메소드
	public void initGame() {
		textManager.deleteAllText();
		gaugePanel.initHP();
		scorePanel.initScore();
		unitLabelManager.initUnit();
		stageManager.initStage();
		gameGroundPanel.setText("");
	}
	
	// 게임 중단 메소드
	public void stopGame() {
		stageManager.stop();
		unitLabelManager.stop();
	}
	
	// 게임 재개 메소드
	public void rePlay() {
		stageManager.rePlay();
		unitLabelManager.rePlay();
	}

	// 게임 오버 메소드
	public void gameOver() {
		if(gaugePanel.getMyHP() == 0){
			stageManager.exit();
			textManager.deleteAllText();
		}
	}
}