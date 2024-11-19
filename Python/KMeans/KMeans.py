import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

class KMeans:
    """
    KMeans++ implementation - a machine learning algorithm that is based on
    clustering, a type of unsupervised learning.  
    """
    def __init__(self, k: int = 1, max_iters: int = 100, tol: float = 1e-5) -> None:
        """
        Instantiate the KMeans++ model, a type of unsupervised machine  
        learning algorithm based on clustering

        k | int
            The number of clusters that will be created
        max_iters | int
            The number of iterations to train the model
        tol | float
            The stopping criterion at which the algorithm will converge
        """
        self.k = k
        self.max_iters = max_iters
        self.tol = tol
        self.centroids = None
        self.clusters = None
        self.data = None
    
    def fit(self, data: np.ndarray) -> None:
        """
        Train the data to fit the KMeans++ model

        data | np.ndarray  
            The dataset that will be trained to fit the
            KMeans++ model
        """
        self.data = self.__standardize(data)
        self.__initialize(self.data)
        for _ in range(self.max_iters):
            self.__assign(self.data)
            new_centroids = self.__update(self.data)
            if np.all(np.abs(new_centroids - self.centroids) <= self.tol):
                break
            self.centroids = new_centroids

    def plot(self, mode: str = '2d') -> None:
        """
        Plot the clusters based on the KMeans++ model in either 2D or 3D

        mode | str = 2d
            The view of the plot in 2D or 3D
        
        ValueError
            If the given mode is not a valid type
        """
        
        if mode.lower() == '2d':
            self.__plot2D()
        elif mode.lower() == '3d':
            self.__plot3D()
        else:
            raise ValueError('Invalid mode. Choose either "2d" or "3d".')
        
    def elbow_plot(self, k_values: np.ndarray) -> None:
        """
        Plot a descending curve of inertia vs the number of clusters.

        k_values | np.ndarray
            A numpy array of k clusters to plot against the inertias
        """
        inertias = []
        for k in k_values:
            self.k = k
            self.fit(self.data)
            inertias.append(self.inertia())

        plt.figure(figsize=(12, 8))
        sns.lineplot(x=k_values, y=inertias, marker='o', linestyle='-', color='blue')

        plt.title('Elbow Plot for K-Means Clustering')
        plt.xlabel('Number of Clusters (k)')
        plt.ylabel('Inertia')
        plt.xticks(k_values)
        plt.show()
        
    def silhouette_score(self) -> float:
        """
        Measures the separability of the clusters in the model.

        Return | float  
        The silhoutte score of the model
        """

        M = self.data.shape[0]
        total_score = 0

        for i in range(M):
            cluster_i = self.clusters[i]
            same_cluster_points = self.data[self.clusters == cluster_i]
            a = np.mean(np.linalg.norm(same_cluster_points - self.data[i], axis=1))

            other_clusters = np.unique(self.clusters[self.clusters != cluster_i])
            b = np.inf

            for other_cluster in other_clusters:
                other_cluster_points = self.data[self.clusters == other_cluster]
                b_candidate = np.mean(np.linalg.norm(other_cluster_points - self.data[i], axis=1))
                b = min(b, b_candidate)

            s = (b - a) / max(a, b)
            total_score += s

        return total_score / M

    def inertia(self) -> float:
        """
        Measures the compactness of the clusters to its given
        centroid.

        return | float  
        The inertia (WCSS) score of the model
        """
        inertia_value = 0
        for i, pt in enumerate(self.data):
            centroid = self.centroids[self.clusters[i]]
            inertia_value += np.linalg.norm(pt - centroid) ** 2
        return inertia_value
    
    def adjusted_rand_index(self, true_labels: np.ndarray) -> float:
        """
        Compares the clusters to the true label

        true_labels | np.ndarray
            The labels to compare the clusters against

        return | float  
        A score between -1 and 1, measuring how close the clusters
        match the true label. A score of -1 represents bad clustering 
        and a score of 1 represents perfect clustering.
        """
        predicted_labels = self.clusters
        labels_true = np.unique(true_labels)
        labels_pred = np.unique(predicted_labels)

        true_idx = np.searchsorted(labels_true, true_labels)
        pred_idx = np.searchsorted(labels_pred, predicted_labels)
        cont_mat = np.zeros((labels_true.size, labels_pred.size))
        np.add.at(cont_mat, (true_idx, pred_idx), 1)

        sum_true = cont_mat.sum(axis=1)
        sum_pred = cont_mat.sum(axis=0)
        sum_total = cont_mat.sum()

        sum_combn_true = np.sum(sum_true * (sum_true - 1)) / 2
        sum_combn_pred = np.sum(sum_pred * (sum_pred - 1)) / 2
        sum_combn_total = np.sum(sum_total * (sum_total - 1)) / 2
        sum_both = np.sum(cont_mat * (cont_mat - 1)) / 2

        num = sum_both - (sum_combn_true * sum_combn_pred) / sum_combn_total
        den = 0.5 * (sum_combn_true + sum_combn_pred) - (sum_combn_true * sum_combn_pred) / sum_combn_total
        return num / den

    def __initialize(self, data: np.ndarray) -> None:
        """
        Randomly initialize the centroids based on the given data

        data | np.ndarray  
        The data that will be used to initialize the centroids
        """
        np.random.seed(0)
        self.centroids = [data[np.random.randint(0, data.shape[0])]]

        for _ in range(1, self.k):
            dist = np.array([min(np.linalg.norm(x - c) ** 2 for c in self.centroids) for x in data])
            probs = dist / np.sum(dist)
            cum_probs = np.cumsum(probs)
            r = np.random.rand()

            for i, p in enumerate(cum_probs):
                if r < p:
                    self.centroids.append(data[i])
                    break

    def __assign(self, data: np.ndarray) -> None:
        """
        Assign a given point in the data to a cluster

        data | np.ndarray  
        A list of data points that will be assigned to its closest centroid
        """
        clusters = []
        for point in data:
            distances = np.linalg.norm(self.centroids - point, axis=1)
            cluster_centroid = np.argmin(distances)
            clusters.append(cluster_centroid)
        self.clusters = np.array(clusters)

    def __update(self, data: np.ndarray) -> np.ndarray:
        """
        Update all of the given data points to a new centroid

        data | np.ndarray  
        A list of data points that will be updated to its new centroid
        """
        centroids = []
        for i in range(self.k):
            cluster_points = data[self.clusters == i]
            if cluster_points.size > 0:
                centroid = np.mean(cluster_points, axis=0)
            else:
                centroid = data[np.random.choice(data.shape[0])]
            centroids.append(centroid)
        return np.array(centroids)

    def __project(self, X: np.ndarray, num_components: int) -> np.ndarray:
        """
        Utilizes the Principle Component Analysis to find a
        certain number of eigenvectors that will be used to 
        project the given data into a lower dimension.

        X | np.ndarray  
        The given dataset to extract its eigenvectors

        num_components | int  
        The number of eigenvectors that will be extracted from
        the dataset

        return | np.ndarray  
        The top k number of eigenvectors that will be used to project
        the data into a lower k-dimensional subspace
        """
        X_std = X - np.mean(X, axis=0)
        cov_matrix = np.cov(X_std, rowvar=False)
        
        eigenvalues, eigenvectors = np.linalg.eigh(cov_matrix)
        sorted_indices = np.argsort(eigenvalues)[::-1]
        sorted_eigenvectors = eigenvectors[:, sorted_indices]

        return sorted_eigenvectors[:, :num_components]

    def __plot2D(self) -> None:
        """
        Helper function to plot the clusters in 2D
        """
        proj = self.__project(self.data, 2)
        proj_data = np.dot(self.data - np.mean(self.data, axis=0), proj)
        proj_centroids = np.dot(self.centroids - np.mean(self.data, axis=0), proj)

        df = pd.DataFrame(proj_data, columns=['X', 'Y'])
        df['cluster'] = self.clusters + 1

        plt.figure(figsize=(12, 10))
        sns.scatterplot(data=df, x='X', y='Y', hue='cluster', palette='deep', legend='full')

        plt.scatter(proj_centroids[:, 0], proj_centroids[:, 1], s=100, c='red', marker='X', label='Centroids')
        plt.title(f'K-Means Clustering 2D (PCA-Reduced Data) with {self.k} clusters')
        plt.xlabel('X')
        plt.ylabel('Y')
        plt.legend()
        plt.show()

    def __plot3D(self) -> None:
        """
        Helper function to plot the clusters in 3D
        """
        proj = self.__project(self.data, 3)
        proj_data = np.dot(self.data - np.mean(self.data, axis=0), proj)
        proj_centroids = np.dot(self.centroids - np.mean(self.data, axis=0), proj)

        df = pd.DataFrame(proj_data, columns=['X', 'Y', 'Z'])
        df['cluster'] = self.clusters + 1

        fig = plt.figure(figsize=(12, 15))
        ax = fig.add_subplot(111, projection='3d')

        for cluster_id in np.unique(df['cluster']):
            cluster_data = df[df['cluster'] == cluster_id]
            ax.scatter(cluster_data['X'], cluster_data['Y'], cluster_data['Z'], label=f'Cluster {cluster_id}')

        ax.scatter(proj_centroids[:, 0], proj_centroids[:, 1], proj_centroids[:, 2], s=100, c='red', marker='X', label='Centroids')
        ax.set_xlabel('X')
        ax.set_ylabel('Y')
        ax.set_zlabel('Z')
        ax.set_title(f'K-Means Clustering 3D (PCA-Reduced Data) with {self.k} clusters')
        ax.legend()
        plt.show()

    def __standardize(self, data: np.ndarray):
        """
        Standardizes the data by giving it a mean of 0
        and standard deviation of 1
        """
        mean = np.mean(data, axis=0)
        std = np.std(data, axis=0)
        return (data - mean) / std