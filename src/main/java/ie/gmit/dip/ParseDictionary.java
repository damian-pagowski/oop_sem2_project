package ie.gmit.dip;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Use to build Dictionary.
 */
public class ParseDictionary {

    private Map<String, WordDetail> dictionary = new TreeMap<>();
    private WordDetail current = null;
    private StringBuilder builder = new StringBuilder();

    /**
     * Parse source file and create dictionary
     * @param dictionaryFileName Name of file with dictionary to be parsed.
     * @throws IOException throws IOException exception
     */
    public void parse(String dictionaryFileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFileName)));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("\"")) current = new WordDetail();
            builder.append(line);
            if (line.endsWith("\"") && current != null) process();
        }
        br.close();
    }

    /**
     * Create dictionary entry by initializing fields of WordDetail instance.
     */
    private void process() {
        StringBuilder temp = new StringBuilder();
        int state = 0;
        for (int i = 0; i < builder.length(); i++) {
            char next = builder.charAt(i);

            if (next == '\u0022') {
                if (state == 1) {
                    current.setWord(temp.toString().toUpperCase());
                    temp.setLength(0);
                } else if (state == 3) {
                    current.setWordType(temp.toString());
                    temp.setLength(0);
                } else if (state == 4) {
                    current.setDefinition(builder.substring(i, builder.length()));
                    addWordDetail();
                    return;
                }
                state++;
            } else {
                temp.append(next);
            }

        }
    }

    /**
     * Set StringBuilder length to 0.
     */
    private void resetBuilder() {
        builder.setLength(0);
    }

    /**
     * Add entry to dictionary. Dictionary is a map which key is word and value is WordDetails instance, that contains word definition.
     */
    private void addWordDetail() {
        WordDetail entry = dictionary.get(current.getWord());

        if (entry == null) {
            dictionary.put(current.getWord().toUpperCase(), current);
        } else {
            entry.setDefinition(entry.getDefinition() + "\n" + current.getDefinition());
        }

        current = null;
        resetBuilder();
    }

    /**
     * Get Dictionary parsed from file.
     * @return dictionary containing words and its definitions.
     */
    public Map<String, WordDetail> getDictionary() {
        return dictionary;
    }


}
