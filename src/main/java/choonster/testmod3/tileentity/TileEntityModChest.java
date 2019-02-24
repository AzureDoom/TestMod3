package choonster.testmod3.tileentity;

import choonster.testmod3.capability.lock.Lock;
import choonster.testmod3.client.gui.GuiIDs;
import choonster.testmod3.inventory.IContainerCallbacks;
import choonster.testmod3.inventory.container.ContainerModChest;
import choonster.testmod3.inventory.itemhandler.IItemHandlerNameable;
import choonster.testmod3.inventory.itemhandler.ItemHandlerLoot;
import choonster.testmod3.inventory.itemhandler.wrapper.NameableCombinedInvWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * A chest that uses {@link IItemHandler} instead of {@link IInventory}.
 * <p>
 * Adapted from {@link TileEntityChest}.
 *
 * @author Choonster
 */
public class TileEntityModChest extends TileEntityItemHandlerLoot implements IContainerCallbacks {
	/**
	 * The number of slots in this chest's inventory.
	 */
	private static final int INVENTORY_SIZE = 27;

	/**
	 * The default name of this chest's inventory.
	 */
	private static final ITextComponent DEFAULT_NAME = new TextComponentTranslation("container.testmod3:chest");

	/**
	 * Create and return the inventory.
	 *
	 * @return The inventory
	 */
	@Override
	protected ItemHandlerLoot createInventory() {
		return new ItemHandlerLoot(INVENTORY_SIZE, DEFAULT_NAME, this);
	}

	/**
	 * Create and return the lock.
	 *
	 * @return The lock
	 */
	@Override
	protected Lock createLock() {
		return new Lock(DEFAULT_NAME);
	}

	/**
	 * Get the GUI ID.
	 *
	 * @return The GUI ID
	 */
	@Override
	protected int getGuiId() {
		return GuiIDs.MOD_CHEST;
	}

	/**
	 * Create a {@link Container} of this inventory for the specified player.
	 *
	 * @param player The player
	 * @return The Container
	 */
	@Override
	public ContainerModChest createContainer(final EntityPlayer player) {
		inventory.fillWithLoot(player);

		final IItemHandlerModifiable playerInventory = (IItemHandlerModifiable) player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		final IItemHandlerNameable playerInventoryWrapper = new NameableCombinedInvWrapper(player.inventory, playerInventory);

		return new ContainerModChest(playerInventoryWrapper, inventory, player, this);
	}

	/**
	 * Called when the {@link Container} is opened by a player.
	 *
	 * @param player The player
	 */
	@Override
	public void onContainerOpened(final EntityPlayer player) {

	}

	/**
	 * Called when the {@link Container} is closed by a player.
	 *
	 * @param player The player
	 */
	@Override
	public void onContainerClosed(final EntityPlayer player) {

	}

	/**
	 * Is this usable by the specified player?
	 *
	 * @param player The player
	 * @return Is this usable by the specified player?
	 */
	@Override
	public boolean isUsableByPlayer(final EntityPlayer player) {
		return world.getTileEntity(pos) == this && player.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64;
	}

	/**
	 * Set the display name of this chest's inventory.
	 *
	 * @param displayName The display name
	 */
	public void setDisplayName(final ITextComponent displayName) {
		inventory.setDisplayName(displayName);
		lock.setDisplayName(displayName);
	}
}
