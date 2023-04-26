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
		
		intro(con, chrInput);
		chrInput = con.currentChar();
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
				//Math quiz
				if(strInput.equalsIgnoreCase("agree")){
					con.clear();
					trollTalk(con,"Question 1: 1 + 1 ");
					intInput = con.readInt();
					if(intInput == 2){
						trollTalk(con,"Question 2: 27 - 44");
						intInput = con.readInt();
						if(intInput == -17){
							trollTalk(con,"Question 3: 7/4");
							dblInput = con.readDouble();
							if(dblInput == 1.75){
								trollTalk(con,"Question 4: 27 * 1.5");
								dblInput = con.readDouble();
								if(dblInput == 40.5){
									//generates random number from 5 to 20
									intRandom = (int)(Math.random()*15.0+5.0);
									//used to be root2^7 divided by the rest instead of multiplied, changed due to answers being far too close to each other
									trollTalk(con,"Question 5: (sqrt(2))^7*(("+intRandom+")*(7 - 4)) + pi"); 
									con.println("round answer to 4 decimal places");
									dbl5Answer = (Math.round((Math.pow(Math.sqrt(2.0),7.0)*((intRandom)*(7.0 - 4.0)) + Math.PI)*10000.0))/10000.0;
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
					//Either you fall or get past, 1 in 4 chance
					intLuck = (int)(Math.random()*4+1);
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
					//randomly sets strSid to "sid hoffman" or "sid frenchman"
					intRandom = (int)(Math.random()*2.0+1.0);
					if(intRandom == 1){
						strSid = "sid hoffman";
					}else{
						strSid = "sid frenchman";
					}
					if(strInput.equalsIgnoreCase(strSid)){
						bolPastBridge = true;
						con.clear();
						con.println(strSid);
						sidCorrect(con);
						pastRightBridge(con);
					}else{
						con.clear();
						con.println(strSid);
						sidIncorrect(con);
					}
				}else if(strInput.equalsIgnoreCase("sneak")){
					//Either you fall or get past, 1 in 4 chance
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
	//NON-SCENE METHODS
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
			//check if inside and clicking NO button
			}else if(con.currentMouseX() >= 700 && con.currentMouseX() <= 950 && con.currentMouseY() >= 20 && con.currentMouseY() <= 200 && con.currentMouseButton() == 1){
				if(intQuestion == 1){
					intMorality += 10;
					angelTalk(con,intMorality,"Well yeah ofc");
					intQuestion++;
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
		//set up variables
		String strInput;
		String strLetter;
		String strBack;
		int intLength;
		int intCount;
		con.println("past right");
		dargon(con);
		
		while(true){
			//palindrome checker
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
	//resets scene 2 and makes the troll say something new
	public static void trollTalk(Console con, String strTalk){
		BufferedImage imgLeft = con.loadImage("left.jpg");
		con.drawImage(imgLeft,0,0);
		con.drawString(strTalk,600,150);
		con.repaint();
	}
	
	//SCENE METHODS
	public static void intro(Console con, char chrInput){
		//scene 1
		con.setDrawColor(Color.BLACK);
		con.setTextColor(Color.BLACK);
		BufferedImage imgIntro1 = con.loadImage("intro1.jpg");
		BufferedImage imgIntro2 = con.loadImage("intro2.jpg");
		BufferedImage imgIntro3Bg = con.loadImage("intro3Bg.jpg");
		BufferedImage imgIntroL = con.loadImage("introL.png");
		BufferedImage imgIntroR = con.loadImage("introR.png");
		int intSideTimer = 0;
		//first part, where Stik talks
		con.drawImage(imgIntro1,0,0);
		con.drawString("Hi! I'm Stik!! Welcome to Quest of Stik!!",400,250);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgIntro1,0,0);
		con.drawString("I'm looking for shiny treasures!!",400,250);
		con.repaint();
		con.sleep(3000);
		//second part, where narrator talks
		con.drawImage(imgIntro2,0,0);
		con.println("And I'm the Narrator, I'll be narrating some things, and telling you what to do sometimes");
		con.println("if something's in all caps, that means you can type it in as an option");
		con.repaint();
		con.sleep(7000);
		//keeps repeating until you press < or >
		while(chrInput!=',' && chrInput!='.'){
			con.clear();
			con.drawImage(imgIntro3Bg,0,0);
			con.println("looks like Stik's at an intersection!! Press < to go left, and > to go right! (no shift)");
			//flips between left facing Stik and right facing Stik
			if(intSideTimer == 100){
				intSideTimer = 0;
				con.drawImage(imgIntroL,550,350);
			}else if(intSideTimer > 50){
				con.drawImage(imgIntroL,550,350);
			}else{
				con.drawImage(imgIntroR,550,350);
			}
			intSideTimer++;
			chrInput = con.currentChar();
			con.repaint();
			con.sleep(20);
		}
	}
	public static void left(Console con){
		//scene 2
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.setTextColor(Color.BLACK);
		BufferedImage imgLeft = con.loadImage("left.jpg");
		con.drawImage(imgLeft,0,0);
		con.println("Stik goes to the left and sees a troll guarding a bridge");
		con.repaint();
		con.sleep(3000);
		trollTalk(con,"In order to pass this bridge you must answer... ");
		con.sleep(3000);
		trollTalk(con,"...my math questions five");
		con.sleep(3000);
		con.drawImage(imgLeft,0,0);
		con.drawString("isn't it normally riddles three?",100,350);
		con.repaint();
		con.sleep(3000);
		trollTalk(con,"People like riddles, people hate math.");
		con.sleep(3000);
		trollTalk(con,"I'm evil >:3");
		con.sleep(3000);
		con.println("You can either AGREE to answer the questions,");
		con.println("try to SNEAK past (1 in 4 chance of working),");
		con.println("or go BACK and take the other path");
	}
	public static void badMath(Console con){
		//scene 3
		con.clear();
		BufferedImage imgBadMath = con.loadImage("badMath.jpg");
		con.drawImage(imgBadMath,0,0);
		con.println("Your math was BAD!! Now go DO IT ALL OVER AGAIN!!! MUAHAHA! EVILNESS!!! >:3 (AGREE, SNEAK, or BACK)");
		con.repaint();
	}
	public static void stabbington(Console con){
		//scene 4
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgStabbington = con.loadImage("stabbington.jpg");
		con.println("Stik gets past the bridge and ends up seeing Sir Stabbington.");
		con.println("Do you ARGUE with them about whether or not stabbin' is ok?");
		con.println("Do you just walk PAST them?");
		con.println("Or do you challenge them to a stabbin' DUEL ?");
		con.drawImage(imgStabbington,0,0);
		con.drawString("I like stabbin' people I don't like, its fun :>",640,145);
		con.repaint();
	}
	public static void bridgeFall(Console con){
		//scene 5
		con.clear();
		BufferedImage imgBridgeFall1 = con.loadImage("bridgeFall1.png");
		BufferedImage imgBridgeFall2 = con.loadImage("bridgeFall2.png");
		BufferedImage imgBridgeFall3 = con.loadImage("bridgeFall3.png");
		int intCount;
		//makes Stik run along the rectangle
		for(intCount = -500; intCount <= 700; intCount += 40){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1280,720);
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,600,640,120);
			con.drawImage(imgBridgeFall1,intCount,100);
			con.repaint();
			con.sleep(33);
		}
		//makes Stik pause for a bit in midair
		con.setDrawColor(Color.WHITE);
		con.fillRect(0,0,1280,720);
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,600,640,120);
		con.drawImage(imgBridgeFall2,700,100);
		con.repaint();
		con.sleep(1500);
		//Makes Stik fall
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
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgGregWrong = con.loadImage("gregWrong.jpg");
		con.drawImage(imgGregWrong,0,0);
		con.drawString("SEE GREG!! I TOLD YOU!!!",800,100);
		con.drawString(":C",300,100);
		con.repaint();
		con.sleep(2000);
	}
	public static void gregRight(Console con){
		//scene 8
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgGregRight = con.loadImage("gregRight.jpg");
		BufferedImage imgGregArms = con.loadImage("gregArms.png");
		int intCount;
		for(intCount = 0; intCount <= 120; intCount++){
			con.drawImage(imgGregRight,0,0);
			//the random numbers makes the arms and text shake
			con.drawImage(imgGregArms,(int)(Math.random()*50+650),(int)(Math.random()*50+250));
			con.drawString("I SPENT 100+ HOURS ON THAT YOU HECCIN-",(int)(Math.random()*50+700),(int)(Math.random()*50+1));
			con.drawString("See?",150,100);
			con.repaint();
			con.sleep(33);
		}
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
		//makes the text scroll by
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
		//makes the dagger fly by
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
		//Stik pauses for a bit
		con.drawImage(imgStabbed,0,0);
		con.fillRoundRect(700,100,20,100,10,10);
		con.fillRoundRect(800,100,20,100,10,10);
		con.repaint();
		con.sleep(800);
		//stik looks down
		for(intCount = 100; intCount <= 200; intCount += 2){
			con.drawImage(imgStabbed,0,0);
			con.fillRoundRect(700,intCount,20,100,10,10);
			con.fillRoundRect(800,intCount,20,100,10,10);
			con.repaint();
			con.sleep(33);
		}
		//stik pauses again
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
		//pauses for a random amount of time (3 to 7 seconds)
		con.sleep((int)(Math.random()*4000+3000));
		//makes the dagger fly by
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
		//Stik pauses for a bit
		con.drawImage(imgStabbed,0,0);
		con.fillRoundRect(700,100,20,100,10,10);
		con.fillRoundRect(800,100,20,100,10,10);
		con.repaint();
		con.sleep(800);
		//Stik looks down
		for(intCount = 100; intCount <= 200; intCount += 2){
			con.drawImage(imgStabbed,0,0);
			con.fillRoundRect(700,intCount,20,100,10,10);
			con.fillRoundRect(800,intCount,20,100,10,10);
			con.repaint();
			con.sleep(33);
		}
		//Stik pauses again
		con.sleep(1500);
	}
	public static void duelWin(Console con){
		//scene 12
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgDuelJump = con.loadImage("duelJump.png");
		BufferedImage imgDuelClink = con.loadImage("duelClink.png");
		BufferedImage imgDuelBg = con.loadImage("duelBg.jpg");
		BufferedImage imgDuelWon = con.loadImage("duelWon.jpg");
		int intCount;
		int intVelocity = -35;
		int intCountY = 300;
		//makes Stik jump
		for(intCount = 200; intCount <= 600; intCount += 25){
			con.drawImage(imgDuelBg,0,0);
			con.drawImage(imgDuelJump,intCount,intCountY);
			intVelocity += 3;
			intCountY += intVelocity;
			con.repaint();
			con.sleep(33);
		}
		con.drawImage(imgDuelBg,0,0);
		con.drawImage(imgDuelClink,intCount,intCountY);
		con.drawString("*clink*",intCount + 250, intCountY - 50);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgDuelWon,0,0);
		con.drawString("Wow ok you won, now leave before I get angry and uh well...",550,100);
		con.repaint();
		con.sleep(3000);
	}
	public static void right(Console con){
		//scene 13
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgRight = con.loadImage("right.jpg");
		con.drawImage(imgRight,0,0);
		con.clear();
		con.println("Stik goes to the right and sees Homestar Runner.");
		con.println("Homestar says:                                                     \"SID HOFFMAN or SID FRENCHMAN\"");
		con.println("You can also go BACK to the left,");
		con.println("or try and SNEAK past homestar (1 in 4).");
		con.repaint();
	}
	public static void sidCorrect(Console con){
		//scene 14
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgSidCorrect = con.loadImage("sidCorrect.jpg");
		con.drawImage(imgSidCorrect,0,0);
		con.println("Homestar says:                                            \"YAY! you got it right! You can pass now :)\"");
		con.repaint();
		con.sleep(3000);
	}
	public static void sidIncorrect(Console con){
		//scene 15
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		BufferedImage imgSidIncorrect = con.loadImage("sidIncorrect.jpg");
		con.drawImage(imgSidIncorrect,0,0);
		con.println("Homestar says:                                                     \"Sorry, you got it wrong :C\"");
		con.repaint();
		con.sleep(3000);
	}
	public static void dargon(Console con){
		//scene 16
		BufferedImage imgDargon = con.loadImage("dargon.jpg");
		con.drawImage(imgDargon,0,0);
		con.clear();
		con.println("Stik gets past Homestar and meets up with.... that thing");
		con.drawString("I am dargon!!1 I havethe golds!!!!11",800,200);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDargon,0,0);
		con.drawString("Can I have the golds?",100, 250);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDargon,0,0);
		con.drawString("NO!!!!1111 Iam NEEVR LEAVING!!1",800, 200);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDargon,0,0);
		con.drawString("Not unless you say the PALINGDROMES!!!!1!11",800,200);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDargon,0,0);
		con.drawString("It makes yuo KABOUM!!1111",800,200);
		con.repaint();
		con.sleep(3000);
		con.drawImage(imgDargon,0,0);
		con.drawString("Itcould be CONTAYGUESS!!!!!!111",800, 200);
		con.repaint();
		con.sleep(3000);
		con.println("...honestly I don't know just say whatever you want I guess");
	}
	public static void dontCare(Console con){
		//scene 17
		BufferedImage imgDontCare = con.loadImage("dontCare.jpg");
		con.drawImage(imgDontCare,0,0);
		con.clear();
		con.println("please just say something I don't wanna deal with this anymore");
		con.repaint();
	}
	public static void stikGoesBOOM(Console con){
		//scene 18
		BufferedImage imgStikGoesBoom = con.loadImage("stikGoesBoom.jpg");
		BufferedImage imgBoom = con.loadImage("boom.jpg");
		BufferedImage imgDargonLeaves = con.loadImage("dargonLeaves.png");
		int intCount;
		con.clear();
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawImage(imgDargonLeaves,700,150);
		con.drawString("YOURE ARE GOIN KABOUM!!!!!!!!!1111",650,120);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawImage(imgDargonLeaves,700,150);
		con.drawString("DARGON ISLEAVING!!!!!!!!!!111111",650,120);
		con.repaint();
		con.sleep(2000);
		//makes dagron fly up
		for(intCount = 150; intCount >= -300; intCount -= 10){
			con.drawImage(imgStikGoesBoom,0,0);
			con.drawImage(imgDargonLeaves,700,intCount);
			con.repaint();
			con.sleep(33);
		}
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawString("YAY",200,300);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawString("ooo gold dlog ooo",200,300);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawString("...wait tiaw...",200,300);
		con.repaint();
		con.sleep(2000);
		con.drawImage(imgStikGoesBoom,0,0);
		con.drawImage(imgBoom,200,350);
		//ok I know the explosion only covers part of Stik, but to me this is funny and part of the joke
		con.repaint();
		con.sleep(2000);
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
		//makes Stik dance forever
		while(true){
			con.drawImage(imgHeavenDanceFloor,0,0);
			con.drawImage(imgHeavenDance1,200,200);
			con.drawString("ENDING 1/3: Heaven",0,0);
			con.repaint();
			con.sleep(500);
			con.drawImage(imgHeavenDanceFloor,0,0);
			con.drawImage(imgHeavenDance2,200,200);
			con.drawString("ENDING 1/3: Heaven",0,0);
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
		con.drawString("ENDING 2/3: Hecc",0,0);
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
		BufferedImage imgEmerald = con.loadImage("emerald.jpg");
		con.drawImage(imgEmerald,0,0);
		con.clear();
		con.println("Stik found a chaos emerald!! It's very shiny!! YIPEE!!!!!!");
		con.println("ENDING 3/3: emerald");
		while(true){
			//Nothing in here so it just does nothing forever
		}
	}
}



//Hey seeing as you're down here, fun fact: Stik is actually nonbinary, and they use they/them
