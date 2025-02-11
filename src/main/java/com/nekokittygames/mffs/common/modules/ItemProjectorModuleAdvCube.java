/*  
    Copyright (C) 2012 Thunderdark

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Contributors:
    Thunderdark - initial implementation
 */

package com.nekokittygames.mffs.common.modules;

import com.nekokittygames.mffs.api.PointXYZ;
import com.nekokittygames.mffs.common.IModularProjector;
import com.nekokittygames.mffs.common.IModularProjector.Slots;
import com.nekokittygames.mffs.common.item.ModItems;
import com.nekokittygames.mffs.common.options.*;
import com.nekokittygames.mffs.common.tileentity.TileEntityProjector;
import com.nekokittygames.mffs.libs.LibItemNames;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class ItemProjectorModuleAdvCube extends Module3DBase {
	public ItemProjectorModuleAdvCube()
	{
		super(LibItemNames.MODULE_ADV_CUBE);
		this.setForceFieldModuleType(8);
	}

	@Override
	public boolean supportsDistance() {
		return false;
	}

	@Override
	public boolean supportsStrength() {
		return true;
	}

	@Override
	public boolean supportsMatrix() {
		return true;
	}

	@Override
	public void calculateField(IModularProjector projector,
			Set<PointXYZ> ffLocs, Set<PointXYZ> ffInterior) {

		int tpx = 0;
		int tpy = 0;
		int tpz = 0;

		int xMout = projector.countItemsInSlot(Slots.FocusLeft);
		int xPout = projector.countItemsInSlot(Slots.FocusRight);
		int zMout = projector.countItemsInSlot(Slots.FocusDown);
		int zPout = projector.countItemsInSlot(Slots.FocusUp);
		int distance = projector.countItemsInSlot(Slots.Distance);
		int Strength = projector.countItemsInSlot(Slots.Strength) + 2;

		for (int y1 = 0; y1 <= Strength; y1++) {
			for (int x1 = -xMout; x1 < xPout + 1; x1++) {
				for (int z1 = -zPout; z1 < zMout + 1; z1++) {
					switch (((TileEntityProjector) projector).getSide()) {
						case DOWN:

						case UP:
							tpy = -y1 + 1;
							tpx = x1;
							tpz = z1;
							break;

						case NORTH:
							tpz = -y1 + 1;
							tpy = -z1;
							tpx = -x1;
							break;

						case SOUTH:
							tpz = y1 - 1;
							tpy = -z1;
							tpx = x1;
							break;

						case WEST:
							tpx = -y1 + 1;
							tpy = -z1;
							tpz = x1;
							break;

						case EAST:
							tpx = y1 - 1;
							tpy = -z1;
							tpz = -x1;
							break;
					}

					if (y1 == 0 || y1 == Strength || x1 == -xMout
							|| x1 == xPout || z1 == -zPout || z1 == zMout) {
						if (((TileEntityProjector) projector)
								.hasOption(
										ModItems.OPTION_FIELD_MANIPULATOR,
										true)) {
							switch (((TileEntityProjector) projector).getSide()) {
								case DOWN:
									if ((((TileEntityProjector) projector).getPos().getY() + tpy) > ((TileEntityProjector) projector).getPos().getY())
										continue;
									break;
								case UP:
									if ((((TileEntityProjector) projector).getPos().getY() + tpy) < ((TileEntityProjector) projector).getPos().getY())
										continue;
									break;
								case NORTH:
									if ((((TileEntityProjector) projector).getPos().getZ() + tpz) > ((TileEntityProjector) projector).getPos().getZ())
										continue;
									break;
								case SOUTH:
									if ((((TileEntityProjector) projector).getPos().getZ() + tpz) < ((TileEntityProjector) projector).getPos().getZ())
										continue;
									break;
								case WEST:
									if ((((TileEntityProjector) projector).getPos().getX()+ tpx) > ((TileEntityProjector) projector).getPos().getX())
										continue;
									break;
								case EAST:
									if ((((TileEntityProjector) projector).getPos().getX()+ tpx) < ((TileEntityProjector) projector).getPos().getX())
										continue;
									break;
							}
						}

						ffLocs.add(new PointXYZ(new BlockPos(tpx, tpy, tpz), 0));

					} else {

						ffInterior.add(new PointXYZ(new BlockPos(tpx, tpy, tpz), 0));
					}
				}
			}
		}

	}

	public static boolean supportsOption(ItemProjectorOptionBase item) {

		if (item instanceof ItemProjectorOptionCamoflage)
			return true;
		if (item instanceof ItemProjectorOptionDefenseStation)
			return true;
		if (item instanceof ItemProjectorOptionFieldFusion)
			return true;
		if (item instanceof ItemProjectorOptionFieldManipulator)
			return true;
		if (item instanceof ItemProjectorOptionForceFieldJammer)
			return true;
		if (item instanceof ItemProjectorOptionMobDefence)
			return true;
		if (item instanceof ItemProjectorOptionSponge)
			return true;
		if (item instanceof ItemProjectorOptionBlockBreaker)
			return true;
		return item instanceof ItemProjectorOptionLight;

	}

	@Override
	public boolean supportsOption(Item item) {

		if (item instanceof ItemProjectorOptionCamoflage)
			return true;
		if (item instanceof ItemProjectorOptionDefenseStation)
			return true;
		if (item instanceof ItemProjectorOptionFieldFusion)
			return true;
		if (item instanceof ItemProjectorOptionFieldManipulator)
			return true;
		if (item instanceof ItemProjectorOptionForceFieldJammer)
			return true;
		if (item instanceof ItemProjectorOptionMobDefence)
			return true;
		if (item instanceof ItemProjectorOptionSponge)
			return true;
		if (item instanceof ItemProjectorOptionBlockBreaker)
			return true;
		return item instanceof ItemProjectorOptionLight;
	}

}