import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
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
		boolean bolPastBridge = false;
		
		intro(con);
		while(chrInput!=',' && chrInput!='.'){
			//keeps repeating until you press < or >
			chrInput = con.currentChar();
		}
		while(!bolPastBridge){
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
									intRandom = (int)(Math.random()*15.0+5.0);
									//used to be root2^7 divided by the rest instead of multiplied, changed due to answers being far too close to each other
									con.println("Question 5: sqrt(2)^7*(("+intRandom+")*(7 - 4)) + pi rounded to 4 decimal places (TEMP)");
									dbl5Answer = (Math.round((Math.pow(Math.sqrt(2.0),7.0)*((intRandom)*(7.0 - 4.0)) + Math.PI)*10000.0))/10000.0;
									con.println(dbl5Answer); //FOR TESTING ONLY
									dblInput = con.readDouble();
									if(dblInput == dbl5Answer){
										//sends you to the scene after you pass the left bridge
										pastLeftBridge(con);
										bolPastBridge = true;
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
					intLuck = (int)(Math.random()*4.0+1.0);
					bolPastBridge = true;
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
					intRandom = (int)(Math.random()*2.0+1.0);
					if(intRandom == 1){
						strSid = "sid hoffman";
					}else{
						strSid = "sid frenchman";
					}
					if(strInput.equalsIgnoreCase(strSid)){
						bolPastBridge = true;
						sidCorrect(con);
						pastRightBridge(con);
					}else{
						sidIncorrect(con);
					}
				}else if(strInput.equalsIgnoreCase("sneak")){
					intLuck = (int)(Math.random()*4+1);
					bolPastBridge = true;
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
		//set up variables
		int intMorality = 0;
		int intQuestion = 0;
		boolean bolMoralQuiz = true;
		boolean bolWaitForClick = true;
		
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		heavenGates(con);
		
		while(bolMoralQuiz){
			//check if clicking and inside YES button
			if(con.currentMouseX() >= 100 && con.currentMouseX() <= 390 && con.currentMouseY() >= 30 && con.currentMouseY() <= 230 && con.currentMouseButton() == 1){
				if(intQuestion == 0){
					angelTalk(con,intMorality,"ok so first question: is murder good?");
					intQuestion++;
					con.sleep(500);
				}else if(intQuestion == 1){
					intMorality -= 10;
					angelTalk(con,intMorality,"UHHHHHHHHHH.....");
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 2){
					gregRight(con);
					intMorality -= 10;
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 3){
					intMorality += 10;
					angelTalk(con,intMorality,"based");
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 4){
					intMorality += 10;
					angelTalk(con,intMorality,"As it should be");
					intQuestion++;
					con.sleep(2000);
					bolMoralQuiz = false;
				}
				bolWaitForClick = true;
			}else if(con.currentMouseX() >= 700 && con.currentMouseX() <= 950 && con.currentMouseY() >= 20 && con.currentMouseY() <= 200 && con.currentMouseButton() == 1){
				if(intQuestion == 1){
					intMorality += 10;
					angelTalk(con,intMorality,"Well yeah ofc");
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 2){
					gregWrong(con);
					intMorality += 10;
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 3){
					angelTalk(con,intMorality,"WHAT!?!?!?!?!");
					con.sleep(1000);
					angelTalk(con,intMorality,"What kind of person doesn't like breadsticks!?!");
					con.sleep(3000);
					intMorality -= 10;
					angelTalk(con,intMorality,"I bet you're like SUPER evil!!!");
					intQuestion++;
					con.sleep(2000);
				}else if(intQuestion == 4){
					intMorality -= 10;
					angelTalk(con,intMorality,"You REALLY don't wanna stop toe stubbing?!?!?!");
					intQuestion++;
					con.sleep(3000);
					bolMoralQuiz = false;
				}
				bolWaitForClick = true;
			}
			if(bolWaitForClick){
				if(intQuestion == 2){
					angelTalk(con,intMorality,"next question now.");
					con.sleep(2000);
					angelTalk(con,intMorality,"Should you delete someone's pokemon save file?");
				}else if(intQuestion == 3){
					angelTalk(con,intMorality,"aaaaaalright, 3rd question. ");
					con.sleep(2000);
					angelTalk(con,intMorality,"Do you like breadsticks? (I like breadsticks)");
				}else if(intQuestion == 4){
					angelTalk(con,intMorality,"Final question now. ");
					con.sleep(2000);
					angelTalk(con,intMorality,"Should people make foot level stuff softer?");
					con.sleep(3000);
					angelTalk(con,intMorality,"so people don't stub their toe on stuff ofc");
				}
				bolWaitForClick = false;
			}
		}
		if(intMorality >= 0){
			heavenDance(con);
			//ENDING 1
		}else{
			hecc(con);
			//ENDING 2
		}
	}
	//resets the scene and makes the angel say smth new
	public static void angelTalk(Console con,int intMorality, String strTalk){
		con.clear();
		BufferedImage imgHeavenGates = con.loadImage("heavenGates.jpg");
		con.drawImage(imgHeavenGates,0,0);
		con.println("your morality score is: "+intMorality);
		con.drawString(strTalk,640,220);
		con.drawRect(100,30,290,200);
		con.drawRect(700,20,250,180);
		con.repaint();
	}
	public static void pastLeftBridge(Console con){
		String strInput = "";
		con.println("past left");
		stabbington(con);
		while(strInput != "past" && strInput != "argue" && strInput != "duel"){
			strInput = con.readLine();
			if(strInput.equalsIgnoreCase("past")){
				emerald(con);
				//ENDING 3
			}else if(strInput.equalsIgnoreCase("argue")){
				argue(con);
				death(con);
			}else if(strInput.equalsIgnoreCase("duel")){
				
			}
		}
	}
	public static void pastRightBridge(Console con){
		String strInput;
		String strLetter;
		String strBack;
		int intLength;
		int intCount;
		con.println("past right");
		dragon(con);
		while(true){
			strInput = con.readLine();
			intLength = strInput.length();
			strBack = "";
			for(intCount = intLength; intCount >= 1; intCount--){
				strLetter = strInput.substring(intCount - 1, intCount);
				strBack += strLetter;
			}
			if(strInput.equalsIgnoreCase(strBack)){
				//strInput is a palindrome
				stikGoesBOOM(con);
				death(con);
			}else{
				//strInput isn't a palindrome
				dontCare(con);
			}
		}
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
	public static void stabbington(Console con){
		//scene 4
		con.println("stabbington. past, argue, or duel");
	}
	public static void bridgeFall(Console con){
		//scene 5
		con.println("bridgeFall.");
	}
	public static void heavenGates(Console con){
		//scene 6
		BufferedImage imgHeavenGates = con.loadImage("heavenGates.jpg");
		con.println("Stik ends up at the gates of heaven");
		con.drawImage(imgHeavenGates,0,0);
		con.drawString("hey uhh, I forgot to measure your morality on Earth",640,220);
		con.drawString("so uh... yeah imma just do a quick test now",640,240);
		con.drawString("...just click the YES button ok?",640,260);
		con.drawRect(100,30,290,200);
		con.drawRect(700,20,250,180);
		con.repaint();
	}
	public static void gregWrong(Console con){
		//scene 7
		con.println("gregWrong.");
	}
	public static void gregRight(Console con){
		//scene 8
		con.println("gregRight.");
	}
	public static void argue(Console con){
		//scene 9
		con.println("argue.");
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
	public static void dragon(Console con){
		//scene 16
		con.println("dragon. say anything, palindrome or not palindrome");
	}
	public static void dontCare(Console con){
		//scene 17
		con.println("dontCare. say anything, palindrome or not palindrome");
	}
	public static void stikGoesBOOM(Console con){
		//scene 18
		con.println("stikGoesBOOM.");
	}
	public static void heavenDance(Console con){
		//scene 19
		con.println("heavenDance.");
	}
	public static void hecc(Console con){
		//scene 20
		con.println("hecc.");
	}
	public static void emerald(Console con){
		//scene 21
		con.println("emerald.");
	}
}
