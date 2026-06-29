
public class Card {
    private int Max_life;//crad's default max life
    private int life;//card's life
    private int atk;//card's atk
    private int Action_speed;//priority of skill
    private int defense;
    private Equipment equipment;
    private Potion potion;
    private static String name_pool[] = {"businessman", "tank", "priest", "shooter", "bomber", "assassin"};
    

    public Card(int MAX_LIFE, int LIFE, int ATK, int ACTION_SPEED, int DEFENSE){
        Max_life = MAX_LIFE;
        life = LIFE;
        atk = ATK;
        Action_speed = ACTION_SPEED;
		defense = DEFENSE;
		
    }


    //null skill, for override
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){

    }

    //assign card's life
    public void assign_life(int current_life){
        life = current_life;
    }
    
    //card attacks player
    public void direct_attack(int damage, Player opponent){
        opponent.assign_life(opponent.get_life()-damage);
        System.out.println("P" + opponent.get_id() + " is directly damaged by " + toString() + " with " + damage + " and has " + opponent.get_life() + " life points left");
    }

    //card attacks card
    public void card_damage(Card attack_Card,Card defense_Card, Player commander, Player opponent){
        int cuurent_life;//defense_Card's current life
        int sum_damage;
        if(attack_Card.get_atk() - defense_Card.get_defense() > 0){
            sum_damage = attack_Card.get_atk() - defense_Card.get_defense();
        }
        else{
            sum_damage = 0;
        }
        cuurent_life = defense_Card.get_life() - sum_damage;
        defense_Card.assign_life(cuurent_life);
        System.out.println( "P"+ commander.get_id() + "'s " + toString() + "(Atk: " + get_atk() + ") attacks P" + opponent.get_id() + "'s " + defense_Card + "(Defense: " + defense_Card.get_defense() + ") and causes " + sum_damage + " damage");
        System.out.println(defense_Card + " has " + defense_Card.get_life() + " left");
        
    }

    //summon unit to the fightground
    public static void summon(String card_class, int position, Player player, Fightground Arena){
        //hand_area[0]-->bomber, [1]-->priest, [2]-->tank, [3]-->shooter, [4]-->assassin, [5]-->businessman
        int card_pp_consume;
        int card_amount;
        boolean position_is_null;
        boolean pp_is_enough;
        boolean card_amount_is_enough;
        Card[] Fightg_area;
        Card summon_card;//deposit the instantiated card object
        int summon_card_id;//[0]-->bomber, [1]-->priest, [2]-->tank, [3]-->shooter, [4]-->assassin, [5]-->businessman
        int[] hand_area = player.get_hand_area(); 
        if(player.get_id() == 1){
            Fightg_area = Arena.get_P1_fight_area();
        }
        else{
            Fightg_area = Arena.get_P2_fight_area();
        }
            
        if(card_class.equals("bomber")){
            card_pp_consume = bomber.get_pp_consume();
            card_amount = hand_area[0];
            summon_card = new bomber();
            summon_card_id = 0;
        }
        else if(card_class.equals("priest")){
            card_pp_consume = priest.get_pp_consume();
            card_amount = hand_area[1];
            summon_card = new priest();
            summon_card_id = 1;
        }
        else if(card_class.equals("tank")){
            card_pp_consume = tank.get_pp_consume();
            card_amount = hand_area[2];
            summon_card = new tank();
            summon_card_id = 2;
        }
        else if(card_class.equals("shooter")){
            card_pp_consume = shooter.get_pp_consume();
            card_amount = hand_area[3];
            summon_card = new shooter();
            summon_card_id = 3;
        }
        else if(card_class.equals("assassin")){
            card_pp_consume = assassin.get_pp_consume();
            card_amount = hand_area[4];
            summon_card = new assassin();
            summon_card_id = 4;
        }
        else if(card_class.equals("businessman")){
            card_pp_consume = businessman.get_pp_consume();
            card_amount = hand_area[5];
            summon_card = new businessman();
            summon_card_id = 5;
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

    public void put_on_equipment(Equipment eq){
        equipment = eq;
        atk += equipment.get_atk_gain();
        life += equipment.get_life_gain();
        defense += equipment.get_defense_gain();
    }

    public void put_equipment_form_save(Equipment eq){
        equipment = eq;
    }

    public void comsume_potion(Potion p){
        potion = p;
        atk += potion.get_atk_gain();
        life += potion.get_life_gain();
        defense += potion.get_defense_gain();
    }

    public void comsume_potion_from_save(Potion p){
        potion = p;
    }

    public void potion_over_a_turn(){
        atk -= potion.get_atk_gain();
        defense -= potion.get_defense_gain();
        potion = null;
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

    public int get_Max_life(){
        return Max_life;
    }

    public int get_defense(){
        return defense;
    }

    public static String[] get_pool(){
        return name_pool;
    }

    public Equipment get_equipment(){
        return equipment;
    }
    
    public Potion get_potion(){
        return potion;
    }

}
