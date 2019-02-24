package choonster.testmod3.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/**
 * A Block with a 3x3x3 bounding box.
 * <p>
 * Currently only the selection bounding box works.
 * Entity collision still treats the bounding box as 1x1x1 and glitches out if you try to enter this bounding box.
 *
 * @author Choonster
 */
public class BlockLargeCollisionTest extends Block {
	private static final VoxelShape SHAPE = makeCuboidShape(-16, -16, -16, 32, 32, 32);

	public BlockLargeCollisionTest(final Block.Properties properties) {
		super(properties);
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(final IBlockState state, final IBlockReader worldIn, final BlockPos pos) {
		return SHAPE;
	}
}
