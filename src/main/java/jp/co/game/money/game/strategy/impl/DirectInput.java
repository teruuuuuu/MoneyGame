package jp.co.game.money.game.strategy.impl;

import java.util.Scanner;

import jp.co.game.money.game.strategy.StrategyInterface;

public class DirectInput implements StrategyInterface{
	int[] myPoint;
	int[] enemyPoint;
	int mySum = 0;

	@Override
	public int getPoint(int round) {
		int nowPoint = 1000; 
		Scanner scan = new Scanner(System.in);
		int max = 10000 - ( mySum + (9 - round));
		int minium = 1;
		if(round == 9){
			minium = max;
		}
		nowPoint = inputData(scan, "direct Input(" + String.valueOf(minium) + String.valueOf(max) + "):", max, minium);
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
	
	int inputData(Scanner scan, String message, int max, int minium){
		int ret=1;
		while(true){
			try{
				System.out.print(message);
				ret = Integer.parseInt(scan.next());
				if(ret <= max && ret >= minium){
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ret;
	}
}
