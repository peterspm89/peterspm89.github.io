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

    /// Returns the words found and their coordinates searching horizontally forward.
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
                    index = line.indexOf(word);
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

    /// Returns the words found and their coordinates searching horizontally forward.
    public ArrayList<String> containsWordVerticallyForward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        int index;
        String formattedOutput;
        String column;
        for (int x = 0; x < this.getHeight(); x++) {

            column = getColumnAtIndex(x);
            for (String word : words) {
                if (column.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = column.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", x, (index+y));
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }

        }

        return wordsFound;
    }

    /// Auxiliary method supporting containsWordVerticallyForward
    private String getColumnAtIndex(Integer column) {
        String temp = new String();
        for (int i = 0; i < this.getWidth(); i++) {
            temp += grid[i][column];
        }

        return temp;
    }

    /// Returns the words found and their coordinates searching horizontally forward.
    public ArrayList<String> containsWordHorizontallyBackward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        String line;
        int index;
        String formattedOutput;
        for (int x = 0; x < this.getHeight(); x++) {

            line = new String(grid[x]);
            java.lang.StringBuilder _reverse = new java.lang.StringBuilder();
            _reverse.append(line);
            _reverse = _reverse.reverse();
            line = _reverse.toString();

            for (String word : words) {

                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", (this.getWidth()-index-y-1), x);
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }
        }

        return wordsFound;
    }

    /// Returns the words found and their coordinates searching horizontally forward.
    public ArrayList<String> containsWordVerticallyBackward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        int index;
        String formattedOutput;
        String column;
        for (int x = 0; x < this.getHeight(); x++) {

            column = getColumnAtIndex(x);
            java.lang.StringBuilder _reverse = new java.lang.StringBuilder();
            _reverse.append(column);
            _reverse = _reverse.reverse();
            column = _reverse.toString();

            for (String word : words) {
                if (column.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = column.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", x, (this.getWidth()-index-y-1));
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }

        }

        return wordsFound;
    }

    /// Returns the words found and their coordinates searching diagonally asc forward.
    public ArrayList<String> containsWordDiagonallyAscForward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        int index;
        String formattedOutput;
        String line;
        /// Words have to be two characters in length.
        for (int x = this.getHeight()-1; x >= 0; x--) {

            line = getDiagonalAscRowAtIndex(x, 0);


            for (String word : words) {
                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", index+x, index+y);
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }

        }

        /// Words have to be two characters in length.
        for (int x = 1; x < this.getWidth(); x++) {

            line = getDiagonalAscRowAtIndex(0, x);


            for (String word : words) {
                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", index+x+y, index+y);
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }

        }


        return wordsFound;
    }

    /// Auxiliary method supporting containsWordDiagonallyForward
    private String getDiagonalAscRowAtIndex(Integer row, Integer column) {
        String temp = new String();
        int i = row;
        int j = column;

        while (i < this.getHeight() && j < this.getWidth()) {

            temp += grid[i][j];

            i++;
            j++;
        }

        return temp;
    }


    /// Returns the words found and their coordinates searching diagonally asc forward.
    public ArrayList<String> containsWordDiagonallyAscBackward() {

        /// Not sure how many words I will find, so I am using a container
        /// that dynamically grows.
        ArrayList<String> wordsFound = new ArrayList();

        int index;
        String formattedOutput;
        String line;
        /// Words have to be two characters in length.
        for (int x = this.getHeight()-1; x >= 0; x--) {

            line = getDiagonalAscRowAtIndex(x, 0);
            java.lang.StringBuilder _reverse = new java.lang.StringBuilder();
            _reverse.append(line);
            _reverse = _reverse.reverse();
            line = _reverse.toString();


            for (String word : words) {
                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", (this.getWidth()-index-y-1), (this.getWidth()-index-y-1));
                    }

                    /// Remove the last character in string, which is an extra comma.
                    formattedOutput = formattedOutput.substring(0, formattedOutput.length()-1);
                    wordsFound.add(formattedOutput);
                }
            }

        }

        /// Words have to be two characters in length.
        for (int x = 1; x < this.getWidth(); x++) {

            line = getDiagonalAscRowAtIndex(0, x);
            java.lang.StringBuilder _reverse = new java.lang.StringBuilder();
            _reverse.append(line);
            _reverse = _reverse.reverse();
            line = _reverse.toString();

            for (String word : words) {
                if (line.contains(word)) {

                    formattedOutput = String.format("%s: ", word);
                    index = line.indexOf(word);
                    for (int y = 0; y < word.length(); y++) {

                        formattedOutput += String.format("(%d,%d),", (this.getWidth()-y-1), word.length()-y-1);
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