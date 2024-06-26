import java.io.File;
import java.io.IOException;

class FileTree {
    FileNode root;

    public FileTree() {
        this(System.getProperty("user.dir"));
    }

    public FileTree(String directory) {
        crawl(new File(directory));
    }

    public FileTree(File file) {
        root = null;
        crawl(file);
    }   

    public boolean insert(String file) {
        return insert(new File(file));
    }

    public boolean insert(File file) {
        if(file.exists()) return false;
        try {
            file.createNewFile();
            crawl(file);
            return true;
        } catch(IOException io) {
            io.printStackTrace();
            return false;
        }
    }

    public boolean find(String file) {
        return find(new File(file));
    }

    public boolean find(File file) {
        interface searchTree {
            default boolean search(FileNode node, File file) {
                if(node == null) 
                    return false;
                if(node.file.equals(file.getName())) 
                    return true;

                return search(node.next, file);
            }
        }

        return (new searchTree() {}).search(root, file);
    }

    public boolean empty() {
        return root == null;
    }

    public void print() {
        print(root);
    }

    private void print(FileNode node) {
        if(node == null) return;
        System.out.printf("%s\n", node);
        print(node.next);
    }

    private void crawl(File file) {
        FileNode ptr = new FileNode(file.getName());
    
        if(empty()) {
            root = ptr;
        } else {
            FileNode curr = root;
            while(curr.next != null) 
                curr = curr.next;
            curr.next = ptr;
        }

        if(!file.isDirectory()) return;
        
        File[] subFiles = file.listFiles();
        for(File f : subFiles)
            crawl(f);
    }    


    class FileNode {
        boolean isDir;
        FileNode next;
        String file;
        String path;

        public FileNode(String file) {
            this(new File(file));
        }

        public FileNode(File file) {
            this.file = file.getName();
            isDir = file.isDirectory();
            next = null;
        }
        
        public String toString() {
            return file;
        }
    }
}