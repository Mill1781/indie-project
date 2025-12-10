import java.util.*;
import card_class.*;
import card_game.*;

public class hw4 {
    public static void main(String args[]){


        Scanner sca = new Scanner(System.in);
        String command;//user's input
        String saving;////user's input for saving
        Player commander;//the player who is in his own turn
        Player opponent;//the player who is not in his own turn
        Player P1 = new Player(1);//player1
        Player P2 = new Player(2);//player2
        Fightground Arena = new Fightground();//fight area
        boolean game_loop = true;//determine whether players want to keep gaming
        int turn[] = new int[1];//represent whose turn, P1 --> 1, P2 --> 2
        int current_phase = 0;
        Card[] P1_fight_area;
        Card[] P2_fight_area;


        //Execution Flow 1 begins

        Game_command.welcome_and_explain();
        Updator.download_Player_data(args[0], P1, P2, Arena);
        Updator.download_Fightground_data(args[1], Arena);
        if(Updator.save_is_downloaded()){
            game_loop = true;
        }
        else{
            game_loop = false;
        }
        if(game_loop){
        current_phase = Arena.get_Phase();
        }
        //Execution Flow 1 ends

        System.out.println("\n--------------------------------------------------------\n");

        while(game_loop && current_phase <= 10){
            
            current_phase = Arena.get_Phase();



            //Execution Flow 2 begins
            
            P1.assign_pp(current_phase);//replenish pp of P1
            P2.assign_pp(current_phase);//replenish pp of P2
            Game_command.show_game_status(P1, P2, Arena);
            System.out.println("\n--------------------------------------------------------");
            
            //Execution Flow 2 ends


            //Execution Flow 3 , 4 begin
            
            turn[0] = 1;//1 --> turn of P1, 2 --> turn of P2
            while(turn[0] < 3){

                if(turn[0] == 1){
                    commander = P1;
                    System.out.print("Now it is turn of P1, please enter your command:");
                }
                else{
                    commander = P2;
                    System.out.print("Now it is turn of P2, please enter your command:");
                }

                command = sca.nextLine().trim();
                Game_command.command_execute(command, P1, P2, commander, Arena, turn);
            }//Execution Flow 3 , 4

            

            //Execution Flow 5 begins

            System.out.println("\n\nEnter the Skill Phase --------------------------------\n");


            //P1's turn --> activate card's skills
            System.out.println("P1's turn : ");
            P1_fight_area = Arena.get_P1_fight_area();
            P2_fight_area = Arena.get_P2_fight_area();
            commander = P1;
            opponent = P2;

            for(int i = 1; i <= 4; i++){
                for(int j = 0; j < 3; j++){
                    if(P1_fight_area[j] != null){
                        if(P1_fight_area[j].get_action_speed() == i){
                            P1_fight_area[j].skill(P1_fight_area, P2_fight_area, commander, opponent, j + 1);//Polymorphism
                            Arena.stage_update(); //clear dead units
                        }                   
                    }
                }
            }//Execution Flow 5 ends


            //Execution Flow 6 begins

            //P2's turn --> activate card's skills

            commander = P2;
            opponent = P1;
            System.out.println("\n\nP2's turn : ");
            for(int i = 1; i <= 4; i++){
                for(int j = 0; j < 3; j++){
                    if(P2_fight_area[j] != null){
                        if(P2_fight_area[j].get_action_speed() == i){
                            P2_fight_area[j].skill(P2_fight_area, P1_fight_area, commander, opponent, j + 1);
                            Arena.stage_update(); //clear dead units
                        }                   
                    }
                }
            }//Execution Flow 6 ends



            System.out.println("\n--------------------------------------------------------\n");
            Game_command.show_game_status(P1, P2, Arena);



            //Execution Flow 7 begins

            System.out.println("\n\nEnter the Battle Phase --------------------------------\n");


            //P1 turn --> attack
            commander = P1;
            opponent = P2;
            System.out.println("P1's turn : \n");
            for(int i = 0; i < 3; i++){
                if(P1_fight_area[i] != null){
                    if(P2_fight_area[i] != null){
                        System.out.println("area " + (i+1) + " : P1(" + commander.get_name() + ")'s " + P1_fight_area[i] + " attacks");
                        P1_fight_area[i].card_damage(P1_fight_area[i], P2_fight_area[i], commander, opponent);//attack card
                        Arena.stage_update();//clear dead units
                        System.out.println();
                    }
                    else{
                        System.out.println("area " + (i+1) + " : P1(" + commander.get_name() + ")'s " + P1_fight_area[i] + " attacks");
                        P1_fight_area[i].direct_attack(P1_fight_area[i].get_atk(), opponent);//attack player
                        System.out.println();
                    }
                }
            }//Execution Flow 7 ends


            //Execution Flow 8 begins

            //P2 turn --> attack
            commander = P2;
            opponent = P1;
            System.out.println("\n\nP2's turn : \n");
            for(int i = 0; i < 3; i++){
                if(P2_fight_area[i] != null){
                    if(P1_fight_area[i] != null){
                        System.out.println("area " + (i+1) + " : P2(" + commander.get_name() + ")'s " + P2_fight_area[i] + " attacks");
                        P2_fight_area[i].card_damage(P2_fight_area[i], P1_fight_area[i], commander, opponent);//attack card
                        Arena.stage_update(); //clear dead units
                        System.out.println();
                    }
                    else{
                        System.out.println("area " + (i+1) + " : P2(" + commander.get_name() + ")'s " + P2_fight_area[i] + " attacks");
                        P2_fight_area[i].direct_attack(P2_fight_area[i].get_atk(), opponent);//attack player
                        System.out.println();
                    }
                }
            }//Execution Flow 8 ends
            
            System.out.println("\n\n");


            //Execution Flow 9 begins

            //for case that Phase < 10
            if(!P1.is_alive() && !P2.is_alive()){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("Since P1 and P2 runs out of life in the same turn, match ties");
                System.out.println("Match Ties!!\n");
                game_loop = false;
            }
            else if(!P1.is_alive()){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("Since P1 runs out of life, P2 wins this match");
                System.out.println("P2 wins and P1 loses!!\n");
                game_loop = false;
            }
            else if(!P2.is_alive()){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("Since P2 runs out of life, P1 wins this match");
                System.out.println("P1 wins and P2 loses!!\n");
                game_loop = false;
            }
            //for case that Phase == 10
            else if(current_phase == 10){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("Now it is tenth Phase, Game is over");
                game_loop = false;
                if(P1.get_life() > P2.get_life()){
                    System.out.println("Since P1 have more life than P2, P1 wins this match");
                    System.out.println("P1 wins and P2 loses!!\n");
                }
                else if(P1.get_life() < P2.get_life()){
                    System.out.println("Since P2 have more life than P1, P2 wins this match");
                    System.out.println("P2 wins and P1 loses!!\n");
                }
                else{
                    System.out.println("both side's life is same, match ties");
                    System.out.println("Match Ties!!\n");
                }
            
            }//Execution Flow 9 ends


            //Execution Flow 10 begins

            while(true && game_loop){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("save game's progress?(game will close after saving) Y:yes / N:no");
                saving = sca.nextLine();

                if(saving.equals("Y")){
                    current_phase++;
                    Arena.assign_Phase(current_phase);
                    Updator.upload_Fightground_data(P1, P2, Arena);
                    Updator.upload_Player_data(P1, P2, Arena);
                    game_loop = false;
                    System.out.println("\nthank you for playing\n");
                    break;
                }
                else if(saving.equals("N")){
                    current_phase++;
                    Arena.assign_Phase(current_phase);
                    System.out.println("Game continue\n\n");
                    break;
                }
                else{
                    System.out.println("Wrong command, please input Y or N\n");
                }
            }//Execution Flow 10 ends



        }//game loop, if game is matched the loop would break
        sca.close();
    }//main method
}

