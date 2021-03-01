import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        String srcFolder = "src/main/java/sortedMapSrc";
        String dstFolder = "src/main/java/sortedMapDst";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        int step = Objects.requireNonNull(files).length / Runtime.getRuntime().availableProcessors();

        int var = 0;
        File[] temp = new File[step];

        for (File file : files) {
            temp[var++] = file;
            if (var == step) {
                new ImageResize(temp,dstFolder,start).start();
                temp = new File[step];
                var = 0;
            }
        }
    }
}
