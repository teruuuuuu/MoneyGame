package jp.co.game.money.game;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jp.co.game.money.game.strategy.StrategyInterface;

public class StrategyClassLooader extends ClassLoader{
	private static final String LoadClassPath = "build/classes/main/jp/co/game/money/game/strategy/impl/".replace("/", File.separator);
	private String filelist[];
	private Map<String, byte[]> classByteMap;
	private Map<String, Class<?>> classMap;
	
	public StrategyClassLooader(){		
		File classDir = new File( LoadClassPath);
		filelist = classDir.list();
		setClassByte(classDir);
		setClass();
	}
	
	public Set<String> getClassKeyMap(){
		return classMap.keySet();
	}
	
	public Object getGameClass(String key){
		Object gameClass = null;
		try {
			gameClass =  classMap.get(key).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return gameClass;
	}
	
    protected void setClassByte(File classDir ) {
    	classByteMap = new HashMap<String, byte[]>();
		for (int i = 0 ; i < filelist.length ; i++){
			//System.out.println(filelist[i]);
			byte[] b = loadClassData(classDir.getPath() + File.separator + filelist[i]);
			classByteMap.put(filelist[i], b);
	    }
    }
    
    protected void setClass(){
    	classMap = new HashMap<String, Class<?>>();
    	for (int i = 0 ; i < filelist.length ; i++){
    		byte[] b = classByteMap.get(filelist[i]);
    		Class<?> loadClass = (Class<StrategyInterface>) defineClass(null, b, 0, b.length);
    		try {
			} catch (SecurityException e) {
				e.printStackTrace();
			}
    		classMap.put(filelist[i], loadClass);
	    }
    }
	
	private byte[] loadClassData(String filePath) {
		byte[] b = new byte[1];
	    FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
		
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    while (fis.read(b) > 0) {
		        baos.write(b);
		    }
		    baos.close();
		    fis.close();
		    b = baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	    return b;
    }

}
