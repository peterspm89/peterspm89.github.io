import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordSearchTest {

    private WordSearch wordSearch;

    @BeforeEach
    public void setUp() {
        /// At this point, I am using the input file that came in the Pillar Technology Kata.
        /// which is listed below if you expand the region.
        //region Sample Input file
        /*
        BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA
        U,M,K,H,U,L,K,I,N,V,J,O,C,W,E
        L,L,S,H,K,Z,Z,W,Z,C,G,J,U,Y,G
        H,S,U,P,J,P,R,J,D,H,S,B,X,T,G
        B,R,J,S,O,E,Q,E,T,I,K,K,G,L,E
        A,Y,O,A,G,C,I,R,D,Q,H,R,T,C,D
        S,C,O,T,T,Y,K,Z,R,E,P,P,X,P,F
        B,L,Q,S,L,N,E,E,E,V,U,L,F,M,Z
        O,K,R,I,K,A,M,M,R,M,F,B,A,P,P
        N,U,I,I,Y,H,Q,M,E,M,Q,R,Y,F,S
        E,Y,Z,Y,G,K,Q,J,P,C,Q,W,Y,A,K
        S,J,F,Z,M,Q,I,B,D,B,E,M,K,W,D
        T,G,L,B,H,C,B,E,C,H,T,O,Y,I,K
        O,J,Y,E,U,L,N,C,C,L,Y,B,Z,U,H
        W,Z,M,I,S,U,K,U,R,B,I,D,U,X,S
        K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B
        */
        //endregion

        wordSearch = new WordSearch();
    }

    @Test
    public void whenWordSearchIsCreated_VerifyTheWordsToFindAreIdentified() {
        /// My first test will simply instantiate an instance of WordSearch and
        // make sure it returns the expected list of words to find.

        /// Create the expected result.
        final String[] expectedWords = ("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA").split(",");

        assertArrayEquals(expectedWords, wordSearch.getWords());
    }

    @Test
    public void whenWordSearchIsCreated_VerifyTheInitialOutputIsEmpty() {
        /// This second test makes sure that after the WordSearch is created the output is
        // set to an empty string array.

        /// Create the expected result.
        final String[] expectedOutput = new String[0];

        assertArrayEquals(expectedOutput, wordSearch.getOutput());
    }

    @Test
    public void whenWordSearchIsCreated_VerifyTheDimensionsOfGridAreIdentified() {
        /// This third test validates the dimensions of the grid from the input file.
        /// Per the kata spec, the input files will be a square, so no validation is needed.
        /// This test assures that I am reading and initializing my grid correctly.

        /// Create the expected result.
        final int expectedDimensions = 15;

        assertEquals(expectedDimensions, wordSearch.getHeight());
        assertEquals(expectedDimensions, wordSearch.getWidth());
    }

    @Test
    public void whenSearchingEachRowHorizontalForward_AreAnyWordsPresent() {
        /// Test if any of the rows contains one of the words we want to find.
        /// containsWordHorizontallyForward will return true if one of the words
        // is present in one of the rows.

        /// Create the expected result.
        final boolean expectedResult = true;

        assertEquals(expectedResult, wordSearch.containsWordHorizontallyForward());
    }

}