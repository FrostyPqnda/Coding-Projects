from Trie import Trie
import requests
import sys

class AutoSuggest:
    """
    A class used to represent the AutoSuggest object

    Attributes:
    word : str:
        The word to derive suggestions from

    Methods:
    printAutoSuggestions(limit=None)
        Print all possible suggested words
    """
    def __init__(self, word: str):
        self.word = word.lower()

        try:
            response = requests.get('https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt')
        except requests.exceptions.HTTPError as errh:
            print('HTTP Error')
            print(errh.args[0])
        
        words = [word.decode().lower() for word in response.content.splitlines()]
    
        self.trie = Trie()
        for word in words:
            self.trie.insert(word)

    def printAutoSuggestions(self, limit: int = None) -> None:
        """
        Print all possible suggestions 

        Parameters:
        limit : int
            The size of the suggestion pool
            A limit size less than 1 means get all suggestions
        """
        def suggest(node: Trie.TrieNode, word: str):
            """
            Recursively find the suggested words

            Parameters:
            node : Trie.TrieNode
                The node of the Trie
            word : str
                The word to build the suggestion from
            """
            if node.end:
                suggestions.append(word)
        
            for (k, v) in node.children.items():
                suggest(v, word + k)

        suggestions = []
        node = self.trie.root
        for ch in self.word:
            if ch not in node.children:
                return
            node = node.children[ch]
        
        if not node.children:
            return

        suggest(node, self.word)
        limit = int(limit) if limit else None
        suggestions = suggestions[:min(limit, len(suggestions)) if limit and limit > 0 else None]
        
        for suggestion in suggestions:
            print(suggestion)

if __name__ == '__main__':
    if not (2 <= len(sys.argv) <= 3):
        print('Usage: python AutoSuggest.py <word> <limit=None>')
        sys.exit(-1)

    suggest = AutoSuggest(sys.argv[1])
    limit = sys.argv[2] if len(sys.argv) == 3 else None
    suggest.printAutoSuggestions(limit)