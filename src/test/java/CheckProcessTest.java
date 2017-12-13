import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckProcessTest {

    @Test
    public void checkProcess() {
        assertEquals(true, CheckProcess.isProcessInUse());
    }
}