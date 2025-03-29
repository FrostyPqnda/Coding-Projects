from FSM import FSM
from collections import deque

class NFA(FSM):
    """
    A class used to represent a Nondeterministic Finite Automaton
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
        Check if the given input string passes the NFA machine
    """

    def __init__(self, states: set, alphabet: set, startState: str):
        """
        Instantiate the NFA with a set of states,
        a set of alphabet, and the start state

        Parameters
        ----------
        states: set[str]
            A set of states in the finite automaton
        alphabet: set[str]
            A set of inputs joined with 'ε' in the finite automaton
        startState: str
            The starting state of the FSM"
        """
        
        super().__init__(states, alphabet, startState)

    def addTransition(self, src: str, dest: str, letter: str = 'ε'):
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

        if (src, letter) not in self.transitions:
            self.transitions[(src, letter)] = []

        self.transitions[(src, letter)].append(dest)
    
    def validate(self, inputStr: str) -> bool:
        """
        Uses a variation of Breadth-First Search
        to validate if the input string is accepted
        by the NFA machine.

        Parameters
        ----------
        inputStr: str
            An input string to be read by the NFA
        """

        def epsilonClosure(states: set[str]):
            """
            Create a set of states that have an
            ε-transitions
            """
            closure = set(states)
            queue = deque(states)

            while queue:
                state = queue.popleft()
                if (state, 'ε') in self.transitions:
                    for nextState in self.transitions[(state, 'ε')]:
                        if nextState not in closure:
                            closure.add(nextState)
                            queue.append(nextState)

            return closure
            
        queue = deque([(epsilonClosure([self.startState]), 0)])

        while queue:
            currStates, index = queue.popleft()

            if index == len(inputStr):
                for state in currStates:
                    if state in self.finalStates:
                        return True
                continue
                
            letter = inputStr[index]
            
            nextStates = set()
            for state in currStates:
                if (state, letter) in self.transitions:
                    nextStates.update(self.transitions[(state, letter)])
                
            nextStates = epsilonClosure(nextStates)

            if nextStates:
                queue.append((nextStates, index + 1))

        return False
    
