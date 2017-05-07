import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuUI extends JPanel{
	private static final long serialVersionUID = -2202648447796498120L;
	private MainUI parent;

	public MenuUI(MainUI parent){
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
				// 10/10 waste of time which i am going to delete in next push
				// (clap) (clap) (clap) (clap) (clap) (clap) (clap) (clap) (clap) (clap)
				String[] buttons = { "Yes", "No"};    
				int returnValue = JOptionPane.showOptionDialog(parent, "You know you could just click the red X."
				+ " Do you even still want to continue?", "WTF BRO",
		        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
				
				if(returnValue == 0){
					String[] buttons2 = { "Yes", "No"};    
					returnValue = JOptionPane.showOptionDialog(parent,"So you want cotinue? Are you ready for a riddle first?" ,
					"WTF BRO"
			        ,JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[1]);
					
					if(returnValue == 0){
						String[] buttons3 = { "You are Heisenberg", "Fuck off"};    
						returnValue = JOptionPane.showOptionDialog(parent, "Say my name!",
						"WTF BRO"
				        ,JOptionPane.WARNING_MESSAGE, 0, null, buttons3, buttons3[1]);
						if(returnValue == 0){
							JOptionPane.showMessageDialog(parent, "Excellent");
							System.exit(0);
						} else {
							JOptionPane.showMessageDialog(parent, "Then you dont get to exit HA.");
						}
					} else {
						JOptionPane.showMessageDialog(parent, "Haha GOT DEM");
					}
				} else {
					JOptionPane.showMessageDialog(parent, "Excellent choice");
				}
			} 
		});

		layout.setHorizontalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(lblTest1)
					.addComponent(btnTest1)
					.addComponent(btnTest2)
					.addComponent(btnTest3)
			)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(lblTest1)
				.addComponent(btnTest1)
				.addComponent(btnTest2)
				.addComponent(btnTest3)
		);
	}
}
