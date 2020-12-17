import javax.swing.*;

public class GamePanel extends JPanel{
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	
	// �����гο� ������ ���� �׶��� �гΰ� ��ǲ �г�
	private GameGroundPanel gameGroundPanel = new GameGroundPanel();
	private InputPanel inputPanel = null;
	private GaugePanel gaugePanel = new GaugePanel();
		
	private TextManager textManager = null; // text���� �����ϴ� �Ŵ���
	private StageManager stageManager = null; // �������� �Ŵ���
	private UnitLabelManager unitLabelManager = new UnitLabelManager(gameGroundPanel); // ���� �Ŵ���
	
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
	
	// ���� ���� �޼ҵ�
	public void startGame(boolean isEnglish) {
		gameGroundPanel.removeStartText();
		
		// �̸� �� �ѿ� ��� ���̵� ����
		name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���!");
		textManager.setMode(isEnglish);
		scorePanel.setName(name);
		scorePanel.setMode(isEnglish);				
		scorePanel.setDfficulty(editPanel.getDifficulty());
		
		// ���� ���� �� �������� ����
		unitLabelManager.createMyUnit(1);
		stageManager.start();
	}
	
	// ���� �ʱ�ȭ �޼ҵ�
	public void initGame() {
		textManager.deleteAllText();
		gaugePanel.initHP();
		scorePanel.initScore();
		unitLabelManager.initUnit();
		stageManager.initStage();
		gameGroundPanel.setText("");
	}
	
	// ���� �ߴ� �޼ҵ�
	public void stopGame() {
		stageManager.stop();
		unitLabelManager.stop();
	}
	
	// ���� �簳 �޼ҵ�
	public void rePlay() {
		stageManager.rePlay();
		unitLabelManager.rePlay();
	}

	// ���� ���� �޼ҵ�
	public void gameOver() {
		if(gaugePanel.getMyHP() == 0){
			stageManager.exit();
			textManager.deleteAllText();
		}
	}
}