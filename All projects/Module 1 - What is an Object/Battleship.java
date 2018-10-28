// This project will help you get more familiar with arrays. You will be recreating the game of
// battleships. A player will place 5 of their ships on a 10 by 10 grid. The computer player will
// deploy five ships on the same grid. Once the game starts the player and computer take turns,
// trying to sink each other's ships by guessing the coordinates to "attack". The game ends when
// either the player or computer has no ships left.


import java.util.Random;
import java.util.Scanner;

public class Battleship {
    public static void main(String args[]){
        // Create the ocean map using a SINGLE 2D array
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println();
        System.out.println("  0123456789");

        String [][]ocean = new String[10][10];

        // Creating the numbers on the grid:
        for (int row = 0; row < ocean.length; row++){
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++){
                if (ocean[row][col] == null){
                    System.out.print(" ");
                } else{
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789");
        System.out.println();

        // Deploy ships:
        Scanner input = new Scanner(System.in);
        int counter = 0;
        while (counter != 5) {
            System.out.print("Enter X coordinate for your ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your ship: ");
            int y = input.nextInt();

            if (x > 9 || x < 0 || y > 9 || y < 0)
                System.out.println("Sorry, but you can’t place ships outside the 10 by 10 grid");
            else if (ocean[x][y] == "1")
                System.out.println("Sorry, but you can NOT place two or more ships on the same location");
            else {
                counter++;
                ocean[x][y] = "1";
            }
        }
        System.out.println("  0123456789");
        for (int row = 0; row < ocean.length; row++){
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++){
                if (ocean[row][col] == null){
                    System.out.print(" ");
                }
                else if(ocean[row][col] == "1"){
                    System.out.print("@");
                }
                else{
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789");
        System.out.println();

        // Deploy computer's ships
        System.out.println("Computer is deploying ships");
        Random rand = new Random();
        counter = 0;
        while (counter != 5){
            int m = rand.nextInt(10);
            int n = rand.nextInt(10);

            if (ocean[m][n] == "1")
                continue;
            else{
                counter++;
                ocean[m][n] = "2";
                System.out.println(counter + ". ship DEPLOYED");
            }
        }
        System.out.println("-----------------------------------------");
        System.out.println();

        // Battle
        int your_ships = 5;
        int computer_ships = 5;
        while (your_ships != 0 && computer_ships != 0) {
            System.out.println("YOUR TURN");
            System.out.print("Enter X coordinate: ");
            int i = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            int j = input.nextInt();
            if (i > 9 || i < 0 || j > 9 || j < 0) {
                System.out.println("Sorry, but the coordinate is is not valid within the Ocean Map");
                continue;
            }
            else if (ocean[i][j] == "1") {
                System.out.println("Oh no, you sunk your own ship :(");
                ocean[i][j] = "3";
                your_ships--;
            } else if (ocean[i][j] == "2") {
                System.out.println("Boom! You sunk the ship!");
                ocean[i][j] = "4";
                computer_ships--;
            } else if (ocean[i][j] == null) {
                System.out.println("Sorry, you missed");
                ocean[i][j] = "5";
            } else {
                System.out.println("Sorry, but the coordinate should haven't been guessed yet");
                continue;
            }

            System.out.println("COMPUTER'S TURN");
            while (true) {
                int a = rand.nextInt(10);
                int b = rand.nextInt(10);

                if (ocean[a][b] == "1") {
                    System.out.println("The Computer sunk one of your ships!");
                    ocean[a][b] = "3";
                    your_ships--;
                    break;
                } else if (ocean[a][b] == "2") {
                    System.out.println("The Computer sunk one of its own ships");
                    ocean[a][b] = "4";
                    computer_ships--;
                    break;
                } else if (ocean[a][b] == null) {
                    System.out.println("Computer missed");
                    ocean[a][b] = "5";
                    break;
                } else
                    continue;
            }
            System.out.println();
            System.out.println("  0123456789");
            for (int row = 0; row < ocean.length; row++){
                System.out.print(row + "|");
                for (int col = 0; col < ocean[row].length; col++){
                    if (ocean[row][col] == null){
                        System.out.print(" ");
                    }
                    else if(ocean[row][col] == "1"){
                        System.out.print("@");
                    }
                    else if (ocean[row][col] == "2"){
                        System.out.print(" ");
                    }
                    else if (ocean[row][col] == "3"){
                        System.out.print("x");
                    }
                    else if (ocean[row][col] == "4"){
                        System.out.print("!");
                    }
                    else if (ocean[row][col] == "5"){
                        System.out.print("-");
                    }
                    else{
                        System.out.print(ocean[row][col]);
                    }
                }
                System.out.println("|" + row);
            }
            System.out.println("  0123456789");
            System.out.println();
            System.out.println("Your ships: " + your_ships + " | Computer ships: " + computer_ships);
            System.out.println("-----------------------------------------");
            System.out.println();
        }
        if (computer_ships == 0)
            System.out.println("Hooray! You win the battle :)");
        else
            System.out.println("Sorry... You lose...");
    }
}
