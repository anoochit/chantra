import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Operate extends JFrame implements ActionListener,ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269503971316253161L;
	/**
	 * @param args
	 */
	static String projectDirectory;
	static String chantraVersion;
	static String config;
	static String projectFolder;
	JPanel mainPanel;
	JPanel newProjectPanel;
	JPanel updateProjectPanel;
	JRadioButton newproject;
	JRadioButton updateproject;
	JButton browseProjectDir;
	JButton browseConfigDir;
	JButton createProj;
	JButton updateProj;
	JButton nextButton;
	JFileChooser fc;
	JTextField projectDirField;
	JTextField configDirField;
	JTextField version;
	JLabel projectDirLabel;
	JLabel configDirLabel;
	JLabel createversion;
	int creatOrUpdate;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operate frame = new Operate();	
		frame.setTitle("Chantra Helper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(610, 377);
		frame.setResizable(false);
		frame.setVisible(true);

	}
	public Operate(){
		mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(3,3));
		newproject = new JRadioButton("Create new project");
		updateproject = new JRadioButton("Update project");
		nextButton = new JButton("Next >>");
		ButtonGroup group = new ButtonGroup();
		group.add(newproject);
		group.add(updateproject);
		mainPanel.add(newproject);
		mainPanel.add(updateproject);
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(nextButton,BorderLayout.EAST);
		mainPanel.add(p2);
		
		newproject.addItemListener(this);
		updateproject.addItemListener(this);
		nextButton.addActionListener(this);
		
	}
	
	public static void init() throws IOException{
		Function.createProjectFolder(projectFolder);
		Function.createProgramFolder(config, projectFolder);
		Function.createDescription(config);
		Function.createHTML(config,projectFolder);
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==nextButton && creatOrUpdate==1){
				mainPanel.setVisible(false);
				projectDirLabel = new JLabel("Choose Project Directory");
				configDirLabel = new JLabel("Choose Config Directory");
				createversion = new JLabel("Chantra version :");
				browseProjectDir = new JButton("browse");
				browseConfigDir = new JButton("browse");
				createProj = new JButton("create");
				createProj.addActionListener(this);
				browseProjectDir.addActionListener(this);
				browseConfigDir.addActionListener(this);
				projectDirField = new JTextField("Choose Project Directory");
				configDirField = new JTextField("Choose Config Directory");
				version = new JTextField(5);
				projectDirField.setEditable(false);
				configDirField.setEditable(false);
				newProjectPanel = new JPanel();
				newProjectPanel.setLayout(new BoxLayout(newProjectPanel,BoxLayout.Y_AXIS));
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
				JPanel panel3 = new JPanel();
				panel1.add(projectDirLabel);
				panel1.add(projectDirField);
				panel1.add(browseProjectDir);
				panel2.add(configDirLabel);
				panel2.add(configDirField);
				panel2.add(browseConfigDir);
				panel3.add(createversion);
				panel3.add(version);
				newProjectPanel.add(panel1);
				newProjectPanel.add(panel2);
				newProjectPanel.add(panel3);
				newProjectPanel.add(createProj);
				add(newProjectPanel);
		}
		if(e.getSource()==nextButton && creatOrUpdate==2){
			mainPanel.setVisible(false);
			projectDirLabel = new JLabel("Choose Project Directory");
			configDirLabel = new JLabel("Choose Config Directory");
			browseProjectDir = new JButton("browse");
			browseConfigDir = new JButton("browse");
			updateProj = new JButton("update");
			updateProj.addActionListener(this);
			browseProjectDir.addActionListener(this);
			browseConfigDir.addActionListener(this);
			projectDirField = new JTextField("Choose Project Directory");
			configDirField = new JTextField("Choose Config Directory");
			projectDirField.setEditable(false);
			configDirField.setEditable(false);
			updateProjectPanel = new JPanel();
			updateProjectPanel.setLayout(new BoxLayout(updateProjectPanel,BoxLayout.Y_AXIS));
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			panel1.add(projectDirLabel);
			panel1.add(projectDirField);
			panel1.add(browseProjectDir);
			panel2.add(configDirLabel);
			panel2.add(configDirField);
			panel2.add(browseConfigDir);
			updateProjectPanel.add(panel1);
			updateProjectPanel.add(panel2);
			updateProjectPanel.add(updateProj);
			add(updateProjectPanel);
		}
		if(e.getSource()==updateProj){
			
				projectDirectory = projectDirField.getText();
				chantraVersion = version.getText();
				config = configDirField.getText();
				projectFolder = projectDirectory+"/Chantra"+chantraVersion;
				
				updateProjectPanel.setVisible(false);
				mainPanel.setVisible(true);
			
	        

		}

		if(e.getSource()==browseProjectDir){
			fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setCurrentDirectory(new File("/home/krapok/Desktop"));
			int returnVal = fc.showDialog(browseProjectDir,"Choose Project Directory");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            projectDirField.setText(file.getAbsolutePath());
	        } 

		}
		
		if(e.getSource()==browseConfigDir){
			fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setCurrentDirectory(new File("/home/krapok/Desktop"));
			int returnVal = fc.showDialog(browseConfigDir,"Choose Config Directory");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            configDirField.setText(file.getAbsolutePath());
	        } 

		}
		
		if(e.getSource()==createProj){
			try {
				projectDirectory = projectDirField.getText();
				chantraVersion = version.getText();
				config = configDirField.getText();
				projectFolder = projectDirectory+"/Chantra"+chantraVersion;
				init();
				newProjectPanel.setVisible(false);
				mainPanel.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        

		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem()==newproject){
			creatOrUpdate=1;
		}
		else if(e.getItem()==updateproject){
			creatOrUpdate=2;
		}
	
	}

}
