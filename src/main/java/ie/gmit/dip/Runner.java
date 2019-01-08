package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Runner {

    public static void main(String[] args) throws Exception {
        // 1 read args
        // 0 - query
        // 1 - dictionary
        // 2 - stopwords

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
        Set<String> strings = index.keySet();
        for (String s : strings){
            WordDetail wordDetail = index.get(s);
            String s1 = wordDetail.toString();
            System.out.println(s1);


            break;
        }
        System.out.println();


        String sampleDef = "\"To cast or drive out; to banish; to expel; to reject.\"\n" +
                "\"To give up absolutely; to forsake entirely ; to   renounce utterly; to relinquish all connection with or concern on; to   desert, as a person to whom one owes allegiance or fidelity; to quit;   to surrender.\"\n" +
                "\"Reflexively: To give (one's self) up without attempt at   self-control; to yield (one's self) unrestrainedly; -- often in a bad   sense.\"\n" +
                "\"To relinquish all claim to; -- used when an insured   person gives up to underwriters all claim to the property covered by a   policy, which may remain after loss or damage by a peril insured   against.\"\n" +
                "\"Abandonment; relinquishment.\"\n" +
                "\"A complete giving up to natural impulses; freedom from   artificial constraint; careless freedom or ease.\"";
   String word = "ABANDON";
   String type = ",v. t.";
        ArrayList<Integer> pages = new ArrayList<>();
        pages.add(45);
        pages.add(80);
    }

}
