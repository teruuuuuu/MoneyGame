package jp.co.game.money;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import jp.co.game.money.game.StrategyClassLooader;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.buttle();
	}

	void buttle(){
		StrategyClassLooader myClassLoader = new StrategyClassLooader();
		Set<String> classKeySet = myClassLoader.getClassKeyMap();
		HashMap<Integer, String> classMap = new HashMap<Integer, String>();
		List<Integer> inputKey = new ArrayList<Integer>();
		int classCount = 1;
		for(String value : classKeySet){
			classMap.put(classCount, value);
			inputKey.add(classCount);
			classCount++;
		}
		
		for(Map.Entry<Integer, String> e : classMap.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		int[] aPoint = new int[10];
		int[] bPoint = new int[10];
		Scanner scan = new Scanner(System.in);
		String aName = classMap.get(inputStrData(scan, "chose playerA:", inputKey));
		String bName = classMap.get(inputStrData(scan, "chose playerB:", inputKey));
		
		Object playerA = myClassLoader.getGameClass(aName);
		Object playerB = myClassLoader.getGameClass(bName);
		
		int aSum = 0, aKickBack = 0;
		int bSum = 0, bKickBack = 0;
		
		try {
			playerA.getClass().getMethod("setMyPoint", int[].class).invoke(playerA, aPoint );
			playerA.getClass().getMethod("setEnemyPoint", int[].class).invoke(playerA, bPoint );
			playerB.getClass().getMethod("setMyPoint", int[].class).invoke(playerB, bPoint );
			playerB.getClass().getMethod("setEnemyPoint", int[].class).invoke(playerB, aPoint );
			
			for(int i=0; i<10; i++){
				int aNow = (int) playerA.getClass().getMethod("getPoint", int.class).invoke(playerA, i );
				int bNow = (int) playerB.getClass().getMethod("getPoint", int.class).invoke(playerB, i );
				aPoint[i] = aNow;
				bPoint[i] = bNow;
				System.out.println(String.valueOf(i) + ": \n 投入金額: " + aName + " " + String.valueOf(aNow) + "\n 投入金額: " + bName + " " + String.valueOf(bNow));
				aSum += aNow;
				bSum += bNow;
				if(aNow == 0 || bNow == 0){
					String message = "";
					if(aNow == 0){
						message += aName + " ";
					}
					if(bNow == 0){
						message += bName + " ";
					}
					message += "loose";
					System.out.println(message);
					return;
				}
				if(aNow < bNow){
					aKickBack += bNow;
				}else if(bNow < aNow){
					bKickBack += aNow;
				}else{
					aKickBack += aNow;
					bKickBack += bNow;
				}
				System.out.println( "\n 取得金額: " + aName + " " + String.valueOf(aKickBack) + "\n 取得金額: " + bName + " " + String.valueOf(bKickBack) + "\n");
			}
			if(aSum != 10000 || bSum != 10000){
				String message = "";
				if(aSum != 10000){
					message += aName + " ";
				}
				if(bSum != 10000){
					message += bName + " ";
				}
				message += "violation";
				System.out.println(message);
				return;
			}
			if(aKickBack > bKickBack){
				System.out.println(aName + " win");
			}else if(bKickBack > aKickBack){
				System.out.println(bName + " win");
			}else{
				System.out.println("draw");
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	}
	
	int inputStrData(Scanner scan, String message, List<Integer> kouho){
		int ret=1;
		while(true){
			try{
				System.out.print(message);
				ret = Integer.parseInt(scan.next());
				if(kouho.contains(ret)){
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ret;
	}
}
