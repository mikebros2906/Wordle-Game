//Name : Somik Chowdhury //Student Number : 3164459

package griffith;
import java.util.Scanner;
import java.util.Random;

public class Wordle {

	//This method removes every letter that is checked from the string that is created temporarily next
	public static String examineAlphabets(String guessedLetters) {
		
		//A string of all the English alphabets which gets removed every time the user uses a letter
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		
		//This is an empty string which will store all the used alphabets by the user
		String remainingLetters = "";
		
		//This for loop iterates over all the alphabets in the string above using guessedLetters and adds the alphabets not present in the remainingLetters string
		for(char c : alphabets.toCharArray()) {
			if(!guessedLetters.contains(String.valueOf(c))) {
				remainingLetters += c + "";
			}
		}
		
		//Returns the alphabets string after the check and removal
		return remainingLetters.toString();
	}

	//This method colors the string to be printed to enhance the user experience with different color codes from ASCII
	public static String colorText(String text, String color) {
		return color + text + "\033[0m";
	}

	//This method marks the letter with wrong position with an asterisk '*' and the wrong letter with an underscore '_'
	public static String wordMarking(char[] userGuessLowerCaseArray, char[] computerRandomWord) {
		
		//Creating a new string which is started by underscores '_', representing unmarked positions
		StringBuilder checked = new StringBuilder("_".repeat(userGuessLowerCaseArray.length));
		
		//Creating a boolean array which will store the correctness of all the letters,
		boolean[] matchedInRandomWord = new boolean[computerRandomWord.length];
		
		//This for loop replaces the correctly matched character with that particular character and turns the boolean as true marking it as checked to avoid double checking
		for(int p = 0; p < userGuessLowerCaseArray.length; p++) {
			if(userGuessLowerCaseArray[p] == computerRandomWord[p]) {
				checked.setCharAt(p, userGuessLowerCaseArray[p]);
				matchedInRandomWord[p] = true;
			}
		}
			
		//This for loop checks the unmarked position for the same letter in a different location, if found, it is replaced with an asterisk '*'
		for(int q=0; q<userGuessLowerCaseArray.length; q++) {
			if(checked.charAt(q) == '_') {
				for(int r = 0; r < computerRandomWord.length; r++) {
					if(!matchedInRandomWord[r] && userGuessLowerCaseArray[q] == computerRandomWord[r]) {
						checked.setCharAt(q, '*');
						matchedInRandomWord[r] = true;
						break;
					}
				}
			}
		}
		
		//returning the string to display it further to the user
		return checked.toString();
	}
	
	//This method resets the randomWord selected by computer so as to mark the end of game and avoid any errors
	public static String resetRandomWord(String randomWord) {
		
		//Returning null will clear the randomWord selected by computer
		return null;
	}
	
	//This method calculates the score of the player in the game
	public static int wordleScore(int attempts, String alphabetsRemaining) {
		
		//Formulae used to calculate score
		int scoreFinal = (alphabetsRemaining.length()) - (6-attempts);
		
		//Method returns the score as an integer
		return scoreFinal;
	}
	
	//Main method from where the program begins
	public static void main(String[] args) {
		
		//creating a scanner object to take in user input
		Scanner scanner = new Scanner(System.in);
		
		//Creating a random object to take random words from the given array of words
		Random random = new Random();

		//Array of strings where all the 5 letter words are stored and can be selected in random by the computer
		String[] words = {"earth", "whole", "choke", "pizza", "dhole", "fuzzy", "which", "squid", "affix", "brown", "cadet", "cameo", "chill", "chime", "prime", "wikis", "kiwis", "after", "trash", "guess", "apple", "enter", "entry", "erase", "eaten", "space"};
		
		//Take in user name to begin the game and greetings to the user to make it more user friendly
		System.out.println("what is your name: ");
		String name = scanner.nextLine();
		
		//Getting a random number according to the size of the array defined above and allocating them in a randomWord variable
		int randomIndex = random.nextInt(words.length);
		String randomWord = words[randomIndex];
		
		//These are character arrays that will store the letters that have been guessed/used and letters that are correct
		String guessedLetters = "";
		
		//Printing the rules of the game and the main menu description
		System.out.println(colorText("Rules are simple for the game:\n   Computer will choose a random word, You have 6 chances to guess that word.\n   If your guessed word has a letter matching with the computer's word, The letter will be marked with a star '*'\n   Else, if the guessed word does not have a matching letter, it will be marked with an underscore '_'\n   Also, if a letter matches with the computer's word, it will be displayed in the console in the same space where the word has it.", "\u001B[33m"));
		System.out.println("If you wish to quit the game, you can type in '1' \nIf you wish to see the rules, you can type in '2' \nIf you wish to restart the current game, you can type in '3' \nIf you wish to play a new game, You can type in '4' \nIf you wish to see the menu, You can type in '5' \n");
		
		//Defining attempts so that players have a limited number of chances before they can get the correct guess
		int attempts = 6;
		System.out.println("You have a total of " + colorText("'6'", "\u001B[31m") + " attempts to guess the correct word.");

		//This is the loop initiation after which the while loop begins until players decides to quit.
		boolean gameActive = true;
		
		//Beginning while loop and the main section of the game
		while(gameActive) {
			
			//Try and Catch methods take any error that might be caused during the game so that the game does'nt end and the loop continues
			//Try will let the game run normally 
			try {
				//Taking in userGuess and converting it to lowerCase to begin the game of wordle
				System.out.println("Please enter your "  + colorText("response", "\u001B[36m") + ": ");
				String userGuess = scanner.nextLine().toLowerCase();
				
				//The regex pattern will force the user to adhere only to the responses that are accepted by the program
				String regexPattern = "^([a-zA-Z]{5}|[1-5])$";
				String testString = userGuess;
				
				//The program will begin it's course if the user has entered the response accepted by the regex pattern
				if(testString.matches(regexPattern)){
					
					//This if statement checks for an integer '1', which will mark the end of the game as user decided to quit
					if(userGuess.equals("1") || userGuess.equals("n") ){
						System.out.println("Thanks for playing " + name + ". Wishing you all the very best.");
						gameActive = false;
						break;
					}
					
					//This if statement prints out the rules of the game as and when the user needs it
					else if(userGuess.equals("2")) {
						System.out.println(colorText("Rules are simple for the game:\n   Computer will choose a random word, You have 6 chances to guess that word.\n   If your guessed word has a letter matching with the computer's word, The letter will be marked with a star '*'\n   Else, if the guessed word does not have a matching letter, it will be marked with an underscore '_'\n   Also, if a letter matches with the computer's word, it will be displayed in the console in the same space where the word has it.", "\u001B[33m"));
						continue;
					}
					
					//If statement to start a new game, that will reset all the guessedLetters, correctLetters, attempts, randomWord and begin the course of the game normally
					else if(userGuess.equals("3")) {
						System.out.println("New game has started...");
						attempts = 6;
						randomIndex = random.nextInt(words.length);
						randomWord = words[randomIndex];
						guessedLetters = "";
						System.out.println("If you wish to quit the game, you can type in '1' \nIf you wish to see the rules, you can type in '2' \nIf you wish to restart the current game, you can type in '3' \nIf you wish to play a new game, You can type in '4'\nIf you wish to see the menu, You can type in '5' \n");
						System.out.println("You have a total of " + colorText("'6'", "\u001B[31m") + " attempts to guess the correct word.");
						continue;
					}
					
					//This if statement restarts the game
					else if(userGuess.equals("4")) {
						System.out.println("Game has been restarted...");
						System.out.println("what is your name: ");
						name = scanner.nextLine();
						System.out.println("Hi " + colorText(name, "\033[31m") + ", let's begin the game of wordle...");
						attempts = 6;
						randomIndex = random.nextInt(words.length);
						randomWord = words[randomIndex];
						guessedLetters = "";
						System.out.println("If you wish to quit the game, you can type in '1' \nIf you wish to see the rules, you can type in '2' \nIf you wish to restart the current game, you can type in '3' \nIf you wish to play a new game, You can type in '4'\nIf you wish to see the menu, You can type in '5' \n");
						System.out.println("You have a total of " + colorText("'6'", "\u001B[31m") + " attempts to guess the correct word.");
						continue;
					}
					
					//This if statement prints out the main menu as and when the user needs to see it
					else if(userGuess.equals("5")) {
						System.out.println("If you wish to quit the game, you can type in '1' \nIf you wish to see the rules, you can type in '2' \nIf you wish to restart the current game, you can type in '3' \nIf you wish to play a new game, You can type in '4' \nIf you wish to see the menu, You can type in '5' \n");
						continue;
					}
					
					//If the game has ended, and the user still tries to guess, it will give an error as the randomWord is NULL.
					//So, this if statement makes sure the game does'nt break and reminds the player about the end of game and they either need to quit the game or start a new one to continue.
					else if(randomWord == null) {
						System.out.println(name + ", The game has already ended, Please continue with pressing 1/3 as mentioned.");
						continue;
					}
					
					//This if statement makes sure the user does'nt enter a word less than or greater than 5 letters
					else if(userGuess.length() != 5) {
						System.out.println("Sorry " + name + ", the word can be of 5 letter only !");
						System.out.println(colorText("Attempts remaining: ", "\u001B[31m") + attempts);
						continue;
					}
					
					//This is the main area where all the game responses (and not the menu options) are processed and passed through the above described methods
					else {
						
						//declaring a character array which stores the letters of user guessed words in a character array
						char[] userGuessLowerCaseArray = userGuess.toCharArray();
						
						//Declaring a character array which stores the letters of computer selected random word in a character array
						char[] computerRandomWord = randomWord.toCharArray();
						
						//This for each loop checks for the availability of the user guessed letter in a pre-defined empty string and adds the alphabets there if not present
						for(char c : userGuess.toCharArray()) {
							if(!guessedLetters.contains(String.valueOf(c))) {
								guessedLetters +=  c + " ";
							}
						}
										
						//Guessed letters are ran through the method and used alphabets are removed from a string having all the letters of the English alphabets
						String alphabetsRemaining = examineAlphabets(guessedLetters);
						
						//All the guessed words are ran through this method and the letters that are not correct are either marked in asterisk or an underscore converted into a string and stored into this variable
						String wordleLetterChecking = wordMarking(userGuessLowerCaseArray, computerRandomWord);		
												
						//this if statement checks if the userGuess matches with the randomWord selected by the computer and declares the player as a a win game situation
						if(userGuess.matches(randomWord)) {
							System.out.println("You guessed the word correctly, " + colorText("You WON " + name + "!!!", "\u001B[32m"));
							double finalScore = wordleScore(attempts, alphabetsRemaining);
							
							//Printing out the final score of the player depending on how many letters are used in total attempts with a prompt to continue the game or quitting the game
							System.out.println(name + ", Your total Score is: " + finalScore);
							System.out.println(name + " Do you wish to play again ?\n(Please enter 3/1 as yes or no respectively): ");
							
							//After game completion, the guessed letters and randomWord selected by the computer is erased to make way for a new game.
							randomWord = resetRandomWord(randomWord);
							guessedLetters = "";
							continue;
						}
						
						//If the word is not correct or it does not matches the computer selected word, then attempts are decreased one by one until player runs out of attempts
						else {
							attempts = attempts - 1;
							System.out.println(colorText("Attempts remaining: ", "\u001B[31m") + attempts);		
							System.out.println("Your remaining alphabets are: " + colorText(alphabetsRemaining, "\u001B[33m"));
							System.out.println("The letters that you have used: " + colorText(guessedLetters, "\u001B[36m"));	
							System.out.println(name + ", Your results are here: " + wordleLetterChecking);
							
							//This if statement gives out a warning to the players that this is the final attempt and they have to guess it now or they lose.
							if(attempts == 1) {
								System.out.println(name + ", " + colorText("LAST CHANCE !!!", "\u001B[31m"));
								continue;
							}
						}
					}
					
					//This if statement checks for the attempt counter which when 0 automatically clears all the guessed letters, correct letters and resets the randomWord to make way for a new game.
					if(attempts == 0) {
						System.out.println("Game Over! The word was: " + randomWord);
						randomWord = resetRandomWord(randomWord);
						guessedLetters = "";
						System.out.println("Sorry, " + name + " but you're out of attempts. Do you wish to play again though ?(Please enter 3/1 as yes or no respectively)");
						continue;
					}
					
				}
				
				//This is the else statement for the regex expression which prompts the user to enter the words or integers specified in the expression so as to continue further
				else {
					System.out.println("Sorry " + name + ", the guess can only be either a 5 letter word from English alphabets or the numbers that are described for menu.");
				}
				
			}
			
			//This is a part of the try and catch statement declared above which catches any exception that might have occurred during the game session so that the program does not encounter any errors and break all of a sudden
			catch (Exception e) {
				System.out.println("Uhmm... " +name + ", There might be an error... Can you restart the game by pressing 3 or exit by pressing 1, if you want ?");
				continue;
			}
		}
		
		//Closing the scanner to ensure non-leakage of memory
		scanner.close();
	}
}
