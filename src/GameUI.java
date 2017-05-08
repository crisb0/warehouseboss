import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.AbstractAction;
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
public class GameUI extends JPanel {
    private static final long serialVersionUID = -5285564050945629510L;
    private MainUI parent;
    private Game gameObj;

    public GameUI(MainUI parent) {
        this.parent = parent;
        this.gameObj = null;

        // --------------------------------------------------
        // Testing code for key input. Delete in next version.
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("K"), "doSomething");
        this.getActionMap().put("doSomething", new TestAction(this.parent));
        // --------------------------------------------------
    }

    public void generateGame(Game.Difficulty diff) {
        MapGenerator mapGen = new MapGenerator(diff);
        Map map = new Map(mapGen);
        this.gameObj = new Game(map);
    }

    // --------------------------------------------------------------
    // The following code are all tests. Will delete in next version.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDonut(g);
    }

    /*
     * THE DRAW DONUT CODE WAS COPIED FROM THE INTERNET AT THIS ADDRESS It just
     * looks cool to draw =)
     * http://zetcode.com/tutorials/javagamestutorial/basics/
     */
    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawString("Testing out if we are able to render and use key presses here", 350, 80);
        g2d.drawString("Press 'k' to return to the main menu", 350, 100);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = parent.getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h / 2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
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
    // --------------------------------------------------------------
}
