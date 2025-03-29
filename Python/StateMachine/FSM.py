from abc import abstractmethod

class FSM:
    """
    A class used to represent a Finite State Machine

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
    addTransitions()
        Abstract method implemented by the DFA and NFA subclasses
    addFinal(state: str)
        Adds the state to the set of final states
    validate()
        Abstract method implemented by the DFA and NFA subclasses
    print()
        Output the set of states, the set of alphabet, the set of transitions,
        the start state, and the set of final states
    """

    def __init__(self, states: set[str], alphabet: set[str], startState: str) -> None:
        """
        Instantiate the FSM with a set of states,
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
            The set of states is empty
            The set of input alphabet is empty
            The start state does not exist in the set of states
        """
        
        if not states:
            raise ValueError('The set of states cannot be null')
        
        if not alphabet:
            raise ValueError('The set of input alphabet cannot be null')

        if startState not in states:
            raise ValueError('Start state must exist in the set of states')

        self.states = set(states)
        self.alphabet = set(alphabet)
        self.transitions = {}
        self.startState = startState
        self.finalStates = set()
    
    @abstractmethod
    def addTransition(self):
        """
        An abstract method used to create a transition
        from one state to the another state on an input

        Raises
        ------
        NotImplementedError
            Attempted to call method from the parent object
        """

        raise NotImplementedError('Call the method from DFA or NFA')
    
    def addFinal(self, state: str):
        """
        Add a state to the set of final states

        Parameters
        ----------
        state: str
            A FSM state to be added to the set of final state

        Raises
        ------
        ValueError
            The state does not exist in the set of states
        """

        if state not in self.states:
            raise ValueError('state must exist in the set of states')
        
        self.finalStates.add(state)

    @abstractmethod
    def validate(self) -> bool:
        """
        Raises
        ------
        NotImplementedError
            Attempted to call method from the parent object
        """

        raise NotImplementedError('Call the method from DFA or NFA')
        
    def print(self):
        """
        Output the following:
        - Set of states
        - Set of alphabets
        - Set of state transitions
        - Start state
        - Set of final states
        """

        print(f'Set of States: {self.states}')
        print(f'Set of Input Alphabets: {self.alphabet}')
        print('Set of Transitions:')
        for t, v in self.transitions.items():
            print(f'\t{t} = {v}')
        print(f'Start State: {self.startState}')
        print(f'Set of Final States: {self.finalStates}')