#include <bits/stdc++.h>
using namespace std;

const int N = 2e5 + 10;
char s[N];
int a[N];

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d %s", &n, s);
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        for (int i = 0; i < n; i++) {
            int l = i;
            while (s[i] == '<') {
                i++;
            }
            reverse(a + l, a + i + 1);
        }
        for (int i = 0; i < n; i++) {
            printf("%d ", a[i]);
        }
        printf("\n");

        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            int l = i;
            while (s[i] == '>') {
                i++;
            }
            reverse(a + l, a + i + 1);
        }
        for (int i = 0; i < n; i++) {
            printf("%d ", a[i]);
        }
        printf("\n");
    }
    return 0;
}
