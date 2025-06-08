from __future__ import annotations
from collections import defaultdict
import numpy as np

class DecisionTree:
    """
    Description
    -----------
    A class representing a Decision Tree Classification model 
    in which each internal (non-leaf) node tests the value of 
    a particular feature. Each leaf node specifies a class label.
    """
    class TreeNode:
        """
        Description
        -----------
        A class representing the node of a DecisionTree
        """
        def __init__(self, name: str, attr_idx: int=None, level: int=0, answer: str=None, child_nodes: list[tuple[str, DecisionTree.TreeNode]]=None, parent_type: str=None, majority: str=None, info_gain: float=None):
            """
            Description
            -----------
            Instantiate the DecisionTree node

            Attributes
            ----------
            name: str
                Name of the attribute being tested
            attr_idx: int
                The index at which the best attribute was found
            level: int
                The level at which the best attribute was found
            answer: str
                The final classification value
            child_nodes: list[DecisionTree.TreeNode]
                A list of children sharing similar features
            parent_type: str
                The feature value used to classify the node
            majority: str
                The majority decision that the node will choose from its parent
            info_gain: float
                A measure of how much the uncertainty decreases after the split
            """
            self.name: str = name
            self.attr_idx: int = attr_idx
            self.level: int = level
            self.answer: str = answer
            self.child_nodes: list[tuple[str, DecisionTree.TreeNode]] = [] if child_nodes is None else child_nodes
            self.parent_type: str = parent_type
            self.majority: str = majority
            self.info_gain: float = info_gain

    def __init__(self, min_samples_split: int = 1, max_depth: int = None):
        """
        Description
        -----------
        Instantiate the decision tree with an optional minimum sample split and
        maximum depth

        Parameters
        ----------
        min_samples_split: int
            Minimum times the decision tree will split on a feature
        max_depth: int
            Maximum height for the decision tree
        """

        # The minimum children for the node
        self.min_samples_split: int = min_samples_split

        # The maximum height of the tree
        self.max_depth: int = max_depth

        # The root of the tree
        self.root: DecisionTree.TreeNode = None

    def fit(self, X: np.ndarray, y: np.ndarray) -> None:
        """
        Description
        -----------
        Build the decision tree using the dataset X and the label y

        Parameters
        ----------
        X: np.ndarray
            The dataset to train the tree
        y: np.ndarray
            The label 
        """
        self.root: DecisionTree.TreeNode = self.build_tree(X, y)

    def predict(self, X: np.ndarray):
        """
        Description
        -----------
        Predict the labels of the dataset

        Parameters
        ----------
        X: np.ndarray
            The dataset to be predicted
        """
        def predict_row(tree: DecisionTree.TreeNode, row: np.ndarray):
            """
            Description
            -----------
            Predict the node classification based on the answer or the majority vote
            given by its parent.

            Parameters
            ----------
            tree: DecisionTree.TreeNode
                A node that will be classified
            row: int
                The row in the dataset where the node is located
            """

            # Go through each of the tree's child nodes
            # and attempt to find the classification value
            # stored in the tree's majority or answer
            while tree.child_nodes:
                # Set the value to a specific value at the attribute index of the row
                value: str = row[tree.attr_idx] 

                # For each child value and node, set the tree to that 
                # child node if the value matches the child value and
                # end the loop.
                # Otherwise, just return the majority vote or the answer
                for child_value, child_node in tree.child_nodes:
                    if value == child_value:
                        tree = child_node
                        break
                else:
                    return tree.majority or tree.answer

            return tree.answer or tree.majority

        return np.array([predict_row(self.root, x) for x in X])

    def accuracy(self, y_pred: np.ndarray, y_true: np.ndarray) -> float:
        """
        Description
        -----------
        Determine how closely the predicted y label matches the true y label

        Parameters
        ----------
        y_pred: np.ndarray
            The predicted y label
        y_true: np.ndarray
            The true y label
        """
        return np.mean(y_pred == y_true)

    def build_tree(self, X: np.ndarray, y: np.ndarray, depth: int=0) -> DecisionTree.TreeNode:
        """
        Description
        -----------
        Construct the decision tree based on the dataset X and 
        the label y

        Parameters
        ----------
        X: np.ndarray
            The dataset
        y: np.ndarray
            The label
        depth: int
            Maximum height
        """
        
        # Compute entropy and information gain
        _, pred_class, maj_class = self.parent_entropy(y)
        best_idx, best_gain = self.information_gain(X, y)

        # Check if all samples belong to one class (stop splitting)
        if pred_class:
            return DecisionTree.TreeNode(
                name='Leaf',
                answer=pred_class,
                level=depth,
                majority=maj_class,
                info_gain=best_gain
            )
        
        # If maximum depth reached or not enough samples to split, return a leaf node
        if (self.max_depth and depth >= self.max_depth) or X.shape[0] < self.min_samples_split:
            return DecisionTree.TreeNode(
                name='Leaf',
                answer=maj_class,
                level=depth,
                majority=maj_class,
                info_gain=best_gain
            )
        
        # Find the best attribute to split on
        tree: DecisionTree.TreeNode = DecisionTree.TreeNode(
            name=f'Attribute {best_idx}',
            attr_idx=best_idx,
            level=depth,
            info_gain=best_gain
        )
        
        # Split the dataset based on the best attribute
        partitions: dict[str, tuple[np.ndarray, np.ndarray]] = self.partition(X, y, best_idx)
        for value, (X_subset, y_subset) in partitions.items():
            child_node = self.build_tree(X_subset, y_subset, depth + 1)
            child_node.parent_type = value
            tree.child_nodes.append((value, child_node))
        
        return tree
    
    def partition(self, X: np.ndarray, y: np.ndarray, attr_idx: int) -> dict[str, tuple[np.ndarray, np.ndarray]]:
        """
        Description
        -----------
        Split the dataset into subsets based on unique values
        of a specific attributes

        Parameters
        ----------
        X: np.ndarray
            The dataset
        y: np.ndarray
            The label
        attr_idx: int
            The attribute at which the dataset will be split at
        """
        
        # Find unique values from the partitioned dataset
        unique_values: np.ndarray = np.unique(X[:, attr_idx])

        # Create a map to which a value key connects to a subset of a dataset and label
        partitions: dict[str, tuple[np.ndarray, np.ndarray]] = defaultdict(list)

        # For each unique values, map a subset for the dataset and label
        # to that value
        for value in unique_values:
            subset_indices = X[:, attr_idx] == value
            partitions[value] = (X[subset_indices], y[subset_indices])

        return partitions
    
    def information_gain(self, X: np.ndarray, y: np.ndarray) -> tuple[int, float]:
        """
        Description
        -----------
        Using entropy to measure uncertainty, greedily select an attribute
        that guarantees the largest expected decrease in entropy
        
        Formula: IG(X) = H(Y) - H(Y|X)

        Parameters
        ----------
        X: np.ndarray
            The dataset
        y: np.ndarray
            The label
        """

        # Get the parent entropy
        parent_ent = self.parent_entropy(y)[0]

        # Set best gain as -inf
        best_gain: float = -np.inf

        # Set best attribute index to None
        best_idx: int = None

        # For each attribute index in the dataset column
        # calculate child entropy and information gain.
        # Based on the information gain, intialize the
        # best gain and best index
        for attr_idx in range(X.shape[1]):
            # Get the child entropy value for that attribute
            child_ent = self.child_entropy(X, y, attr_idx)

            # Calculate information gain
            gain = parent_ent - child_ent

            if gain > best_gain or (gain == best_gain and (best_idx is None or attr_idx > best_idx)):
                best_gain = gain
                best_idx = attr_idx
            elif gain == best_gain and attr_idx > best_idx:
                best_idx = attr_idx

        return best_idx, best_gain
    
    def entropy(self, y: np.ndarray) -> float:
        """
        Description
        -----------
        Calculate the measure of uncertainty for
        a random variable

        Parameters
        ----------
        y: np.ndarray
            The label
        """

        # Get the frequency of the label
        _, freq = np.unique(y, return_counts=True)

        # Calculate the probability of each label element
        probs: np.ndarray = freq / y.size

        return -np.sum(probs * np.log2(probs + 1e-9))
    
    def parent_entropy(self, y: np.ndarray) -> tuple[float, str, str]:
        """
        Description
        -----------
        Compute the total uncertainty for the entire dataset label
        and obtain the predicted and majority class

        Parameters
        ----------
        y: np.ndarray
            The label to measure the overall uncertainty
        """

        # Get the total entropy
        total_ent: float = self.entropy(y)

        # Get the unique values and frequency count of the y label
        unique, freq = np.unique(y, return_counts=True)

        # Get the majority from the unique values based on highest frequency
        maj_class = unique[np.argmax(freq)]

        # Set prediction to majority if there is only one item in the unique values, otherwise None
        pred_class = maj_class if unique.size == 1 else None
        
        return total_ent, pred_class, maj_class
    
    def child_entropy(self, X: np.ndarray, y: np.ndarray, attr_idx: int):
        """
        Description
        -----------
        Calculates the weighted entropy after the dataset
        is split on a specific attribute

        Parameters
        ----------
        X: np.ndarray
            The dataset
        y: np.ndarray
            The label
        attr_idx: int
            The partition index at where the dataset is split
        """
        
        # Get unique values of the partitioned dataset
        values: np.ndarray[str] = np.unique(X[:, attr_idx])

        # Initialize the weighted entropy
        weighted_ent: float = 0

        # For each unique values, find subset indices 
        # in which the partitioned dataset matches that
        # value and find a subset of the label using 
        # the subset indices. 
        # Use that to calculate the weighted entropy 
        for value in values:
            subset_indices = X[:, attr_idx] == value
            y_subset = y[subset_indices]
            weighted_ent += ((y_subset.size / y.size) * self.entropy(y_subset))

        return weighted_ent