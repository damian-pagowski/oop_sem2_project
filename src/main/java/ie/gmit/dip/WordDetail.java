package ie.gmit.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordDetail {

    private String word;
    private String wordType;
    private String definition;
    private List<Integer> pages = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordtype) {
        this.wordType = wordtype;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public boolean addPage(Integer integer) {
        return pages.add(integer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordDetail that = (WordDetail) o;
        return Objects.equals(word, that.word) &&
                Objects.equals(wordType, that.wordType) &&
                Objects.equals(definition, that.definition) &&
                Objects.equals(pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, wordType, definition, pages);
    }

    @Override
    public String toString() {
        return "WordDetail{" +
                "word='" + word + '\'' +
                ", wordType='" + wordType + '\'' +
                ", definition='" + definition + '\'' +
                ", pages=" + pages +
                '}';
    }
}
