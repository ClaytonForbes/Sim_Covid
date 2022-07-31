package Covid_Simulator;
/**
 * Class Name: Human Purpose: This class implements the comparable interface,
 * which comes from Java. Note that when you implement Comparable, you have to
 * specify the class of the object that you're comparing to in the < and >
 * operator. This means that the parameter type of the compareTo() method should
 * be the same as this class that you specify. Otherwise, the program won't
 * compile. In this case I'm comparing a Person to another Person (specifically,
 * their ages), but I could compare a Person to something else, if I wanted.
 * Coder: Madhavi Mohan Date: Feb 5th, 2019
 */
/**
 * Program Name:Person.java
 * Purpose: 
 * Coder: Joshua Carpio, 0882513
 * Date: Aug. 5, 2021
 */

import java.util.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.color.*;
import java.util.Random;

public class Person extends JFrame
{
	//variables
	private boolean isAlive = true;
	private boolean isInfected;
	private int immunityStatus;
	private Color color;
	private int xCoordinate;
	private int yCoordinate;
	private int xIncrementValue;
	private int yIncrementValue;
	private int cycleCounter;
	private int personDiam = 10;
	
	//methods
	public void move() {
		
		//check if near boundary. If so, then apply negative operator to the relevant increment
		//Changed the operators to >= and <= from == to fix the "disappearing p" problem
		if(this.getxCoordinate() >= WIDTH - this.getPersonDiam() )
		{
			//we are at right side, so change xIncrementValue to a negative
			this.setxIncrementValue(this.getxIncrementValue() * -1);
		}
		if(this.getxCoordinate() <= 0)//changed operator to <=
		{
			//if true, we're at left edge, flip the flag
			this.setxIncrementValue(this.getxIncrementValue() * -1);;
		}
		if(this.getyCoordinate() >= HEIGHT - this.getPersonDiam() )
		{
			this.setyIncrementValue(this.getyIncrementValue() * -1);
		}
		if(this.getyCoordinate() <= 0)
		{
			//if true, we're at left edge, flip the flag
			this.setyIncrementValue(this.getyIncrementValue() * -1);;
		}
		//adjust the this positions using the getters and setters
		this.setxCoordinate(this.getxCoordinate() + this.getxIncrementValue());
		this.setyCoordinate(this.getyCoordinate() + this.getyIncrementValue());
	}
	
	public void checkCollision(Person per2) {
		
		int random = (int)(Math.random() * 100 + 1);
		
		if(this.getColor().equals(Color.RED) && per2.getColor().equals(Color.BLUE) && (random <= 80))
		{
			per2.setColor(this.getColor());
			per2.setInfected(true);
		}
		if(this.getColor().equals(Color.RED) && per2.getColor().equals(Color.CYAN) && (random <= 40))
		{
			per2.setColor(this.getColor());
			per2.setInfected(true);
		}
		if(this.getColor().equals(Color.RED) && per2.getColor().equals(Color.YELLOW) && (random <= 10))
		{
			per2.setColor(this.getColor());
			per2.setInfected(true);
		}
		if(this.getColor().equals(Color.RED) && per2.getColor().equals(Color.GREEN) && (random <= 10))
		{
			per2.setColor(this.getColor());
			per2.setInfected(true);
		}
	}
	
	
	//constructor
	public Person(int xCoordinate, int yCoordinate, Color color, int immunityStatus) 
	{
		this.immunityStatus = immunityStatus;
		this.color = color;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		boolean flag1 = true;
		while(flag1) {
			this.xIncrementValue = (int)(Math.random()*10 - 5);
			this.yIncrementValue = (int)(Math.random()*10 - 5);
			if(this.xIncrementValue ==0 && this.xIncrementValue==0)
			{
				this.xIncrementValue = (int)(Math.random()*10 - 5);
				this.yIncrementValue = (int)(Math.random()*10 - 5);
			}
			else
			{
				flag1 = false;
			}
		}
	}
	
	
	  public Person(Color color, int personWidth, int personHeight, int immunityStatus)
	  { 
		  this.immunityStatus = immunityStatus;
		  if(immunityStatus == 0)
			  this.isInfected = true;
		  
          this.color = color;
          int randomX, randomY;
          boolean loopflag1 = true;
          
        //loop 
          while(loopflag1)
          {
              //generate a random value using widthValue
              randomX = (int)(Math.random() * personWidth);
              if(randomX >= 0 && randomX <= personWidth - this.personDiam)
              {
                  //we have a valid x value, assign it to xCoord
                  this.xCoordinate = randomX;
                  //System.out.println("STUB:Valid random xCoord value of " + randomX);
                  loopflag1 = false;
              }
          }//end while
          
          //reset flag1 to true to start second loop
          loopflag1 = true;
          while(loopflag1)
          {
              //repeat for yCoord
              randomY = (int)(Math.random() * personHeight);
              if(randomY >= 0 && randomY <= personHeight - this.personDiam)
              {
                  //we have a valid y value, assign it to yCoord
                  this.yCoordinate = randomY;
                  //System.out.println("STUB:Valid random yCoord value of " + randomY);
                loopflag1 = false;
              }
          }//end while
          
          //Added July 15 to get the values for the increments
          boolean loopFlag = true;
          while(loopFlag)
          {
              this.xIncrementValue = (int)(Math.random()*11 - 5);
              this.yIncrementValue = (int)(Math.random()*11 - 5);        
              if(this.xIncrementValue ==0 && this.xIncrementValue ==0)
              {
                //run it again
                  this.xIncrementValue = (int)(Math.random()*11 - 5);
                  this.yIncrementValue = (int)(Math.random()*11 - 5);
              }
              else
              {
                  loopFlag = false;
              }
          }//end loop this.color = color; 
		 
	  } 
	  
	 
	
	
	//getters and setters

	public void getAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	
	public void setisAlive(boolean isAlive)
	{
		 this.isAlive = isAlive;
	}
	
	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public boolean getisInfected()
	{
		return isInfected;
	}

	public void setInfected(boolean isInfected)
	{
		this.isInfected = isInfected;
	}

	public int getImmunityStatus()
	{
		return immunityStatus;
	}

	public void setImmunityStatus(int immunityStatus)
	{
		this.immunityStatus = immunityStatus;
	}


	public int getCycleCounter()
	{
		return cycleCounter;
	}

	public void setCycleCounter(int cycleCounter)
	{
		this.cycleCounter = cycleCounter;
	}

	public int getPersonDiam()
	{
		return personDiam;
	}

	public void setPersonDiam(int personDiam)
	{
		this.personDiam = personDiam;
	}

	public int getcycleCounter()
	{
		return cycleCounter;
	}

	public void setcycleCounter(int setcycleCounter)
	{
		this.cycleCounter = setcycleCounter;
	}

	public int getxCoordinate()
	{
		return this.xCoordinate;
	}

	public void setxCoordinate(int x) {
		this.xCoordinate = x;
	}
	
	public int getyCoordinate()
	{
		return this.yCoordinate;
	}
	public void setyCoordinate(int yCoordinate)
	{
		this.yCoordinate = yCoordinate;
	}

	public int getxIncrementValue()
	{
		return this.xIncrementValue;
	}
	
	public int getyIncrementValue()
	{
		return this.yIncrementValue;
	}

	public void setxIncrementValue(int xIncrementValue)
	{
		this.xIncrementValue = xIncrementValue;
	}
	
	public void setyIncrementValue(int yIncrementValue)
	{
		this.yIncrementValue = yIncrementValue;
	}


	
	
}