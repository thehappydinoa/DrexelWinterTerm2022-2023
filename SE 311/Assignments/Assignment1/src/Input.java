import java.io.IOException;
import java.util.ArrayList;

abstract class Input {
    abstract ArrayList<String> readlines() throws IOException;
    abstract KwicIndex read() throws IOException;
}
