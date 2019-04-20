import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    /// The words located on the first line of the input file that specify
    /// what we have to find.
    private String[] words;

    /// The output that will consist of the coordinates of each letter found.
    private String[] output;

    /// The grid of single character values.
    private char[][] grid;

    public WordSearch() {

        /// Create a temporary List of Strings that will hold the contents of the input
        // file until we are ready to set our member properties.
        /// Not sure what the dimensions are for the grid, so I am using a container
        /// that dynamically grows.
        List<String> lines = new ArrayList();


        /// Open input file and get contents.
        try {

            /// Open and read from input file.
            /// Right now, I am hard-coding the input file.
            FileReader fileReader = new FileReader("sample_input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            /// Add each input file line if they do not equal null.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }


        /// Parse contents
        try {

            words = lines.get(0).split(",");
            output = new String[0];

            grid = new char[lines.size()-1][lines.size()-1];

            String line;
            for (int i = 1; i < lines.size(); i++) {

                /// Remove commas.
                line = lines.get(i).replaceAll(",","");

                /// Convert String to character array.
                grid[i-1] = line.toCharArray();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /// Public getter
    public String[] getWords() {
        return words;
    }

    /// Public getter
    public String[] getOutput() {
        return output;
    }

    /// Public getter
    public int getHeight() {
        return grid[0].length;
    }

    /// Public getter
    public int getWidth() {
        return grid.length;
    }

    /// Returns true if one of the words is found by search the grid's
    // rows by searching horizontally forward.
    public ArrayList<String> containsWordHorizontallyForward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        String line;
        int index;
        String formattedOutput;
        for (int x = 0; x < this.getHeight(); x++) {

            line = new String(grid[x]);
            for (String word : words) {

                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word.charAt(0));
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", (index+y), x);
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }
        }

        return wordsFound;
    }
}