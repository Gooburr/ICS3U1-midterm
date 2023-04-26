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
		
		duelWin(con);
		while(!bolBadMath){
			
		}
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
			angelTalk(con,intMorality,"ok you passed, welcome to heaven");
			con.sleep(2000);
			heavenDance(con);
			//ENDING 1
		}else{
			angelTalk(con,intMorality,"YOU FAILED!! You're going STRAIGHT TO HECC!!!!");
			con.sleep(2000);
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
				duelChallenge(con);
				if(con.currentChar() == ' '){
					duelWin(con);
					emerald(con);
				}else{
					duelLose(con);
					death(con);
				}
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
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgStabbington = con.loadImage("stabbington.jpg");
		con.println("You end up seeing Sir Stabbington.");
		con.println("Do you ARGUE with them about whether or not stabbin' is ok?");
		con.println("Do you just walk PAST them?");
		con.println("Or do you challenge them to a stabbin' DUEL ?");
		con.drawImage(imgStabbington,0,0);
		con.drawString("I like stabbin' people I don't like, its fun :>",640,145);
	}
	public static void bridgeFall(Console con){
		//scene 5
		con.clear();
		BufferedImage imgBridgeFall1 = con.loadImage("bridgeFall1.png");
		BufferedImage imgBridgeFall2 = con.loadImage("bridgeFall2.png");
		BufferedImage imgBridgeFall3 = con.loadImage("bridgeFall3.png");
		int intCount;
		for(intCount = -500; intCount <= 700; intCount += 40){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1280,720);
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,600,640,120);
			con.drawImage(imgBridgeFall1,intCount,100);
			con.repaint();
			con.sleep(33);
		}
		con.setDrawColor(Color.WHITE);
		con.fillRect(0,0,1280,720);
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,600,640,120);
		con.drawImage(imgBridgeFall2,700,100);
		con.repaint();
		con.sleep(1500);
		for(intCount = 100; intCount <= 720; intCount += 40){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1280,720);
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,600,640,120);
			con.drawImage(imgBridgeFall3,700,intCount);
			con.repaint();
			con.sleep(33);
		}
	}
	public static void heavenGates(Console con){
		//scene 6
		BufferedImage imgDead = con.loadImage("DEAD.jpg");
		con.drawImage(imgDead,0,0);
		con.repaint();
		con.sleep(3000);
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
		int intCount;
		BufferedImage imgArgue = con.loadImage("argue.jpg");
		BufferedImage imgDagger = con.loadImage("dagger.jpg");
		BufferedImage imgStabbed = con.loadImage("stabbed.jpg");
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		for(intCount = -2500;intCount <= 0; intCount += 30){
			con.drawImage(imgArgue,0,0);
			//all this text is made by chatGPT just saying
			con.drawString("Good sir knight, I humbly implore you to hear me out on this matter of grave importance. As a fellow human being, I beseech you to consider the moral implications of your actions, specifically the act of stabbing other people.",intCount,60);
			con.drawString("Firstly, let us acknowledge that every human being has the right to life and bodily integrity. It is a basic tenet of any civilized society that individuals should be free from harm caused by others. By stabbing someone, you are violating this fundamental right and causing harm to another human being, which is not only morally reprehensible but also illegal.",intCount,180);
			con.drawString("Moreover, consider the potential consequences of your actions. By stabbing someone, you risk causing permanent physical damage, including severe injury, disability, or even death. You also risk causing emotional trauma and psychological harm, not only to the victim but also to their loved ones.",intCount,300);
			con.drawString("Furthermore, let us reflect on the values that we as a society hold dear. Compassion, empathy, and respect for others are among the most cherished human virtues. By stabbing someone, you are displaying a lack of these virtues and instead promoting violence, aggression, and cruelty. These values have no place in a just and compassionate society, and we must strive to uphold the dignity of every human being.",intCount,420);
			con.drawString("Lastly, I implore you to consider the long-term effects of your actions. Violence begets violence, and a cycle of revenge and retaliation can ensue, perpetuating harm and suffering for generations to come. We must break this cycle and seek peaceful and nonviolent solutions to our problems.",intCount,540);
			con.drawString("In conclusion, my dear knight, I urge you to reflect on the moral and ethical implications of your actions. The act of stabbing another human being is not only wrong but also a violation of the basic human rights that we all hold dear. Let us strive to uphold the values of compassion, empathy, and respect for others and seek peaceful solutions to our problems.",intCount,660);
			con.repaint();
			con.sleep(33);
		}
		for(intCount = 1000;intCount >= -400; intCount -=100){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1280,720);
			con.repaint();
			con.setDrawColor(Color.BLACK);
			con.drawImage(imgDagger,intCount,200);
			con.fillRect(intCount + 426, 300,100000,20);
			con.repaint();
			con.sleep(33);
		}
		con.drawImage(imgStabbed,0,0);
		con.fillRoundRect(700,100,20,100,10,10);
		con.fillRoundRect(800,100,20,100,10,10);
		con.repaint();
		con.sleep(800);
		for(intCount = 100; intCount <= 200; intCount += 2){
			con.drawImage(imgStabbed,0,0);
			con.fillRoundRect(700,intCount,20,100,10,10);
			con.fillRoundRect(800,intCount,20,100,10,10);
			con.repaint();
			con.sleep(33);
		}
		con.sleep(1500);
	}
	public static void duelChallenge(Console con){
		//scene 10
		BufferedImage imgDuelChallenge = con.loadImage("duelChallenge.jpg");
		BufferedImage imgDuelSword = con.loadImage("duelSword.jpg");
		BufferedImage imgDagger = con.loadImage("dagger.jpg");
		int intCount;
		con.drawImage(imgDuelChallenge,0,0);
		con.drawString("I challenge you to a stabbin' duel!!",100,260);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDuelSword,0,0);
		con.drawString("Ok, here's your sword",700,100);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDuelSword,0,0);
		con.drawString("Get ready to press space as soon as my blade flies by",600,100);
		con.repaint();
		con.sleep((int)(Math.random()*4000+3000));
		for(intCount = 1000;intCount >= -400 && con.currentChar() != ' '; intCount -= 150){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1280,720);
			con.repaint();
			con.setDrawColor(Color.BLACK);
			con.drawImage(imgDagger,intCount,200);
			con.fillRect(intCount + 426, 300,100000,20);
			con.repaint();
			con.sleep(33);
		}
	}
	public static void duelLose(Console con){
		//scene 11
		int intCount;
		BufferedImage imgStabbed = con.loadImage("stabbed.jpg");
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.println("duelLose.");
		con.drawImage(imgStabbed,0,0);
		con.fillRoundRect(700,100,20,100,10,10);
		con.fillRoundRect(800,100,20,100,10,10);
		con.repaint();
		con.sleep(800);
		for(intCount = 100; intCount <= 200; intCount += 2){
			con.drawImage(imgStabbed,0,0);
			con.fillRoundRect(700,intCount,20,100,10,10);
			con.fillRoundRect(800,intCount,20,100,10,10);
			con.repaint();
			con.sleep(33);
		}
		con.sleep(1500);
	}
	public static void duelWin(Console con){
		//scene 12
		BufferedImage imgDuelJump = con.loadImage("duelJump.png");
		BufferedImage imgDuelClink = con.loadImage("duelClink.png");
		BufferedImage imgDuelBg = con.loadImage("duelBg.jpg");
		BufferedImage imgDuelWon = con.loadImage("duelWon.jpg");
		int intCount;
		int intVelocity = -20;
		int intCountY = 300;
		for(intCount = 200; intCount <= 670; intCount += 30){
			con.drawImage(imgDuelBg,0,0);
			con.drawImage(imgDuelJump,intCount,intCountY);
			intVelocity += 2;
			intCountY += intVelocity;
			con.repaint();
			con.sleep(33);
		}
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
		BufferedImage imgHeavenEnter = con.loadImage("heavenEnter.jpg");
		BufferedImage imgHeavenNotice = con.loadImage("heavenNotice.jpg");
		BufferedImage imgHeavenDanceFloor = con.loadImage("heavenDanceFloor.jpg");
		BufferedImage imgHeavenDance1 = con.loadImage("heavenDance1.png");
		BufferedImage imgHeavenDance2 = con.loadImage("heavenDance2.png");
		con.clear();
		con.drawImage(imgHeavenEnter,0,0);
		con.drawString("Oh nice, I'm in heaven now!",400,200);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgHeavenNotice,0,0);
		con.drawString("WAIT IS THAT A DANCE PARTY!?!?!",500,200);
		con.repaint();
		con.sleep(2000);
		while(true){
			con.drawImage(imgHeavenDanceFloor,0,0);
			con.drawImage(imgHeavenDance1,200,200);
			con.drawString("ENDING 1: Heaven",0,0);
			con.repaint();
			con.sleep(500);
			con.drawImage(imgHeavenDanceFloor,0,0);
			con.drawImage(imgHeavenDance2,200,200);
			con.drawString("ENDING 1: Heaven",0,0);
			con.repaint();
			con.sleep(500);
		}
	}
	public static void hecc(Console con){
		//scene 20
		con.clear();
		BufferedImage imgHeccEnter = con.loadImage("heccEnter.jpg");
		BufferedImage imgHeccCry = con.loadImage("heccCry.jpg");
		con.drawImage(imgHeccEnter,0,0);
		con.drawString("You know, this isn't actually that bad!",650,280);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgHeccEnter,0,0);
		con.drawString("It's nice and warm in here :)",650,280);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgHeccCry,0,0);
		con.drawString("ENDING 2: Hecc",0,0);
		con.drawString("NOOOOOO!!!!!",800,200);
		con.drawString("You SURE about that!?!?!",50,200);
		con.drawString("*vocaloid english dubs*",400,200);
		con.repaint();
		while(true){
			//Nothing in here so it just does nothing forever
		}
	}
	public static void emerald(Console con){
		//scene 21
		con.println("emerald.");
	}
}
