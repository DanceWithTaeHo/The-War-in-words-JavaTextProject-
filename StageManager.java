public class StageManager {
	private Stage stage [] = new Stage[4];
	
	private ScorePanel scorePanel = null;
	private GameGroundPanel gameGroundPanel = null;
	private GaugePanel gaugePanel = null;
	private TextManager textManager = null;
	private UnitLabelManager unitLabelManager = null;
	private int level = 1;
	public StageManager(ScorePanel scorePanel, GameGroundPanel gameGroundPanel, GaugePanel gaugePanel, TextManager textManager, UnitLabelManager unitLabelManager) {
		this.scorePanel = scorePanel;
		this.gameGroundPanel = gameGroundPanel;
		this.gaugePanel = gaugePanel;
		this.textManager = textManager;
		this.unitLabelManager = unitLabelManager;
		for(int i=0; i<stage.length; i++) { // �������� ����
			stage[i] = new Stage(gameGroundPanel, textManager, unitLabelManager, i+1);
		}		
	}
	
	// �������� ����
	public void start() {
		scorePanel.setStageLabel("Stage 1");
		stage[0].start();
	}
	
	// �������� �ʱ�ȭ
	public void initStage() {
		exit();
		level = 1;
		for(int i=0; i<stage.length; i++) {
			stage[i] = new Stage(gameGroundPanel, textManager, unitLabelManager, i+1);
		}	
		stage[0].start();
	}
	
	// �������� �簳
	public void rePlay() {
		for(int i=0; i<level; i++) {
			stage[i].resume();
		}
		textManager.moveThreadStart();
	}
	
	// �������� �ߴ�
	public void stop() {
		for(int i=0; i<level; i++) {
			stage[i].suspend();
		}
		textManager.moveThreadStop();
	}
	
	// ����
	public void exit() {
		for(int i=0; i<level; i++) {
			stage[i].interrupt();
		}
	}
	
	// �������� ���� ��ȯ
	public int getLevel() {		
		return level;
	}
	
	// ���� �׶����� �ؽ�Ʈ ����
	public void setStageMsg(String msg) {
		if(scorePanel.isSpcialMove()) {
			gameGroundPanel.setText(msg);
		}		
	}
	
	// ���� ���������� �Ѿ �� �ִ��� Ȯ��
	public void checkNextStage() {
		int enemyHP = gaugePanel.getEnemyHP();
		if(level==1 && enemyHP<=160) {
			scorePanel.setStageLabel("Stage 2!");
			scorePanel.setMsg("������ ����ź�� �����ϴ�");
			level++;
			stage[level-1].start();
		}
		else if(level==2 && enemyHP<=120) {
			scorePanel.setStageLabel("Stage 3!");
			scorePanel.setMsg("�� ��ũ ����!");
			level++;
			stage[level-1].start();
		}
		else if(level==3 && enemyHP<=80) {
			scorePanel.setStageLabel("Stage 4!");
			scorePanel.setMsg("�� ������ ����");
			level++;
			stage[level-1].start();
		}
		else if(enemyHP<=0) {
			textManager.deleteAllText();
			scorePanel.setStageLabel("Clear!");
			gameGroundPanel.setText("���ӿ��� �¸��Ͽ����ϴ�!");
			scorePanel.setMsg("���ӿ��� �¸��Ͽ����ϴ�!");
			exit();
		}
	}
}
