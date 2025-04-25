from NFA import NFA
import sys
sys.stdout.reconfigure(encoding='utf-8')

if __name__ == '__main__':
    states=['q0', 'q1', 'q2']
    alphabet=['a', 'b', 'c']

    nfa = NFA(states=states, alphabet=alphabet, startState='q0')
    nfa.addTransition('q0', 'q0', 'a')
    nfa.addTransition('q0', 'q1')
    nfa.addTransition('q1', 'q1', 'b')
    nfa.addTransition('q1', 'q2')
    nfa.addTransition('q2', 'q2', 'c')
    nfa.addFinal('q2')
    nfa.print()
    print(nfa.validate(sys.argv[1], debug=True))