#include <bits/stdc++.h>

#define MOD 1000000007

#define MIN_N 2000
#define MAX_N 2000

#define MIN_WEIGHT 1
#define MAX_WEIGHT 5


using namespace std;

typedef unsigned long long ull;
typedef pair<int, int> pii;

inline ull f(ull x){
	return ((((3*((((x*x)%MOD)*x)%MOD))%MOD
		+ (554*((x*x)%MOD))%MOD)%MOD)
		+ 7)%MOD;
}

mt19937 rng;

int gi(int lb, int ub) {
	return uniform_int_distribution<int>(lb, ub)(rng);
}

void init() {
	int seed;
	scanf("%d", &seed);
	rng = mt19937(f(seed));
}

pii generate_edge(map<pii, bool>& in_g, int n, int src = -1) {
	int a, b;

    do {
		a = (src == -1 ? gi(1, n) : src);
    	b = (src == -1 ? gi(1, n) : gi(1, a-1));
    } while (in_g[{a, b}] || a == b);
	
	if (gi(0, 1))
		swap(a, b);
		
	in_g[{a, b}] = true;
	in_g[{b, a}] = true;

    return {a, b};
}

int main() {
	init();
    int n = gi(MIN_N, MAX_N);
    int m = gi(n-1, n*(n-1)/2);

    printf("%d %d\n", n, m);

	map<pii, bool> in_g;
    for (int i = 2; i <= n; i++) {
    	pii e = generate_edge(in_g, n, i);
		int w = gi(MIN_WEIGHT, MAX_WEIGHT);
		printf("%d %d %d\n", e.first, e.second, w);
    }

	for (int i = 0; i < m-n+1; i++) {
		pii e = generate_edge(in_g, n);
		int w = gi(MIN_WEIGHT, MAX_WEIGHT);
		printf("%d %d %d\n", e.first, e.second, w);
	}
}

