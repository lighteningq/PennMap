package test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import BaseNode;

public class QuadTreeTest {

	private QuadTree tree;
	
	@Before
	public void setUp() {
		BaseNode root = new LeafNode();
		tree = new QuadTree(root);
	}
	
	@Test
	public void testInsert() {
		BaseNode newLeafNode = new LeafNode("name", "type", new Coordinate(1.0, 1.0),
				new ArrayList<LeafNode>());
		assertTrue(tree.insert(newLeafNode));
		
		BaseNode newInternalNode = new InternalNode(new LeafNode(), new LeafNode(),
				new LeafNode(), new LeadNode());
		assertTrue(tree.insert(newInternalNode));
	}
	
	@Test
	public void testSearch() {
		Range range = new Range(new Coordinate(1.0, 1.0), new Coordinate(1.0, 1.0));
		List<Location> exp = new ArrayList<Location>();
		exp.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		exp.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		exp.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		exp.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		assertEquals(exp, tree.search("type", range));
	}
	
	@Test
	public void testEnclosingQuad() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		locations.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		locations.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		locations.add(new Location("name", "type", new Coordinate(1.0, 1.0)));
		Coordinate exp = new Coordinate(1.0, 1.0);
		assertEquals(exp, tree.enclosingQuad(locations));
	}
}