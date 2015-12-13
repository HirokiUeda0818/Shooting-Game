package jp.libroworks.supers;

import java.io.IOException;

import jp.libroworks.GraphicsInfo;

public abstract class Stage {
	public abstract GameChara getPlayer();
	public abstract void loadMedia() throws IOException;
	public abstract GameChara getEnemy();
	public abstract void draw(GraphicsInfo ginfo);
	public abstract void init(GraphicsInfo ginfo);

}
