import java.io.File;

public class Main {
    public static void main(String[] args) {
        //File f = new File(args[0]);
        //crawl(args[0]);
        FileTree ft = new FileTree(args[0]);
        ft.print();
        System.out.println(ft.find("A.txt"));
    }

    static void crawl(String directory) {
        crawl(new File(directory), "");
    }

    static void crawl(File f, String indent) {
        System.out.println(indent + f.getName());
        if(!f.isDirectory()) return;

        File[] subFiles = f.listFiles();
        indent += "    ";
        for(File file : subFiles)
            crawl(file, indent);
    }
}
