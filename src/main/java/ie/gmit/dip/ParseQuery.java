package ie.gmit.dip;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Parse source file and build index
 */
public class ParseQuery {


    private Set<String> stop;
    private Map<String, WordDetail> dictionary;
    private Map<String, WordDetail> index = new TreeMap<>();

    /**
     * Constructor. Takes 2 arguments.
     *
     * @param stop       list of stop words - words to be ignored.
     * @param dictionary map containing words and its definitions.
     */
    public ParseQuery(Set<String> stop, Map<String, WordDetail> dictionary) {
        this.stop = stop;
        this.dictionary = dictionary;
    }

    /**
     * Build index from file
     *
     * @param queryFileName name of file from which index should be build
     * @throws IOException throws IOException exception
     */
    public void parse(String queryFileName) throws IOException {
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

    /**
     * Gets index build from query
     *
     * @return index containing word with count, definition and type
     */
    public Map<String, WordDetail> getIndex() {
        return index;
    }
}
