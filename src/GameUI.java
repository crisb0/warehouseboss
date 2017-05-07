import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUI extends JPanel{
	private static final long serialVersionUID = -5285564050945629510L;
	private MainUI parent;

	public GameUI(MainUI parent){
		this.parent = parent;
		this.initUI();
	}
	
	private void initUI(){
		// Initialise and set the GroupLayout setting for this panel
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Create the components to form the menu
		JLabel lblTest1 = new JLabel("Game UI Testing");
		JLabel lblTest2 = new JLabel("This class will no longer render buttons or menu items in future.");
		JButton btnTest1 = new JButton("Test 1");
		
		// Add some test data to the menu
		btnTest1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				parent.changeInterface(MainUI.PanelName.MAIN_MENU);
			} 
		});

		layout.setHorizontalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(lblTest1)
					.addComponent(lblTest2)
					.addComponent(btnTest1)
			)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(lblTest1)
				.addComponent(lblTest2)
				.addComponent(btnTest1)
		);
	}
}
