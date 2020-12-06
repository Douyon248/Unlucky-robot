/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unluckyrobot;

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author douyo
 */
public class UnluckyRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int x = 0;
        int y = 0; 
        int itrCount = 0;
        int totalScore = 300;
        int reward;
        String str = "";
        
        do{
        char direction = inputDirection();
        switch (direction) {
            case 'u':
                y++;
                break;
            case 'd':
                y--;
                break;
            case 'r':
                x--;
                break;
            case 'l':
                x++;
                break;
            default:
                break;
        }        
        if (doesExceed(x, y, direction)){
            totalScore = (totalScore - 2000);
        }
        displayinfo(x, y, itrCount, totalScore);
        reward = reward();
        totalScore = (totalScore + punishOrMercy(direction, reward));
        itrCount++;
    } while (isGameOver(x, y, totalScore, itrCount) == false);
        System.out.println(toTitleCase(str));
        evaluation(totalScore);
        System.out.println(itrCount);
   
    }  
    
    
        
    /**
     * To determine the location of the robot
     * @param x current x coordinate for robot
     * @param y current y coordinate for robot
     * @param itrCount number of step the robot take
     * @param totalScore the total score
     */
    public static void displayinfo(int x, int y, int itrCount, int totalScore) {
        System.out.printf("At point (X = %d, Y = %d) at %d  iterations, "
                + "the current score is: %d\n", x, y, itrCount, totalScore);
    }
    /**
     * to determine if robot exit grid
     * @param x current x coordinate for robot
     * @param y current y coordinate for robot
     * @param direction the direction the robot is going
     * @return the value of x and y base on the direction
     */
    public static boolean doesExceed(int x, int y, char direction){
        return (y > 4 && Character.toLowerCase(direction) == 'u' || x < 0 
                && Character.toLowerCase(direction) == 'l' || y < 0 
                && Character.toLowerCase(direction) == 'd' || x > 4
                && Character.toLowerCase(direction) == 'r');
        
    }
    /**
     * when the robot makes a move and ends up in a cell
     * @return number of rewards of punishment of entering that cell.
     */
    public static int reward(){
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        rand.nextInt();

        switch(dice) {
           case '1':    
              System.out.println("Dice: 1, reward: -100");
              return -100;
           case '2':   
              System.out.println("Dice: 2, reward: -200");
             return -200;
           case '3':   
              System.out.println("Dice: 3, reward: -300");
              return -300;
           case '4':  
              System.out.println("Dice: 4, reward: 300");
              return 300;
           case '5':  
              System.out.println("Dice: 5, reward: 400");
              return 400;
           case '6':  
              System.out.println("Dice: 6, reward: 600");
              return 600;   
           
        }
        return dice;
}
   /**
    * int value which will be the value of 0 or the initial reward
    * @param direction the robot only goes up, down, right, left
    * @param reward coin flip either head or tail
    * @return the initial reward with or without punishment
    */ 
    public static int punishOrMercy(char direction, int reward){
        if (reward < 0 && direction == 'u'){
            Random dice = new Random();
            int coin = dice.nextInt(2);
            if (coin == 0){
                reward = 0;
                System.out.println("Coin: tail | Mercy, the negative reward was removed");
            } else {
                System.out.println("Coin: head | No Mercy, the negative reward was applied");
            }    
            
        }
        return reward;
    }
    /**
     * string that contains only two words
     * @param str two words 
     * @return two words separated by space
     */
    public static String toTitleCase(String str){
         char c = str.charAt(0);
         String str1 = str.substring(1, str.indexOf(' ')+ 2);
         String str2 = str.substring(str.indexOf(' '), str.indexOf(' ') + 2);
         char c1 = str2.charAt(1);
         String str3 = str.substring(str.indexOf(' ') + 2, str.length());
         String str4 = Character.toTitleCase(c) + str.toLowerCase() + 
                 Character.toLowerCase(c) + str3.toLowerCase();
         return str4;
        
    }
    /**
     * the input the total score of the robot
     * @param totalScore the score of the robot
     */
    public static void evaluation(int totalScore){
        String str = null;
           if (totalScore >= 2000)
               System.out.println("Victory," + toTitleCase(str) + "your score is" 
                       + evaluation(totalScore));
         
        }
    /**
     * the direction of the robot
     * @return up, down, right, left
     */
    public static char inputDirection(){
        char direction = 0;
        int y = 0;
        int x = 0;
        switch (direction) {
            case 'u':
                y++;
                break;
            case 'd':
                y--;
                break;
            case 'r':
                x--;
                break;
            case 'l':
                x++;
                break;
            default:
                break;
        }
        
        Scanner console = new Scanner(System.in);
      
        do {         
            System.out.println("Please input a vaild direction: ");
            direction = console.next().charAt(0);
        } while (!inputDirection(direction));
        
        return direction;
       
        
    }
    /**
     * to check if the game is over
     * @param x the current x position of robot
     * @param y the current y position of robot
     * @param totalScore the total of the robot
     * @param itrCount number of steps the robot made
     * @return total score of the robot
     */
    public static boolean isGameOver(int x, int y, int totalScore, int itrCount){
        return itrCount > 20|| totalScore < -1000|| totalScore <= 2000|| 
                x == 4 && y == 4|| x == 4 && y == 0;
         
    }
   
               
   

    private static boolean inputDirection(char direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
