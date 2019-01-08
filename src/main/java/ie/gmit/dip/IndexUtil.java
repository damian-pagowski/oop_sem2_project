package ie.gmit.dip;

import java.util.ArrayList;

public class IndexUtil {

    public static void main(String[] args) {
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
        WordDetail wordDetail = new WordDetail();
        wordDetail.setWord(word);
        wordDetail.setDefinition(sampleDef);
        wordDetail.setWordType(type);
        wordDetail.setPages(pages);
        IndexUtil util = new IndexUtil();
        util.printIndexEntry(wordDetail);
    }

    private void printIndexEntry(WordDetail wordDetail) {
        String separator = "----------------------------------------";
        System.out.println(separator);
        System.out.println(String.format("# Word: %s", wordDetail.getWord()));
        System.out.println(String.format("# Word Type: %s", wordDetail.getWordType()));
        System.out.println("# Definition: ");
        String[] split = wordDetail.getDefinition().split("\n");
        for (String line : split){
            print5WordsInLine(line);
        }
        System.out.println(String.format("# Pages: ", wordDetail.getWord()));


    }
//    public void print5WordsInLine(String line){
//        String[] split = line.split(" ");
//        if (split.length<=5){
//            System.out.println(line);
//        }else{
//            for (int i = 0; i < split.length; i++){
//                if (i % 5  == 0){
//                    System.out.print("\n");
//                }
//                System.out.print(split[i] + " ");
//            }
//        }
//    }

    String splitString(String text, int wordsPerLine)
    {
        final StringBuilder newText = new StringBuilder();

        final StringTokenizer wordTokenizer = new StringTokenizer(text);
        long wordCount = 1;
        while (wordTokenizer.hasMoreTokens())
        {
            newText.append(wordTokenizer.nextToken());
            if (wordTokenizer.hasMoreTokens())
            {
                if (wordCount++ % wordsPerLine == 0)
                {
                    newText.append(LINEBREAK);
                }
                else
                {
                    newText.append(WHITESPACE);
                }
            }
        }
        return newText.toString();
    }
}
}
