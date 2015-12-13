package jp.libroworks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FirstShooting {

	public static void main(String[] args) {
		
		//スタティックメソッド内では、通常のフィールドやメソッド直接利用できないため
		FirstShooting fst = new FirstShooting();
		fst.start();
	}
	
	//JFrame型のフィールド定義
	JFrame mainwindow;
	//BufferStrategy型のフィールド定義
	BufferStrategy strategy;
	//MyGameDisplay型のインスタンス作成
	MyGameDisplay display = new MyGameDisplay();
	

	//コンストラクタ
	FirstShooting(){
		//ウィンドウの各種設定
		this.mainwindow = new JFrame("シューティング");
		this.mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainwindow.setSize(800, 720);
		this.mainwindow.setLocationRelativeTo(null);
		this.mainwindow.setVisible(true);
		this.mainwindow.setResizable(false);
		
		//バッファストラテジーの各種設定
		this.mainwindow.setIgnoreRepaint(true);
		this.mainwindow.createBufferStrategy(2);
		this.strategy = this.mainwindow.getBufferStrategy();
		
		
		
		//キーアダプタのインスタンスを作成
		this.mainwindow.addKeyListener(new MyKeyAdapter());
		
		//画像読み込み
		try {
			this.display.loadMedia();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.mainwindow, "タイトル画像読み込みエラー");
		}
	}
	
	//startメソッドの定義
	void start(){
		//Timerクラスのインスタンス作成
		Timer t = new Timer();
		//RenderTaskクラスのrunメソッドを16ミリ秒間隔で呼び出している
		t.schedule(new RenderTask(), 0, 16);
	}
	
	//RenderTaskクラスの定義
	class RenderTask extends TimerTask{

		@Override
		public void run() {
			FirstShooting.this.render();
		}
	}
	
	//1970年1月1日からのミリ秒数を返している。FirstShootingクラスのインスタンスが作成された時点の時刻
	long lasttime = System.currentTimeMillis();
	//GraphicsInfoクラスのインスタンスを作成
	GraphicsInfo ginfo = new GraphicsInfo();
	
	//renderメソッドの定義
	void render(){
		//renderメソッド呼び出し時の時刻を記録
		long time = System.currentTimeMillis();
		//renderメソッドの1ループ分の長さを記録
		this.ginfo.frametime = (time - this.lasttime) * 0.001;
		//lasttimeフィールドの更新
		this.lasttime = time;
		//currenttimeフィールドの更新
		this.ginfo.currenttime = time;
			
		//Graphics2Dクラスのインスタンスを取得
		Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
		//ウィンドウ全体をクリア
		g.setBackground(Color.black);
		g.clearRect(0, 0, this.mainwindow.getWidth(), this.mainwindow.getHeight());
		
		//GraphicsInfoクラスのggフィールドに、Graphics2Dインスタンスを代入
		ginfo.gg = g;
		
		this.display.getCurrentDisplay().show(ginfo);
		
		//描画を完了し、バッファを切り替える
		g.dispose();
		this.strategy.show();
	}
	
	//MyKeyAdapterクラスの定義
	class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			this.setValue(e.getKeyCode(), true);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			this.setValue(e.getKeyCode(), false);
		}
		
		//setValueメソッドの定義
		private void setValue(int keycode, boolean b){
			//GraphicsInfoクラスで初期化したkeystateフィールドを代入。要素数が8個
			boolean[] keystate = FirstShooting.this.ginfo.keystate;
			
			//キーが押された際、setValueメソッドのboolean bがそれぞれ代入される
			switch(keycode){
			case KeyEvent.VK_LEFT:
				keystate[KEY_STATE.LEFT] = b;
				break;
			case KeyEvent.VK_RIGHT:
				keystate[KEY_STATE.RIGHT] = b;
				break;
			case KeyEvent.VK_UP:
				keystate[KEY_STATE.UP] = b;
				break;
			case KeyEvent.VK_DOWN:
				keystate[KEY_STATE.DOWN] = b;
				break;
			case KeyEvent.VK_Z:
				keystate[KEY_STATE.Z] = b;
				break;
			case KeyEvent.VK_X:
				keystate[KEY_STATE.X] = b;
				break;
			case KeyEvent.VK_C:
				keystate[KEY_STATE.C] = b;
				break;
			case KeyEvent.VK_SPACE:
				keystate[KEY_STATE.SPACE] = b;
				break;
			}
		}
		
	}
	
}
