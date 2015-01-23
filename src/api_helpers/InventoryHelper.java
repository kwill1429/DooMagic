package api_helpers;

public class InventoryHelper {
	public InventorySlot[] inventorySlots;
	private final int spacerX = 14;
	private final int spacerY = 5;
	
	public InventoryHelper(int canvasWidth, int canvasHeight) {
		InventorySlot itemSlot;
		int topLeftX = 584 - (800 - canvasWidth);
		int topLeftY = 291 - (600 - canvasHeight);
		int startingX = topLeftX;
		
		inventorySlots = new InventorySlot[28];
		
		/* Top Row */
		System.out.println("Top Left X: "+topLeftX);
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[0] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[1] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[2] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[3] = itemSlot;
		topLeftX += (spacerX + 35);
		
		
		/* Second Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[4] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[5] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[6] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[7] = itemSlot;
		topLeftX += (spacerX + 35);
		
		/* Third Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[8] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[9] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[10] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[11] = itemSlot;
		topLeftX += (spacerX + 35);
		
		/* Fourth Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[12] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[13] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[14] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[15] = itemSlot;
		topLeftX += (spacerX + 35);
		
		/* Fifth Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[16] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[17] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[18] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[19] = itemSlot;
		topLeftX += (spacerX + 35);
		
		/* Sixth Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[20] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[21] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[22] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[23] = itemSlot;
		topLeftX += (spacerX + 35);
		
		/* Sixth Row */
		topLeftX = startingX;
		topLeftY = topLeftY + spacerY + 31;
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[24] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[25] = itemSlot;
		topLeftX += (spacerX + 35);

		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[26] = itemSlot;
		topLeftX += (spacerX + 35);
		
		itemSlot = new InventorySlot(topLeftX, topLeftY);
		inventorySlots[27] = itemSlot;
		topLeftX += (spacerX + 35);
		
	}
	
	public InventorySlot[] getInventorySlots() {
		return this.inventorySlots;
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
		
		public int getCenterX() {
			return this.centerX;
		}
		
		public int getCenterY() {
			return this.centerY;
		}
	}
}
