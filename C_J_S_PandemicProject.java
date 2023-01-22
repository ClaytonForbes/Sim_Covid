package Covid_Simulator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.TimerTask;


public class C_J_S_PandemicProject extends JPanel
{
	private final int WIDTH = 800, HEIGHT = 700;//size of JPanel
	private final int LAG_TIME = 100; // time in milliseconds between re-paints of screen
	private Timer time;//Timer class object that will fire events every LAG_TIME interval
	private final int IMG_DIM = 10; //size of ball to be drawn
	
	//REVISION July 14 : create an array of Ball objects here in class scope
	//private final int ARRAY_SIZE = 600;
	private Person [] personArray;

	private Pandemic_Sim parent;
	private boolean complete; 
    private int timeCounter;
    //REVSION NEEDED HERE: need to use the Ball class to create two Ball objects
    // with different starting locations 
    //private integer x, y, offsetX, offsetY; //used to position ball on JPanel
    int uVacTotal =0;
    int infTotal = 0;
    int oneShotTotal = 0;
    int twoShotTotal = 0;
    int deadTotal = 0;
    int naturalTotal =0;
	public void stop()
    {
        if(!complete)
           time.stop();
    }
    public void resume()
    {
        if(!complete)
           time.start();
    }
	
	//REVSION NEEDED HERE: need to use the Ball class to create two Ball objects
	// with different starting locations 
	//private integer x, y, offsetX, offsetY; //used to position ball on JPanel
	
	
	public C_J_S_PandemicProject(int popSize, int uVacPr, int oneShotPr, int twoShotPr, int naturalPr, Pandemic_Sim parentFrame)
	{
		parent = parentFrame;

		personArray = new Person[popSize+1];
		//create Timer and register a listener for it.
		this.time = new Timer(LAG_TIME, new BounceListener() );
		
		//REVISION JULY 15
		//use a loop to populate the personArray with balls with random positions
		//Set the color of the first ball to RED
		personArray[0] = new Person(Color.RED,WIDTH, HEIGHT, 0);
		//now set color of remaining balls to BLUE
		
		Color color = Color.BLUE;//color to pass in to Ball constructor	
		
		int index = 1;
		
		for(int i = 0; i < (personArray.length-1) * uVacPr * 0.01; i++)
		{
				personArray[index] = new Person(color, WIDTH, HEIGHT, 1);	
				index++;
		}//end for
		
		color = Color.CYAN;
		for(int i = 0; i < (personArray.length-1) * oneShotPr * 0.01; i++)
		{
				personArray[index] = new Person(color, WIDTH, HEIGHT, 1);	
				index++;
		}//end for
		
		color = Color.YELLOW;
		for(int i = 0; i < (personArray.length-1) * twoShotPr * 0.01; i++)
		{
				personArray[index] = new Person(color, WIDTH, HEIGHT, 1);	
				index++;
		}//end for
		
		color = Color.orange;
		for(int i = 0; i < (personArray.length-1) * naturalPr * 0.01; i++)
		{
				personArray[index] = new Person(color, WIDTH, HEIGHT, 1);
				index++;
		}//end for
		
		//set preferred size of panel using an ANONYMOUS Dimension object
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT) );
		this.setBackground(Color.WHITE);
		
		//start the timer so that it starts creating ActionEvent baby objects. 
		this.time.start();	
		
	}//end constructor
	
	public void paintComponent(Graphics g)//The Graphics object 'g' is your paint brush
	{
		//call super version of this method to "throw the bucket of paint onto the canvas"
		// and cover up any previous image. 
		//NOTE: try commenting this out to see the effect of not repainting.
		super.paintComponent(g);
		
		//set brush color
		g.setColor(Color.PINK);
		
		for(int i = 0; i < personArray.length; i++)
		{
			//get the color
			g.setColor(personArray[i].getColor());
			g.fillOval(personArray[i].getxCoordinate(), personArray[i].getyCoordinate(),  personArray[i].getPersonDiam(), personArray[i].getPersonDiam());
		}
		
		
	}//end paintComponent over-ride
	
	private class BounceListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// on each Timer event (every 20 milliseconds) re-calculate the co-ordinates
			// of where the ball shape will be drawn next. 
			
			//Simplified code...Now we just call calcPosition() for each ball object, which
			//will update their positions.
			
			//REVISION JULY 14: put this in a loop to call calcPosition() on each ball in the array
			for(int i = 0; i < personArray.length; i++)
			{
				calculatePosition(personArray[i]);
			}
			
			
			
			int deltaX;//difference in pixels of the x Coordinateinates of the two balls being compared.
			int deltaY;//difference in pixels of the y Coordinateinates of the two balls being compared.
			
			//temp variables to hold the x and y Coordinates of both balls in the pair.
			//The balls will be referred to as firstBall and secondBall
			int firstBallX,  firstBallY, secondBallX, secondBallY;
			
			//outer loop gets the firstBall of the pair and its Coordinateinates.
			for(int i = 0; i < personArray.length -1; i++)//LCC to length-1 to avoid out of bounds
			{
				//get the x and y co-ords of  first ball of the pair
				firstBallX = personArray[i].getxCoordinate();
				firstBallY = personArray[i].getyCoordinate();
				
				//Inner loop gets the second ball of the pair
				//start inner loop counter at i+1 so we don't compare the first ball to itself.
				for(int j = i+1; j < personArray.length; j++)
				{
					secondBallX = personArray[j].getxCoordinate();
					secondBallY = personArray[j].getyCoordinate();
					
					//now calculate deltaX and deltaY for the pair of balls
					deltaX = firstBallX - secondBallX;
					deltaY = firstBallY - secondBallY;
					//square them to get rid of negative values, then add them and take square root of total
					// and compare it to ball diameter held in IMG_DIM
					if(Math.sqrt(deltaX *deltaX + deltaY * deltaY) <= IMG_DIM)//if true, they have touched
					{
						//REVSION HERE: not using the xFlag and yFlag anymore, so now we  adjust
						// the xIncrementValue and yIncrementValue by multiplying by -1
						personArray[i].setxIncrementValue(personArray[i].getxIncrementValue() * -1);
						personArray[i].setyIncrementValue(personArray[i].getyIncrementValue() * -1);
						
						//now do the secondBall
						personArray[j].setxIncrementValue(personArray[j].getxIncrementValue() * -1);
						personArray[j].setyIncrementValue(personArray[j].getyIncrementValue() * -1);
						
						//ALSO, to get a bit of directional change generate a new set of random values for the xIncrementValue
						//  and yIncrementValue of each ball involved in the collision and assign them.
						int firstBallnewxIncrementValue = (int)(Math.random()*11 - 5);
						int firstBallnewyIncrementValue = (int)(Math.random()*11 - 5);
						int secondBallnewxIncrementValue = (int)(Math.random()*11 - 5);
						int secondBallnewyIncrementValue = (int)(Math.random()*11 - 5);
						
						//this will prevent balls from "getting stuck" on the borders.
						personArray[i].setxIncrementValue(firstBallnewxIncrementValue);
						personArray[i].setyIncrementValue(firstBallnewyIncrementValue);
						personArray[j].setxIncrementValue(secondBallnewxIncrementValue);
						personArray[j].setyIncrementValue(secondBallnewyIncrementValue);
						
						
						//IN VERSION FIVE change the color of a blue ball to red.
						personArray[i].checkCollision(personArray[j]);
						personArray[j].checkCollision(personArray[i]);
						/*
						 * if(personArray[i].getColor().equals(Color.RED) &&
						 * personArray[j].getColor().equals(Color.BLUE)) { //change second ball to color
						 * of first ball personArray[j].setColor(personArray[i].getColor()); }
						 * if(personArray[j].getColor().equals(Color.RED) &&
						 * personArray[i].getColor().equals(Color.BLUE)) { //second ball is red, so
						 * change first ball to color of second ball
						 * personArray[i].setColor(personArray[j].getColor());; }
						 */
					}//end if
				}//end inner for				
			}//end outer loop
			
			//call repaint(), which in turn calls paintComponent() 
			repaint();

			int uVacCount = 0;
			int infectedCount = 0;
			int oneShotCount = 0;
			int twoShotCount = 0;
			int naturalCount = 0;
			

			for (int i = 0; i < personArray.length; i++) {
				
				if (personArray[i].getColor().equals(Color.BLUE)) {
					uVacCount++;
					continue;
				}
				
				if (personArray[i].getColor().equals(Color.RED)) {
					infectedCount++;
				}
				
				if(personArray[i].getColor().equals(Color.CYAN)) {
					oneShotCount++;
				}
				if(personArray[i].getColor().equals(Color.YELLOW)) {
					twoShotCount++;
				}
				if(personArray[i].getColor().equals(Color.orange)) {
					naturalCount++;
				}

				
			}
			parent.uVacPb.setValue((int)((uVacCount * 1.0 / personArray.length) * 100));// color red
			parent.infectedPb.setValue((int)((infectedCount * 1.0 / personArray.length) * 100));// color blue 
			parent.oneShotPb.setValue((int)((oneShotCount * 1.0 / personArray.length) * 100));//color light blue 
			parent.twoShotPb.setValue((int)((twoShotCount * 1.0 / personArray.length) * 100));// color yellow 
			
		}//end method
		
	}//end inner class
	
	public void calculatePosition(Person ball)
	{
		
		//check if near boundary. If so, then apply negative operator to the relevant IncrementValue
		//Changed the operators to >= and <= from == to fix the "disappearing ball" problem
		if(ball.getxCoordinate() >= WIDTH - ball.getPersonDiam() )
		{
			//we are at right side, so change xIncrementValue to a negative
			ball.setxIncrementValue(ball.getxIncrementValue() * -1);
		}
		if(ball.getxCoordinate() <= 0)//changed operator to <=
		{
			//if true, we're at left edge, flip the flag
			ball.setxIncrementValue(ball.getxIncrementValue() * -1);;
		}
		if(ball.getyCoordinate() >= HEIGHT - ball.getPersonDiam() )
		{
			ball.setyIncrementValue(ball.getyIncrementValue() * -1);
		}
		if(ball.getyCoordinate() <= 0)
		{
			//if true, we're at left edge, flip the flag
			ball.setyIncrementValue(ball.getyIncrementValue() * -1);;
		}
		//adjust the ball positions using the getters and setters
		ball.setxCoordinate(ball.getxCoordinate() + ball.getxIncrementValue());
		ball.setyCoordinate(ball.getyCoordinate() + ball.getyIncrementValue());
		
		
	}//end calcPosition
	
	
	

	public static void main(String[] args)
	{
	// create a JFrame to hold the JPanel
			JFrame frame = new JFrame("CJS Limit: Epdemic Transmission Simulation");
			
			//boilerplate
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new FlowLayout() );//ANONYMOUS object
			frame.setSize(1200,1000);
			frame.setLocationRelativeTo(null);
			
			//set background color of contentPane
			frame.getContentPane().setBackground(Color.BLUE);
			
			//create an ANONYMOUS object of the class and add the JPanel to the JFrame
			//frame.add(new C_J_S_PandemicProject() );
			
			frame.pack();//shrinks the JFrame to the smallest size possible to conserve
			             //screen real estate. Comment it out to see its effect
			frame.setVisible(true);		

	}

}
