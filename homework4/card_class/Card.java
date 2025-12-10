package card_class;
import card_game.*;

public class Card {
    protected int Max_life;//crad's default max life
    protected int life;//card's life
    protected int atk;//card's atk
    protected int Action_speed;//priority of skill
    
    //null skill
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){

    }

    //assign card's life
    public void assign_life(int current_life){
        life = current_life;
    }
    
    //card attacks player
    public void direct_attack(int damage, Player opponent){
        opponent.assign_life(opponent.get_life()-damage);
        System.out.println("P" + opponent.get_id() + " is directly damaged by " + toString() + " with " + damage + " aNnd has " + opponent.get_life() + " life points left");
    }

    //card attacks card
    public void card_damage(Card attack_Card,Card defense_Card, Player commander, Player opponent){
        int cuurent_life;//defense_Card's current life
        cuurent_life = defense_Card.get_life() - attack_Card.get_atk();
        defense_Card.assign_life(cuurent_life);
        System.out.println( "P"+ commander.get_id() + "'s " + toString() + " attacks P" + opponent.get_id() + "'s " + defense_Card + " and causes " + attack_Card.get_atk() + " damage");
        System.out.println(defense_Card + " has " + defense_Card.get_life() + " left");
        if(defense_Card.get_life() <= 0){
            System.out.println(defense_Card + " is dead");
        }
    }

    //summon unit to the fightground
    public static void summon(String card_class, int position, Player player, Fightground Arena){
        //hand_area[0]-->priest, [1]-->tank, [2]-->shooter, [3]-->assassin
        int card_pp_consume;
        int card_amount;
        boolean position_is_null;
        boolean pp_is_enough;
        boolean card_amount_is_enough;
        Card[] Fightg_area;
        Card summon_card;//deposit the instantiated card object
        int summon_card_id;//[0]-->priest, [1]-->tank, [2]-->shooter, [3]-->assassin
        int[] hand_area = player.get_hand_area(); 
        if(player.get_id() == 1){
            Fightg_area = Arena.get_P1_fight_area();
        }
        else{
            Fightg_area = Arena.get_P2_fight_area();
        }
            
        if(card_class.equals("priest")){
            card_pp_consume = priest.pp_consume;
            card_amount = hand_area[0];
            summon_card = new priest();
            summon_card_id = 0;
        }
        else if(card_class.equals("tank")){
            card_pp_consume = tank.pp_consume;
            card_amount = hand_area[1];
            summon_card = new tank();
            summon_card_id = 1;
        }
        else if(card_class.equals("shooter")){
            card_pp_consume = shooter.pp_consume;
            card_amount = hand_area[2];
            summon_card = new shooter();
            summon_card_id = 2;
        }
        else if(card_class.equals("assassin")){
            card_pp_consume = assassin.pp_consume;
            card_amount = hand_area[3];
            summon_card = new assassin();
            summon_card_id = 3;
        }
        else{
            System.out.println("command failed, this class is not existing in this game");
            return;
        }

        if(player.get_pp() >= card_pp_consume){
            pp_is_enough = true;
        }
        else{
            System.out.println("command failed, your energy is not enough");
            return;
        }

        if(Fightg_area[position - 1] == null){
            position_is_null = true;
        }
        else{
            System.out.println("command failed, this position is occupied");
            return;
        }

        if(card_amount > 0){
            card_amount_is_enough = true;
        }
        else{
            System.out.println("command failed, you don't have any cards of this class");
            return;
        }

        if(pp_is_enough && position_is_null && card_amount_is_enough){
            System.out.println("command successed, unit is appointed");
            Fightg_area[position - 1] = summon_card;//assign card
            player.assign_pp(player.get_pp() - card_pp_consume);
            hand_area[summon_card_id]--;
        }
        
    }

    //return card's action spped
    public int get_action_speed(){
        return Action_speed;
    }
    
    //return card's atk
    public int get_atk(){
        return atk;
    }
    
    //return card's life
    public int get_life(){
        return life;
    }
    

}
