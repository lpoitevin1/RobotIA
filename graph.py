from collections import defaultdict

graph = defaultdict(list) 


def addArc(graph,u,v,d): 
	sublist=[v,d]
	graph[u].append(sublist) 
    
    

def generate_Arc(graph):
	edges=[]
	
	
	for node in graph:
		
		for neighbour in graph[node]:
			edges.append((node,neighbour))
	return edges
	
	

addArc(graph,'a','c','N') 
addArc(graph,'b','c','S') 
addArc(graph,'b','e','E') 
addArc(graph,'c','d','W') 
addArc(graph,'c','e','S') 
addArc(graph,'c','a','S') 


print(generate_Arc(graph))
