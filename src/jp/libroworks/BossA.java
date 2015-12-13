package jp.libroworks;

import java.util.ArrayList;

import jp.libroworks.supers.GameChara;
import jp.libroworks.supers.Pattern;
import jp.libroworks.supers.Stage;

public class BossA extends GameChara {
	
	//ArrayList<要素の型> 変数名 = new ArrayList<要素の型>();
	private ArrayList<Pattern> patternlist = new ArrayList<Pattern>();
	private Pattern curpat;
	
	//コンストラクタ
	//PatternA1クラスのインスタンスを作成し、patternlistフィールドに追加
	//その最初の要素を、curpatフィールドに代入
	public BossA(){
		this.patternlist.add(new PatternA1());
		this.curpat = this.patternlist.get(0);
		this.patternlist.add(new PatternA2());
		this.patternlist.get(0).setNext(this.patternlist.get(1));
		this.patternlist.add(new PatternA3());
		this.patternlist.get(1).setNext(this.patternlist.get(2));
		this.patternlist.add(new PatternA4());
		this.patternlist.get(2).setNext(this.patternlist.get(3));
	}
	
	//出現
	class PatternA1 extends Pattern{

		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossA.this.position.y > 200) return true;
			return false;
		}

		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossA.this.position.y += 120 * ginfo.frametime;
			
		}
	}
	
	
	
	//左移動
		class PatternA2 extends Pattern{

			@Override
			public boolean isFinished(GraphicsInfo ginfo) {
				if(BossA.this.position.x < 100) return true;
				return false;
			}

			@Override
			public void move(GraphicsInfo ginfo, Stage stage) {
				BossA.this.position.x -= 240 * ginfo.frametime;
				
			}
		}
		
		//右移動
		class PatternA3 extends Pattern{

			@Override
			public boolean isFinished(GraphicsInfo ginfo) {
				if(BossA.this.position.x > 800 - 100) return true;
				return false;
			}

			@Override
			public void move(GraphicsInfo ginfo, Stage stage) {
				BossA.this.position.x += 240 * ginfo.frametime;
				
			}
		}
		
		//中央移動
		class PatternA4 extends Pattern{
			double spd;

			@Override
			public void start(GraphicsInfo ginfo) {
				//位置によって、速度調整
				if(BossA.this.position.x < 400) spd = 240;
				else spd = -240;
				super.start(ginfo);
			}

			@Override
			public boolean isFinished(GraphicsInfo ginfo) {
				if(Math.abs(BossA.this.position.x - 400) < 10) return true;
				return false;
			}

			@Override
			public void move(GraphicsInfo ginfo, Stage stage) {
				BossA.this.position.x += spd * ginfo.frametime;
				
			}
		}
		
		@Override
		public GameChara draw(GraphicsInfo ginfo, Stage stage) {
			//メソッドから脱出
			if(this.curpat == null) return super.draw(ginfo, stage);
			
			//終了チェック
			if(this.curpat.isFinished(ginfo) == false){
				//移動処理
				this.curpat.move(ginfo, stage);
			} else {
				//次のパターンを取得し
				this.curpat = curpat.getNext();
				//パターン開始時刻を記録
				if(this.curpat != null) this.curpat.start(ginfo);
			}
			return super.draw(ginfo, stage);
		}
	

}
