package main;

import java.util.List;

public class PennMap implements IMapMaker, IMapModel {

	@Override
	public String findShortestPath(String loc1, String loc2) {
		return null;
	}

	@Override
	public List<Location> findAll(String type, double dist) {
		return null;
	}

	@Override
	public Location findNearest(String type) {
		return null;
	}

	@Override
	public IQuadTree makeQuadTree(List<Location> locs) {
		IQuadTree quadTree = new QuadTree();
		for (Location location : locs) {
			quadTree.insert(location);
		}
		return quadTree;
	}

	@Override
	public IGraph makeGraph(List<String> locNames) {
		return null;
	}
}