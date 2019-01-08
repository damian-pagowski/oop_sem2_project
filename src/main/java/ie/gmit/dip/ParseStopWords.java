package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class ParseStopWords {
    private Set<String> stop = new TreeSet<>();

    public void parse(String stopWordFileName) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordFileName)));

        String line = null;

        while ((line = br.readLine()) != null){
            stop.add(line.toUpperCase());
        }

        br.close();
    }

    public Set<String> getStopWords(){
        return stop;
    }
}
