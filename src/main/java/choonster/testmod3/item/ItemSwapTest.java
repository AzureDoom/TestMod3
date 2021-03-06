package choonster.testmod3.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * An item that's converted to another when shift-right clicked.
 * <p>
 * Test for this thread:
 * http://www.minecraftforge.net/forum/index.php/topic,34244.0.html
 *
 * @author Choonster
 */
public class ItemSwapTest extends Item {
	private ItemStack otherItem;

	public boolean hasOtherItem() {
		return otherItem != null;
	}

	public void setOtherItem(final ItemStack otherItem) {
		this.otherItem = otherItem;
	}

	@Override
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);

		if (hasOtherItem()) {
			tooltip.add(I18n.format("item.testmod3:swap_test.with_item.desc", otherItem.getDisplayName()));
		} else {
			tooltip.add(I18n.format("item.testmod3:swap_test.without_item.desc"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand hand) {
		if (hasOtherItem() && playerIn.isSneaking()) {
			return new ActionResult<>(EnumActionResult.SUCCESS, otherItem.copy());
		}

		return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(hand));
	}
}
