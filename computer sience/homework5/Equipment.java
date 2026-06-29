public class Equipment {

    private String ep_name;//return equipment's name
    private int life_gain;
    private int atk_gain;
    private int defense_gain;
    private static String name_pool[] = {"armor", "hat", "knife", "mask", "force", "shield"};

    //constructor for the use of super function from the subclass
    public Equipment(String name, int LIFE_GAIN, int ATK_GIAN, int DEFENSE_GAIN){
        ep_name = name;
        life_gain = LIFE_GAIN;
        atk_gain = ATK_GIAN;
        defense_gain = DEFENSE_GAIN;
    }

    //return equipment's name
    public String toString(){
        return ep_name;
    }
    
    //return name pool
    public static String[] get_pool(){
        return name_pool;
    }

    //return life gain
    public int get_life_gain(){
        return life_gain;
    }

    //return atk gain
    public int get_atk_gain(){
        return atk_gain;
    }

    //return defense gain
    public int get_defense_gain(){
        return defense_gain;
    }

}
