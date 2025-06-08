import numpy as np
import pandas as pd
from DecisionTree import DecisionTree

def load_data(file: str, label_idx: int=0) -> tuple[np.ndarray]:
    df: pd.DataFrame = pd.read_csv(file, header=None)
    X: np.ndarray = df.drop(df.columns[label_idx], axis=1).to_numpy()
    y: np.ndarray = df.iloc[:, label_idx].to_numpy().flatten()
    return X, y

if __name__ == '__main__':
    X_train, y_train = load_data('mush_train.data')
    X_test, y_test = load_data('mush_test.data')
    dt = DecisionTree(min_samples_split=5, max_depth=3)
    dt.fit(X_train, y_train)

    train_pred = dt.predict(X_train)
    train_acc = dt.accuracy(train_pred, y_train)
    print(f'Training accuracy = {train_acc}')

    test_pred = dt.predict(X_test) 
    test_acc = dt.accuracy(test_pred, y_test)
    print(f'Testing accuracy = {test_acc}')