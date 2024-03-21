import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    // exists/notExists
    Path desktop = Path.of("/Users/dzenan/Desktop");
    System.out.println("Desktop path exists: " + Files.exists(desktop));

    Path desktop2 = Path.of("/Users/dzenan/Desktop2");
    System.out.println("Desktop 2 path exists: " + Files.exists(desktop2));

    // createDirectory
    Path dir = Paths.get("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir");
    Path createdDir = null;
    if (Files.notExists(dir)) {
      try {
        createdDir = Files.createDirectory(dir);
        System.out.println(createdDir);
      } catch (IOException e) {
        System.out.println("Directory creation failed: " + e.getMessage());
      }
    }
    System.out.println(dir.equals(createdDir));
    System.out.println(createdDir);
    System.out.println(dir);

    // copy (StandardCopyOption.COPY_ATTRIBUTES is default option)
    Path dirCopy = Paths.get("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dirCopy");

    Path dirCopyCreated = null;
    if (Files.exists(dir)) {
      dirCopyCreated = Files.copy(dir, dirCopy, StandardCopyOption.REPLACE_EXISTING);
    }
    System.out.println(dirCopy.equals(dirCopyCreated));

    // move (and rename)
    Path newDirCopy = Paths.get("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir/dirCopy");
    Files.move(dirCopy, newDirCopy, StandardCopyOption.REPLACE_EXISTING); // move

    Path newDirectory = Paths.get("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir/newDirectory");
    Files.move(newDirCopy, newDirectory, StandardCopyOption.REPLACE_EXISTING); // rename

    Files.move(newDirectory, dirCopy); // move and rename

    // delete
    if (Files.exists(dirCopy)) {
      Files.delete(dirCopy);
    }

    // createFile (everything is the same in terms of operations for files as for directories)
    Path file = Paths.get("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir/file");
//    if (Files.notExists(file)) {
//      Files.createFile(file);
//    }

    // traversing a directory (search for some file example)
    Path rootPath = Path.of("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir");
    String fileToFind = "file";

    Files.walkFileTree(rootPath, new OurSimpleFileVisitor(fileToFind));

    // reading files
    Path fileVisitor = Path.of("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/src/OurSimpleFileVisitor.java");

    byte[] fileVisotorBytes = Files.readAllBytes(fileVisitor);
    System.out.println("\n========Start of OurSimpleFileVisitor.java===========");
    System.out.println(new String(fileVisotorBytes));
    System.out.println("\n========End of OurSimpleFileVisitor.java===========");

    List<String> lines = Files.readAllLines(fileVisitor);
    System.out.println("\n========Start of OurSimpleFileVisitor.java===========");
    for (String line : lines) {
      System.out.println(line);
    }
    System.out.println("\n========End of OurSimpleFileVisitor.java===========");


    String classFile = Files.readString(fileVisitor);
    System.out.println("\n========Start of OurSimpleFileVisitor.java===========");
    System.out.println(classFile);
    System.out.println("\n========End of OurSimpleFileVisitor.java===========");

    // writing to files
    Path file2 = Path.of("/Users/dzenan/Desktop/DCI/projects/FilesNIO2/dir/file2.txt");
    String content = "Hello students! Hope you are all having a great day!\n";

    Files.write(file2, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    Files.write(file2, List.of(content), StandardOpenOption.APPEND);
    Files.writeString(file2, content, StandardOpenOption.APPEND);

    // converting
    File file2FileObject = file2.toFile();
    System.out.println(file2FileObject);

    file2 = file2FileObject.toPath();
    System.out.println(file2);
  }
}