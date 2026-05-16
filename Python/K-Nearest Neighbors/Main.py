from ucimlrepo import fetch_ucirepo 
import pandas as pd
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.model_selection import train_test_split
from KNearestNeighbor import KNN
import json

if __name__ == '__main__':
    # fetch dataset 
    iris = fetch_ucirepo(id=53) 

    if not iris or not iris.data:
        raise ValueError('iris dataset not found')
    
    # data (as pandas dataframes)
    X = iris.data.features
    y = iris.data.targets['class']


    print(json.dumps(iris.metadata, indent=4))
    print(iris.variables)

    # -----------------------------
    # COMBINE DATAFRAME
    # -----------------------------
    df = pd.concat([X, y], axis=1)

    print(df.head())
    print(df.dtypes)
    print(df.describe())

    # -----------------------------
    # ENCODE LABELS
    # -----------------------------
    le = LabelEncoder()
    y_encoded = le.fit_transform(y)

    # -----------------------------
    # TRAIN / TEST SPLIT
    # -----------------------------
    X_train, X_test, y_train, y_test = train_test_split(
        X,
        y_encoded,
        test_size=0.2,
        random_state=42 
    )


    # -----------------------------
    # SCALE FEATURES (IMPORTANT)
    # -----------------------------
    scaler = StandardScaler()
    X_train = scaler.fit_transform(X_train)    
    X_test = scaler.transform(X_test)

    # -----------------------------
    # TRAIN KNN
    # -----------------------------
    knn = KNN(k=56)
    knn.fit(X_train, y_train)

    # -----------------------------
    # PREDICT
    # -----------------------------
    y_pred = knn.predict(X_test)

    # -----------------------------
    # ACCURACY
    # -----------------------------
    acc = knn.accuracy_score(y_test, y_pred)
    print(f"\nAccuracy: {acc * 100:.2f}%")

    # -----------------------------
    # PLOT (IMPORTANT)
    # -----------------------------
    knn.plot()

