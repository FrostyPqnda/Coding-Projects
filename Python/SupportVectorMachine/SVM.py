import cvxopt as cp
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

class SVM:
    def __init__(self, c: float = 1, tol: float = 1e-5):
        self.c = c
        self.tol = tol
        self.weight = None
        self.bias = None
        self.support_vectors = None

    def fit(self, X: np.ndarray, y: np.ndarray):
        self.X_proj = self.__project(X, 2)
        self.y = y
        M, N = self.X_proj.shape
        
        P = np.zeros((N + 1 + M, N + 1 + M))
        P[:N, :N] = np.eye(N)
        P[-1, -1] = 0
        q = np.zeros(N + 1 + M)
        q[N + 1:] = self.c

        G_top = -np.hstack((y[:, None] * self.X_proj, self.y[:, None], np.eye(M)))
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
        self.support_vectors = self.X_proj[
            np.where(self.y * np.dot(self.X_proj, self.weight) + self.bias <= self.tol)[0]
        ]

    def predict(self, X: np.ndarray):
        return np.sign(X @ self.weight + self.bias)
    
    def accuracy(self, y_true, y_pred):
        return np.mean(y_true == y_pred)
    
    def plot(self):
        h = .02       
        
        x_min, x_max = self.X_proj[:, 0].min() - 1, self.X_proj[:, 0].max() + 1
        y_min, y_max = self.X_proj[:, 1].min() - 1, self.X_proj[:, 1].max() + 1
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
            x=self.X_proj[:, 0], y=self.X_proj[:, 1], hue=self.y, 
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
