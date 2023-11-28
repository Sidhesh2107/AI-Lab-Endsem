#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// Structure for graph nodes
struct GraphNode {
    int data;
    bool isVisited;
    vector<GraphNode*> neighbors;
    GraphNode(int val) : data(val), isVisited(false) {}
};

// Function to insert nodes into graph
void insert(vector<GraphNode*>& graph, int data) {
    graph.push_back(new GraphNode(data));
}

// Function to add an edge between two nodes in the graph
void addEdge(GraphNode* node1, GraphNode* node2) {
    node1->neighbors.push_back(node2);
    node2->neighbors.push_back(node1);
}

// Depth-First Search (DFS) traversal
void dfs(GraphNode* node) {
    if (!node)
        return;
    node->isVisited = true;
    cout << node->data << " ";
    for (GraphNode* neighbor : node->neighbors) {
        if (!neighbor->isVisited) {
            dfs(neighbor);
        }
    }
}

// Breadth-First Search (BFS) traversal
void bfs(GraphNode* root) {
    if (!root)
        return;
    queue<GraphNode*> q;
    q.push(root);
    root->isVisited = true;
    while (!q.empty()) {
        GraphNode* current = q.front();
        q.pop();
        cout << current->data << " ";
        for (GraphNode* neighbor : current->neighbors) {
            if (!neighbor->isVisited) {
                neighbor->isVisited = true;
                q.push(neighbor);
            }
        }
    }
}

int main() {
    vector<GraphNode*> graph;
    int values[] = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

    for (int value : values) {
        insert(graph, value);
    }

    // Adding edges to form a graph structure
    addEdge(graph[0], graph[1]);
    addEdge(graph[0], graph[2]);
    addEdge(graph[1], graph[3]);
    addEdge(graph[1], graph[4]);
    addEdge(graph[2], graph[5]);
    addEdge(graph[2], graph[6]);
    addEdge(graph[3], graph[7]);
    addEdge(graph[3], graph[8]);
    addEdge(graph[4], graph[9]);
    addEdge(graph[4], graph[10]);
    addEdge(graph[5], graph[11]);
    addEdge(graph[5], graph[12]);
    addEdge(graph[6], graph[13]);
    addEdge(graph[6], graph[14]);

    cout << "Graph Traversal using Depth-First Search (DFS):" << endl;
    dfs(graph[0]);
    cout << endl;

   
    for (GraphNode* node : graph) {
        node->isVisited = false;
    }

    cout << "Graph Traversal using Breadth-First Search (BFS):" << endl;
    bfs(graph[0]);
    cout << endl;

    
    for (GraphNode* node : graph) {
        delete node;
    }

    return 0;
}
