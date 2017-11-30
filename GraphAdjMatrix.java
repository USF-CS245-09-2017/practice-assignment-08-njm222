import java.util.LinkedList;
import java.util.Stack;


public class GraphAdjMatrix implements Graph {
	int[][] edges;

	public GraphAdjMatrix(int size) {
		// TODO Auto-generated constructor stub
		edges = new int[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				edges[i][j] = -1;
			}
		}
	}

	public void addEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		edges[v1][v2] = 1;
	}

	public void topologicalSort() {
		// TODO Auto-generated method stub
		int[] sort = new int[edges.length];
		int count = 0;
		Stack<Integer> s = new Stack<Integer>();
		int[] indegree = new int[edges.length];
		boolean[] ifVisited = new boolean[edges.length];
		
		//adding all the nodes with inDegree of 0 to the stack
		for(int i = 0; i < edges.length; i++){
			ifVisited[i] = false;
			int temp = inDegree(i);
			indegree[i] = temp;
			if(temp == 0)
				s.push(i);
		}
		
		/*popping of the stack and adding all its neighbors that aren't 
		visited to the stack until there are no nodes left*/
		while(!s.isEmpty()){
			sort[count] = s.pop();
			ifVisited[sort[count]] = true;
			int[] neighbours = neighbors(sort[count]);
			count++;
			if(neighbours != null){
				for(int i = 0; i < neighbours.length; i++){
					if(!ifVisited[neighbours[i]] && !s.contains(neighbours[i]))
						s.push(neighbours[i]);
				}
			}
		}
		
		for(int i = 0; i < sort.length; i++){
			System.out.print(sort[i] + ", ");
		}
		
	}

	private int inDegree(int vertex) {
		// TODO Auto-generated method stub
		int degree = 0;
		for(int i = 0; i < edges.length; i++){
			if(edges[i][vertex] > 0)
				degree++;
		}
		return degree;
	}

	public int[] neighbors(int vertex) {
		// TODO Auto-generated method stub
		int n = 0;
		for(int i = 0; i < edges.length; i++){
			if(edges[vertex][i] > 0)
				n++;
		}
		
		if(n == 0)
			return null;
		int[] temp = new int[n];
		int count = 0;
		for(int i = 0; i <= n; i++){
			if(edges[vertex][i] > 0)
				temp[count++] = i;
		}
		return temp;
	}

}
