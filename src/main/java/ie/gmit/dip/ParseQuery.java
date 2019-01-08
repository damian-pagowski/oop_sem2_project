package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParseQuery {



    private Set<String> stop;
    private Map<String, WordDetail> dictionary;
    private Map<String, WordDetail> index = new TreeMap<>();

    public ParseQuery(Set<String> stop, Map<String, WordDetail> dictionary) {
        this.stop = stop;
        this.dictionary = dictionary;
    }

    public void parse(String queryFileName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(queryFileName)));

        String line = null;
        int lineNumber = 0;
        int page = 1;

        while ((line = br.readLine()) != null) {
            line = line.toUpperCase().replaceAll("[^A-Za-z0-9 ]", "");
            lineNumber++;

            if (lineNumber % 40 == 0) page++;

            String[] words = line.split(" "); //Regex
            for (String s : words) {
                if (!stop.contains(s)) {
                    WordDetail entry = dictionary.get(s);

                    if (entry != null) {
                        entry.addPage(page);
                        index.put(s, entry);
                    }
                }
            }
        }

        br.close();
    }

    public Map<String, WordDetail> getIndex() {
        return index;
    }
}
