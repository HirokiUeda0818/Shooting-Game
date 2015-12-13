package jp.libroworks;

import java.awt.Graphics2D;

public class GraphicsInfo {
	
	//Graphics2D型のフィールドgを定義
	public Graphics2D gg;
	//double型のフィールドframetimeを定義
	public double frametime;
	//long型のフィールドcurrenttimeを定義
	public long currenttime;
	//boolean型の配列keystateを定義
	public boolean[] keystate;
	
	//コンストラクタ
	public GraphicsInfo(){
		//int[] array = new int[5]の定義方法と同じこと
		this.keystate = new boolean[8];
		//keystateの要素に、すべてfalseを代入
		for(int i = 0; i<8; i++){
			this.keystate[i] = false;
		}
	}

}
