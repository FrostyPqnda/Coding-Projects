from DFA import DFA
import sys
sys.stdout.reconfigure(encoding='utf-8')

if __name__ == '__main__':
    states=['q0', 'q1']
    alphabet=['a', 'b']

    dfa = DFA(states=states, alphabet=alphabet, startState='q0')
    dfa.addTransition('q0', 'q0', 'a')
    dfa.addTransition('q0', 'q1', 'b')
    dfa.addTransition('q1', 'q1', 'a')
    dfa.addTransition('q1', 'q0', 'b')
    dfa.addFinal('q0')
    dfa.print()
    print(dfa.validate(sys.argv[1], debug=True))