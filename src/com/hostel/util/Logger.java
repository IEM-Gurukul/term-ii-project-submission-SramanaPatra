package com.hostel.util;

import java.io.FileWriter;

public class Logger {

    private static Logger instance;

    private Logger(){}

    public static Logger getInstance(){

        if(instance==null)
            instance=new Logger();

        return instance;

    }

    public void log(String msg){

        try{

            FileWriter fw=new FileWriter("activity.log",true);

            fw.write(msg+"\n");

            fw.close();

        }
        catch(Exception e){

            System.out.println("Log failed");

        }

    }
}
