import numpy as np
from collections import Counter
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd

class KNN:
    """
    Docstring for K-Nearest Neighbors
    ---------------------------------

    Represents a custom K-Nearest Neighbors algorithm, a form of supervised learning in ML used
    for classification and regression problems. 

    It works by identifying the K closest points in the training set and making predictions based
    on the majority class or average value of those neighbors
    """

    def __init__(self, k: int):
        self.k = k

    def fit(self, X: np.ndarray, y: np.ndarray) -> None:
        """
        Docstring for fit
        -----------------

        Not exactly a fit/train method typically used in a typical ML algorithm where the model learns
        from the parameters. Rather, the "fit" method in this case merely stores the dataset and
        the labels.  

        :param self: The instance of the class.

        :param self: The instance of the class.
        :param X: The dataset used to train the model
        :type X: np.ndarray[np.float128]
        :param y: The target label that the model will try to learn to predict.
        :type y: np.ndarray[np.float128]
        """
        self.X_train = np.array(X, dtype=float)
        self.y_train = np.array(y)

    def predict(self, X: np.ndarray) -> np.ndarray:
        """
        Docstring for predict
        ---------------------

        Predicts the majority class for each data point in the training data based on its k-nearest
        neighbors using a euclidean distance calculation

        :param self: The instance of the class.

        :param X: The dataset used to predict the new labels.
        :type X: np.ndarray[np.float128]

        :return: The majority class labels for each sample
        :rtype: np.ndarray[np.int64]
        """

        def euclidean_distance(a: np.ndarray, b: np.ndarray) -> np.float128:
            """
            Calculates the euclidean distance between two arrays

            :param a: A numpy array
            :type a: np.ndarray[np.float128]

            :param b: A numpy array
            :type b: np.ndarray[np.float128]
            """
            return np.sqrt(np.dot(a - b, a - b))
        
        def predict_class(new_point: np.ndarray):
            """
            Predicts the class that a data point will belong to, i.e., find the majority class
            the data point will belong to based on its k-nearest neighbors

            :param new_point: The data point that will be assigned a class
            :type new_point: np.ndarray[np.float128]
            """

            # Create an array of euclidean distance from the new_point to every data point in the training data
            distances = np.array([euclidean_distance(point, new_point) for point in self.X_train])

            # Get the 1st k indices closest to distance
            k_nearest_indices = np.argsort(distances)[:self.k]

            # Get the labels from the output using the 1st k indices
            k_nearest_labels = [self.y_train[i] for i in k_nearest_indices]

            # Return the majority class that the new point belongs to 
            most_common = Counter(k_nearest_labels).most_common(1)[0][0]
            return most_common
        
        return np.array([predict_class(new_point) for new_point in X])

    def accuracy_score(self, y_true: np.ndarray, y_pred: np.ndarray) -> np.float128:
        """
        Docstring for accuracy_score
        ----------------------------

        Measures how closely the predicted class labels matches the
        true class labels.
        
        :param self: The instance of the class
        :param y_pred: The predicted label
        :type y_pred: np.ndarray[np.float128]
        :param y_true: The true label
        :type y_true: np.ndarray[np.float128]
        :return: Measure of how much the predicted labels matches the true labels
        :rtype: float
        """
        return np.mean(y_true == y_pred)
    
    def plot(self):
        """
        Docstring for plot
        ------------------

        Plots the Iris dataset for graph visualization

        :param self: The instance of the class
        """

        df = pd.DataFrame(self.X_train, columns=[
            'sepal length', 
            'sepal width', 
            'petal length', 
            'petal width'
        ])

        df['class'] = self.y_train

        df["class"] = df["class"].map({
            0: "Setosa",
            1: "Versicolor",
            2: "Virginica"
        })


        sns.scatterplot(
            data=df,
            x="petal length",
            y="petal width",
            hue="class",
            style="class",
            palette="Set2",
            s=125
        )

        plt.show()


        