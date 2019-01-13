package ie.gmit.dip;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Runner {

    private IndexUtil indexUtil;

    //    o Return all words in sorted / reverse sorted order (maybe print 5 words / line)
//    o The total number of unique words.
//    o The top n most frequent / infrequent words
    public void initIndex() throws Exception {
        // TODO files from parameters
        String stopWordsFilePath = "stopwords.txt";
        String queryFilePath = "ThePrince-Machiavelli.txt";
        String dictionaryFilePath = "dictionary.csv";
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
        Runner runner = new Runner();
        runner.initIndex();

        String mainMenuPrompt = "[1] Print all words in ascending order\n[2] Print all words in descending order\n[3] Print total number of unique words\n[4] The top n most frequent words\n[5] The top n most infrequent words\n[0] Exit";
        int[] choiceAvailable = {0, 1, 2, 3, 4, 5}; // list of possible choice  - should match prompt defined above
        // initial call of menu method - later its called at the end of loop
        int choice = runner.menu(choiceAvailable, mainMenuPrompt);
        // in loop -  until user enters 0 (exit) reads user input, execute code to complete action user selected
        while (choice != 0) {
            switch (choice) {
                case 1:
                    runner.indexUtil.printIndexSortedAsc();
                    break;
                case 2:
                    runner.indexUtil.printIndexSortedDesc();
                    break;
                case 3:
                    int uniqueWordsCount = runner.indexUtil.getUniqueWordsCount();
                    System.out.println("Total Unique Words: " + uniqueWordsCount);
                    break;
                case 4:
                    int numberOfItemsToDisplayDesc = getNumberOfItemsToDisplay();
                    List<WordDetail> topFrequentWords = runner.indexUtil.getTopFrequentWords(numberOfItemsToDisplayDesc);
                    runner.indexUtil.printWordList(topFrequentWords);
                    break;
                case 5:
                    int numberOfItemsToDisplayAsc = getNumberOfItemsToDisplay();
                    List<WordDetail> topInfrequentWords = runner.indexUtil.getTopInfrequentWords(numberOfItemsToDisplayAsc);
                    runner.indexUtil.printWordList(topInfrequentWords);
                    break;
                    // TODO - menu to print just words and counts without definition
                default:
                    System.out.println("Invalid input. Hit enter and try again");
            }
            choice = runner.menu(choiceAvailable, mainMenuPrompt);
        }
    }

    private static int getNumberOfItemsToDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number Of Items To Display: ");
        return scanner.nextInt();
    }

    /* display prompt and get input from user*/
    private String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    /* display menu and get input from user. Return user choice(number) */
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

    /*
    helper function that actually prints text to console
     */
    private void displayMainMenu(String prompt) {
        System.out.println("Insert item number and hit enter:");
        System.out.println(prompt);
    }

    /* helper function check if array contains given character*/
    private boolean contains(int i, int[] arr) {
        for (int elem : arr) {
            if (elem == i) {
                return true;
            }
        }
        return false;
    }


//    public static void main(String[] args) throws Exception {
//        // 1 read args
//        // 0 - query
//        // 1 - dictionary
//        // 2 - stopwords
//
//        String stopWordsFilePath = "stopwords.txt";
//        String queryFilePath = "ThePrince-Machiavelli.txt";
//        String dictionaryFilePath = "dictionary.csv";
//        // 2 parse stopwords
//        ParseStopWords parseStopWords = new ParseStopWords();
//        parseStopWords.parse(stopWordsFilePath);
//        Set<String> stopWords = parseStopWords.getStopWords();
//        // parse dictionary
//        ParseDictionary parseDictionary = new ParseDictionary();
//        parseDictionary.parse(dictionaryFilePath);
//        Map<String, WordDetail> dictionary = parseDictionary.getDictionary();
//        // parse text file
//        ParseQuery parseQuery = new ParseQuery(stopWords, dictionary);
//        parseQuery.parse(queryFilePath);
//        Map<String, WordDetail> index = parseQuery.getIndex();
//        Set<String> strings = index.keySet();
//        for (String s : strings){
//            WordDetail wordDetail = index.get(s);
//            String s1 = wordDetail.toString();
//            System.out.println(s1);
//
//
//            break;
//        }
//        System.out.println();
//
//
//        String sampleDef = "\"To cast or drive out; to banish; to expel; to reject.\"\n" +
//                "\"To give up absolutely; to forsake entirely ; to   renounce utterly; to relinquish all connection with or concern on; to   desert, as a person to whom one owes allegiance or fidelity; to quit;   to surrender.\"\n" +
//                "\"Reflexively: To give (one's self) up without attempt at   self-control; to yield (one's self) unrestrainedly; -- often in a bad   sense.\"\n" +
//                "\"To relinquish all claim to; -- used when an insured   person gives up to underwriters all claim to the property covered by a   policy, which may remain after loss or damage by a peril insured   against.\"\n" +
//                "\"Abandonment; relinquishment.\"\n" +
//                "\"A complete giving up to natural impulses; freedom from   artificial constraint; careless freedom or ease.\"";
//   String word = "ABANDON";
//   String type = ",v. t.";
//        ArrayList<Integer> pages = new ArrayList<>();
//        pages.add(45);
//        pages.add(80);
//    }

}
