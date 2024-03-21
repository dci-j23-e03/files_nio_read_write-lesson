import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class OurSimpleFileVisitor extends SimpleFileVisitor<Path> {

  private final String fileToFind;

  public OurSimpleFileVisitor(String fileToFind) {
    this.fileToFind = fileToFind;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    String filePathString = file.toAbsolutePath().toString();

    if (filePathString.endsWith(fileToFind)) {
      // we have found our file
      System.out.println("File found at path: " + filePathString);
      return FileVisitResult.TERMINATE;
    }
    System.out.println("Not the file we are looking for: " + filePathString);
    return FileVisitResult.CONTINUE;
  }
}
