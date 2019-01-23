import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void TestOffByOne(){
        assertFalse(offByOne.equalChars('a', 'b'));
    }
}
