from FSM import FSM

class DFA(FSM):
    """
    A class used to represent a Deterministic Finite Automaton
    inherited from the FSM class

    Attributes
    ----------
    states: set[str]
        A set of states in the finite automaton
    alphabet: set[str]
        A set of inputs in the finite automaton
    startState: str
        The starting state of the FSM

    Methods
    -------
    addTransition(src: str, dest: str, letter: str)
        Creates a transition from one state to the 
        next state on an input
    validate(inputStr: str)
        Check if the given input string passes the DFA machine
    """

    
    def __init__(self, states: set[str], alphabet: set[str], startState: str):
        """
        Instantiate the DFA with a set of states,
        a set of alphabet, and the start state

        Parameters
        ----------
        states: set[str]
            A set of states in the finite automaton
        alphabet: set[str]
            A set of inputs in the finite automaton
        startState: str
            The starting state of the FSM

        Raises
        ------
        ValueError
            There exists an epsilon ε in the set of alphabet
        """
        
        if 'ε' in alphabet:
            raise ValueError('ε is invalid for a DFA')

        super().__init__(states, alphabet, startState)

    def addTransition(self, src: str, dest: str, letter: str):
        """
        Create a transition from the src state to the dest state
        on an input letter

        Parameters
        ----------
        src: str
            The source state
        dest: str
            The destination state
        letter: str
            The input that the src state will use to
            transition to the dest state.

        Raises
        ------
        ValueError 
            Input does not exist in the set of alphabet
            The source and destination states does not exist in the set of states
            A transition already exists between the src and dest states
        """
        
        if letter not in self.alphabet:
            raise ValueError('Input must exist in the alphabet')
        
        if src not in self.states or dest not in self.states:
            raise ValueError('src state and dest state must exist in the states')
    
        if (src, letter) in self.transitions and letter in self.alphabet:
            raise ValueError(f'A transition from {src} to {dest} already exists on \'{letter}\'!')

        self.transitions[(src, letter)] = dest

    def validate(self, inputStr: str, debug: bool = False) -> bool:
        """
        Validate if the input string is accepted
        by the DFA machine.

        Parameters
        ----------
        inputStr: str
            An input string to be read by the DFA
        """

        currState = self.startState
        for letter in inputStr:
            prevState = currState
            if (currState, letter) not in self.transitions:
                return False
            currState = self.transitions[(currState, letter)]
            if debug:
                print(f'{prevState} -> {currState} on {letter}')

        return currState in self.finalStates
    
