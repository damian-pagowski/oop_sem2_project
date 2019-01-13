package ie.gmit.dip;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Use to build collection of stop words (words to be ignored when creating index).
 */
public class ParseStopWords {
    private Set<String> stop = new TreeSet<>();

    /**
     * Parse file and build collection of stop words
     *
     * @param stopWordFileName name of file to be parsed
     * @throws IOException throws IOException exception
     */
    public void parse(String stopWordFileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordFileName)));

        String line = null;

        while ((line = br.readLine()) != null) {
            stop.add(line.toUpperCase());
        }

        br.close();
    }

    /**
     * Get list of stop words - words that will be ignored while creating index.
     *
     * @return a set of stop words.
     */
    public Set<String> getStopWords() {
        return stop;
    }
}
