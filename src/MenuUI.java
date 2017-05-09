import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuUI extends JPanel {
	private static final long serialVersionUID = -2202648447796498120L;
	private MainUI parent;

	public MenuUI(MainUI parent) {
		this.parent = parent;
		this.initUI();
	}

	private void initUI() {
		// Initialise and set the GroupLayout setting for this panel
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Create the components to form the menu
		JLabel lblTest1 = new JLabel("Warehouse Boss");
		JButton btnTest1 = new JButton("Start Game");
		JButton btnTest2 = new JButton("Tutorial");
		JButton btnTest3 = new JButton("Quit");

		// Add some test data to the menu
		btnTest1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.changeInterface(MainUI.PanelName.GAME_MENU);
			}
		});

		btnTest2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(parent, "Tutorial Coming Out As DLC");
			}
		});

		btnTest3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		layout.setHorizontalGroup(
				layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(lblTest1).addComponent(btnTest1).addComponent(btnTest2).addComponent(btnTest3)));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(lblTest1).addComponent(btnTest1)
				.addComponent(btnTest2).addComponent(btnTest3));
	}
}
