package com.havoc.surveyautomation.prepare;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NameService {
    public List<String> firstNames = new ArrayList<>();
    public List<String> lastNames = new ArrayList<>();

    public void readNameValues(String filePath, List<String> nameContainer){
        File file = new File(filePath);
        try{
            // Creates a FileReader
            FileReader fr = new FileReader(filePath);
            // Creates a BufferedReader [default buffer size = 8192 bytes]
            BufferedReader buffer = new BufferedReader(fr);

            // reading line by line
            String line = buffer.readLine();
            while(line != null){
                //System.out.println(line);
                String nameValue = line;
                nameValue = nameValue.trim();
                nameContainer.add(nameValue);
                line = buffer.readLine();
            }
            buffer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void writeNameValues(String filePath, List<String> nameContainer){
        File file = new File(filePath);
        try{
            // Creates a FileWriter
            FileWriter fw = new FileWriter(file, true);
            // Creates a BufferedWriter [default buffer size = 8192 bytes][default buffer size = 8192 bytes]
            BufferedWriter buffer = new BufferedWriter(fw);
            for(String name : nameContainer){
                buffer.write(name);
                buffer.newLine();
            }
            buffer.flush();
            buffer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public String buildRandomName(List<String> firstNames, List<String> lastNames){
        Random rand = new Random();
        String randomFName = firstNames.get(rand.nextInt(firstNames.size()));
        String randomLName = lastNames.get(rand.nextInt(lastNames.size()));
        return randomFName + " " + randomLName;
    }

    /*public static void main(String[] args) {
        String filePath = "src/main/resources/firstnames.txt";
        readNameValues(filePath, firstNames);
        filePath = "src/main/resources/firstnamesOutput.txt";
        writeNameValues(filePath, firstNames);
        String filePath = "src/main/resources/lastnames.txt";
        readNameValues(filePath, lastNames);
        filePath = "src/main/resources/lastnamesOutput.txt";
       writeNameValues(filePath, lastNames);
    }*/
}
