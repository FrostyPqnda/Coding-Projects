import numpy as np
from KMeans import KMeans
import sys

def make_blobs(n_samples:int=100, n_features:int=2, centers:int=3, cluster_std:float=1, random_state:int=None):
    if random_state:
        np.random.seed(random_state)

    if isinstance(centers, int):
        centers = np.random.randn(centers, n_features) * 5
    
    X = np.zeros((n_samples, n_features))
    y = np.zeros(n_samples)

    n_centers = len(centers)
    center_idx = np.random.choice(n_centers, n_samples)

    for i in range(n_centers):
        center_points = X[center_idx == i]
        n_points = center_points.shape[0]

        cluster_points = np.random.randn(n_points, n_features) * cluster_std + centers[i]
        X[center_idx == i] = cluster_points
        y[center_idx == i] = i

    return X, y

if __name__ == '__main__':
    if not sys.argv or len(sys.argv) < 3:
        sys.exit('python Main.py <No. of samples> <No. of features> <No. of clusters> <Tolerance>')
        
    n_samples = int(sys.argv[1])
    n_features = int(sys.argv[2])
    k = int(sys.argv[3])
    tol = float(sys.argv[4]) if len(sys.argv) == 5 else 1e-10
        
    X, y = make_blobs(n_samples=n_samples, n_features=n_features, centers=k)
    kmeans = KMeans(k=k, tol=tol)
    kmeans.fit(X)
    print(f'Silhouette Score: {kmeans.silhouette_score()}')
    print(f'Inertia: {kmeans.inertia()}')
    print(f'ARI: {kmeans.adjusted_rand_index(y)}')
    kmeans.plot(mode='2d')
    kmeans.plot(mode='3d')
    
