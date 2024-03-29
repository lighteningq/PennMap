package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import main.Coordinate;
import main.Location;
import main.QuadTree;
import main.Range;

/**
 * Test methods in the QuadTree class
 * @author calchen
 *
 */
public class QuadTreeTest {

	/**
	 * The QuadTree
	 */
	private QuadTree tree;
	
	/**
	 * A list of Locations
	 */
	private List<Location> locs;
	
	Location loc0 = new Location("White Dog Cafe", "Restaurant", new Coordinate(60, 20));
	Location loc1 = new Location("Pottruck Fitness Center", "School", new Coordinate(10, 20));
	Location loc2 = new Location("Fine Wine and Good Spirit", "Store", new Coordinate(0, 0));
	Location loc3 = new Location("Parking Lot", "School", new Coordinate(80, 10));
	Location loc4 = new Location("Ochatto", "Restaurant", new Coordinate(25, 0));
	Location loc5 = new Location("Fisher Fine Arts Library", "School", new Coordinate(60, 80));
	Location loc6 = new Location("John Huntsman Hall", "School", new Coordinate(10, 50));
	Location loc7 = new Location("Graduate Center", "School", new Coordinate(20, 50));
	Location loc8 = new Location("Annenberg School for Communication Library", "School", new Coordinate(35, 50));
	Location loc9 = new Location("Franklin Building", "School", new Coordinate(40, 50));
	Location loc10 = new Location("Starbucks", "Restaurant", new Coordinate(60, 50));
	Location loc11 = new Location("Honey Grow", "Restaurant", new Coordinate(30, 50));
	Location loc12 = new Location("AT&T", "Store", new Coordinate(0, 50));
	Location loc13 = new Location("Institute of Contemporary Art", "Museum", new Coordinate(40, 20));
	Location loc14 = new Location("Van Pelt Library", "School", new Coordinate(55, 50));
	Location loc15 = new Location("Spicy Now", "Restaurant", new Coordinate(30, 0));
	Location loc16 = new Location("Irvine Auditorm", "School", new Coordinate(60, 90));
	Location loc17 = new Location("SteinBerg Hall", "School", new Coordinate(30, 100));
	Location loc18 = new Location("Williams Hall", "School", new Coordinate(60, 100));
	Location loc19 = new Location("WaWa@Chestnut", "Restaurant", new Coordinate(20, 0));
	Location loc20 = new Location("Happy Ending Bar", "Restaurant", new Coordinate(100, 100));

//	private List<Location> locs2;
//	Location a = new Location("A", "x", new Coordinate(0, 0));
//	Location b = new Location("B", "x", new Coordinate(6, 1));
//	Location c = new Location("C", "x", new Coordinate(8, 4));
//	Location d = new Location("D", "x", new Coordinate(11, 18));
//	Location e = new Location("E", "x", new Coordinate(8, 11));
//	Location f = new Location("F", "x", new Coordinate(18, 6));
//	Location g = new Location("G", "x", new Coordinate(20, 20));

	/**
	 * Setting up the instance variable for the tests
	 */
	@Before
	public void setUp() {
		tree = new QuadTree();
//		Location l2[] = new Location[] { a,b,c,d,e,f,g };
//		locs2 = Arrays.asList(l2);
	}

	/**
	 * Test the insert() method in the QuadTree class
	 */
	@Test
	public void testInsert() {
		Location l[] = new Location[]{loc0, loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8, loc9, loc10, loc11, loc12,
				loc13, loc14, loc15, loc16, loc17, loc18, loc19, loc20};
		locs = Arrays.asList(l);
		tree.enclosingQuad(locs);
		assertEquals(0, tree.getSize());
		// test inserting null
		assertFalse(tree.insert(null));
		for (int i = 0; i < locs.size(); i++) {
			assertTrue(tree.insert(locs.get(i)));
			assertEquals(i + 1, tree.getSize());
		}
		// test inserting duplicate Locations
		 assertFalse(tree.insert(loc0));
		 assertFalse(tree.insert(loc10));
	}

	/**
	 * Test the search() method in the QuadTree class
	 */
	@Test
	public void testSearch() {
		Location l[] = new Location[]{loc0, loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8, loc9, loc10, loc11, loc12,
				loc13, loc14, loc15, loc16, loc17, loc18, loc19, loc20};
		locs = Arrays.asList(l);
		tree.enclosingQuad(locs);
		assertEquals(0, tree.getSize());
		
		// insert locations into the quadtree
		for (int i = 0; i < locs.size(); i++) {
			assertTrue(tree.insert(locs.get(i)));
			assertEquals(i + 1, tree.getSize());
		}
		Range range;
		List<Location> res;
		Set<Location> result;

		// search all stores
		range = new Range(new Coordinate(0, 0), new Coordinate(100, 100));
		Set<Location> exp0 = new HashSet<Location>();
		exp0.add(loc2);
		exp0.add(loc12);
		res = tree.search("Store", range);
		result = new HashSet<>(res);
//		System.out.println();
		assertEquals(exp0, result);

		// search all schools
		Set<Location> exp1 = new HashSet<Location>();
		int schools[] = { 1, 3, 5, 6, 7, 8, 9, 14, 16, 17, 18 };
		for (int i : schools) {
			exp1.add(l[i]);
		}
		res = tree.search("School", range);
		result = new HashSet<>(res);
		assertEquals(exp1, result);

		// search all schools in (50,50) to (100,100)
		Set<Location> exp2 = new HashSet<Location>();
		range = new Range(new Coordinate(50, 50), new Coordinate(100, 100));
		res = tree.search("School", range);
		int schools2[] = { 5, 16, 18 };
		for (int i : schools2) {
			exp2.add(l[i]);
		}
		result = new HashSet<>(res);
		assertTrue(exp2.equals(result));

		Set<Location> exp3 = new HashSet<Location>();
		range = new Range(new Coordinate(0,51),new Coordinate(60,101));
		int school3[] = {5,16, 17, 18};
		for (int i : school3) {
			exp3.add(l[i]);
		}
		res = tree.search("School", range);
		result = new HashSet<>(res);
//		System.out.println(res.size());
		assertTrue(exp3.equals(result));
		
		// search Restaurant
		Set<Location> exp4 = new HashSet<Location>();
		range = new Range(new Coordinate(50,19),new Coordinate(75,75));
		int restaurant4[] = {0,10};
		for (int i : restaurant4) {
			exp4.add(l[i]);
		}
		res = tree.search("Restaurant", range);
		result = new HashSet<>(res);
//		System.out.println(res.size());
//		for (Location location : result) {
//			System.out.println(location.getName());
//		}
		assertTrue(exp4.equals(result));
	}

	/**
	 * Test the enclosingQuad() method in the QuadTree class
	 */
	@Test
	public void testEnclosingQuad() {
		List<Location> locs = new ArrayList<Location>();
		locs.add(new Location("Fine Wine and Good Spirit", "Store", new Coordinate(0, 0)));
		locs.add(new Location("WaWa@Chestnut", "Restaurant", new Coordinate(20, 0)));
		Range exp = new Range(new Coordinate(0, 0), new Coordinate(20, 0));
		assertEquals(exp, tree.enclosingQuad(locs));
	}
}