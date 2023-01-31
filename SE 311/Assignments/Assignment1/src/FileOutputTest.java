import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileOutputTest {

    @Test
    void createDir() throws IOException {
        // Given
        String tmpDir = Files.createTempDirectory("tmpDir").toFile().getAbsolutePath();
        String dirName = tmpDir + "/testDir";
        File dir = new File(dirName);
        assertFalse(dir.exists());

        // When
        FileOutput.createDir(dirName);

        // Then
        assertTrue(dir.exists());
        assertTrue(dir.isDirectory());
        assertTrue(dir.delete());
    }

    @Test
    void write() throws IOException {
        // Given
        String tmpDir = Files.createTempDirectory("tmpDir").toFile().getAbsolutePath();
        String fileName = "test.txt";
        String filePath = tmpDir + "/" + fileName;
        ArrayList<String> lines = new ArrayList<>();
        lines.add("line1");
        lines.add("line2");
        lines.add("line3");
        FileOutput fileOutput = new FileOutput(filePath);
        File file = new File(filePath);
        assertFalse(file.exists());

        // When
        fileOutput.write(lines);

        // Then
        assertTrue(file.exists());
        FileInput fileInput = new FileInput(filePath);
        ArrayList<String> actualLines = fileInput.readlines();
        assertArrayEquals(lines.toArray(), actualLines.toArray());
        assertTrue(file.delete());
    }
}