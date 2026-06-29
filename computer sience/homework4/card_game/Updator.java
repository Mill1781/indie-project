package card_game;
import card_class.*;
import java.io.*;
import java.util.*;

public class Updator {


    private static boolean Playerdata_download_succeed = false;
    private static boolean Playground_download_succeed = false;

    //return boolean to make ture files have been loaded
    public static boolean save_is_downloaded(){
        if(Playerdata_download_succeed && Playground_download_succeed){
            return true;
        }   
        else
            return false;
    }

    //download current progress from player_info.txt
    public static void download_Player_data(String file_txt, Player P1, Player P2, Fightground Arena){
        
        
        String Phase;
        String name;
        String life;
        String pp;
        String card_amount[] = new String[4];
        Player p;
        
        
        try{
            Scanner player_data = new Scanner(new FileInputStream(file_txt));
            player_data.useDelimiter("[ ,\n\r]+");//spliting comma and newline
            
            
            Phase = player_data.nextLine();//eat first line
            Arena.assign_Phase(Integer.parseInt(Phase));
            
            for(int i = 0; i < 2; i++){

                if(i == 0){
                    p = P1;
                }
                else{
                    p = P2;
                }

                name = player_data.next();//player's name
                life = player_data.next();//player's life
                pp = player_data.next();//player's energy

                for(int k = 0; k < 4; k++){
                    card_amount[k] = player_data.next();//card amount
                }
                if(Integer.parseInt(life) > 1000){
                    p.life = 1000;
                    System.out.println(name + "'s life is overflow, system automatically adjust player's life into default value 1000");
                }
                else{
                    p.life = Integer.parseInt(life);
                }
                p.pp = Integer.parseInt(pp);
                p.player_name = name;
                for(int s = 0; s < 4; s++){
                    p.hand_area[s] = Integer.parseInt(card_amount[s]);
                }

            }
            Playerdata_download_succeed = true;
            System.out.printf("%s and upload success\n", file_txt);
            player_data.close();
        } catch(FileNotFoundException e){
            System.out.println("player_info.txt didn't be found. please make sure to establish player and fight data before executing this program\n");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("player_info.txt didn't be found. please check the typing or the existance of player and fight data\n");
        } catch(Exception e){
            System.out.println("Please check whether the save data is available and being written in right the pattern");
        }
        
    }

    //download current progress from playground.txt
    public static void download_Fightground_data(String file_txt, Fightground arena){
        try{
            Scanner fightground_data = new Scanner(new FileInputStream(file_txt));
            
            
            fightground_data.useDelimiter("[ ,\n\r]+");//spliting comma and newline
            
            int life;
            int atk;
            String card_class;


            fightground_data.next();//skip P1's name
            for(int i = 0; i < 3; i++){
                fightground_data.next();
                card_class = fightground_data.next();
                if(card_class.equals("none")){
                    arena.P1_fight_area[i] = null;
                }
                else{
                    life = Integer.parseInt(fightground_data.next());
                    atk = Integer.parseInt(fightground_data.next());
                    if(card_class.equals("shooter")){
                        arena.P1_fight_area[i] = new shooter(life, atk);
                    }
                    else if(card_class.equals("tank")){
                        arena.P1_fight_area[i] = new tank(life, atk);
                    }
                    else if(card_class.equals("assassin")){
                        arena.P1_fight_area[i] = new assassin(life, atk);
                    }
                    else if(card_class.equals("priest")){
                        arena.P1_fight_area[i] = new priest(life, atk);
                    }
                }
            }
            

            fightground_data.next();//skip P2's name
            for(int i = 0; i < 3; i++){
                fightground_data.next();
                card_class = fightground_data.next();
                if(card_class.equals("none")){
                    arena.P2_fight_area[i] = null;
                }
                else{
                    life = Integer.parseInt(fightground_data.next());
                    atk = Integer.parseInt(fightground_data.next());
                    if(card_class.equals("shooter")){
                        arena.P2_fight_area[i] = new shooter(life, atk);
                    }
                    else if(card_class.equals("tank")){
                        arena.P2_fight_area[i] = new tank(life, atk);
                    }
                    else if(card_class.equals("assassin")){
                        arena.P2_fight_area[i] = new assassin(life, atk);
                    }
                    else if(card_class.equals("priest")){
                        arena.P2_fight_area[i] = new priest(life, atk);
                    }
                }
            }
            Playground_download_succeed = true;
            System.out.printf("%s and upload success\n", file_txt);
            fightground_data.close();
            

        } catch(FileNotFoundException e){
            System.out.println("playground.txt didn't be found. please make sure to establish player and fight data before executing this program\n");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("playground.txt didn't be found. please check the typing or the existance of player and fight data\n");
        } catch(Exception e){
            System.out.println("Please check whether the save data is available and being written in right the pattern");
        }
    }

    //upload current progress into player_info .txt
    public static void upload_Player_data(Player P1, Player P2, Fightground Arena){
        int Phase = Arena.get_Phase();
        String P1_info = P1.get_name() + "," + P1.get_life() + "," + P1.get_pp();
        int[] P1_card = P1.get_hand_area();
        String P2_info = P2.get_name() + "," + P2.get_life() + "," + P2.get_pp();
        int[] P2_card = P2.get_hand_area();
        try{
            PrintWriter output_file = new PrintWriter(new FileOutputStream("player_info.txt", false));
            output_file.println(Phase);
            output_file.println(P1_info);
            for(int i = 0; i < 3; i++){
                output_file.print(P1_card[i] + ",");
            }
            output_file.println(P1_card[3]);

            output_file.println(P2_info);
            for(int i = 0; i < 3; i++){
                output_file.print(P2_card[i] + ",");
            }
            output_file.println(P2_card[3]);
            output_file.flush();
            System.out.println("\nplayer_info.txt is saved\n");
            output_file.close();
        } catch(FileNotFoundException e){
            System.out.println("\nunexpected error, file is missing, player_info.txt saving fail\n");
        }
    }

    //upload current progress from into playground.txt
    public static void upload_Fightground_data(Player P1, Player P2, Fightground arena){
        String P1_name = P1.get_name();
        String P2_name = P2.get_name();
        try{
            PrintWriter output_file = new PrintWriter(new FileOutputStream("playground.txt", false));
            
            output_file.println(P1_name);
            for(int i = 0; i < 3; i++){
                output_file.print( (i+1) + ",");
                if(arena.P1_fight_area[i] == null){
                    output_file.println("none");
                }
                else{
                    output_file.println(arena.P1_fight_area[i] + "," + arena.P1_fight_area[i].get_life() + "," + arena.P1_fight_area[i].get_atk());
                }
            }

            output_file.println(P2_name);
            for(int i = 0; i < 3; i++){
                output_file.print( (i+1) + ",");
                if(arena.P2_fight_area[i] == null){
                    output_file.println("none");
                }
                else{
                    output_file.println(arena.P2_fight_area[i] + "," + arena.P2_fight_area[i].get_life() + "," + arena.P2_fight_area[i].get_atk());
                }
            }
            output_file.flush();
            System.out.println("\nplayground.txt is saved\n");
            output_file.close();
        } catch(FileNotFoundException e){
            System.out.println("\nunexpected error, file is missing, playground.txt saving fail\n");
        }
    }
    
}
