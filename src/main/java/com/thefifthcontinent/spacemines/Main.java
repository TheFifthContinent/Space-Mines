package com.thefifthcontinent.spacemines;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        int year = 2375;
        int turn = 1;

        int mines = rand.nextInt(3) + 5;
        int population = rand.nextInt(60) + 40;
        int money = rand.nextInt(50) + 10;
        money *= population;
        int foodPrice = rand.nextInt(40) + 80;
        int oreProduced = rand.nextInt(40) + 80;
        int oreStored = 0;
        double satisfactionFactor = 1;
        int minePrice = rand.nextInt(2000) + 2000;
        int orePrice = rand.nextInt(12) + 7;

        boolean victory = true;

        do {

            if (satisfactionFactor < 0.5) {
                System.out.println("The people revolt and chuck you out of the airlock");
                victory = false;
                break;
            }

            if ((population / mines) < 10) {
                System.out.println("You've overworked everybody");
                victory = false;
                break;
            }

            if (population < 30) {
                System.out.println("There are not enough people left to run the mines");
                victory = false;
                break;
            }

            System.out.println("Year: " + (year + turn));
            System.out.println("\nPeople: " + population);
            System.out.println("Mines: " + mines);
            System.out.println("Money: " + money + " Quatloos");
            System.out.println("Production: " + oreProduced + "per mine");
            System.out.println("\nSatisfaction factor: " + satisfactionFactor);

            oreStored += oreProduced * mines;

            System.out.println("\nSelling Ore");
            System.out.println("Ore in stock: " + oreStored + " tonnes");
            System.out.println("Ore selling price: " + orePrice + " Quatloos");
            int oreToSell = -1;

            do {
                System.out.print("How much ore to sell: ");
                oreToSell = input.nextInt();
            } while(oreToSell < 0 || oreToSell > oreStored);

            oreStored -= oreToSell;
            money += oreToSell * orePrice;

            System.out.println("\nSelling Mines");
            System.out.println("Mines available: " + mines);
            System.out.println("Mine selling price: " + minePrice + " Quatloos");
            int minesToSell = -1;

            do {
                System.out.print("How many mines to sell: ");
                minesToSell = input.nextInt();
            } while(minesToSell < 0 || minesToSell > mines);

            mines -= minesToSell;
            money += minesToSell * minePrice;

            System.out.println("\nBuying food");
            System.out.println("\nMoney available: " + money + " Quatloos");
            System.out.println("Food price: " + foodPrice + " Quatloos");
            int foodToBuy = -1;
            int total;

            do {
                System.out.print("How much food to buy: ");
                foodToBuy = input.nextInt();
                total = foodToBuy * foodPrice;
            } while(foodToBuy < 0 || total > money);

            if (foodToBuy / population > 120) {
                satisfactionFactor += 0.1;
            }

            if (foodToBuy / population < 80) {
                satisfactionFactor -= 0.2;
            }

            money -= foodToBuy * foodPrice;

            System.out.println("\nBuying mines");
            System.out.println("\nMoney available: " + money + " Quatloos");
            System.out.println("Mine price: " + minePrice + " Quatloos");
            int minesToBuy = -1;

            do {
                System.out.print("How many mines to buy: ");
                minesToBuy = input.nextInt();
                total = minesToBuy * minePrice;
            } while(minesToBuy < 0 || total > money);

            mines += minesToBuy;
            money -= total;

            if (satisfactionFactor > 1.1) {
                oreProduced += rand.nextInt(20) + 1;
                population += rand.nextInt(10) + 1;
            }

            if (satisfactionFactor < 0.8) {
                oreProduced -= rand.nextInt(20) + 1;
                population -= rand.nextInt(10) + 1;
            }

            if (rand.nextInt(100) <= 1) {
                System.out.println("Radiation leak! Many are killed");
                population /= 2;
            }

            if (oreProduced > 150) {
                System.out.println("Market glut. Price falls");
                orePrice /= 2;
            }

            System.out.println("\n\n");


            turn ++;

        } while(turn <= 10);

        if (victory) {
            System.out.println("Well done, you survived your term in office");
        }
    }

}
