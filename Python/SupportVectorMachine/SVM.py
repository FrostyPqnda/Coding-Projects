import cvxopt as cp
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

class SVM:
    def __init__(self, c: float = 1, tol: float = 1e-5):
        self.c = c
        self.tol = tol
        self.weight = None
        self.bias = None
        self.support_vectors = None

    def fit(self, X: np.ndarray, y: np.ndarray):
        #X = self.__featurize(X)
        M, N = X.shape
        
        P = np.zeros((N + 1 + M, N + 1 + M))
        P[:N, :N] = np.eye(N)
        P[-1, -1] = 0
        q = np.zeros(N + 1 + M)
        q[N + 1:] = self.c

        G_top = -np.hstack((y[:, None] * X, y[:, None], np.eye(M)))
        G_bottom = np.hstack((np.zeros((M, N + 1)), -np.eye(M)))
        G = np.vstack((G_top, G_bottom))
        h = np.hstack((-np.ones(M), np.zeros(M)))

        P = cp.matrix(P, tc='d')
        q = cp.matrix(q, tc='d')
        G = cp.matrix(G, tc='d')
        h = cp.matrix(h, tc='d')

        cp.solvers.options['show_progress'] = False
        solution = cp.solvers.qp(P, q, G, h)
        coeff = np.array(solution['x'][:N + 1]).flatten()
        
        self.weight = coeff[:-1]
        self.bias = coeff[-1]
        self.support_vectors = X[
            np.where(y * np.dot(X, self.weight) + self.bias <= self.tol)[0]
        ]

    def predict(self, X: np.ndarray):
        #X = self.__featurize(X)
        print(self.weight.shape)
        if X.shape[1] != self.weight.shape[0]:
            weight_proj = self.__project(self.weight.reshape(-1, 1), X.shape[1]).flatten()
            print(weight_proj)
        else:
            weight_proj = self.weight

        return np.sign(X @ weight_proj + self.bias)
    
    def accuracy(self, y_true, y_pred):
        return np.mean(y_true == y_pred)
    
    def plot(self, X: np.ndarray, y: np.ndarray, mode: str = '2d'):
        if mode.lower() == '2d':
            self.__plot2D(X, y)
        elif mode.lower() == '3d':
            self.__plot3D(X, y)

    def __project(self, X: np.ndarray, num_components: int) -> np.ndarray:
        if X.shape[1] == num_components:
            return X

        X_std = X - np.mean(X, axis=0)
        cov_matrix = np.cov(X_std, rowvar=False)
        
        eigenvalues, eigenvectors = np.linalg.eigh(cov_matrix)
        sorted_indices = np.argsort(eigenvalues)[::-1]
        sorted_eigenvectors = eigenvectors[:, sorted_indices]

        proj_data = np.dot(X_std, sorted_eigenvectors[:, :num_components])
        return proj_data
    
    def __plot2D(self, X: np.ndarray, y: np.ndarray):
        proj_data = self.__project(X, 2)    
        h = .02       
        
        x_min, x_max = proj_data[:, 0].min() - 1, proj_data[:, 0].max() + 1
        y_min, y_max = proj_data[:, 1].min() - 1, proj_data[:, 1].max() + 1
        xx, yy = np.meshgrid(
            np.arange(x_min, x_max, h),
            np.arange(y_min, y_max, h)
        )
        
        grid_points = np.c_[xx.ravel(), yy.ravel()]
        grid_points = self.__project(grid_points, 2)
        Z = self.predict(grid_points).reshape(xx.shape)


        plt.figure(figsize=(12, 10))
        plt.contourf(xx, yy, Z, alpha=0.8, cmap='coolwarm')
        sns.scatterplot(
            x=proj_data[:, 0], y=proj_data[:, 1], hue=y, 
            palette='coolwarm', s=100, edgecolor='k'
        )

        if self.support_vectors is not None:
            proj_sv = self.__project(self.support_vectors, 2)
            sns.scatterplot(
                x=proj_sv[:, 0],
                y=proj_sv[:, 1],
                color='purple',
                marker='X',
                s=200,
                label='Support Vectors'
            )

        plt.contour(
            xx, yy, Z,
            levels=[0], colors='black', linewidths=2
        )

        plt.title('SVM Decision Boundary and Support Vectors')
        plt.xlabel('X')
        plt.ylabel('Y')
        plt.legend()
        plt.grid(True)
        plt.show()

    def __plot3D(self, X: np.ndarray, y: np.ndarray):
        proj_data = self.__project(X, 3)
        h = 0.5

        x_min, x_max = proj_data[:, 0].min() - 1, proj_data[:, 0].max() + 1
        y_min, y_max = proj_data[:, 1].min() - 1, proj_data[:, 1].max() + 1
        z_min, z_max = proj_data[:, 2].min() - 1, proj_data[:, 2].max() + 1

        xx, yy, zz = np.meshgrid(
            np.arange(x_min, x_max, h),
            np.arange(y_min, y_max, h),
            np.arange(z_min, z_max, h)
        )

        grid_points = np.c_[xx.ravel(), yy.ravel(), zz.ravel()]
        grid_points = self.__project(grid_points, 3)
        #Z = self.predict(grid_points).reshape(xx.shape)

        fig = plt.figure(figsize=(12, 10))
        ax = fig.add_subplot(111, projection='3d')

        #ax.scatter(
        #    xx.ravel(), yy.ravel(), zz.ravel(),
        #    c=Z.ravel(), cmap='coolwarm', alpha=0.5
        #)

        ax.scatter(
            proj_data[:, 0], proj_data[:, 1], proj_data[:, 2],
            c=y, cmap='coolwarm', s =100, edgecolors='k', label='Data Points'
        )

        if self.support_vectors is not None:
            proj_sv = self.__project(self.support_vectors, 3)
            ax.scatter(
                proj_sv[:, 0],
                proj_sv[:, 1],
                proj_sv[:, 2],
                color='purple',
                marker='X',
                s=200,
                label='Support Vectors'
            )

        ax.set_title('SVM Decision Boundary and Support Vectors (3D)')
        ax.set_xlabel('X')
        ax.set_ylabel('Y')
        ax.set_zlabel('Z')
        ax.legend()
        plt.show()

    def __featurize(self, X: np.ndarray) -> np.ndarray:
        M, N = X.shape
        intercept = np.ones((M, 1))
        poly_features = np.einsum('ij,ik->ijk', X, X)
        upper_tri_indices = np.triu_indices(N)
        poly_features = poly_features[:, upper_tri_indices[0], upper_tri_indices[1]]
        transformed_features = np.hstack((intercept, X, poly_features))
        return transformed_features
