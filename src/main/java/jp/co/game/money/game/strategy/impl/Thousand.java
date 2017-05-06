package jp.co.game.money.game.strategy.impl;

import jp.co.game.money.game.strategy.StrategyInterface;

public class Thousand implements StrategyInterface{
	int[] myPoint;
	int[] enemyPoint;
	
	@Override
	public int getPoint(int round) {
		return 1000;
	}
	
	@Override
	public void setMyPoint(int[] myPoint ) {
		this.myPoint = myPoint;
		
	}
	
	@Override
	public void setEnemyPoint(int[] enemyPoint) {
		this.enemyPoint = enemyPoint;
	}

}
