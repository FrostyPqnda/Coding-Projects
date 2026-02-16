import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

class LogisticRegression:
    """
    Docstring for LogisticRegression
    --------------------------------

    Represents a custom Logistic Regression algorithm, a form of supervised learning
    in Machine Learning that is used for classification problems. It uses a sigmoid 
    function to convert inputs into probabilty values between 0 and 1.

    Methods
    -------
    train(X: np.ndarray, y: np.ndarray, learning_rate: float = 1e-1, epochs: int = 1000, reg_lambda: float = 1, tol: float = 1e-5, reg_mode: str = 'l1'):
        Trains a dataset X to fit the Logistic Regression model using label y. 
    predict(X: np.ndarray, w: np.ndarray, b: float, threshold: float = 0.5):
        Predicts the new y labels of the LR model based on X
    accuracy_score(y_pred: np.ndarray, y_true: np.ndarray):
        Measures how closely the predicted y labels match the true y labels
    plot(X: np.ndarray, y: np.ndarray, w: np.ndarray, b: float):
        Visually plots the model
    __sigmoid(z: np.ndarray):
        Performs a sigmoid function that maps any real-valued number into a value between 0 and 1
    __project(X: np.ndarray, num_components: int):
        Projects the dataset into a lower dimensional form by extracting its top k eigenvectors
    """

    def train(self, X: np.ndarray[np.float128], y: np.ndarray[np.float128], learning_rate: float = 1e-1, epochs: int = 1000, reg_lambda: float = 1, tol: float = 1e-5, reg_mode: str = 'l1') -> tuple[np.ndarray[float], float]:
        """
        Docstring for train
        -------------------

        Trains a dataset X with label y to fit a Logistic Regression model, creating a weight vector
        and a bias value where weight w represents how strongly a feature of X influences the
        prediction and bias b represents the baseline (intercept) value.
        
        :param self: The instance of the class.
        :param X: The dataset that will be trained to fit the Logistic Regression model.
        :type X: np.ndarray[np.float128]
        :param y: The target label that the model will try to learn to predict.
        :type y: np.ndarray[np.float128]
        :param learning_rate: Determines the size of the steps taken to minimize loss.
        :type learning_rate: float = 1e-1
        :param epochs: Determines the number of complete passes through the entire dataset during training.
        :type epochs: int = 1000
        :param reg_lambda: Controls how much the model penalizes large weights to prevent overfittng.
        :type reg_lambda: float = 1
        :param tol: Convergence threshold at which the model should stop because it isn't improving significantly anymore.
        :type tol: float = 1e-5
        :param reg_mode: Type of reguarization to apply during training.
        :type reg_mode: str = 'l1'
        :return: Tuple containing the learned weight vector and the bias. 
        :rtype: tuple[np.ndarray[np.float128], float]
        """    
        m, n = X.shape
        w = np.zeros(n)
        b = 0
        y_transformed = (y + 1) / 2
        prev_log_likelihood = -np.inf

        for i in range(epochs):
            z = X @ w + b
            p = self.__sigmoid(z)
            
            eps = 1e-9
            p = np.clip(p, eps, 1 - eps)
            log_likelihood = np.sum(y_transformed * np.log(p) + (1 - y_transformed) * np.log(1 - p)) / m

            if reg_mode == 'l1':
                log_likelihood -= reg_lambda * np.sum(np.abs(w)) / m
            elif reg_mode == 'l2':
                log_likelihood -= reg_lambda * np.sum((w ** 2)) / (2 * m)

            if np.abs(log_likelihood - prev_log_likelihood) <= tol:
                print(f'Converged at iteration {i}')
                break
            prev_log_likelihood = log_likelihood

            error = y_transformed - p
            grad_w = (X.T @ error) / m
            grad_b = np.sum(error) / m

            if reg_mode == 'l1':
                grad_w -= reg_lambda * np.sign(w) / m
            elif reg_mode == 'l2':
                grad_w -= 2 * reg_lambda * w / m

            clip_val = 1e3
            grad_w = np.clip(grad_w, -clip_val, clip_val)
            grad_b = np.clip(grad_b, -clip_val, clip_val)

            w += (learning_rate * grad_w)
            b += (learning_rate * grad_b)

        return w, b
    
    def predict(self, X: np.ndarray[np.float128], w: np.ndarray[np.float128], b: float, threshold: float = 0.5) -> np.ndarray[np.int64]:
        """
        Docstring for predict
        ---------------------

        Predict class labels for the given dataset using the trained logistic regression 
        parameters.

        :param self: The instance of the class.
        :param X: The dataset used to predict the new labels.
        :type X: np.ndarray[np.float128]
        :param w: The weight vector obtained after training the model.
        :type w: np.ndarray[np.float128]
        :param b: The bias term obtained after training the model.
        :type b: float
        :param threshold: The probability threshold used to assign class label for a prediction.
        :type threshold: float
        :return: The predicted class labels for each sample.
        :rtype: ndarray[np.int64]
        """
        
        z = X @ w + b
        return np.where(self.__sigmoid(z) >= threshold, 1, -1)
    
    def accuracy_score(self, y_pred: np.ndarray[np.float128], y_true: np.ndarray[np.float128]) -> float:
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
        return np.mean(y_pred == y_true)
    
    def plot(self, X_train: np.ndarray, y_train: np.ndarray, w: np.ndarray, b: float, reg_mode: str, X_valid: np.ndarray = None, y_valid: np.ndarray = None, X_test: np.ndarray = None, y_test: np.ndarray = None,):
        """
        Docstring for plot
        ------------------

        Visually plots the logistic regression model 
        
        :param self: The instance 
        :param X: Description
        :type X: np.ndarray
        :param y: Description
        :type y: np.ndarray
        :param w: Description
        :type w: np.ndarray
        :param b: Description
        :type b: float
        """

        _, n = X_train.shape
        if n != 2:
            X_train_proj, W_pca = self.__project(X_train, 2)
            w = W_pca.T @ w
            if X_valid is not None:
                X_valid = (X_valid - X_train.mean(axis=0)) @ W_pca
            if X_test is not None:
                X_test = (X_test - X_train.mean(axis=0)) @ W_pca

        sns.set_style('whitegrid')
        plt.figure(figsize=(10, 8))

        # Plot training data
        sns.scatterplot(
            x=X_train_proj[:, 0],
            y=X_train_proj[:, 1],
            hue=y_train,
            palette={-1: 'red', 1: 'blue'},
            edgecolor='black',
            s=60,
        )

        # Plot validation data
        if X_valid is not None:
            plt.scatter(
                X_valid[:, 0],
                X_valid[:, 1],
                c='green',
                marker='^',
                s=80,
                label='Validation'
            )

        # Plot test data
        if X_test is not None:
            plt.scatter(
                X_test[:, 0],
                X_test[:, 1],
                c='purple',
                marker='x',
                s=80,
                label='Test'
            )

        # Decision boundary
        x_values = np.linspace(X_train_proj[:, 0].min(), X_train_proj[:, 0].max(), 100)
        if w[1] != 0:
            y_values = -(w[0] * x_values + b) / w[1]
            plt.plot(x_values, y_values, color="black", linewidth=2, label="Decision Boundary")
        else:
            plt.axvline(-b / w[0], color="black", linewidth=2, label="Decision Boundary")

        plt.title(f"{reg_mode} Logistic Regression Decision Boundary")
        plt.xlabel("Feature 1")
        plt.ylabel("Feature 2")
        plt.legend()
        plt.tight_layout()
        plt.show()

    def __sigmoid(self, z: np.ndarray[np.float128]) -> np.ndarray[np.float128]:
        """
        Docstring for __sigmoid
        -----------------------

        Logistic function that maps every input value in z
        to a range of probabilities betweeen 0 and 1
        
        :param self: The instance of the class.
        :param z: The numpy array that the sigmoid function will be applied to.
        :type z: np.ndarray
        :return: A numpy array where each value is its probability value
        :rtype: np.ndarray[np.float128]
        """

        z = np.clip(z, -500, 500)
        return 1 / (1 + np.exp(-z))
    
    def __project(self, X: np.ndarray, num_components: int) -> np.ndarray[np.float128]: 
        """
        Docstring for __project
        -----------------------

        Utilizes the Principle Component Analysis to find a certain number of eigenvectors 
        that will be used to project the given data into a lower dimension.
        
        :param self: The instance of the class.
        :param X: The dataset containing the necessary eigenvectors.
        :type X: np.ndarray
        :param num_components: The number of eigenvectors to extract from the dataset.
        :type num_components: int
        :return: The top k number of eigenvectors used to project the daa into a lower k-dimensional subspace.
        :rtype: np.ndarray[np.float128]
        """

        X_std = X - np.mean(X, axis=0)
        cov_matrix = np.cov(X_std, rowvar=False)
        
        eigenvalues, eigenvectors = np.linalg.eigh(cov_matrix)
        sorted_indices = np.argsort(eigenvalues)[::-1]
        sorted_eigenvectors = eigenvectors[:, sorted_indices]

        W_pca = sorted_eigenvectors[:, :num_components]

        X_proj = X_std @ W_pca

        return X_proj, W_pca
    
