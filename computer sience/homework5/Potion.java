public class Potion {

    private String po_name;//potion's name
    private int life_gain;
    private int atk_gain;
    private int defense_gain;
    private static String name_pool[] = {"healing", "damage", "defense"};

    //constructor for the use of super function from the subclass
    public Potion(String name, int LIFE_GAIN, int ATK_GIAN, int DEFENSE_GAIN){
        po_name = name;
        life_gain = LIFE_GAIN;
        atk_gain = ATK_GIAN;
        defense_gain = DEFENSE_GAIN;
    }

    //return potion's name
    public String toString(){
        return po_name;
    }
    
    //for the use of printing
    public String tostring(){
        return po_name + " potion";
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
