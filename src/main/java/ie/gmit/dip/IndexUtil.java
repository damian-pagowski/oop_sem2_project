package ie.gmit.dip;

import java.util.*;

/**
 * Operations that can be performed on index.
 */
public class IndexUtil {

    private Map<String, WordDetail> index;

    public IndexUtil(Map<String, WordDetail> index) {
        this.index = index;
    }


    /**
     * print index with word details like type, definition and frequency ordered alphabetic.
     */
    public void printIndexSortedAscFull() {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByName());
        printWordList(list);
    }

    /**
     * Print index to console. For each index entry only word name will Be displayed.
     */
    public void printIndexSortedAscNamesOnly() {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByName());
        printWordListJustNames(list);
    }

    /**
     * print index with word details like type, definition and frequency in reversed alphabetic order.
     */
    public void printIndexSortedDescFull() {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByName());
        Collections.reverse(list);
        printWordList(list);
    }

    /**
     * print index with word names from index in reversed alphabetic order.
     */
    public void printIndexSortedDescNamesOnly() {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByName());
        Collections.reverse(list);
        printWordListJustNames(list);
    }

    /**
     * Print names of words from collection of WordDetails.
     *
     * @param list List of WordDetails
     */
    public void printWordListJustNames(List<WordDetail> list) {
        StringBuffer line = new StringBuffer();
        for (WordDetail wordDetail : list) {
            line.append(wordDetail.getWord()).append(", ");
        }
        System.out.println(formatLine(line.toString(), 5));
    }

    /**
     * Prints list of words with definition and number of occurrence to console
     *
     * @param wordDetails list of words to be printed
     */
    public void printWordList(List<WordDetail> wordDetails) {
        for (WordDetail wordDetail : wordDetails) {
            printIndexEntry(wordDetail);
        }

    }

    /**
     * Get total number of unique words in index.
     *
     * @return The total number of unique words.
     */
    public int getUniqueWordsCount() {
        return index.size();
    }

    /**
     * Get list of most frequent words
     *
     * @param n number of elements to return
     * @return most frequent elements of index
     */
    public List<WordDetail> getTopFrequentWords(int n) {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByFrequency());
        Collections.reverse(list);
        return list.subList(0, n);
    }

    /**
     * Get list of most infrequent words
     *
     * @param n number of elements to return
     * @return most infrequent elements of index
     */
    public List<WordDetail> getTopInfrequentWords(int n) {
        List<WordDetail> list = new ArrayList<WordDetail>(index.values());
        Collections.sort(list, new SortByFrequency());
        return list.subList(0, n);
    }

    /**
     * Prints single index entry {@paramref wordDetail} to console
     *
     * @param wordDetail single index entry
     */
    private void printIndexEntry(WordDetail wordDetail) {
        String separator = "----------------------------------------";
        System.out.println(separator);
        System.out.println(String.format("# Word: %s", wordDetail.getWord()));
        System.out.println(String.format("# Word Type: %s", wordDetail.getWordType()));
        System.out.println("# Definition: ");
        String[] split = wordDetail.getDefinition().split("\n");
        for (String line : split) {
            System.out.println(formatLine(line, 5));
        }
        System.out.print("# Pages: ");
        for (int page : wordDetail.getPages()) {
            System.out.print(page + " ");
        }
        System.out.println("\n");
    }

    /**
     * Format {@paramref text} by adding new line after every {@paramref lineLength} words
     *
     * @param text       text to be formatted
     * @param lineLength expected line length in words
     * @return formatted text
     */
    private String formatLine(String text, int lineLength) {
        final StringTokenizer split = new StringTokenizer(text);
        long count = 1;
        final StringBuilder formattedText = new StringBuilder();
        while (split.hasMoreTokens()) {
            formattedText.append(split.nextToken());
            if (split.hasMoreTokens()) {
                formattedText.append((count++ % lineLength == 0) ? System.getProperty("line.separator") : " ");
            }
        }
        return formattedText.toString();
    }
}

