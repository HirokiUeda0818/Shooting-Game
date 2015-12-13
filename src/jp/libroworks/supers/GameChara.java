package jp.libroworks.supers;


import java.awt.geom.AffineTransform;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import jp.libroworks.GraphicsInfo;

public class GameChara {
	
	//キャラの画像データを代入するフィールド	
	protected BufferedImage img = null;
	//キャラの位置を記録するフィールド
	public Point2D.Double position = new Point2D.Double(100,100);
	//キャラの中心位置を記録するフィールド
	public Point2D.Double center = new Point2D.Double();
	//キャラの角度を記録するフィールド	
	public double angle = 0.0;
	//キャラの表示を記録するフィールド
	public boolean visible = true;
	//キャラのサイズを記録するフィールド
	public double size;
	
	
	/**
	 * 引数で与えられた画像データを、imgフィールドに代入
	 * また、画像データの中心位置も、centerフィールドへ代入
	 * 
	 */
	public GameChara setImage(BufferedImage img){
		this.img = img;
		this.center.x = this.img.getWidth()/2;
		this.center.y = this.img.getHeight()/2;
		return this;
	}
	
	
	/**
	 * 画像データを取得する
	 * @return 画像データ
	 */
	public BufferedImage getImage(){
		return this.img;
	}
	
	/**
	 * @param ginfo
	 * @param stage
	 * @return GameChara
	 */
	public GameChara draw(GraphicsInfo ginfo, Stage stage){
		//imgフィールドがnull、または非表示の場合は、描画処理をスキップ
		if(this.img == null){
			return this;
		}
		if(this.visible == false){
			return this;
		}
		
		//画像を回転させるために、変型という処理を使う
		//変型を利用するには、最初に現在の変型設定を退避させる必要があり、
		//Graphics2DクラスのgetTransformメソッドで、変型設定を取得している
		AffineTransform oldtr = ginfo.gg.getTransform();
		//rotateメソッドを使用するには、translateメソッドで、画像の描画位置を指定する必要あり
		ginfo.gg.translate(this.position.x, this.position.y);
		//画像の回転は、rotateメソッド。
		ginfo.gg.rotate(this.angle/180.0 * Math.PI , 0 ,0);
		//画像の描画位置はtranslateメソッドで指定しているが、画像の中心位置の分だけずらしている
		ginfo.gg.drawImage(this.img, -(int)this.center.x, -(int)this.center.y, null);
		//変型設定を戻す
		ginfo.gg.setTransform(oldtr);
		return this;
	}
}

