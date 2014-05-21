package com.toniprada.pfc.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by toni on 20/05/14.
 */
public class SourcesList {


    private static final String SOURCES_PATH = "data/sources";
    private static Object[] sources;


    public static Object[] getRandomSources() {
        if (sources == null) {
            buildSourcesArray();
        }
        shuffleArray();
        int randomIndex = (int) (Math.random()* sources.length);
        int randomIndex2 = (int) (Math.random()* sources.length);
        if (randomIndex == randomIndex2) {
            randomIndex--;
        }

        return Arrays.copyOfRange(sources,Math.min(randomIndex, randomIndex2),Math.max(randomIndex, randomIndex2));
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray()
    {
        Random rnd = new Random();
        for (int i = sources.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Object a = sources[index];
            sources[index] = sources[i];
            sources[i] = a;
        }
    }


    private static void buildSourcesArray() {
        try{
            FileInputStream fstream = new FileInputStream(SOURCES_PATH);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            ArrayList<String> namesList = new ArrayList<String>();
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                namesList.add(strLine);
            }
            in.close();
            sources = namesList.toArray();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

}
