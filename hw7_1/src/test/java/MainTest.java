import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 20.11.2016.
 */
public class MainTest {

    private String rootPath = "C:\\Users\\KatyaKos\\Desktop\\Java\\Practice\\Progs\\hw7_1";
    private String testPath = "C:\\Users\\KatyaKos\\Desktop\\Java\\Practice\\Progs\\hw7_1\\test";
    private String pattern = ".....";
    private Path testDirectory;
    private Path newDirectory;

    @Before
    public void setUp() throws Exception {
        testDirectory = Paths.get(testPath);
    }

    @Test
    public void extractingTest() throws Exception {
        Main.main(new String[]{rootPath, pattern});
        newDirectory = Paths.get(rootPath + "\\Extracted_files");

        long newDirectoriesNumber = Files.walk(newDirectory).filter(path -> Files.isDirectory(path) && path.getFileName().toString().startsWith("new_")).count();
        long testDirectoriesNumber = Files.walk(testDirectory).filter(path -> Files.isDirectory(path) && path.getFileName().toString().startsWith("new_")).count();
        assertEquals(newDirectoriesNumber, testDirectoriesNumber);

        Set<Path> testFiles = Files.walk(testDirectory).collect(Collectors.toCollection(HashSet::new));
        Set<Path> newFiles = Files.walk(newDirectory).collect(Collectors.toCollection(HashSet::new));
        assertEquals(testFiles.size(), newFiles.size());

        Map<Path, Integer> newFound = new HashMap<>();
        for (Path file : newFiles) {
            newFound.put(file, 0);
        }

        int equalsNumber = 0;
        for (Path testFile : testFiles) {
            for (Path newFile : newFiles) {
                if (newFound.get(newFile) == 1) {
                    continue;
                }
                if (!Files.isDirectory(testFile) && (!Files.isDirectory(newFile))) {
                    if (!testFile.getFileName().toString().equals(newFile.getFileName().toString())) {
                        continue;
                    }
                    byte[] testBytes = Files.readAllBytes(testFile);
                    byte[] newBytes = Files.readAllBytes(newFile);
                    if (Arrays.equals(testBytes, newBytes)) {
                        equalsNumber++;
                        newFound.replace(newFile, 0, 1);
                    }

                }
            }
        }

        assertEquals(equalsNumber, testFiles.size() - testDirectoriesNumber - 1);
    }


}