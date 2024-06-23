import MyImplementations.*;

/**
 * Implementation of a Trie (Prefix Tree)
 */
public class Trie {
    TrieNode root; // Root of the Prefix Tree

    /**
     * Trie()
     */
    public Trie() {
        root = new TrieNode(); // Set root to a TrieNode object
    }

    /**
     * insert(String word)
     * 
     * @param word String object to be inserted into the prefix tree
     */
    public void insert(String word) {
        TrieNode currNode = root;
        for(char ch : word.toCharArray()) {
            if(!currNode.children.containsKey(ch)) {
                currNode.children.put(ch, new TrieNode(ch));
            }
            currNode = currNode.children.get(ch);
        }
        currNode.isLeaf = true;
    }

    /**
     * search(String word)
     * 
     * @param word String object to be searched in the prefix tree 
     * @return True if the word exists in the prefix tree
     */
    public boolean search(String word) {
        TrieNode currNode = root; 
        for(char ch : word.toCharArray()) {
            if(!currNode.children.containsKey(ch)) {
                return false;
            }
            currNode = currNode.children.get(ch);
        }
        return currNode.isLeaf;
    }
    
    /**
     * startsWith(String prefix)
     * 
     * @param prefix Starting word of some string in the tree
     * @return True if prefix is a prefix to some string in the tree
     */
    public boolean startsWith(String prefix) {
        TrieNode currNode = root;
        for(char ch : prefix.toCharArray()) {
            if(!currNode.children.containsKey(ch)) {
                return false;
            }
            currNode = currNode.children.get(ch);
        }
        return true;
    }

    /**
     * print()
     * 
     * Print the prefix tree
     */
    public void print() {
        printTrie(root, "");
    }

    /**
     * printTrie(TrieNode node, String s)
     * 
     * @param node Starting root of the tree
     * @param s String to be built from words in the tree
     */
    private void printTrie(TrieNode node, String s) {
        String curr = s;
        curr += String.valueOf(node.data);
        if(node.isLeaf) {
            System.out.println(curr);
            return;
        } else {
            Stack<TrieNode> stack = new Stack<>();
            Iterator<TrieNode> itr = node.children.values().iterator();
            while(itr.hasNext()) 
                stack.push(itr.next());
            while(!stack.empty()) {
                TrieNode t = stack.pop();
                printTrie(t, curr);
            }
        }
    }

    /**
     * Inner class to store the nodes of the Prefix Tree
     */
    class TrieNode {
        char data; // Data stored in TrieNode
        Map<Character, TrieNode> children; // HashMap stored in the TrieNode
        boolean isLeaf; // TrieNode is a child node

        /**
         * TrieNode()
         * 
         * Default constructor for the Trie class
         * Set data to Character.MIN_VALUE
         */
        public TrieNode() {
            this(Character.MIN_VALUE);
        }

        /**
         * TrieNode(char data)
         * 
         * @param data Character stored in the TrieNode
         */
        public TrieNode(char data) {
            this.data = data; // Set instance's data to data
            this.children = new HashMap<Character, TrieNode>(); // Create a HashMap using data as the root
            this.isLeaf = false; // Set isLeaf to false
        }
    }
}
