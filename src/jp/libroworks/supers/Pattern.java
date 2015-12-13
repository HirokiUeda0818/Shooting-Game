package jp.libroworks.supers;

import jp.libroworks.GraphicsInfo;

public abstract class Pattern {
	//次に実行するパターンをセットするゲッター、セッター
	protected Pattern next = null;
	
	public void setNext(Pattern pt) {
		this.next = pt;
	}
	public Pattern getNext(){
		return this.next;
	}
	
	//時間制限
	protected long limit, starttime;
	
	public void setLimit(long l){
		this.limit = l;
	}
	
	public void start(GraphicsInfo ginfo){
		this.starttime = ginfo.currenttime;	
	}
	
	//終了チェック
	public abstract boolean isFinished(GraphicsInfo ginfo);
	//移動処理
	public abstract void move(GraphicsInfo ginfo, Stage stage);
}
