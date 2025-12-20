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
        String card_amount[] = new String[6];
        int equipment_amount[] = new int[6];
        int potion_amount[] = new int[3];
        Player p;
        int hand_area[];
        ArrayList<Equipment> hand_area_equipments;
        ArrayList<Potion> hand_area_potions;
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

                hand_area = p.get_hand_area();
                hand_area_equipments = p.get_hand_area_equipments();
                hand_area_potions = p.get_hand_area_potions();
                name = player_data.next();//player's name
                life = player_data.next();//player's life
                pp = player_data.next();//player's energy

                for(int k = 0; k < 6; k++){
                    card_amount[k] = player_data.next();//card amount
                }

                for(int k = 0; k < 6; k++){
                    equipment_amount[k] = Integer.parseInt(player_data.next());//equipment amount
                }

                for(int k = 0; k < 3; k++){
                    potion_amount[k] = Integer.parseInt(player_data.next());//potion amount
                }


                if(Integer.parseInt(life) > 1000){
                    p.assign_life(1000);
                    System.out.println(name + "'s life is overflow, system automatically adjust player's life into default value 1000");
                }
                else{
                    p.assign_life(Integer.parseInt(life));
                }
                p.assign_pp(Integer.parseInt(pp));
                p.assign_player_name(name);
                for(int s = 0; s < 6; s++){
                    hand_area[s] = Integer.parseInt(card_amount[s]);
                }
                push_equipment(hand_area_equipments, equipment_amount);
                push_potion(hand_area_potions, potion_amount);
            }
            Playerdata_download_succeed = true;
            System.out.printf("%s and upload success\n", file_txt);
            player_data.close();
        } catch(FileNotFoundException e){
            System.out.println("player_info.txt didn't be found. please make sure to establish player and fight data before executing this program\n");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Score.txt didn't be found. please check the typing or the existance of player and fight data\n");
        } catch(Exception e){
            System.out.println("Please check whether the save data is available and being written in right the pattern");
        }
        
    }

    //download current progress from playground.txt
    public static void download_Fightground_data(String file_txt, Fightground arena){
        Card P1_fight_area[] = arena.get_P1_fight_area();
        Card P2_fight_area[] = arena.get_P2_fight_area();

        try{
            Scanner fightground_data = new Scanner(new FileInputStream(file_txt));
            
            
            fightground_data.useDelimiter("[ ,\n\r]+");//spliting comma and newline
            
            Equipment equipment;
            Potion potion;
            int life;
            int atk;
            int defense;
            String card_class;
            String temp;

            fightground_data.next();//skip P1's name
            fightground_data.next();
            for(int i = 0; i < 3; i++){
                card_class = fightground_data.next();
                if(card_class.equals("none")){
                    P1_fight_area[i] = null;
                    fightground_data.next();
                }
                else{
                    life = Integer.parseInt(fightground_data.next());
                    atk = Integer.parseInt(fightground_data.next());
                    defense = Integer.parseInt(fightground_data.next());
                    if(card_class.equals("shooter")){
                        P1_fight_area[i] = new shooter(life, atk, defense);
                    }
                    else if(card_class.equals("tank")){
                        P1_fight_area[i] = new tank(life, atk, defense);
                        
                    }
                    else if(card_class.equals("assassin")){
                        P1_fight_area[i] = new assassin(life, atk, defense);
                    }
                    else if(card_class.equals("priest")){
                        P1_fight_area[i] = new priest(life, atk, defense);
                    }
                    temp = fightground_data.next();
                    if(Game_command.is_equipment(temp)){
                        equipment = find_equipment(temp);
                        P1_fight_area[i].put_equipment_form_save(equipment);

                        temp = fightground_data.next();
                        if(Game_command.is_potion(temp)){
                            potion = find_potion(temp);
                            P1_fight_area[i].comsume_potion_from_save(potion);
                            fightground_data.next();
                            fightground_data.next(); 
                            
                        }
                    }
                    else if(Game_command.is_potion(temp)){
                        potion = find_potion(temp);
                        P1_fight_area[i].comsume_potion_from_save(potion);
                        fightground_data.next();
                        fightground_data.next();
                        
                    }
                }
            }
            
            fightground_data.next();
            for(int i = 0; i < 3; i++){
                card_class = fightground_data.next();
                if(card_class.equals("none")){
                    P2_fight_area[i] = null;
                    fightground_data.next();
                }
                else{
                    life = Integer.parseInt(fightground_data.next());
                    atk = Integer.parseInt(fightground_data.next());
                    defense = Integer.parseInt(fightground_data.next());
                    if(card_class.equals("shooter")){
                        P2_fight_area[i] = new shooter(life, atk, defense);
                    }
                    else if(card_class.equals("tank")){
                        P2_fight_area[i] = new tank(life, atk, defense);
                    }
                    else if(card_class.equals("assassin")){
                        P2_fight_area[i] = new assassin(life, atk, defense);
                    }
                    else if(card_class.equals("priest")){
                        P2_fight_area[i] = new priest(life, atk, defense);
                    }
                    try{
                        temp = fightground_data.next();
                        if(Game_command.is_equipment(temp)){
                            equipment = find_equipment(temp);
                            P2_fight_area[i].put_equipment_form_save(equipment);
                            temp = fightground_data.next();
                            if(Game_command.is_potion(temp)){
                                potion = find_potion(temp);
                                P2_fight_area[i].comsume_potion_from_save(potion);
                                fightground_data.next();
                                fightground_data.next();

                            }
                        }
                        else if(Game_command.is_potion(temp)){
                        potion = find_potion(temp);
                        P2_fight_area[i].comsume_potion_from_save(potion);
                        fightground_data.next();
                        fightground_data.next();

                    }
                    }catch(NoSuchElementException e){
                        //System.out.println("this is fine");
                    }


                    
                }
            }
            Playground_download_succeed = true;
            System.out.printf("%s and upload success\n", file_txt);
            fightground_data.close();
            

        } catch(FileNotFoundException e){
            System.out.println("Score.txt didn't be found. please make sure to establish player and fight data before executing this program\n");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Score.txt didn't be found. please check the typing or the existance of player and fight data\n");
        } catch(Exception e){
            e.printStackTrace();
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

        int[] P1_equipment_amount = {0, 0, 0, 0, 0, 0};
        int[] P1_potion_amount = {0, 0, 0};
        int[] P2_equipment_amount = {0, 0, 0, 0, 0, 0};
        int[] P2_potion_amount = {0, 0, 0};
        compress_equipment_into_int_list(P1_equipment_amount, P1);
        compress_equipment_into_int_list(P2_equipment_amount, P2);
        compress_potion_into_int_list(P1_potion_amount, P1);
        compress_potion_into_int_list(P2_potion_amount, P2);


        try{
            PrintWriter output_file = new PrintWriter(new FileOutputStream("player_info.txt", false));
            output_file.println(Phase);

            //P1 output
            output_file.println(P1_info);
            for(int i = 0; i < 5; i++){
                output_file.print(P1_card[i] + ",");
            }
            output_file.println(P1_card[5]);

            for(int i = 0; i < 5; i++){
                output_file.print(P1_equipment_amount[i] + ",");
            }
            output_file.println(P1_equipment_amount[5]);

            for(int i = 0; i < 2; i++){
                output_file.print(P1_potion_amount[i] + ",");
            }
            output_file.println(P1_potion_amount[2]);


            //P2 output
            output_file.println(P2_info);
            for(int i = 0; i < 5; i++){
                output_file.print(P2_card[i] + ",");
            }
            output_file.println(P2_card[5]);

            for(int i = 0; i < 5; i++){
                output_file.print(P2_equipment_amount[i] + ",");
            }
            output_file.println(P2_equipment_amount[5]);

            for(int i = 0; i < 2; i++){
                output_file.print(P2_potion_amount[i] + ",");
            }
            output_file.println(P2_potion_amount[2]);
            output_file.flush();
            System.out.println("\nplayer_info.txt is saved\n");
            output_file.close();
        } catch(FileNotFoundException e){
            System.out.println("\nunexpected error, file is missing, player_info.txt saving fail\n");
        }
    }

    //upload current progress from into playground.txt
    public static void upload_Fightground_data(Player P1, Player P2, Fightground arena){
        Card P1_fight_area[] = arena.get_P1_fight_area();
        Card P2_fight_area[] = arena.get_P2_fight_area();
        String P1_name = P1.get_name();
        String P2_name = P2.get_name();
        try{
            PrintWriter output_file = new PrintWriter(new FileOutputStream("playground.txt", false));
            
            output_file.println(P1_name);
            for(int i = 0; i < 3; i++){
                output_file.print( (i+1) + ",");
                if(P1_fight_area[i] == null){
                    output_file.println("none");
                }
                else{
                    output_file.print(P1_fight_area[i] + "," + P1_fight_area[i].get_life() + "," + P1_fight_area[i].get_atk() + "," + P1_fight_area[i].get_defense());
                    if(P1_fight_area[i].get_equipment() != null){
                        output_file.print("," + P1_fight_area[i].get_equipment());
                    }
                    if(P1_fight_area[i].get_potion() != null){
                        output_file.print("," + P1_fight_area[i].get_potion());
                    }
                    output_file.println();
                }

            }

            output_file.println(P2_name);
            for(int i = 0; i < 3; i++){
                output_file.print( (i+1) + ",");
                if(P2_fight_area[i] == null){
                    output_file.println("none");
                }
                else{
                    output_file.println(P2_fight_area[i] + "," + P2_fight_area[i].get_life() + "," + P2_fight_area[i].get_atk() + "," + P2_fight_area[i].get_defense());
                    if(P2_fight_area[i].get_equipment() != null){
                        output_file.print("," + P2_fight_area[i].get_equipment());
                    }
                    if(P2_fight_area[i].get_potion() != null){
                        output_file.print("," + P2_fight_area[i].get_potion());
                    }
                    output_file.println();
                }
            }
            output_file.flush();
            System.out.println("\nplayground.txt is saved\n");
            output_file.close();
        } catch(FileNotFoundException e){
            System.out.println("\nunexpected error, file is missing, playground.txt saving fail\n");
        }
    }
    

    private static void push_equipment(ArrayList<Equipment> hand_area, int[] equipment_amount){
        //[0]-->armor, [1]-->hat, [2]-->knife, [3]-->mask, [4]-->force, [5]-->shield
        for(int i = 0; i < equipment_amount.length; i++){

            if(equipment_amount[i] > 0){
                if(i == 0){
                    hand_area.add(new armor());
                    equipment_amount[i]--;
                }
                else if (i == 1){
                    hand_area.add(new hat());
                    equipment_amount[i]--;
                }
                else if (i == 2){
                    hand_area.add(new knife());
                    equipment_amount[i]--;
                }
                else if (i == 3){
                    hand_area.add(new mask());
                    equipment_amount[i]--;
                }
                else if (i == 4){
                    hand_area.add(new force());
                    equipment_amount[i]--;
                }
                else if (i == 5){
                    hand_area.add(new shield());
                    equipment_amount[i]--;
                }
                i--;
            }
        }
    }

    public static void compress_equipment_into_int_list(int[] equipments, Player player){
        ArrayList<Equipment> eq_pool = player.get_hand_area_equipments();

        for(int i = 0; i < eq_pool.size(); i++){
            
            if(eq_pool.get(i).toString().equals("armor")){
                equipments[0]++;
            }
            else if(eq_pool.get(i).toString().equals("hat")){
                equipments[1]++;
            }
            else if(eq_pool.get(i).toString().equals("knife")){
                equipments[2]++;
            }
            else if(eq_pool.get(i).toString().equals("mask")){
                equipments[3]++;
            }
            else if(eq_pool.get(i).toString().equals("force")){
                equipments[4]++;
            }
            else if(eq_pool.get(i).toString().equals("shield")){
                equipments[5]++;
            }
        }
    }



    public static void push_potion(ArrayList<Potion> hand_area, int[] potion_amount){

        for(int i = 0; i < potion_amount.length; i++){
            //[0]-->healing potion, [1]-->damage_potion, [2]-->defense potion
            if(potion_amount[i] > 0){
                if(i == 0){
                    hand_area.add(new healing_potion());
                    potion_amount[i]--;
                }
                else if (i == 1){
                    hand_area.add(new damage_potion());
                    potion_amount[i]--;
                }
                else if (i == 2){
                    hand_area.add(new defense_potion());
                    potion_amount[i]--;
                }
                i--;
            }
        }
    }

    public static void compress_potion_into_int_list(int[] potion, Player player){
        ArrayList<Potion> eq_pool = player.get_hand_area_potions();

        for(int i = 0; i < eq_pool.size(); i++){
            if(eq_pool.get(i).toString().equals("healing")){
                potion[0]++;
            }
            else if(eq_pool.get(i).toString().equals("damage")){
                potion[1]++;
            }
            else if(eq_pool.get(i).toString().equals("defense")){
                potion[2]++;
            }
        }
    }


    public static Equipment find_equipment(String name){
        if(name.equals("armor")){
            return new armor();
        }
        else if(name.equals("hat")){
            return new hat();
        }
        else if(name.equals("knife")){
            return new knife();
        }
        else if(name.equals("mask")){
            return new mask();
        }
        else if(name.equals("force")){
            return new force();
        }
        else if(name.equals("shield")){
            return new shield();
        }
        return null;
    }

    public static Potion find_potion(String name){
        if(name.equals("damage")){
            return new damage_potion();
        }
        else if(name.equals("defense")){
            return new defense_potion();
        }
        return null;
    }



    

}


