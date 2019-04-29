import csv
from collections import defaultdict


 

with open('nodesTest.csv') as fichier:
	r=csv.reader(fichier)
	graph = defaultdict(list)
	for row in r:
		graph[row[0]].append(row[1:])	    

def generate_Arc(graph):
	edges=[]
		
	for node in graph:
		
		for neighbour in graph[node]:
			edges.append((node,neighbour))
	return edges



print(generate_Arc(graph))
