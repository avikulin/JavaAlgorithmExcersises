package YTester;


/**
 * Container class for test-case
 * @param <T> Type of the input dataset
 * @param <R> Type of the result dataset
 */
public class YTestCase< T, R> {
    String Id;
    Boolean hasId = false;
    R input;
    T expectedResult;


    /**
     * Constructor for general test-case
     * @param input_data    Input dataset
     * @param expectedResult    Dataset for Expected result
     */
    public YTestCase(R input_data, T expectedResult) {
        this.input = input_data;
        this.expectedResult = expectedResult;
        this.Id = "";
        this.hasId = false;
    }


    /**
     * Constructor for identified test-case
     * @param ID    Test-case ID
     * @param input_data    Input dataset
     * @param expectedResult    Dataset for Expected result
     */
    public YTestCase(String ID, R input_data, T expectedResult) {
        this.Id = ID;
        this.input = input_data;
        this.expectedResult = expectedResult;
        this.hasId = true;
    }
}

