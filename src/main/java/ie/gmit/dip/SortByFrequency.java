package ie.gmit.dip;

import java.util.Comparator;

/**
 * Compares WordDetails by number of pages word occurs on. Use For sorting.
 */
public class SortByFrequency implements Comparator<WordDetail> {

    @Override
    public int compare(WordDetail o1, WordDetail o2) {
        int s1 = o1.getPages().size();
        int s2 = o2.getPages().size();
        return s1 - s2;
    }
}
