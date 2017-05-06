package jp.co.game.money.game.strategy.impl;

import jp.co.game.money.game.strategy.StrategyInterface;

public class Minus1 implements StrategyInterface{
	int[] myPoint;
	int[] enemyPoint;
	int mySum = 0;

	@Override
	public int getPoint(int round) {
		int nowPoint = 1000;
			
		if(round >= 1 && round < 9){
			if(enemyPoint[round - 1] > 2){
				nowPoint = enemyPoint[round -1] - 1;
			}
		}
		int mySumTemp = mySum + nowPoint;
		if(mySumTemp > 10000 - (9 - round)){
			nowPoint = 10000 - ( mySum + (9 - round));
		}
		
		if( round == 9){
			nowPoint = 10000 - mySum;
		}
		mySum += nowPoint;
		
		return nowPoint;
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
