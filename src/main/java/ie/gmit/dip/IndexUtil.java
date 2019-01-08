package ie.gmit.dip;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
        for (String line : split) {
            System.out.println(formatLine(line, 5));
        }
        System.out.print("# Pages: ");
        for (int page : wordDetail.getPages()) {
            System.out.print(page + " ");

        }
    }

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

