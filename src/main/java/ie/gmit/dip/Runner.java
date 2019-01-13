package ie.gmit.dip;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Runs application, from here initialize stop words, dictionary and build index.
 * Contains code to build and display CLI that allows certain operations on index.
 */
public class Runner {

    private IndexUtil indexUtil;

    /**
     * Uses stopwords and dictionary to create index from source file.
     *
     * @param stopWordsFilePath  stop words. will be excluded from creating index.
     * @param queryFilePath      source file to be indexed.
     * @param dictionaryFilePath file with dictionary.
     * @throws IOException throws IOException exception
     */
    //    o Return all words in sorted / reverse sorted order (maybe print 5 words / line)
//    o The total number of unique words.
//    o The top n most frequent / infrequent words
    public void initIndex(String stopWordsFilePath, String queryFilePath, String dictionaryFilePath) throws IOException {

        // 2 parse stopwords
        ParseStopWords parseStopWords = new ParseStopWords();
        parseStopWords.parse(stopWordsFilePath);
        Set<String> stopWords = parseStopWords.getStopWords();
        // parse dictionary
        ParseDictionary parseDictionary = new ParseDictionary();
        parseDictionary.parse(dictionaryFilePath);
        Map<String, WordDetail> dictionary = parseDictionary.getDictionary();
        // parse text file
        ParseQuery parseQuery = new ParseQuery(stopWords, dictionary);
        parseQuery.parse(queryFilePath);
        Map<String, WordDetail> index = parseQuery.getIndex();
        indexUtil = new IndexUtil(index);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("please provide 3 parameters: 1. stop words file, 2. query file, 3. dictionary file");
            System.exit(0);
        }
        Runner runner = new Runner();
        String stopWordsFilePath = args[0];
        String queryFilePath = args[1];
        String dictionaryFilePath = args[2];
        runner.initIndex(stopWordsFilePath, queryFilePath, dictionaryFilePath);

        String mainMenuPrompt = "[1] Print all words in ascending order (with definition and occurrences)\n[2] Print all words in ascending order (word names only)\n[3]Print all words in descending order (with definition and occurrences)\n[4]Print all words in descending order (word names only)\n[5] Print total number of unique words\n[6] The top n most frequent words (with definition and occurrences)\n[7] The top n most frequent words (word names only)\n[8] The top n most infrequent words (with definition and occurrences)\n[9] The top n most infrequent words (word names only\n[0] Exit";
        int[] choiceAvailable = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // list of possible choice  - should match prompt defined above
        // initial call of menu method - later its called at the end of loop
        int choice = runner.menu(choiceAvailable, mainMenuPrompt);
        // in loop -  until user enters 0 (exit) reads user input, execute code to complete action user selected
        while (choice != 0) {
            switch (choice) {
                case 1:
                    runner.indexUtil.printIndexSortedAscFull();
                    break;
                case 2:
                    runner.indexUtil.printIndexSortedAscNamesOnly();
                    break;
                case 3:
                    runner.indexUtil.printIndexSortedDescFull();
                    break;
                case 4:
                    runner.indexUtil.printIndexSortedDescNamesOnly();
                    break;
                case 5:
                    int uniqueWordsCount = runner.indexUtil.getUniqueWordsCount();
                    System.out.println("Total Unique Words: " + uniqueWordsCount);
                    break;
                case 6:
                    int numberOfItemsToDisplayDesc = getNumberOfItemsToDisplay();
                    List<WordDetail> topFrequentWords = runner.indexUtil.getTopFrequentWords(numberOfItemsToDisplayDesc);
                    runner.indexUtil.printWordList(topFrequentWords);
                    break;
                case 7:
                    int numberOfItemsToDisplayDesc2 = getNumberOfItemsToDisplay();
                    List<WordDetail> topFrequentWords2 = runner.indexUtil.getTopFrequentWords(numberOfItemsToDisplayDesc2);
                    runner.indexUtil.printWordListJustNames(topFrequentWords2);
                    break;
                case 8:
                    int numberOfItemsToDisplayAsc = getNumberOfItemsToDisplay();
                    List<WordDetail> topInfrequentWords = runner.indexUtil.getTopInfrequentWords(numberOfItemsToDisplayAsc);
                    runner.indexUtil.printWordList(topInfrequentWords);
                    break;
                case 9:
                    int numberOfItemsToDisplayAsc2 = getNumberOfItemsToDisplay();
                    List<WordDetail> topInfrequentWords2 = runner.indexUtil.getTopInfrequentWords(numberOfItemsToDisplayAsc2);
                    runner.indexUtil.printWordListJustNames(topInfrequentWords2);
                    break;
                default:
                    System.out.println("Invalid input. Hit enter and try again");
            }
            choice = runner.menu(choiceAvailable, mainMenuPrompt);
        }
    }

    /**
     * Ask user for number of index positions to be displayed
     *
     * @return number of positions to be displayed.
     */
    private static int getNumberOfItemsToDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number Of Items To Display: ");
        return scanner.nextInt();
    }

    /**
     * display prompt and get input from user
     *
     * @param prompt message that will be displayed to user
     * @return
     */
    private String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    /**
     * display menu and get input from user
     *
     * @param choiceAvailable list of available choices
     * @param prompt          message displayed to user when asking for choice
     * @return number that corresponds to operation user want to perform
     */
    private int menu(int[] choiceAvailable, String prompt) {
        Scanner scanner = new Scanner(System.in);
        displayMainMenu(prompt);
        int value = 0;
        while (true) {
            value = scanner.nextInt();
            if (!contains(value, choiceAvailable)) {
                System.out.println("Invalid input, please try again");
                displayMainMenu(prompt);
            } else {
                System.out.println("Selected:" + value);
                break;
            }
        }
        return value;
    }

    /**
     * display message to user. Used when composing menu in CLI
     *
     * @param prompt message displayed to user
     */
    private void displayMainMenu(String prompt) {
        System.out.println("Insert item number and hit enter:");
        System.out.println(prompt);
    }

    /**
     * check if array contains given number
     *
     * @param i   number that need to be checked
     * @param arr aray of numbers to be searched
     * @return true if array contains number, false otherwise.
     */
    /* helper function check if array contains given character*/
    private boolean contains(int i, int[] arr) {
        for (int elem : arr) {
            if (elem == i) {
                return true;
            }
        }
        return false;
    }
}
