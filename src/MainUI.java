import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame{
	private static final long serialVersionUID = -5285564050945629510L;
	private int width = 900;
	private int height = 600;

	public MainUI(){
		this.initUI();
	}
	
	private void initUI(){
		this.setTitle("Warehouse Game");
		/*
		 * The window has a set size for now. We might need to implement
		 * resizability later.
		 */
		this.setSize(this.width, this.height);
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contPanel = this.getContentPane();
		contPanel.setLayout(new CardLayout());
		
		JPanel menuCont = new JPanel(new GridBagLayout());
		menuCont.add(new MenuUI(this));
		JPanel gameCont = new JPanel(new GridBagLayout());
		gameCont.add(new GameUI(this));
		
		contPanel.add(menuCont, "menu");
		contPanel.add(gameCont, "game");
		
		this.changeInterface(PanelName.MAIN_MENU);
	}
	
	public void changeInterface(PanelName pN){
		Container contPanel = this.getContentPane();
		CardLayout c = (CardLayout)(contPanel.getLayout());
		
		switch(pN){
		case MAIN_MENU:
			c.show(contPanel, "menu");
			break;
		case GAME_MENU:
			c.show(contPanel, "game");
			break;
		}
	}
	
	public enum PanelName {
		MAIN_MENU, GAME_MENU
	}
}
