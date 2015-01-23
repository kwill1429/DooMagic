package api_helpers;

import java.awt.Dimension;

public class InventoryHelper {
	public static int[] inventorySlots;
	
	public InventoryHelper(Dimension d) {
		
	}
	
	
	public class InventorySlot {
		private int topLeftX, topLeftY;
		private int btmRightX, btmRightY;
		private final int width = 35;
		private final int height = 31;
		public int centerX, centerY;
		
		public InventorySlot(int x, int y) {
			topLeftX = x;
			topLeftY = y;
			btmRightX = x + width;
			btmRightY = y + height;
			centerX = (topLeftX + btmRightX) / 2;
			centerY = (topLeftY + btmRightY) / 2;
		}
	}
}
