package Covid_Simulator;
//Pandemic Project
//Group members:Clayton,Josh,Sully
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList; 
import java.util.Timer;
import java.util.TimerTask;

public class Pandemic_Sim extends JFrame{
	JPanel containerPanel, canvasPanel, inputPanel, outputPanel, menuPanel;
	JLabel popLb, uVacLb1, oneShotLb1, twoShotLb1, naturalLb1;
	JLabel uVacLb2, oneShotLb2, twoShotLb2, naturalLb2, deadLb;
	JLabel infTot, uVacTot, oneShotTot, twoShotTot, naturalT, deadTot;
	JComboBox popCb, uVacCb, oneShotCb, twoShotCb, naturalCb;
	public JProgressBar infectedPb, uVacPb, oneShotPb, twoShotPb, naturalPb, deadPb;
	JButton startBtn, stopBtn, resumeBtn;
	JMenuBar mb;
	JMenu members;
	JMenu about;
	JMenuItem m1, m2, m3,a1,a2,a3;
	
	String [] popSizes = {"200", "400", "600", "800", "1000"};
	String [] percents = {"0", "25", "50", "75", "100"};
	//ArrayList<Person> persons = new ArrayList<Person>();
	int popSize, uVacPr, oneShotPr, twoShotPr, naturalPr;
	C_J_S_PandemicProject pandemic;
	public static int uVacCount = 0;

	public Pandemic_Sim() {
		super("CJS.CO Pandemic Console");
		setSize(660, 450);			//suggested size
		setLocationRelativeTo(null); 	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		 this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Clayton\\Pictures\\covid19_sim.jpg")));
//		 this.setContentPane(new JLabel(new ImageIcon("//Users//claytonforbes/Documents//TestPhoto//Covid.jpg")));//mac users to get photos//mac users to get photos
		 this.setLayout(new FlowLayout());
		
		
		
		mb = new JMenuBar();
		members = new JMenu("CJS.CO Coders Members");
		m1 = new JMenuItem("Name/Student#:Suleyman-######");
	    m2 = new JMenuItem("Name/Student#:Josh-######");
	    m3 = new JMenuItem("Name/Studnet#:Clayton-######");
	
	    members.add(m1);
	    members.add(m2);
	    members.add(m3);
	     
	     about = new JMenu("Coder Section");
	    a1= new JMenu("Clayton is from Section 2 ");
	    a2= new JMenu("Josh is from Section 2 ");
	    a3= new JMenu("Sully is from Section 3 ");
	    about.add(a1);
	    about.add(a2);
	    about.add(a3);
	    
	    
	    startBtn = new JButton("Start");
	    stopBtn = new JButton("Stop");
	    resumeBtn = new JButton("Resume");
	   // f about = new JMenu("")
	    
	
	    mb.add(members);
	    mb.add(about);
	    mb.add(startBtn);
	    mb.add(stopBtn);
	    mb.add(resumeBtn);
	    menuPanel = new JPanel();
	    menuPanel.add(mb);
	    this.add(menuPanel,BorderLayout.NORTH);
	    
	    popLb = new JLabel("Population Size:");
	    uVacLb1 = new JLabel("Unvaccinated %:");
	    oneShotLb1 = new JLabel("One Shot of Vaccine %:");
	    twoShotLb1 = new JLabel("Two Shots of Vaccine %:");
	    naturalLb1 =  new JLabel("Natural Immunity %:");
	    
	    popCb = new JComboBox(popSizes);
	    uVacCb = new JComboBox(percents);
	    oneShotCb = new JComboBox(percents);
	    twoShotCb = new JComboBox(percents);
	    naturalCb = new JComboBox(percents);
	    
	    inputPanel = new JPanel(new GridLayout(5,2,0,35));
	    inputPanel.add(popLb);
	    inputPanel.add(popCb);
	    inputPanel.add(uVacLb1);
	    inputPanel.add(uVacCb);
	    inputPanel.add(oneShotLb1);
	    inputPanel.add(oneShotCb);
	    inputPanel.add(twoShotLb1);
	    inputPanel.add(twoShotCb);
	    inputPanel.add(naturalLb1);
	    inputPanel.add(naturalCb);
	    
	    containerPanel = new JPanel(new GridLayout(0,2,50,10));
	  
	    containerPanel.add(inputPanel);
	    this.add(containerPanel);
	    
	    JPanel contain1 = new JPanel(new GridLayout(2,0,0,5));
	    JPanel contain2 = new JPanel(new GridLayout(2,0,0,5));
	    JPanel contain3 = new JPanel(new GridLayout(2,0,0,5));
	    JPanel contain4 = new JPanel(new GridLayout(2,0,0,5));
	    JPanel contain5 = new JPanel(new GridLayout(2,0,0,5));
	    JPanel contain6 = new JPanel(new GridLayout(2,0,0,5));
	    
	   
	    infTot =  new JLabel("Total infected:");
	    uVacTot = new JLabel("Total Unvaccinated:");
	    oneShotTot = new JLabel("Total One Shot of Vaccine:");
	    twoShotTot = new JLabel("Total Two Shots of Vaccine:");
	    naturalT =  new JLabel("Total Natural Immunity:");
	    deadTot =  new JLabel("Total Dead:");
	    
	    infectedPb = new JProgressBar();
	    infectedPb.setValue(0);
        infectedPb.setStringPainted(true);
	    this.setVisible(true);
	    
	    uVacPb = new JProgressBar();
	    uVacPb.setValue(0);
        uVacPb.setStringPainted(true);
	    this.setVisible(true);
	    
	    oneShotPb = new JProgressBar();
	    oneShotPb.setValue(0);
        oneShotPb.setStringPainted(true);
	    this.setVisible(true);
	    
	    twoShotPb = new JProgressBar();
	    twoShotPb.setValue(0);
        twoShotPb.setStringPainted(true);
	    this.setVisible(true);
	    
	    naturalPb = new JProgressBar();
	    naturalPb.setValue(0);
        naturalPb.setStringPainted(true);
	    this.setVisible(true);
	    
	    deadPb = new JProgressBar(SwingConstants.HORIZONTAL);
	    deadPb.setValue(0);
        deadPb.setStringPainted(true);
	  
	    
	    contain1.add(infTot);
	    contain1.add(infectedPb);
	    contain2.add(uVacTot);
	    contain2.add(uVacPb);
	    contain3.add(oneShotTot);
	    contain3.add(oneShotPb);
	    contain4.add(twoShotTot);
	    contain4.add(twoShotPb);
	    contain5.add(naturalT);
	    contain5.add(naturalPb);
	    contain6.add(deadTot);
	    contain6.add(deadPb);
	   
	    
	    outputPanel = new JPanel(new GridLayout(6,0,0,5));
	    
	    outputPanel.add(contain1);
	    outputPanel.add(contain2);
	    outputPanel.add(contain3);
	    outputPanel.add(contain4);
	    outputPanel.add(contain5);
	    outputPanel.add(contain6);
	    
		
	    containerPanel.add(outputPanel);
	    startBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                popSize = Integer.parseInt(popSizes[popCb.getSelectedIndex()]);
                uVacPr = Integer.parseInt(percents[uVacCb.getSelectedIndex()]);
                oneShotPr = Integer.parseInt(percents[oneShotCb.getSelectedIndex()]);
                twoShotPr = Integer.parseInt(percents[twoShotCb.getSelectedIndex()]);
                naturalPr = Integer.parseInt(percents[naturalCb.getSelectedIndex()]);


				if (uVacPr + oneShotPr + twoShotPr + naturalPr != 100){
					JOptionPane.showMessageDialog(null, "Please enter percentages that add up to 100%");
					return;
				} 

                JFrame frame = new JFrame ("CJS Simulation of Covid ");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                frame.setSize(100,1000);
                pandemic =  new C_J_S_PandemicProject(popSize, uVacPr, oneShotPr, twoShotPr, naturalPr, null);
                //frame.add(pandemic);
                createNewFrame(frame);
                frame.getContentPane().setBackground(Color.BLUE);
               // this.setContentPane(new JLabel(new ImageIcon("//Users//claytonforbes/Documents//TestPhoto//Covid.jpg")));//mac users to get photos
         		 //this.setLayout(new FlowLayout());
//                pandemic = new C_J_S_PandemicProject(popSize, uVacPr, oneShotPr, twoShotPr, naturalPr);
//                frame.add(pandemic);
                frame.pack();
                frame.setVisible(true);
                
            }
            

        });
	    
	    
	    stopBtn.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		pandemic.stop();
	    	}
	    });
	    
	    resumeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                pandemic.resume();
            }
        });
	    
	    this.setVisible(true);
	    
	    
	}
	
	public static void main(String[] args)
	{
		new Pandemic_Sim();
	}

	public void createNewFrame(JFrame frame) {
		frame.add(new C_J_S_PandemicProject(popSize, uVacPr, oneShotPr, twoShotPr, naturalPr, this));
	}
}
//end class
