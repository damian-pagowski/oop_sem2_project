package ie.gmit.dip;

public class TestRunner {
    public static void main(String[] args) {
        /*
        Your application should parse a specified e-book
         build an index by examining every word in each sentence.
         A number of e-books,
         in TXT format, are available on Moodle - you can assume that a
         page of text corresponds to 40 lines of text or blank spaces.
         Your API should also parse and process the 15Mb file “dictionary.csv”,
         which contains a set of over 176,000 words and their definitions.

Your software should provide a useful abstraction (in the form of interfaces)
of the type of functionality you would expect from an index,
e.g. a successful search for a word should return a list of page numbers.
 Extra marks will be given for designs that exhibit more behavior. Please note the following:

• The user of the API should not know how you are building the index,
i.e. you should encapsulate your data structure.


• All API operations on the index should be case insensitive.
 The definitions of words read from the dictionary should only be added to words that exist in the text or URL
  being parsed.
  • A list of ignore words have been provided on Moodle.
  Your API should exclude these words from the index.
  • The API should contain a method that outputs the index, with indentations, as text.
   It should also expose a suite of methods appropriate to an index such as the following (indicative only):
    o Return all words in sorted / reverse sorted order (maybe print 5 words / line)
    o The total number of unique words. o The top n most frequent / infrequent words. Methods such as these can be implemented easily using the Java Collections Framework. Extra marks will be given for relevant methods that work AND have been listed in the README.txt. • Document your code using the JavaDoc standard. Eclipse provides full support for the JavaDoc standard, including intelli-sense drop down suggestions. • A command-line menu-driven user interface should be launched by a class called Runner containing the main() method. The user interface should provide a set of options for parsing and processing text resources and exercising the methods of the classes you design. • Provide a UML class diagram of your design (a photograph of a sketch will suffice).

Note that the whole point of this assignment is for you to demonstrate an understanding of the principles of object-oriented design by using abstraction, encapsulation, composition, inheritance and polymorphism WELL throughout the application. Please pay particular attention to how your application must be packaged and submitted.
         */

    // 1 start from parsilng stopwords - words to be ignored

    }
}
