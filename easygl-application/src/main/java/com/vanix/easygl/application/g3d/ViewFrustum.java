package com.vanix.easygl.application.g3d;


import org.joml.Matrix4f;
import org.joml.Vector3f;

public class ViewFrustum {

	private static final int Near = 0;
	private static final int Far = 1;
	private static final int Left = 2;
	private static final int Right = 3;
	private static final int Top = 4;
	private static final int Bottom = 5;

	private final Plane[] planes = { new Plane(), new Plane(), new Plane(), new Plane(), new Plane(), new Plane() };

	public void update(Matrix4f mat) {

		planes[Left].normal.set(mat.get(3, 0) + mat.get(0, 0), mat.get(3, 1) + mat.get(0, 1),
		    mat.get(3, 2) + mat.get(0, 2));
		planes[Left].distanceToOrigin = mat.get(3, 3) + mat.get(0, 3);

		planes[Right].normal.set(mat.get(3, 0) - mat.get(0, 0), mat.get(3, 1) - mat.get(0, 1),
		    mat.get(3, 2) - mat.get(0, 2));
		planes[Right].distanceToOrigin = mat.get(3, 3) - mat.get(0, 3);

		planes[Bottom].normal.set(mat.get(3, 0) + mat.get(1, 0), mat.get(3, 1) + mat.get(1, 1),
		    mat.get(3, 2) + mat.get(1, 2));
		planes[Bottom].distanceToOrigin = mat.get(3, 3) + mat.get(1, 3);

		planes[Top].normal.set(mat.get(3, 0) - mat.get(1, 0), mat.get(3, 1) - mat.get(1, 1), mat.get(3, 2) - mat.get(1, 2));
		planes[Top].distanceToOrigin = mat.get(3, 3) - mat.get(1, 3);

		planes[Near].normal.set(mat.get(3, 0) + mat.get(2, 0), mat.get(3, 1) + mat.get(2, 1),
		    mat.get(3, 2) + mat.get(2, 2));
		planes[Near].distanceToOrigin = mat.get(3, 3) + mat.get(2, 3);

		planes[Far].normal.set(mat.get(3, 0) - mat.get(2, 0), mat.get(3, 1) - mat.get(2, 1), mat.get(3, 2) - mat.get(2, 2));
		planes[Far].distanceToOrigin = mat.get(3, 3) - mat.get(2, 3);

		for (var plane : planes) {
			float length = plane.normal.length();
			plane.normal.mul(1 / length);
			plane.distanceToOrigin /= length;
		}
	}

	public boolean isBoxInFrustum(AABB box) {
		boolean result = true;
		for (var plane : planes) {
			if (plane.distanceToPoint(box.getVP(plane.normal)) < 0) {
				return false;
			} else if (plane.distanceToPoint(box.getVN(plane.normal)) < 0) {
				result = true;
			}
		}
		return result;
	}

	private static class Plane {
		private float distanceToOrigin;
		private Vector3f normal = new Vector3f();

		private float distanceToPoint(Vector3f point) {
			return point.dot(normal) + distanceToOrigin;
		}
	}
}
