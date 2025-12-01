#include<iostream>
using namespace std;

int main(){
    int n;cin >> n;
    int a = n;//原数
    int b = n / 10;//去倒数第一位
    int c = (n / 100) * 10 + (n % 10);//去倒数第二位
    cout << max(a,max(b,c)) << endl;
    return 0;
}