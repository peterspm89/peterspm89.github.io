

public class Main {

    public static void main(String[] args) {

        WordSearch wordSearch = new WordSearch();
        wordSearch.findAll();

        String[] output = wordSearch.getOutput();

        for (String s: output
             ) {
            System.out.println(s);
        }

    }
}
