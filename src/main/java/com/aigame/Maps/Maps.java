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
            {"BS","W","BS","",""},
            {"P","BS","P","G",""},
            {"B","","B","",""},
            {"A","B","P","B",""}
        };

        String[][] risky={
            {"","BS","P","B",""},
            {"BS","W","BS","",""},
            {"P","BS","P","G",""},
            {"B","","B","",""},
            {"A","B","P","B",""}
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
