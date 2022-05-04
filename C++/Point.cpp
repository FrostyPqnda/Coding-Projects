#include <iostream>
#include <math.h>
using namespace std;

class Point {
    private:
        double x,y;
    public:
        Point() {
            x = 0;
            y = 0;
        }

        Point(double x, double y) {
            this->x = x;
            this->y = y;
        }

        double operator-(const Point& b) {
            double xDist = pow((b.x - x), 2);
            double yDist = pow((b.y - y), 2);

            return sqrt(xDist + yDist);
        }

        ostream& operator<<(ostream& os) {
            os << "[" << x << ", " << y << "]";
            return os;
        }
};

int main() {
    double x1, y1;
    cout << "Enter a x1 & y1: ";
    cin >> x1 >> y1;
    Point a(x1, y1);

    double x2, y2;
    cout << "Enter a x2 & y2: ";
    cin >> x2 >> y2;
    Point b(x2, y2);

    cout << "Point A: " << a << endl;
    cout << "Point B: " << b << endl;
}