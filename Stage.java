import java.awt.Point;
import java.util.Vector;

import javax.swing.*;

public class Stage extends Thread{
	private int makeNum; // 단어 갯수
	private int delay; // 딜레이
	private int level; // 스테이지 레벨
	private Point location; // 단어 발사 위치
	
	private GameGroundPanel gameGroundPanel = null;	
	private Vector<JLabel> labelVector = null;	
	private TextManager textManager = null;
	private UnitLabelManager unitLabelManager = null;
	
	public Stage(GameGroundPanel gameGroundPanel, TextManager textManager, UnitLabelManager unitLabelManager, int level) {
		this.gameGroundPanel = gameGroundPanel;
		this.textManager = textManager;
		this.labelVector = textManager.getLabelVector();
		this.unitLabelManager = unitLabelManager;
		this.level = level;
		setStage();
	}
	
	@Override
	public void run() {
		while(true) {
			JLabel text = null;
			int count = 0;
			int pointY = (int)(Math.random()*450);
			
			// 스테이지 4일 때만 작업
			if(level == 4) {
				Point fighterLocation = new Point(800,10);
				unitLabelManager.createEnemyUnit(fighterLocation, level);
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					return;
				}
			}
			for(int i=0; i<makeNum; i++) {
				count++;
				setPoint(pointY);
				text = textManager.makeText(location, level); // 텍스트 발사
				labelVector.add(text);
				gameGroundPanel.repaint();
				makeUnit(count);
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	// 스테이지 생성시 초기화 작업
	private void setStage() {	
		if(level == 1) {
			makeNum = 1;
			delay= 3500; 
		}
		else if(level == 2) {
			makeNum = 2;
			delay= 10000;
		}
		else if(level == 3) {
			makeNum = 3;
			delay= 15000;
		}
		else if(level == 4) {
			makeNum = 5;
			delay= 24000;
		}
	}
	
	// 적 유닛 생성
	private void makeUnit(int count) {
		if((level == 1 && count==1) || (level == 2 && count==2) || (level == 3 && count == 3))
			unitLabelManager.createEnemyUnit(location, level);
	}
	
	// 스테이지별 단어의 발사 위치를 정의
	private void setPoint(int pointY) {
		int x, y;
		
		if(level == 1) location = new Point(800,(int)(Math.random()*500));		
		else if(level == 2) {
			x = (int)(Math.random()*(800-700))+700;
			y = (int)(Math.random()*((pointY+50)-pointY))+(pointY+50);
			location = new Point(x,y);
		}
		else if(level == 3) {
			x = (int)(Math.random()*(800-650))+650;
			y = (int)(Math.random()*((pointY+50)-pointY))+(pointY+50);
			location = new Point(x,y);
		}
		else if(level == 4) {
			x = (int)(Math.random()*(650-500))+500;
			y = (int)(Math.random()*(400-200))+200;
			location = new Point(x,y);
		}
	}
}