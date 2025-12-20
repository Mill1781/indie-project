public class Potion {

    private String po_name;
    private int life_gain;
    private int atk_gain;
    private int defense_gain;
    private static String name_pool[] = {"healing", "damage", "defense"};


    public Potion(String name, int LIFE_GAIN, int ATK_GIAN, int DEFENSE_GAIN){
        po_name = name;
        life_gain = LIFE_GAIN;
        atk_gain = ATK_GIAN;
        defense_gain = DEFENSE_GAIN;

    }

    public String toString(){
        return po_name;
    }
    
    public String tostring(){
        return po_name + " potion";
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
