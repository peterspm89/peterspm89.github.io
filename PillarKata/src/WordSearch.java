import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    /// The words located on the first line of the input file that specify
    /// what we have to find.
    private String[] words;

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /// Public getter
    public String[] getWords() {
        return words;
    }
}
