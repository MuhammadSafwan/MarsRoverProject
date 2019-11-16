/**
 * 
 */
package com.assignment.MarsRoverSW.model;

/**
 * @author safwan
 *
 */
public class Rover {
	
	private int xCoordinate;
	private int yCoordinate;
	private String currentOrientation;
	
	public Rover() {
	}
	
	public Rover(int x, int y, String currentDirection) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.currentOrientation = currentDirection;
	}
	
	public int getX() {
		return xCoordinate;
	}
	public void setX(int x) {
		this.xCoordinate = x;
	}
	public int getY() {
		return yCoordinate;
	}
	public void setY(int y) {
		this.yCoordinate = y;
	}
	public String getCurrentOrientation() {
		return currentOrientation;
	}
	public void setCurrentOrientation(String currentOrientation) {
		this.currentOrientation = currentOrientation;
	}

	@Override
	public String toString() {
		return "Rover [x=" + xCoordinate + ", y=" + yCoordinate + ", currentDirection=" + currentOrientation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentOrientation == null) ? 0 : currentOrientation.hashCode());
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rover other = (Rover) obj;
		if (currentOrientation == null) {
			if (other.currentOrientation != null)
				return false;
		} else if (!currentOrientation.equals(other.currentOrientation))
			return false;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}

}
