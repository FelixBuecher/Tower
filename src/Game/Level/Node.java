package Game.Level;

import Game.Tools.Vector2i;

/**
 * Node class needed for the A* search algorithm.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;

	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}

}
