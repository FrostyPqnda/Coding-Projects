import itertools
import pandas as pd
from LogisticRegression import LogisticRegression
import numpy as np

if __name__ == '__main__':
    def load_data(file: str, label_idx: int = -1) -> tuple[np.ndarray]:
        df = pd.read_csv(file, header=None)
        X = df.iloc[:, :label_idx].to_numpy()
        y = df.iloc[:, label_idx].to_numpy().flatten()
        y = np.where(y == 2, 1, -1)
        return X, y
    
    def standardize(X: np.ndarray):
        mean = np.mean(X, axis=0)
        std = np.std(X, axis=0)
        return (X - mean) / std

    log_reg = LogisticRegression()

    X_train, y_train = load_data('sonar_train.data')
    X_valid, y_valid = load_data('sonar_valid.data')
    X_test, y_test = load_data('sonar_test.data')

    reg_lambdas = np.logspace(-6, 3, num=10)
    lr_values = np.logspace(-3, 1, num=5)

    param_pairs = list(itertools.product(reg_lambdas, lr_values))

    # L2 Regularization
    print('L2 Regularization')
    print('-----------------')
    best_lr_l2 = 0
    best_lambda_l2 = 0
    best_accuracy_l2 = 0
    best_l2w = None
    best_l2b = 0

    for lr, reg_lambda in param_pairs:
        w, b = log_reg.train(X_train, y_train, reg_mode='l2', reg_lambda=reg_lambda, learning_rate=lr)
        y_pred = log_reg.predict(X_valid, w, b)
        a = log_reg.accuracy_score(y_pred, y_valid)
        if a > best_accuracy_l2:
            best_lr_l2 = lr
            best_lambda_l2 = reg_lambda
            best_accuracy_l2 = a
            best_l2w = w
            best_l2b = b

    print(f'Best learning rate for l2 regularization: {best_lr_l2}')
    print(f'Best lambda for l2 regularization: {best_lambda_l2}')
    print(f'Best weight for l2 regularization: {best_l2w}')
    print(f'Best bias for l2 regularization: {best_l2b}')

    y_pred = log_reg.predict(X_test, best_l2w, best_l2b)
    a = log_reg.accuracy_score(y_pred, y_test)
    print(f'l2 Regularization Test accuracy: {a}')

    log_reg.plot(
        X_train=X_train,
        y_train=y_train,
        w=best_l2w,
        b=best_l2b,
        reg_mode='L2',
        X_valid=X_valid,
        y_valid=y_valid,
        X_test=X_test,
        y_test=y_test
    )

    print('-----------------')

    # L1 Regularization
    print('L1 Regularization')
    print('-----------------')
    best_lr_l1 = 0
    best_lambda_l1 = 0
    best_accuracy_l1 = 0
    best_l1w = None
    best_l1b = 0

    for lr, reg_lambda in param_pairs:
        w, b = log_reg.train(X_train, y_train, reg_mode='l1', reg_lambda=reg_lambda, learning_rate=lr)
        y_pred = log_reg.predict(X_valid, w, b)
        a = log_reg.accuracy_score(y_pred, y_valid)
        if a > best_accuracy_l1:
            best_lr_l1 = lr
            best_lambda_l1 = reg_lambda
            best_accuracy_l1 = a
            best_l1w = w
            best_l1b = b

    print(f'Best learning rate for l1 regularization: {best_lr_l1}')
    print(f'Best lambda for l1 regularization: {best_lambda_l1}')
    print(f'Best weight for l1 regularization: {best_l1w}')
    print(f'Best bias for l1 regularization: {best_l1b}')

    y_pred = log_reg.predict(X_test, best_l1w, best_l1b)
    a = log_reg.accuracy_score(y_pred, y_test)
    print(f'L1 Regularization Test accuracy: {a}')

    log_reg.plot(
        X_train=X_train, 
        y_train=y_train,
        w=best_l1w,
        b=best_l1b,
        reg_mode='L1',
        X_valid=X_valid,
        y_valid=y_valid,
        X_test=X_test,
        y_test=y_test
    )

    print('-----------------')
    