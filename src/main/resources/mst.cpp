#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

struct Edge {
	// ids of the vertices connected by the edge
	int v, u;
	// edge weight
	int cost;

	int neigbour_of(int vertex) {
		return vertex == u ? v : u;
	}

	bool operator<(const Edge& e) const {
		return this->cost < e.cost;
	}
	bool operator>(const Edge& e) const {
		return this->cost > e.cost;
	}

	string to_string() {
		return std::to_string(v) + " ~> " + std::to_string(u) + " (" + std::to_string(cost) + ")";
	}
};

struct Graph {
	vector<vector<Edge>> graph;

	vector<Edge>& operator[](size_t idx) {
		return graph[idx];
	}
};


void add_edge(Graph& graph, Edge e) {
	graph[e.u].push_back(e);
	graph[e.v].push_back(e);
}

void read_graph(Graph &graph) {
	int n, m;
	scanf("%d%d", &n, &m);
	graph.graph.resize(n);

	for (int i = 0; i < m; ++i) {
		int a, b, cost;
		scanf("%d%d%d", &a, &b, &cost);
		--a, --b;

		add_edge(graph, {a, b, cost});
	}
}

void print_graph(Graph& graph) {
	printf("----\n");
	for (int i = 0; i < graph.graph.size(); i++) {
		printf("%d: ", i);

		for (Edge e : graph[i]) {
			printf("%d (%d), ", e.neigbour_of(i), e.cost);
		}

		printf("\n");
	}
}

Graph calc_mst(Graph &graph) {
	priority_queue<Edge, vector<Edge>, greater<Edge>> pq;
	bool vis[graph.graph.size()]{};

	Graph mst;
	mst.graph.resize(graph.graph.size());

	vis[0] = true;
	for (Edge e : graph[0]) {
		pq.push(e);
	}
	
	while (!pq.empty()) {
		Edge top = pq.top();
		pq.pop();

		if (vis[top.u] && vis[top.v])
			continue;

		add_edge(mst, top);
		int next_vertex = vis[top.v] ? top.u : top.v;
		vis[top.u] = vis[top.v] = true;

		for (Edge e : graph[next_vertex]) {
			if (!vis[e.u] || !vis[e.v])
				pq.push(e);
		}
	}
	return mst;
}

int sum_edge_weights(Graph& g) {
	int sum = 0;

	for (int i = 0; i < g.graph.size(); i++) {
		for (Edge& e : g[i]) {
			sum += e.cost;
		}
	}

	return sum/2;
}

int main() {
	Graph g;
	read_graph(g);

	Graph mst = calc_mst(g);
	// print_graph(mst);

	printf("%d\n", sum_edge_weights(mst));

	return 0;
}