import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Takes path and regex as arguments. Extracts all files matching given pattern from all zip archives in the given directory.
 * @author KatyaKos
 */
public class Main {
    /**
     * Where everything starts. Gets arguments.
     * @param args path and pattern
     * @throws IOException any exception that can be thrown during extracting, "walking", etc.
     */
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile(args[1]);
        Path root = Paths.get(args[0]);
        RegexInZipSearch walk = new RegexInZipSearch(pattern);
        walk.extractFromZip(root);
    }

    /**
     * Class that extends SimpleFileVisitor and overrides it's visiting method as we need.
     */
    private static class RegexInZipSearch extends SimpleFileVisitor<Path> {
        private final Pattern pattern;
        private Path newDirectory;

        public RegexInZipSearch(Pattern pattern) {
            this.pattern = pattern;
        }

        /**
         * Starts extracting process. Finds all zip files and gives them to Visitor.
         * @param root parent path where to find zips
         * @throws IOException if creating or walking goes wrong
         */
        private void extractFromZip(Path root) throws IOException {
            Path rootDirectoty = root.toAbsolutePath().resolve("Extracted_files");
            Files.walk(root).filter(path -> Files.isRegularFile(path) && path.getFileName().toString().endsWith(".zip")).forEach(path -> {
                newDirectory = rootDirectoty.toAbsolutePath().resolve("new_" + path.getFileName().toString());
                try {
                    Files.createDirectories(newDirectory);
                    Files.walkFileTree(path, this);
                } catch (IOException e) {
                    System.out.println("No such directory");
                }
            });
        }

        /**
         * What we do with each file in walking tree.
         * @param file path we are working with
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
            ZipFile zip = new ZipFile(file.toFile());
            Enumeration<? extends ZipEntry> entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    Path canonical = Paths.get(entry.getName()).getFileName();
                    if (pattern.matcher(canonical.toString()).matches()) {
                        Files.copy(zip.getInputStream(entry), newDirectory.resolve(canonical.toString()), REPLACE_EXISTING);
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
