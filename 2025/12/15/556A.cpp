#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

int main(){
    int n;cin >> n;
    string s;cin >> s;
    int cnt1 = count(s.begin(),s.end(),'1');
    int cnt0 = count(s.begin(),s.end(),'0');
    if(cnt1 > cnt0)cout << (cnt1 - cnt0) << endl;
    else cout << (cnt0 - cnt1) << endl;
    return 0;
}