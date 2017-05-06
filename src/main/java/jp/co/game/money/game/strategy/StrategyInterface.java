package jp.co.game.money.game.strategy;

public interface StrategyInterface {
	int getPoint(int round);
	void setMyPoint(int[] myPoint);
	void setEnemyPoint(int[]enemyPoint);
}
