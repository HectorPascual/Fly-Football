package com.example.android.skyfootball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class Data {
    private static ArrayList<Team> teams = new ArrayList<>();
    private static HashMap<Team,String> completeTeams = new HashMap<>();

    public static void fillTeams(ArrayList<Team> t){
        teams = t;

        completeTeams.put(teams.get(0),"Lisboa");
        completeTeams.put(teams.get(1),"Moscow");
        completeTeams.put(teams.get(2),"Athenes");
        completeTeams.put(teams.get(3),"Lisboa");
        completeTeams.put(teams.get(4),"Barcelona");
        completeTeams.put(teams.get(5),"Turin");
        completeTeams.put(teams.get(6),"Rome");
        completeTeams.put(teams.get(7),"Madrid");
        completeTeams.put(teams.get(8),"Chelsea");
        completeTeams.put(teams.get(9),"Baku");
        completeTeams.put(teams.get(10),"Glasgow");
        completeTeams.put(teams.get(11),"Paris");
        completeTeams.put(teams.get(12),"Munich");
        completeTeams.put(teams.get(13),"Brussels");
        completeTeams.put(teams.get(14),"Manchester");
        completeTeams.put(teams.get(15),"Basel");
        completeTeams.put(teams.get(16),"Madrid");
        completeTeams.put(teams.get(17),"Nicosia");
        completeTeams.put(teams.get(18),"Leipzig");
        completeTeams.put(teams.get(19),"Monaco");
        completeTeams.put(teams.get(20),"Porto");
        completeTeams.put(teams.get(21),"Istanbul");
        completeTeams.put(teams.get(22),"Donetsk");
        completeTeams.put(teams.get(23),"Napoles");
        completeTeams.put(teams.get(24),"Rotterdam");
        completeTeams.put(teams.get(25),"Manchester");
        completeTeams.put(teams.get(26),"Maribor");
        completeTeams.put(teams.get(27),"Moscow");
        completeTeams.put(teams.get(28),"Liverpool");
        completeTeams.put(teams.get(29),"Sevilla");
        completeTeams.put(teams.get(30),"London");
        completeTeams.put(teams.get(31),"Dortmund");

        //completeTeams2 = reverse(completeTeams);

    }
    public static HashMap<Team,String> getCompleteTeams(){
        return completeTeams;
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }
    /*public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
        HashMap<V,K> rev = new HashMap<V, K>();
        for(Map.Entry<K,V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }*/
    public static HashMap<String, Integer> iconsMap = new HashMap<>();
}
