from SVM import SVM
import numpy as np
import sys

def generate(num_samples: int, num_features: int, noise: float = 0.1):
    X = np.random.randn(num_samples, num_features)
    y = np.where(np.sum(X, axis=1) >= 0, 1, -1)
    noise_idx = np.random.choice(num_samples, size=int(noise * num_samples), replace=False)
    y[noise_idx] = -y[noise_idx]

    return X, y
    
if __name__ == '__main__':
    if len(sys.argv) != 4:
        print('python Main.py <X> <y> <c>')
        exit(-1)
    
    X, y = generate(int(sys.argv[1]), int(sys.argv[2]))
    svm = SVM(c=float(sys.argv[3]))
    svm.fit(X, y)
    svm.plot()
    