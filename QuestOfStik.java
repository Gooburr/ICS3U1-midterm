import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class QuestOfStik {
	public static void main(String[] args){
		Console con = new Console("Quest of Stik", 1280, 720);
		//set up variables
		char chrInput = con.currentChar();
		String strInput;
		String strSid;
		int intInput;
		int intLuck;
		int intRandom;
		double dblInput;
		double dbl5Answer;
		boolean bolBadMath = false;
		boolean bolPast = false;
		
		intro(con);
		while(chrInput!=',' && chrInput!='.'){
			//keeps repeating until you press < or >
			chrInput = con.currentChar();
		}
		while(!bolPast){
			if(chrInput == ','){
				//left path
				if(!bolBadMath){
					left(con);
				}else{
					badMath(con);
					bolBadMath = false;
				}
				strInput = con.readLine();
				if(strInput.equalsIgnoreCase("agree")){
					
					con.println("Question 1: 1 + 1 (TEMP)");
					intInput = con.readInt();
					if(intInput == 2){
						con.println("Question 2: 27 - 44 (TEMP)");
						intInput = con.readInt();
						if(intInput == -16){
							con.println("Question 3: 7/4(TEMP)");
							dblInput = con.readDouble();
							if(dblInput == 1.75){
								con.println("Question 4: 27 * 1.5 (TEMP)");
								dblInput = con.readDouble();
								if(dblInput == 40.5){
									//generates random number from 5 to 20
									intRandom = (int)(Math.random()*15+5);
									//used to be root2^7 divided by the rest instead of multiplied, changed due to answers being far too close to each other
									con.println("Question 5: sqrt(2)^7*(("+intRandom+")*(7 - 4)) + pi rounded to 4 decimal places (TEMP)");
									dbl5Answer = (Math.round((Math.pow(Math.sqrt(2.0),7.0)*((intRandom)*(7.0 - 4.0)) + Math.PI)*10000.0))/10000.0;
									con.println(dbl5Answer); //FOR TESTING ONLY
									dblInput = con.readDouble();
									if(dblInput == dbl5Answer){
										//sends you to the scene after you pass the left bridge
										pastLeftBridge(con);
										bolPast = true;
									}else{
										//sends you back to the start with scene 3
										bolBadMath = true;
										strInput = "";
									}
								}else{
									//sends you back to the start with scene 3
									bolBadMath = true;
									strInput = "";
								}
							}else{
								//sends you back to the start with scene 3
								bolBadMath = true;
								strInput = "";
							}
						}else{
							//sends you back to the start with scene 3
							bolBadMath = true;
							strInput = "";
						}
					}else{
						//sends you back to the start with scene 3
						bolBadMath = true;
						strInput = "";
					}
				}else if(strInput.equalsIgnoreCase("sneak")){
					intLuck = (int)(Math.random()*4+1);
					bolPast = true;
					if(intLuck == 1){
						pastLeftBridge(con);
					}else{
						bridgeFall(con);
						death(con);
					}
				}else if(strInput.equalsIgnoreCase("back")){
					chrInput = '.';
				}
			}else{
				//right path
				right(con);
				strInput = con.readLine();
				if(strInput.equalsIgnoreCase("sid hoffman") || strInput.equalsIgnoreCase("sid frenchman")){
					intRandom = (int)(Math.random()*2+1);
					if(intRandom == 1){
						strSid = "sid hoffman";
					}else{
						strSid = "sid frenchman";
					}
					if(strInput.equalsIgnoreCase(strSid)){
						bolPast = true;
						sidCorrect(con);
						pastRightBridge(con);
					}else{
						sidIncorrect(con);
					}
				}else if(strInput.equalsIgnoreCase("sneak")){
					intLuck = (int)(Math.random()*4+1);
					bolPast = true;
					if(intLuck == 1){
						pastRightBridge(con);
					}else{
						bridgeFall(con);
						death(con);
					}
				}else if(strInput.equalsIgnoreCase("back")){
					chrInput = ',';
				}
			}
		}
	}
	//non-scene methods
	public static void death(Console con){
		int intMorality;
		con.println("death");
		
	}
	public static void pastLeftBridge(Console con){
		con.println("past left");
	}
	public static void pastRightBridge(Console con){
		con.println("past right");
	}
	//scene methods
	public static void intro(Console con){
		//scene 1
		con.println("intro. < or > (TEMP)");
	}
	public static void left(Console con){
		//scene 2
		con.println("left. agree, sneak, or back (TEMP)");
	}
	public static void badMath(Console con){
		//scene 3
		con.println("badMath. agree, sneak, or back (TEMP)");
	}
	public static void bridgeFall(Console con){
		//scene 5
		con.println("bridgeFall.");
	}
	public static void heavenGates(Console con){
		//scene 6
		con.println("heavenGates.");
	}
	public static void right(Console con){
		//scene 13
		con.println("right. sid hoffman, sid frenchman, sneak, or back (TEMP)");
	}
	public static void sidCorrect(Console con){
		//scene 14
		con.println("sidCorrect.");
	}
	public static void sidIncorrect(Console con){
		//scene 15
		con.println("sidIncorrect.");
	}
}

