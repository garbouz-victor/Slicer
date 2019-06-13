package com.nexign.taskproject.slicer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author viga0114
 */
public class Slicer {
    public static void main(String... args) 
            throws FileNotFoundException, IOException {
        if (args[0] == null) {
            throw new IllegalArgumentException("Please, enter the file path!");
        }
        try(BufferedReader br
                = new BufferedReader(
                        new InputStreamReader(new FileInputStream(args[0]),
                                "UTF-8"));
                PrintWriter writer = new PrintWriter("outputUtf8.txt", "UTF-8")) {
            br.lines().forEach(str -> {
                List<String> strVals = handle(str);
                boolean wrote = false;
                for (String s : strVals) {
                    writer.print(s+";");
                    wrote = true;
                }
                if (wrote) {
                    writer.print("\r\n");
                }                
            });
        }
    }
    
    private static List<String> handle(String str) {
        List<String> returnValue = new ArrayList<>();
        Pattern p = Pattern.compile("\\w{3,}");
        Matcher m = p.matcher(str);
        while (m.find()) {            
            returnValue.add(str.substring(m.start(), m.end()));
        }
        return returnValue;
    }
}
