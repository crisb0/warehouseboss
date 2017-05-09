import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * The GameUI holds the code for the rendering of the game. Thus, this class
 * will perform the game loop as well. The game logic can be delegated to the
 * game class.
 * 
 * @author z5115782
 */
public class GameUI extends JPanel implements Runnable {
	private static final long serialVersionUID = -5285564050945629510L;
	private MainUI parent;
	private Game gameObj;
	private Thread gameLoop;
	private int lastFps;

	public GameUI(MainUI parent) {
		this.parent = parent;
		MapGenerator mapGen = new MapGenerator(null);
		Map map = new Map(mapGen);
		this.gameObj = new Game(map);
		this.initGameScreen();
		this.lastFps = 0;

		// --------------------------------------------------
		// Testing code for key input. Delete in next version.
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("K"), "doSomething");
		this.getActionMap().put("doSomething", new TestAction(this.parent));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "goDown");
		this.getActionMap().put("goDown", new DownAction());
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "goRight");
		this.getActionMap().put("goRight", new RightAction());
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "goLeft");
		this.getActionMap().put("goLeft", new LeftAction());
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "goUp");
		this.getActionMap().put("goUp", new UpAction());
		// --------------------------------------------------
	}

	public void initGameScreen() {
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	}

	public void generateGame(Game.Difficulty diff) {
		
	}

	// --------------------------------------------------------------
	// The following code are all tests. Will delete in next version.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		clearMap(g);
		drawMap(g);
		drawFPS(g);
	}
	
	void clearMap(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 600);
	}
	
	void drawMap(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		int[][] grid = this.gameObj.getGrid();
		
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y < grid.length; y++){
				if(grid[x][y] == Game.OBSTACLE){
					g2d.setColor(Color.CYAN);
					g2d.drawRect(x * 32, y * 32, 32, 32);
				} else if (grid[x][y] == Game.PLAYER){
					g2d.setColor(Color.RED);
					g2d.drawRect(x * 32, y * 32, 32, 32);
				} else if (grid[x][y] == Game.BOX){
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.drawRect(x * 32, y * 32, 32, 32);
				} else if (grid[x][y] == Game.GOAL){
					g2d.setColor(Color.GREEN);
					g2d.drawRect(x * 32, y * 32, 32, 32);
				}
			}
		}
	}

	void drawFPS(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		Font font = new Font("Serif", Font.BOLD, 15);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString("FPS: " + this.lastFps, 0, 15);
	}
		
	class TestAction extends AbstractAction {
		private static final long serialVersionUID = -8932029940888012027L;

		MainUI parent;

		public TestAction(MainUI parent) {
			this.parent = parent;
		}

		public void actionPerformed(ActionEvent e) {
			this.parent.changeInterface(MainUI.PanelName.MAIN_MENU);
		}
	}
	
	class DownAction extends AbstractAction {
		public void actionPerformed(ActionEvent e){
			gameObj.getPlayer().movePlayer('s', gameObj.getMap());
		}
	}
	
	class RightAction extends AbstractAction {
		public void actionPerformed(ActionEvent e){
			gameObj.getPlayer().movePlayer('d', gameObj.getMap());
		}
	}
	
	class UpAction extends AbstractAction {
		public void actionPerformed(ActionEvent e){
			gameObj.getPlayer().movePlayer('a', gameObj.getMap());
		}
	}
	
	class LeftAction extends AbstractAction {
		public void actionPerformed(ActionEvent e){
			gameObj.getPlayer().movePlayer('w', gameObj.getMap());
		}
	}
	
	// --------------------------------------------------------------
	
	private void gameCycle(double dt) {
	}

	@Override
	public void addNotify() {
		super.addNotify();

		this.gameLoop = new Thread(this);
		this.gameLoop.start();
	}

	@Override
	public void run() {
		long lastLoopTime = System.nanoTime();
		long lastFpsTime = 0;
		int fps = 0;
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		
		while (true) {
			long currentTime = System.nanoTime();
			long updateTime = currentTime - lastLoopTime;
			lastLoopTime = currentTime;
			
			double deltaTime = updateTime / ((double)OPTIMAL_TIME);
			
			lastFpsTime += updateTime;
			fps++;
			
			if(lastFpsTime > 1000000000){
				lastFpsTime = 0;
				this.lastFps = fps;
				fps = 0;
			}
			
			this.gameCycle(deltaTime);
			this.repaint();
			
			try{
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
