#include <iostream>
#include <cmath>
using namespace std;

int main() {
    setlocale(LC_ALL, "russian");
    double x;
    unsigned int y;
    cout << "Введите число x: ";
    cin >> x;
    cout << "Введите степень y: ";
    cin >> y;
    cout << endl;
    cout << "Результат: " << pow(x, y) << endl;
    system("pause");
    return 0;
}