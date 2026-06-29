public class Equipment {

    private String ep_name;
    private int life_gain;
    private int atk_gain;
    private int defense_gain;
    private static String name_pool[] = {"armor", "hat", "knife", "mask", "force", "shield"};

    public Equipment(String name, int LIFE_GAIN, int ATK_GIAN, int DEFENSE_GAIN){
        ep_name = name;
        life_gain = LIFE_GAIN;
        atk_gain = ATK_GIAN;
        defense_gain = DEFENSE_GAIN;
    }

    public String toString(){
        return ep_name;
    }
    
    public static String[] get_pool(){
        return name_pool;
    }

    public int get_life_gain(){
        return life_gain;
    }

    public int get_atk_gain(){
        return atk_gain;
    }

    public int get_defense_gain(){
        return defense_gain;
    }

}
