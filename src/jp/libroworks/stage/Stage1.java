package jp.libroworks.stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.libroworks.BossA;
import jp.libroworks.GraphicsInfo;
import jp.libroworks.PlayerChara;
import jp.libroworks.supers.GameChara;
import jp.libroworks.supers.Stage;

public class Stage1 extends Stage {
	private PlayerChara player = new PlayerChara();
	private BufferedImage img_chara, img_back;
	private BossA enemy = new BossA();

	@Override
	public GameChara getPlayer() {
		return this.player;
	}

	@Override
	public void loadMedia() throws IOException {
		//キャラ画像の読み込み
		this.img_chara = ImageIO.read(new File("media/chara.png"));
		//背景画像の読み込み
		this.img_back = ImageIO.read(new File("media/back.jpg"));
		//キャラ画像のセット
		this.player.setImage(this.img_chara.getSubimage(0, 0, 48, 48));
		this.enemy.setImage(this.img_chara.getSubimage(0, 72, 192, 192));
	}

	@Override
	public GameChara getEnemy() {
		return this.enemy;
	}

	@Override
	public void draw(GraphicsInfo ginfo) {
		//背景画像の描画
		ginfo.gg.drawImage(this.img_back, 0, 0, null);
		
		//ボスと自機のdrawメソッド
		this.enemy.draw(ginfo, this);
		this.player.draw(ginfo, this);
		
	}

	@Override
	public void init(GraphicsInfo ginfo) {
		//それぞれの位置を初期化
		this.player.position.x = 400;
		this.player.position.y = 520;
		this.enemy.position.x = 400;
		this.enemy.position.y = -100;
	}

}
