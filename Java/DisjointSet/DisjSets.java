class DisjSets {
    int[] s;

    public DisjSets(int numItems) {
        s = new int[numItems];
        for(int i = 0; i < numItems; i++)
            s[i] = -1;
    }


    public void union(int root1, int root2) {
        if(s[root2] < s[root1]) {
            s[root1] = root2;
        } else {
            if(s[root1] == s[root2])
                s[root1]--;
            s[root2] = root1;
        }
    }

    public void print() {
        for(int i = 0; i < s.length; i++)
            System.out.printf("%d: %d\n", i, s[i]);
    }
}

class DisjMain {
    public static void main(String[] args) {
        DisjSets disj = new DisjSets(17);
        disj.union(1, 2);
        disj.union(3, 4);
        disj.union(3, 5);
        disj.union(1, 7);
        disj.union(3, 6);
        disj.union(8, 9);
        disj.union(1, 8);
        disj.union(3, 10);
        disj.union(3, 11);
        disj.union(3, 12);
        disj.union(3, 13);
        disj.union(14, 15);
        disj.union(16, 0);
        disj.union(14, 16);
        disj.union(1, 3);
        disj.union(1, 14);
        disj.print();
    }
}