package ie.gmit.dip;

import java.util.Comparator;

/**
 *  Compare WordDetails by name. Use for sorting.
 */
public class SortByName implements Comparator<WordDetail> {
    @Override
    public int compare(WordDetail o1, WordDetail o2) {
        String name1 = o1.getWord();
        String name2 = o2.getWord();
        return name1.compareTo(name2);
    }
}