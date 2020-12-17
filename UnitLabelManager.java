import java.awt.Point;

import javax.swing.*;

public class UnitLabelManager {
	private ImageIcon mySoldierIcon = new ImageIcon("./images/MySoldier.png");
	private ImageIcon mySoldierBaseIcon = new ImageIcon("./images/MySoldier-base.png");
	
	private ImageIcon myGrenadeIcon = new ImageIcon("./images/MyGrenade.png");
	private ImageIcon ThrowMyGrenadeIcon = new ImageIcon("./images/ThrowMyGrenade.png");
	
	private ImageIcon myRocetLauncherIcon = new ImageIcon("./images/MyRocetLauncher.png");
	private ImageIcon shotMyRocetLauncherIcon = new ImageIcon("./images/ShotMyRocetLauncher.png");

	private ImageIcon myTankIcon = new ImageIcon("./images/MyTank.png");
	private ImageIcon shotMyTankIcon = new ImageIcon("./images/ShotMyTank.png");

	private ImageIcon myFighterIcon = new ImageIcon("./images/MyFighter.png");
	private ImageIcon myMissileIcon = new ImageIcon("./images/MyMissile.png");
	private ImageIcon myBangIcon = new ImageIcon("./images/MyBang.png");
	
	private ImageIcon enemySoldierIcon = new ImageIcon("./images/EnemySoldier.png");
	private ImageIcon enemyGrenadeIcon = new ImageIcon("./images/EnemyGrenade.png");
	private ImageIcon enemyTankIcon = new ImageIcon("./images/EnemyTank.png");
	
	private ImageIcon enemyFighterIcon = new ImageIcon("./images/EnemyFighter.png");
	private ImageIcon enemyMissileIcon = new ImageIcon("./images/EnemyMissile.png");
	private ImageIcon enemyBangIcon = new ImageIcon("./images/EnemyBang.png");
	
	private Unit myUnit [] = new Unit[7];
	private Unit enemyUnit [] = new Unit[6];
	
	private Unit mySoldier = null;
	private Unit myGrenade = null;
	private Unit myRocetLauncher = null;
	private Unit myTank = null;
	private Unit myFighter = null;
	private Unit myMissile = null;
	private Unit myBomb = null;
	
	private Unit enemySoldier = null;
	private Unit enemyGrenade = null;
	private Unit enemyTank = null;
	
	private Unit enemyFighter = null;
	private Unit enemyMissile = null;
	private Unit enemyBomb = null;
	
	GameGroundPanel gameGroundPanel = null;
	FighterThread fighterThread = null;
	UnitDeleteThread unitDeleteThread = null;
	
	public UnitLabelManager(GameGroundPanel gameGroundPanel) {
		this.gameGroundPanel = gameGroundPanel;
		
		// 유닛을 생성
		myUnit[0] = mySoldier = new Unit(mySoldierBaseIcon.getImage(), mySoldierIcon.getImage(), 150, 150, 150, 150);
		myUnit[1] = myGrenade = new Unit(myGrenadeIcon.getImage(), ThrowMyGrenadeIcon.getImage(), 150, 150, 200, 150);
		myUnit[2] = myRocetLauncher = new Unit(myRocetLauncherIcon.getImage(), shotMyRocetLauncherIcon.getImage(), 150, 150, 215, 150);
		myUnit[3] = myTank = new Unit(myTankIcon.getImage(), shotMyTankIcon.getImage(), 230, 260, 250, 250);
		myUnit[4] = myFighter = new Unit(myFighterIcon.getImage(), myFighterIcon.getImage(), 150, 150, 150, 150);
		myUnit[5] = myMissile = new Unit(myMissileIcon.getImage(), myMissileIcon.getImage(), 150, 150, 150, 150);
		myUnit[6] = myBomb = new Unit(myBangIcon.getImage(), myBangIcon.getImage(), 500, 300, 500, 300);
		
		enemyUnit[0] = enemySoldier = new Unit(enemySoldierIcon.getImage(), enemySoldierIcon.getImage(), 150, 150, 150, 150);
		enemyUnit[1] = enemyGrenade = new Unit(enemyGrenadeIcon.getImage(), enemyGrenadeIcon.getImage(), 150, 150, 200, 150);
		enemyUnit[2] = enemyTank = new Unit(enemyTankIcon.getImage(), enemyTankIcon.getImage(), 250, 250, 250, 250);
		enemyUnit[3] = enemyFighter = new Unit(enemyFighterIcon.getImage(), enemyFighterIcon.getImage(), 150, 150, 150, 150);
		enemyUnit[4] = enemyMissile = new Unit(enemyMissileIcon.getImage(), enemyMissileIcon.getImage(), 100, 100, 100, 100);
		enemyUnit[5] = enemyBomb = new Unit(enemyBangIcon.getImage(), enemyBangIcon.getImage(), 500, 300, 500, 300);
	}

	// 유닛 초기화
	public void initUnit() {
		for(int i=0; i<myUnit.length; i++) {
			gameGroundPanel.remove(myUnit[i]);
		}
		for(int i=0; i<enemyUnit.length; i++) {
			gameGroundPanel.remove(enemyUnit[i]);
		}
		unitDeleteThread.interrupt();
		if(fighterThread != null)
			fighterThread.interrupt();
		createMyUnit(1);
		gameGroundPanel.repaint();
	}
	
	// 아군 유닛 생성
	public void createMyUnit(int upgrade) {
		if(upgrade == 1) {
			mySoldier.setLocation(10, 200);
			gameGroundPanel.add(mySoldier);
		}
		else if(upgrade == 2) {
			myGrenade.setLocation(10, 100);
			gameGroundPanel.add(myGrenade);
		}
		else if(upgrade ==3) {
			myRocetLauncher.setLocation(10, 350);
			gameGroundPanel.add(myRocetLauncher);
		}
		else if(upgrade ==4) {
			myTank.setLocation(50, 250);
			gameGroundPanel.add(myTank);
		}
	}
	
	// 적군 유닛 생성
	public void createEnemyUnit(Point location, int level) {
		int x = (int) location.getX();
		int y = (int) location.getY();

		if(level == 1) {
			enemySoldier.setLocation(x+40, y-50);
			unitDeleteThread = new UnitDeleteThread(enemySoldier, 1500);
			gameGroundPanel.add(enemySoldier);	
		}
		else if(level == 2) {
			enemyGrenade.setLocation(x+40, y-30);
			unitDeleteThread = new UnitDeleteThread(enemyGrenade, 1500);
			gameGroundPanel.add(enemyGrenade);
		}
		else if(level == 3) {
			enemyTank.setLocation(x+40, y-60);
			unitDeleteThread = new UnitDeleteThread(enemyTank, 1500);
			gameGroundPanel.add(enemyTank);
		}
		if(level == 4) {
			enemyFighter.setLocation(x, y);
			fighterThread = new FighterThread(enemyFighter, enemyMissile, enemyBomb, true);
			gameGroundPanel.add(enemyFighter);
			fighterThread.start();
			return;
		}
		unitDeleteThread.start();
	}
	
	// 적 공격
	public void attackEnemy() {
		StopMotionThread stopMotionThread = null;
		for(int i=0; i<myUnit.length-3; i++) {
			myUnit[i].attack();
			stopMotionThread = new StopMotionThread(myUnit[i], 1000);
			stopMotionThread.start();
		}
	}
	
	// 공격 중지
	public void stopAttack() {
		for(int i=0; i<myUnit.length-3; i++)
			myUnit[i].stopAttack();
	}
	
	// 유닛 삭제 스레드 중단
	public void stop() {
		unitDeleteThread.suspend();
		if(fighterThread != null)
			fighterThread.suspend();		
	}
	
	// 유닛 삭제 스레드 재개
	public void rePlay() {
		unitDeleteThread.resume();
		if(fighterThread != null)
			fighterThread.resume();
	}
	
	// 필살기 유닛 생성
	public void specialMove() {
		myFighter.setLocation(10, 10);
		fighterThread = new FighterThread(myFighter, myMissile, myBomb, false);
		gameGroundPanel.add(myFighter);
		fighterThread.start();
	}
	
	/* 전투기 유닛을 움직이게할 스레드 */
	class FighterThread extends Thread{
		private Unit fighter = null;
		private Unit missile = null;
		private Unit bomb = null;
		private int x, y, bombX, bombY;
		boolean isEnemy;
		
		public FighterThread(Unit fighter, Unit missile, Unit bomb, boolean isEnemy) {
			this.fighter = fighter;
			this.missile = missile;
			this.bomb = bomb;
			this.isEnemy = isEnemy;
			x = fighter.getX();
			y = fighter.getY();
		}
		@Override
		public void run() {
			while(true) {
				if(isEnemy) { // 적 항공기라면
					fighter.setLocation(fighter.getX()-3, y);
					if((x - fighter.getX()) >= 100) {
						bombX = fighter.getX();
						bombY = fighter.getY();
						shotBomb();
						this.interrupt();
					}
				}
				else { // 아군 항공기라면
					fighter.setLocation(fighter.getX()+3, y);
					if((myFighter.getX() - x) >= 100) {
						bombX = fighter.getX();
						bombY = fighter.getY();
						shotBomb();
						this.interrupt();
					}
				}
				try {					
					sleep(100);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
		private void shotBomb() { //  폭탄 발사
			missile.setLocation(bombX, bombY);
			gameGroundPanel.add(missile);
			gameGroundPanel.repaint();
			while(true) {
				if(isEnemy) {
					fighter.setLocation(fighter.getX()-3, y);
					missile.setLocation(missile.getX()-1, missile.getY()+3);
					if(missile.getY() >= 200) {						
						bomb.setLocation(450, missile.getY());
						this.interrupt();
					}
				}
				else {
					fighter.setLocation(fighter.getX()+3, y);
					missile.setLocation(missile.getX()+1, missile.getY()+3);
					if(missile.getY() >= 200) {						
						bomb.setLocation(300, missile.getY()-100);
						this.interrupt();
					}
				}				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					gameGroundPanel.remove(fighter);
					gameGroundPanel.remove(missile);
					gameGroundPanel.add(bomb);
					unitDeleteThread = new UnitDeleteThread(bomb, 1300);
					unitDeleteThread.start();
					gameGroundPanel.repaint();
					return;
				}
			}
		}
	}
	
	// 적 유닛을 삭제하는 스레드
	// 적 유닛을 생성하고 delay 이후에 삭제시킴
	class UnitDeleteThread extends Thread{
		private JLabel unit = null;
		private int delay;
		public UnitDeleteThread(Unit unit, int delay) {
			this.unit = unit;
			this.delay = delay;
		}
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					gameGroundPanel.remove(unit);
					gameGroundPanel.repaint();
					return;
				}
				this.interrupt();
			}
		}
	}
	
	// 아군 유닛의 스탑모션 스레드
	// 적 공격후 delay 이후 공격 중지
	class StopMotionThread extends Thread{
		Unit unit = null;
		private int delay;
		public StopMotionThread(Unit unit, int delay) {
			this.unit = unit;
			this.delay = delay;
		}
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					return;
				}
				unit.stopAttack();
				gameGroundPanel.repaint();				
				this.interrupt();
			}
		}
	}
}