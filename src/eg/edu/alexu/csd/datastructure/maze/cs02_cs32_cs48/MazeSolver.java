package eg.edu.alexu.csd.datastructure.maze.cs02_cs32_cs48;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs32_cs2_cs48.Myqueue2;
import eg.edu.alexu.csd.datastructure.stack.cs48.Stack;
//import eg.edu.alexu.csd.datastructure.stack.cs48.point;

import java.util.Scanner;
/**
*
* @author Dell
*/
public class MazeSolver implements IMazeSolver {

	/*
	* reading a file
	* @param maze store a file.txt
	*/
char[][] fileTocharArr(final File maze) throws FileNotFoundException {
		Scanner x;
		x = new Scanner(maze);
		int i = x.nextInt();
		int j = x.nextInt();
		x.nextLine();
		char[][] arr = new char[i][j];
		for (int k = 0; k < i; k++) {
			String s = x.nextLine();
			for (int l = 0; l < j; l++) {
				arr[k][l] = s.charAt(l);
			}
		}
		x.close();
		return arr;
	}

	@Override
	public int[][] solveBFS(final File maze) {
		// TODO Auto-generated method stub
		boolean found = false;
		char[][] arr;
		try {
			arr = fileTocharArr(maze);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("invalid file");
		}
		/**
		 * length of the maze
		 */
		int l = arr.length + 2;
		/**
		 * width of the maze
		 */
		int w = arr[0].length + 2;
		point2[][] p = new point2[l][w];
		for (int g = 0; g < l; g++) {
			for (int j = 0; j < w; j++) {
				p[g][j] = new point2();
			}
		}
		Myqueue2 q = new Myqueue2();
		int counter1 = 0;
		boolean z = true;
		for (int g = 0; g < l; g++) {
			for (int j = 0; j < w; j++) {
		if (g > l - 2 || j > w - 2 || g == 0 || j == 0) {
					p[g][j].value = '#';
				} else {
					p[g][j].value = arr[g - 1][j - 1];
					p[g][j].x = g;
					p[g][j].y = j;
					p[g][j].isVisited = false;
				}
			}
		}
		boolean n = false;
		for (int g = 0; g < l; g++) {
			for (int j = 0; j < w; j++) {
				if (p[g][j].value == 'E') {
					z = false;
				}
				if (p[g][j].value == 'S') {
					q.enqueue(p[g][j]);
					p[g][j].isVisited = true;
					p[g][j].level = counter1;
					counter1++;
					n = true;
				}
			}
		}
		if (z) {
			throw new RuntimeException("no end BFS");
		}
		if (!n) {
			throw new RuntimeException();
		}
		point2 temp = new point2();
		int tx = 0;
		int ty = 0;
		while (!q.isEmpty()) {
			temp = (point2) q.dequeue();
			tx = temp.x;
			ty = temp.y;
if (p[tx + 1][ty].value == '.' && !p[tx + 1][ty].isVisited) {
				q.enqueue(p[tx + 1][ty]);
				p[tx + 1][ty].isVisited = true;
				p[tx + 1][ty].level = counter1;
				p[tx + 1][ty].parent = p[tx][ty];
			}
if (p[tx][ty + 1].value == '.' && !p[tx][ty + 1].isVisited) {
				q.enqueue(p[tx][ty + 1]);
				p[tx][ty + 1].isVisited = true;
				p[tx][ty + 1].level = counter1;
				p[tx][ty + 1].parent = p[tx][ty];
			}
if (p[tx - 1][ty].value == '.' && !p[tx - 1][ty].isVisited) {
				q.enqueue(p[tx - 1][ty]);
				p[tx - 1][ty].isVisited = true;
				p[tx - 1][ty].level = counter1;
				p[tx - 1][ty].parent = p[tx][ty];
			}
if (p[tx][ty - 1].value == '.' && !p[tx][ty - 1].isVisited) {
				q.enqueue(p[tx][ty - 1]);
				p[tx][ty - 1].isVisited = true;
				p[tx][ty - 1].level = counter1;
				p[tx][ty - 1].parent = p[tx][ty];
			} else if (p[tx][ty - 1].value == 'E') {
				p[tx][ty - 1].parent = p[tx][ty];
				temp = p[tx][ty - 1];
				found = true;
				break;
			} else if (p[tx - 1][ty].value == 'E') {
				p[tx - 1][ty].parent = p[tx][ty];
				temp = p[tx - 1][ty];
				found = true;
				break;
			} else if (p[tx][ty + 1].value == 'E') {
				p[tx][ty + 1].parent = p[tx][ty];
				temp = p[tx][ty + 1];
				found = true;
				break;
			} else if (p[tx + 1][ty].value == 'E') {
				q.enqueue(p[tx + 1][ty]);
				p[tx + 1][ty].parent = p[tx][ty];
				temp = p[tx + 1][ty];
				found = true;
				break;
			} else if (p[tx][ty].value == 'E') {
				q.enqueue(p[tx][ty]);
				temp = p[tx][ty];
				found = true;
				break;
			}
		
			counter1++;
		}
		if (!found) {
			return null;
		}
		Point pp = new Point(0, 0);
		Stack s1 = new Stack();
		while (temp != null) {
			pp = new Point(temp.x - 1, temp.y - 1);
			s1.push(pp);
			temp = temp.parent;
		}
		int[][] ans = new int[s1.size()][2];
		int ansSize = s1.size();
		for (int h = 0; h < ansSize; h++) {
			Point tempP = new Point();
			tempP = (Point) s1.pop();
			ans[h][0] = tempP.x;
			ans[h][1] = tempP.y;
		}
		return ans;
	}
	@Override
	public int[][] solveDFS(final File maze) {
		// TODO Auto-generated method stub
		boolean z = true;
		boolean z1 = true;
		char[][] arr;
		try {
			arr = fileTocharArr(maze);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("invalid file");
		}
		int l = arr.length + 2;
		int w = arr[0].length + 2;
		point[][] p = new point[l][w];
		for (int g = 0; g < l; g++) {
			for (int h = 0; h < w; h++) {
				p[g][h] = new point();
			}
		}
		Stack s1 = new Stack();
		for (int g = 0; g < l; g++) {
			for (int h = 0; h < w; h++) {
			if (g > l - 2 || h > w - 2 || g == 0 || h == 0) {
					p[g][h].value = '#';
				} else {
					p[g][h].value = arr[g - 1][h - 1];
					p[g][h].x = g;
					p[g][h].y = h;
					p[g][h].isVisited = false;
				}
			}
		}
		for (int g = 0; g < l; g++) {
			for (int j = 0; j < w; j++) {
				if (p[g][j].value == 'E') {
					z = false;
				}
				if (p[g][j].value == 'S') {
					s1.push(p[g][j]);
					z1 = false;
				}
			}
		}
		if (z) {
			throw new RuntimeException("no end DFS");
		}
		if (z1) {
			throw new RuntimeException("no start DFS");
		}
		point temp = new point();
		int tx = 0;
		int ty = 0;
		boolean found = false;
		while (!s1.isEmpty()) {
			temp = (point) s1.peek();
			tx = temp.x;
			ty = temp.y;
			if (p[tx][ty].value == 'E') {
				found = true;
				break;
			}
	if (p[tx + 1][ty].value == '.' && !p[tx + 1][ty].isVisited) {
				s1.push(p[tx + 1][ty]);
				p[tx + 1][ty].isVisited = true;
} else if (p[tx][ty + 1].value == '.' && !p[tx][ty + 1].isVisited) {
				s1.push(p[tx][ty + 1]);
				p[tx][ty + 1].isVisited = true;
} else if (p[tx - 1][ty].value == '.' && !p[tx - 1][ty].isVisited) {
				s1.push(p[tx - 1][ty]);
				p[tx - 1][ty].isVisited = true;
} else if (p[tx][ty - 1].value == '.' && !p[tx][ty - 1].isVisited) {
				s1.push(p[tx][ty - 1]);
				p[tx][ty - 1].isVisited = true;
			} else if (p[tx][ty - 1].value == 'E') {
				s1.push(p[tx][ty - 1]);
			} else if (p[tx - 1][ty].value == 'E') {
				s1.push(p[tx - 1][ty]);
			} else if (p[tx][ty + 1].value == 'E') {
				s1.push(p[tx][ty + 1]);
			} else if (p[tx + 1][ty].value == 'E') {
				s1.push(p[tx + 1][ty]);
			} else {
				s1.pop();
			}
		}
		if (found) {
			int[][] ans = new int[s1.size()][2];
			int n = s1.size();
			for (int g = n - 1; g >= 0; g--) {
				temp = (point) s1.pop();
				ans[g][0] = temp.x - 1;
				ans[g][1] = temp.y - 1;
			}
			return ans;
		} else {
			return null;
		}
	}
}