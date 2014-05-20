package com.toniprada.pfc.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class NamesList {

        private static final String NAMES_PATH = "data/names";
        private static Object[] names;


        public static String getRandomName() {
            if (names == null) {
                buildNamesArray();
            }
            int randomIndex = (int) (Math.random()*names.length);
            return (String) names[randomIndex];
        }


        private static void buildNamesArray() {
            try{
                FileInputStream fstream = new FileInputStream(NAMES_PATH);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                ArrayList<String> namesList = new ArrayList<String>();
                String strLine;
                while ((strLine = br.readLine()) != null)   {
                    namesList.add(strLine);
                }
                in.close();
                names = namesList.toArray();
            }catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }

    }
