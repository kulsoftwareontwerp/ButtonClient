package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import java.awt.Graphics;
import java.util.HashSet;
import com.kuleuven.swop.group17.GameWorldApi.Action;


public class ActionButton  implements Constants {

		private String id;
		private Action action;
		private int x_coord;
		private int y_coord;


		private HashSet<Pair<Integer, Integer>> coordinatesAction;
		private int height;
		private int width;

		public ActionButton(Action action, int x, int y) {
			//hasToBeRemovedOnUndo = false; TODO
			setId(action.toString());
			setAction(action);
			setX_coord(x);
			setY_coord(y);
			initDimensions();
			coordinatesAction = createCoordinatePairs(getX_coord(), getY_coord());
			
		}


		private void setId(String id) {
			this.id = id;
			
		}


		public void draw(Graphics g) {
			int startX = getX_coord();
			int startY = getY_coord();
			
			g.drawLine(startX + 10, startY, startX + 120, startY);//Upline
			g.drawLine(startX + 10, startY + 30, startX + 120, startY + 30);//DownLine
			g.drawLine(startX + 120, startY, startX + 120, startY + 30);//SideRight
			g.drawLine(startX + 10, startY, startX + 10, startY + 30);//Side Left
			
			g.drawString(id, startX + 20, startY + 19);
		}
		
		public HashSet<Pair<Integer, Integer>> createCoordinatePairs(int x, int y){
			HashSet<Pair<Integer, Integer>> set = new HashSet<Pair<Integer, Integer>>();

			for (int i = x+10; i < x + getWidth()+10; i++) {
				for (int j = y; j < y + getHeight(); j++) {
						set.add(new Pair<Integer, Integer>(i, j));
				}
			}
		return set;
		}


		public int getStandardHeight() {
			return STANDARD_HEIGHT_BLOCK;
		}

		

		public String getId() {
			return id;
		}

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}

		public int getX_coord() {
			return x_coord;
		}

		public void setX_coord(int x_coord) {
			this.x_coord = x_coord;
		}

		public int getY_coord() {
			return y_coord;
		}

		public void setY_coord(int y_coord) {
			this.y_coord = y_coord;
		}

		public HashSet<Pair<Integer, Integer>> getCoordinatesAction() {
			return coordinatesAction;
		}

		public void setCoordinatesAction(HashSet<Pair<Integer, Integer>> coordinatesAction) {
			this.coordinatesAction = coordinatesAction;
		}

		
		

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}


		public void initDimensions(){
			setHeight(30);
			setWidth(110);
		}

		

		@Override
		public int hashCode() {
			return getId().hashCode() + getAction().hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Action))
				return false;
			Action action = (Action) o;
			return this.getId().equals(action.toString()) && getAction().equals(action);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Action [id=");
			builder.append(id);
			builder.append(", x_coord=");
			builder.append(x_coord);
			builder.append(", y_coord=");
			builder.append(y_coord);
			builder.append("]");
			return builder.toString();
		}

	}

