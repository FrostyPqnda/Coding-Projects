from SVM import SVM
import numpy as np

def generate(num_samples: int, num_features: int, noise: float = 0.1):
    X = np.random.randn(num_samples, num_features)
    y = np.where(np.sum(X, axis=1) >= 0, 1, -1)
    noise_idx = np.random.choice(num_samples, size=int(noise * num_samples), replace=False)
    y[noise_idx] = -y[noise_idx]

    return X, y
    
if __name__ == '__main__':
    X, y = generate(500, 2)


    svm = SVM(c=1)
    svm.fit(X, y)

    svm.plot(X, y, mode='2d')
    