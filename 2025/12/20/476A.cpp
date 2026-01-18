#include<iostream>

using namespace std;

int main(){
    int n,m;cin >> n >> m;
    int ans = -1;
    int min_moves = (n + 1) / 2;
    for(int x = min_moves;x <= n;x++){
        if(x % m == 0){
            ans = x;
            break;
        }
    }
    cout << ans << endl;
    return 0;
}