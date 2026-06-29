import java.util.Scanner;
public class hw1 {
    public static void main(String arg[]){
        double sum = 0;//sum of the surcharge
        boolean loop = true;//to check whether you want to quit in step7
        Scanner sca = new Scanner(System.in);// for input
        //Step1
        System.out.println("Welcome to trading system.\n you can use this service to trade Virtual Currency and calculate the surcharge, please follow the instruction to smoothly complete your need");
        while(loop){//the loop between step2 and step6
            //Step2
            int c1;//an integer to check whether survice you desire
            System.out.print("Trade:1 / Calculate surcharge:2\nPlease press the coresponding button to choose your service:");
            do{
            c1 = sca.nextInt();//receive number
                if(c1 > 2 || c1 < 1){//if the input doesn't be qualified, jump error message
                    System.out.print("wrong type, please try again:");
                }
            }while(c1 > 2 || c1 < 1);//if the input doesn't be qualified, run step2 again
            if(c1 == 1){//if user chooses survice1, execute step3 to step5
                //Step3
                int c2;//an integer to decide the coin type
                double coin = 0;//the value of specific coin type
                System.out.print("1:BTC  2.ETH  3.USDT  4.USDC  5.SOL  6.DOGE  7.XRP\n");   
                System.out.print("Please choose you disirable coin:");
                do{
                c2 = sca.nextInt();
                    if(c2 > 7 || c2 < 1){
                        System.out.print("wrong type, please try again:");
                    }// wrong input and jump the message above
                    else{
                        switch (c2) {
                            case 1:
                                coin = 103000.09;
                                break;
                            case 2:
                                coin = 2600.93;
                                break;
                            case 3:
                                coin = 1;
                                break;
                            case 4:
                                coin = 10;
                                break;
                            case 5:
                                coin = 147.32;
                                break;
                            case 6:
                                coin = 0.17;
                                break;
                            case 7:
                                coin = 2.13;
                                break;
                        }//give the value to the coin, the value should be always reset for every run

                    }
                }while((c2 > 7 || c2 < 1));

                //step4
                double c4;//coin's number the user want
                    do{
                    System.out.print("How many you want to buy:");
                    c4 = sca.nextDouble();
                    sca.nextLine();
                    if(!(c4%1 == 0)){
                        System.out.print("try again:");
                    }//if you type a number which is not integer, the value0 c4%1 would not equal to 0, so it is wrong input
                    }while (!(c4%1 == 0));
                
                
                
                //step5
                    double surcharge = coin*c4*0.1;// surcharge for this time's calculation
                    surcharge = Math.round((surcharge*1000))/1000;//to round the number
                    sum += surcharge;//calculate the surcharge for this run and the value would not reset every time
                    System.out.printf("The surcharge is : %.3f\n", surcharge);
 
            }
            else{
                sca.nextLine();//to make sure enter is excluded so the method (sca.nextLine().charAt(0)) would not be skipped
            }

            //step6
            if(c1 == 2)//if user chooses service 2, go to step6 and run this code
                System.out.printf("sum of the charge is %.3f dollers\n", sum);
            //step7
            char c7;//a char to dicide whether you want to continue by receiving y/n
            System.out.print("continue trading?(y/n):");
             do{
            c7 = sca.nextLine().charAt(0);
                if(!(c7 == 'y' || c7 == 'n')){
                    System.out.print("wrong type, please try again:");
                }//check the input
                if(c7 == 'n'){
                    loop = false;//if user types n, end the loop, if he doesn't, the loop would remain ture and keep running
                    System.out.println("Thank you for using our system, have a nice day.");
                }
            }while(!(c7 == 'y' || c7 == 'n'));   
        }
    }
}
