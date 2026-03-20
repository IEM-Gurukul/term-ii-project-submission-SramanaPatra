package com.hostel.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static Logger instance;

    private Logger() {}

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message){

        String logMessage = LocalDateTime.now() + " - " + message;

        // Print in console
        System.out.println("[LOG] " + logMessage);

        // Save to file (optional but impressive)
        try(FileWriter fw = new FileWriter("log.txt", true)){
            fw.write(logMessage + "\n");
        } catch(IOException e){
            System.out.println("Logging failed");
        }
    }
}
