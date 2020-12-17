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
		for(int i=0; i<stage.length; i++) { // 스테이지 생성
			stage[i] = new Stage(gameGroundPanel, textManager, unitLabelManager, i+1);
		}		
	}
	
	// 스테이지 시작
	public void start() {
		scorePanel.setStageLabel("Stage 1");
		stage[0].start();
	}
	
	// 스테이지 초기화
	public void initStage() {
		exit();
		level = 1;
		for(int i=0; i<stage.length; i++) {
			stage[i] = new Stage(gameGroundPanel, textManager, unitLabelManager, i+1);
		}	
		stage[0].start();
	}
	
	// 스테이지 재개
	public void rePlay() {
		for(int i=0; i<level; i++) {
			stage[i].resume();
		}
		textManager.moveThreadStart();
	}
	
	// 스테이지 중단
	public void stop() {
		for(int i=0; i<level; i++) {
			stage[i].suspend();
		}
		textManager.moveThreadStop();
	}
	
	// 종료
	public void exit() {
		for(int i=0; i<level; i++) {
			stage[i].interrupt();
		}
	}
	
	// 스테이지 레벨 반환
	public int getLevel() {		
		return level;
	}
	
	// 게임 그라운드의 텍스트 설정
	public void setStageMsg(String msg) {
		if(scorePanel.isSpcialMove()) {
			gameGroundPanel.setText(msg);
		}		
	}
	
	// 다음 스테이지로 넘어갈 수 있는지 확인
	public void checkNextStage() {
		int enemyHP = gaugePanel.getEnemyHP();
		if(level==1 && enemyHP<=160) {
			scorePanel.setStageLabel("Stage 2!");
			scorePanel.setMsg("적군이 수류탄을 던집니다");
			level++;
			stage[level-1].start();
		}
		else if(level==2 && enemyHP<=120) {
			scorePanel.setStageLabel("Stage 3!");
			scorePanel.setMsg("적 탱크 등장!");
			level++;
			stage[level-1].start();
		}
		else if(level==3 && enemyHP<=80) {
			scorePanel.setStageLabel("Stage 4!");
			scorePanel.setMsg("적 전투기 등장");
			level++;
			stage[level-1].start();
		}
		else if(enemyHP<=0) {
			textManager.deleteAllText();
			scorePanel.setStageLabel("Clear!");
			gameGroundPanel.setText("게임에서 승리하였습니다!");
			scorePanel.setMsg("게임에서 승리하였습니다!");
			exit();
		}
	}
}
