package jp.co.game.money.game.strategy.impl;

import jp.co.game.money.game.strategy.StrategyInterface;

public class Random implements StrategyInterface{
	int[] myPoint;
	int[] enemyPoint;
	int mySum = 0;

	@Override
	public int getPoint(int round) {
		int nowPoint = getEneAve(round) - getRand(round);
			

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
	
	private int getEneSum(){
		int ret=0;
		for(int i=0; i<enemyPoint.length; i++){
			ret += enemyPoint[i];
		}
		return ret;
	}
	
	private int getEneAve(int round){
		int ret=1000;
		if(round!=0){
			ret=(getEneSum()) / (round);
		}
		return ret;
	}
	
	private int getRand(int round){
		int seed = 100;
		if(round != 0){
			seed =  (int) (getEneSum()/round * 0.1);
		}
		int ret = (int)(Math.random()*seed);
        return ret;
	}

}
