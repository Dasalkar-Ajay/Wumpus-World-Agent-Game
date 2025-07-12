package com.aigame.Maps;

import java.util.Random;

public class Maps {
    public String[][] getmap(int value) {

        String[][] simple = {
                { "B", "", "B", "", "" },
                { "P", "BS", "P", "B", "" },
                { "BS", "W", "BS", "", "" },
                { "", "S", "", "B", "G" },
                { "A", "", "B", "P", "B" } };

        String [][] medium={
            {"","BS","P","B",""},
            {"BS","W","BS","G",""},
            {"P","BS","B","P","B"},
            {"B","","","B",""},
            {"A","","B","P","B"}
        };

        String[][] risky={
            {"P","B","S","W","S"},
            {"B","P","B","BS","G"},
            {"","BS","B","P","B"},
            {"S","W","BS","B",""},
            {"A","BS","P","B",""}
        };

        switch (value) {
            case 0:
                return simple;
            case 1:
                return medium;
            case 2:
                return risky;
            default:{
                Random random=new Random();
                int x=random.nextInt(3);
                if(x==0)return simple;
                else if(x==1)return medium;
                return risky;
            }
        }

    }
}
