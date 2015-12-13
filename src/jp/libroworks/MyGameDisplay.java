package jp.libroworks;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.libroworks.stage.Stage1;
import jp.libroworks.supers.GameChara;
import jp.libroworks.supers.GameDisplay;
import jp.libroworks.supers.Stage;

public class MyGameDisplay extends GameDisplay {
	
	//内部クラスを保存するためのフィールド
	GameDisplay title, main, over, clear;
	//Fontクラスのフィールド
	private Font mfont = new Font("Sanserif", Font.BOLD, 50);
	//Stageクラスのフィールド
	Stage stage = new Stage1();
	
	//4つのインスタンス作成
	public MyGameDisplay(){
		this.title = new MyGameTitle();
		this.main = new MyGameMain();
		this.over = new MyGameOver();
		this.clear = new MyStageClear();
		GameDisplay.current = this.title;
	}

	@Override
	public void show(GraphicsInfo ginfo) {
	}

	//MyGameDisplay.loadMediaを呼び出せば、4つの内部クラスのloadMediaメソッドを呼び出す
	@Override
	public void loadMedia() throws IOException {
		this.title.loadMedia();
		this.main.loadMedia();
		this.over.loadMedia();
		this.clear.loadMedia();
	}
	
	//タイトル画面
	class MyGameTitle extends GameDisplay{
		//タイトル画面を代入するフィールド
		private BufferedImage img_title;

		@Override
		public void show(GraphicsInfo ginfo) {
			//タイトル画像の表示
			ginfo.gg.drawImage(this.img_title, 0, 0, null);
			
			//フォント設定
			ginfo.gg.setColor(Color.CYAN);
			ginfo.gg.setFont(MyGameDisplay.this.mfont);
			
			//文字幅を調べる
			String str = "PUSH SPACE";
			//getFontMetricsメソッドで、フォント情報を取得
			FontMetrics fm = ginfo.gg.getFontMetrics();
			//stringWidthメソッドで文字幅を取得
			int strw = fm.stringWidth(str)/2;
			
			//文字列の表示（画面中央に）
			ginfo.gg.drawString(str, 400 - strw, 600);
			
			//スペースキーが押されたら、currentフィールドに、mainフィールドを代入
			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				GameDisplay.current = MyGameDisplay.this.main;
				//自機とボスの位置を初期化
				MyGameDisplay.this.stage.init(ginfo);
			}
		}

		@Override
		public void loadMedia() throws IOException {
			//スタティックメソッドのreadメソッドで読み込み
			//Fileクラスのインスタンスを受け取り、BufferedImageクラスのインスタンスを返す
			//readメソッドは必ず、例外処理を実装しなければならない.
			//throws IOExceptionをつけることで、例外処理を呼び出し元のクラスに任せる
			this.img_title = ImageIO.read(new File("media/title.jpg"));
		}
		
	}
	
	//ゲーム本編
	class MyGameMain extends GameDisplay{

		@Override
		public void show(GraphicsInfo ginfo) {
			//自機とプレイヤーの描画
			MyGameDisplay.this.stage.draw(ginfo);
			
		}
		
		//TODO
		@Override
		public void loadMedia() throws IOException {
			MyGameDisplay.this.stage.loadMedia();
		}
		
	}
	
	//ゲームオーバー画面
	class MyGameOver extends GameDisplay{

		@Override
		public void show(GraphicsInfo ginfo) {
			ginfo.gg.setColor(Color.RED);
			ginfo.gg.setFont(MyGameDisplay.this.mfont);
			String str = "ゲームオーバー";
			FontMetrics fm = ginfo.gg.getFontMetrics();
			int strw = fm.stringWidth(str)/2;
			ginfo.gg.drawString(str, 400-strw, 200);
			if(ginfo.currenttime - this.starttime > 5000){
				GameDisplay.current = MyGameDisplay.this.title;
			}
		}

		@Override
		public void loadMedia() {
		}
		
	}
	
	//ステージクリア画面
	class MyStageClear extends GameDisplay{

		@Override
		public void show(GraphicsInfo ginfo) {
			ginfo.gg.setColor(Color.RED);
			ginfo.gg.setFont(MyGameDisplay.this.mfont);
			String str = "ステージクリア";
			FontMetrics fm = ginfo.gg.getFontMetrics();
			int strw = fm.stringWidth(str)/2;
			ginfo.gg.drawString(str, 400-strw, 200);
			if(ginfo.currenttime - this.starttime > 5000){
				GameDisplay.current = MyGameDisplay.this.title;
			}
		}

		@Override
		public void loadMedia() {
		}
		
	}

}
