package com.vanix.easygl.application.g3d;


import org.joml.Vector3f;

public class AABB {
	private final Vector3f dimensions;
	private final Vector3f position = new Vector3f();

	public AABB(Vector3f dimensions) {
		this.dimensions = new Vector3f(dimensions);
	}

	public void setPosition(Vector3f position) {
		this.position.set(position);
	}

	public Vector3f getVN(Vector3f normal) {
		return new Vector3f(normal.x() < 0 ? position.x() + dimensions.x() : position.x(),
		    normal.y() < 0 ? position.y() + dimensions.y() : position.y(),
		    normal.z() < 0 ? position.z() + dimensions.z() : position.z());
	}

	public Vector3f getVP(Vector3f normal) {
		return new Vector3f(normal.x() > 0 ? position.x() + dimensions.x() : position.x(),
		    normal.y() > 0 ? position.y() + dimensions.y() : position.y(),
		    normal.z() > 0 ? position.z() + dimensions.z() : position.z());
	}
}
