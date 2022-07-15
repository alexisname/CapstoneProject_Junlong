package com.example.togetherwegrow;

/*
current input:
worktype - sedentary results in high energy, but maybe low freshness
         - physical results in low energy, but high freshness
workload - factors/coefficient that affect above result, when high, decrease both level (energy and freshness), when low, increase a bit, do nothing when medium
energy after work -  subjective evaluation according to user, if low, decrease both level (energy and freshness), if high increase a bit, do nothing when medium
activity hour - used to avoid exciting activity at night
child age - used to decide which activity would not suit
preferred -  different weight in three, main criteria of selection
dislike - definitely avoid

activity table:
catalog -
            quiet - 2 energy requirement, 4 freshness requirement
            physical - 4 energy requirement, 2 freshness requirement
            game - both medium, 3
age - means this activity would not suit child beyond this age (too simple/boring for current age)

strategy example -
energy and freshness point, for both parent and activities, range from 1 - 5
start for physical, energy 2, freshness 4; sedentary, energy 4, freshness 2
                 - worktype sedentary, energy point = 4, freshness point = 2
                 - workload low, energy point = 5, freshness point = 3
                 - energy after work medium, do nothing, if fresh, increase by 1, if tired, decrease by 1
                 - current energy point = 5, freshness point = 3
now for parent, the activity leans towards more physical
also consider the preference of child
                 - most preferred, weighs 0.6, second, weighs 0.3, third, weighs 0.1
                 drawing, falls into quiet activity, energy point = 2*0.6 = 1.2， freshness point = 4*0.6 = 2.4
                 music, falls into game activity, energy point = 3*0.3 = 0.9, freshness point = 3*0.3 = 0.9
                 sport, falls into physical activity, energy point = 4*0.1 = 0.4, freshness point = 2*0.1 = 0.2
                 in total, energy point = 2.5, freshness point = 3.5
now for child, it is more quiet activity, or both are somewhere in the middle

now assign weight to parent and child, 0.4 and 0.6 respectively, this app is more about child, however activities can be done without parent
energy point = 5*0.4 + 2.5*0.6 = 3.5, freshness point = 3*0.4 + 3.5*0.6 = 3.3

so this may give a game oriented plan, however, at least one activity will be in total favor of child, quiet, and is best for night time (if is physical, then in morning or afternoon)
 */


import java.util.HashMap;

public class InputMatch {

    /*fields for activity calculation*/
    private String worktype;
    private String workload;
    private String freshnessafter;
    private String mostPreferred;
    private String secondPreferred;
    private String thirdPreferred;
    /*fields for other factors*/
    private String age;


    private double energyP;    //factors for parent
    private double freshnessP;
    private double energyC;    //factors for child
    private double freshnessC;
    private double energy;    //overall factor
    private double freshness;
    private HashMap<String, Double[]> activityMap = new HashMap<>();

    public InputMatch(String worktype, String workload, String freshnessafter, String mostPreferred, String secondPreferred, String thirdPreferred, String age) {
        this.worktype = worktype;
        this.workload = workload;
        this.freshnessafter = freshnessafter;
        this.mostPreferred = mostPreferred;
        this.secondPreferred = secondPreferred;
        this.thirdPreferred = thirdPreferred;
        this.age = age;
    }

    public void calPoints(){
        /*calculate points regarding parent's info*/
        //initialize activityMap
        activityMap.put("Reading",new Double[]{2.0, 4.0});
        activityMap.put("Drawing",new Double[]{2.0, 4.0});
        activityMap.put("Music",new Double[]{2.0, 4.0});
        activityMap.put("Sports",new Double[]{4.0, 2.0});
        activityMap.put("Game",new Double[]{3.0, 3.0});
        activityMap.put("Cartoon",new Double[]{2.0, 4.0});
        //activityMap.put("N/A",new Double[]{0.0, 0.0});
        /*worktype*/
        if(worktype.equals("Sedentary")){
            energyP = 4.0;
            freshnessP = 2.0;
        }
        else if(worktype.equals("Physical")){
            energyP = 2.0;
            freshnessP = 4.0;
        }
        /*workload*/
        if(workload.equals("High")){
            energyP--;
            freshnessP--;
        }
        else if(workload.equals("Low")){
            energyP++;
            freshnessP++;
        }
        /*freshness after work*/
        if(freshnessafter.equals("Fresh")){
            energyP++;
            freshnessP++;
        }
        else if(freshnessafter.equals("Tired")){
            energyP--;
            freshnessP--;
        }
        /*calculate points regarding child's preference*/
        /*consider the second or third pref might be N/A, change weight*/
        /*if all three preferences are filled*/
        if(!mostPreferred.equals("N/A") && !secondPreferred.equals("N/A") && !thirdPreferred.equals("N/A")){
            energyC = 0.6*activityMap.get(mostPreferred)[0]+0.3*activityMap.get(secondPreferred)[0]+0.1*activityMap.get(thirdPreferred)[0];
            freshnessC = 0.6*activityMap.get(mostPreferred)[1]+0.3*activityMap.get(secondPreferred)[1]+0.1*activityMap.get(thirdPreferred)[1];
        }
        if(!mostPreferred.equals("N/A") && !secondPreferred.equals("N/A") && thirdPreferred.equals("N/A")){
            energyC = 0.6*activityMap.get(mostPreferred)[0]+0.4*activityMap.get(secondPreferred)[0];
            freshnessC = 0.6*activityMap.get(mostPreferred)[1]+0.4*activityMap.get(secondPreferred)[1];
        }
        if(!mostPreferred.equals("N/A") && secondPreferred.equals("N/A") && thirdPreferred.equals("N/A")){
            energyC = activityMap.get(mostPreferred)[0];
            freshnessC = activityMap.get(mostPreferred)[1];
        }

        /*calculate total*/
        energy = 0.4*energyP + 0.6*energyC;
        freshness = 0.4*freshnessP + 0.6*freshnessC;
    }

    public double getEnergy(){
        return this.energy;
    }
    public double getFreshness(){
        return this.freshness;
    }

}
