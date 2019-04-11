import os
import pandas as pd

ROOT_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) #./TSP/src/main
RESSOURCE_PATH = os.path.join(ROOT_DIR, 'ressources')
NODES_PATH = os.path.join(RESSOURCE_PATH, 'nodesTest')
LINKS_PATH = os.path.join(RESSOURCE_PATH, 'linksTest')

def ReadFileNodes(directory):
    # Read file nodes into dataframe
    dataframe = pd.read_csv(directory, sep=' ', header=None, names=['id', 'nom', 'x', 'y'],
                            dtype={'id': int, 'nom': str, 'x': float, 'y': float})
    return dataframe


def ReadFileLinks(directory):
    # Read file links
    dataframe = pd.read_csv(directory, sep=' ', header=None,
                            names=['StartP', 'EndP', 'distance', 'direction'],
                            dtype={'StartP': str, 'EndP': str, 'distance': float, 'direction': str})
    return dataframe


def WriteFile(dataframe, directory):
    dataframe.to_csv(directory, sep=' ', index=False, header=False)


# Calculate new position of Node generated
def NewPosition(startP_X, startP_Y, dist, direct):
    if (direct == "N"):
        endP_X = startP_X
        endP_Y = startP_Y + dist
    elif (direct == "S"):
        endP_X = startP_X
        endP_Y = startP_Y - dist
    elif (direct == "W"):
        endP_X = startP_X - dist
        endP_Y = startP_Y
    else:
        endP_X = startP_X + dist
        endP_Y = startP_Y
    return endP_X, endP_Y


def CheckNodeExist(df, curNode_id, dist, direct):

    # Get positions of current node in file by id
    curNode_posX = df.iloc[curNode_id]['x']
    curNode_posY = df.iloc[curNode_id]['y']

    # Calculate new position of Node generated
    newNode_posX, newNode_posY = NewPosition(curNode_posX, curNode_posY, dist, direct)

    # Check if new Node already exists in file (sum rows based on new Node's position)
    dfCheck = df.loc[(df.x == newNode_posX) & (df.y == newNode_posY)]
    return dfCheck, newNode_posX, newNode_posY


# Insert a row in file Links
def AddLink(startP, endP, dist, direct, dir_file):
    # Read file links
    dfL = ReadFileLinks(dir_file)

    # Insert a row of new link in a dataframe
    dfL = dfL.append({'StartP': startP, 'EndP': endP, 'distance': dist, 'direction': direct}, ignore_index=True)

    # Write dataframe into file
    WriteFile(dfL, dir_file)


# Verify and add a Node in file nodes
def GenerateNode(curNode_id, distance, direction):

    # Read file nodes into dataframe
    dfN = ReadFileNodes(NODES_PATH)

    # Check if new Node already exists in file (sum rows based on new Node's position)
    dfTest, newNode_posX, newNode_posY = CheckNodeExist(dfN, curNode_id, distance, direction)
    if (len(dfTest) > 0):
        curNode_id = dfTest.iloc[0]['id']
        return curNode_id                       # -> Trace this Node for next move

    # If not exists, insert a row of new Node in a dataframe
    nb_row = len(dfN)                           # (Header=None) id Node = id row
    prefix_nom = "Node"                         # prefix nom du Node
    newNode_nom = prefix_nom + str(nb_row)
    dfN = dfN.append({'id': nb_row, 'nom': newNode_nom, 'x': newNode_posX, 'y': newNode_posY},
                     ignore_index=True)

    # Write dataframe into file
    WriteFile(dfN, NODES_PATH)

    # Add new link in file Links
    curNode_nom = dfN.iloc[curNode_id]['nom']
    AddLink(curNode_nom, newNode_nom, distance, direction, LINKS_PATH)
    return nb_row                               # id of Node generated -> Trace this Node for next move



# Test generate Node: curNode_id, distance, direction
#instant_Node = GenerateNode(2, 5 , "E")
#print ("Instant Node:" + str(instant_Node))
# Test print file NodesTest
# df1 = pd.read_csv(NODES_PATH, sep=' ', header=-1)
# # print(df1)