package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class ParseDictionary {

    private Map<String, WordDetail> dictionary = new TreeMap<>(); //O(log(n))
    //private Map<String, WordDetail> dictionary = new HashMap<>(); //Average O(1)
    //private Map<String, WordDetail> dictionary = new ConcurrentHashMap<>(); //Average O(1) //Threads
    //private Map<String, WordDetail> dictionary = new ConcurrentSkipList<>(); //O(log(n))  //Threads

    private WordDetail current = null;
    private StringBuilder builder = new StringBuilder();


    public static void main(String[] args) throws Exception {
        ParseDictionary parseDictionary = new ParseDictionary();
        parseDictionary.parse("C:\\Users\\Damian\\Downloads\\advenced_oop_project\\dictionary.csv");
        Map<String, WordDetail> dictionary = parseDictionary.getDictionary();

    }
    public void parse(String dictionaryFileName) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFileName)));

        String line = null;

        while ((line = br.readLine()) != null){
            if (line.startsWith("\"")) current = new WordDetail();
            builder.append(line);
            if (line.endsWith("\"") && current != null) process();
        }

        br.close();
    }

    private void process(){
        //"word","wordtype","definition"
        StringBuilder temp = new StringBuilder();

        int state = 0;
        for (int i = 0; i < builder.length(); i++){
            char next = builder.charAt(i);

            if (next == '\u0022'){ //Double-quotes
                if (state == 1){
                    current.setWord(temp.toString().toUpperCase());
                    temp.setLength(0);
                }else if (state == 3){
                    current.setWordType(temp.toString());
                    temp.setLength(0);
                }else if (state == 4){
                    current.setDefinition(builder.substring(i, builder.length()));
                    addWordDetail();
                    return;
                }
                state++;
            }else{
                temp.append(next);
            }

        }
    }

    private void resetBuilder(){
        //builder = StringBuilder(); //Resets the builder, but it's inefficient!
        builder.setLength(0); //A bit of a hack, but much faster.
    }

    private void addWordDetail(){
        WordDetail entry = dictionary.get(current.getWord());

        if (entry == null){
            dictionary.put(current.getWord().toUpperCase(), current);
        }else{
            entry.setDefinition(entry.getDefinition() + "\n" + current.getDefinition());
        }

        current = null;
        resetBuilder();
    }

    public Map<String, WordDetail> getDictionary(){
        return dictionary;
    }


}
