class Trie:
    """
    A class used to represent a Trie (Prefix Tree)

    Methods:
    insert(word)
        Insert the word into the trie

    search(word)
        Search for the word in the trie

    startsWith(prefix)
        Check if the prefix exists in the trie
    """
    class TrieNode:
        """
        A class used to represent the node of the Trie

        Attributes:
        char : str
            A single char in the TrieNode
        """
        def __init__(self, char: str = ''):
            self.char = char
            self.children: dict[Trie.TrieNode] = {}
            self.end: bool = False
        
    def __init__(self):
        self.root = self.TrieNode()

    def insert(self, word: str) -> None:
        """
        Insert the word into the Trie
        """
        currNode = self.root
        for ch in word:
            if ch not in currNode.children:
                currNode.children[ch] = self.TrieNode(ch)
            currNode = currNode.children[ch]
        currNode.end = True

    def search(self, word: str) -> bool:
        """
        Search for the word in the Trie
        """
        currNode = self.root
        for ch in word:
            if ch not in currNode.children:
                return False
            currNode = currNode.children[ch]
        return currNode.end
    
    def startsWith(self, prefix: str) -> bool:
        """
        Check if the previously inserted word has the prefix
        """
        currNode = self.root
        for ch in prefix:
            if ch not in currNode.children:
                return False
            currNode = currNode.children[ch]
        return True