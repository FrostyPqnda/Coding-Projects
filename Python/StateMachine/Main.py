import DFA as d
import sys
sys.stdout.reconfigure(encoding='utf-8')

if __name__ == '__main__':
    states: set[str] = {'q0', 'q1', 'q2', 'q3', 'q4'}
    alphabet: set[str] = {'a', 'b', 'c'}

    dfa = d.DFA(states=states, alphabet=alphabet, startState='q0', finalStates=states)
    dfa.addTransition('q0', 'q1', 'a')
    dfa.addTransition('q1', 'q2', 'b')
    dfa.addTransition('q2', 'q1', 'a')
    dfa.addTransition('q0', 'q3', 'b')
    dfa.addTransition('q3', 'q4', 'a')
    dfa.addTransition('q4', 'q3', 'b')
    
    dfa.print()
    print(dfa.validate(sys.argv[1], debug=True))