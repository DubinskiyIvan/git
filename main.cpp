#include <iostream>
using namespace std;
double my_pow(double x, int y) {
    double c = x;
    for (int i = 1; i < y; i++)
        c *= x;
    return c;
}

int main() {
    setlocale(LC_ALL, "russian");
    double x;
    int y;
    cout << "Введите число x: ";
    cin >> x;
    cout << "Введите степень y: ";
    cin >> y;
    cout << endl;
    cout << "Результат: " << my_pow(x, y) << endl;
    system("pause");
    return 0;
}

