//This file mainly include information about movie related function
public class movie {

    //attribute ==> setting default data
    private static final String[] name = {"馴龍高手", "不可能的任務：最終清算", "不可能的任務：最終清算", "絕命終結站 血脈", "膽大黨：邪視", "心之谷", "山忌 黃衣小飛俠", "搜查瑠公圳", "夏日最後的祕密", "劇場版 世界計畫 崩壞的世界與無法唱歌的初音未來", "捍衛任務：復仇芭蕾", "冥燈", "腓尼基計劃", "寶藏獵人"};
    private static final int[] time = {125, 169, 108, 110, 93, 111, 89, 109, 114, 111, 125, 102, 101, 98};
    private static final String[] type = {"fantasy", "action", "fantasy", "horror", "anime", "anime", "horror", "Suspense", "romantic", "anime", "action", "horror", "drama", "fantasy"};
    private static final char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};
    private static boolean found = false;

    
    //Method ==> show movie
    public static void show_movie(){
        System.out.println("---------------------------------------------------\n");
        System.out.println("English / Translated Title / Notes\n");
        for(int i = 0 ; i < 14; i++){
            System.out.printf("%-10s/ %-5d/ %-5s/ %-5c\n\n", name[i], time[i], type[i], alpha[i]);
        }
        System.out.println("---------------------------------------------------\n");
    }


    //Method ==> show movie by type
    public static void movie_type_search(String Type){
        System.out.println("---------------------------------------------------\n");
        System.out.println("English / Translated Title / Notes\n");
        for(int i = 0; i < 14; i++){
            if(type[i].equals(Type)){
                System.out.printf("%-10s/ %-5d/ %-5s/ %-5c\n\n", name[i], time[i], type[i], alpha[i]);
                found = true;
            }
        }
        System.out.println("---------------------------------------------------\n");
        if(!found){
            System.out.println("cannot find the corresponding category");
        }
        found = false;
    }


    //Method ==> show movie by time
    public static void movie_time_search(double Time){
        System.out.println("---------------------------------------------------\n");
        System.out.println("English / Translated Title / Notes\n");
        for(int i = 0; i < 14; i++){
            if(movie.time[i] <= Time){
                System.out.printf("%-10s/ %-5d/ %-5s/ %-5c\n\n", name[i], time[i], type[i], alpha[i]);
                found = true;
            }
        }
        System.out.println("---------------------------------------------------\n");
        if(!found){
            System.out.println("cannot find the corresponding category");
        }
        found = false;
    }

}
