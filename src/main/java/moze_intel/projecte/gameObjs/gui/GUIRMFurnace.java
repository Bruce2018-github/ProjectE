package moze_intel.projecte.gameObjs.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import moze_intel.projecte.PECore;
import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GUIRMFurnace extends ContainerScreen<RMFurnaceContainer> {

	private static final ResourceLocation texture = new ResourceLocation(PECore.MODID, "textures/gui/rmfurnace.png");
	private final RMFurnaceTile tile;

	public GUIRMFurnace(RMFurnaceContainer container, PlayerInventory invPlayer, ITextComponent title) {
		super(container, invPlayer, title);
		this.xSize = 209;
		this.ySize = 165;
		this.tile = (RMFurnaceTile) container.tile;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GlStateManager.color4f(1, 1, 1, 1);
		Minecraft.getInstance().textureManager.bindTexture(texture);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		this.blit(x, y, 0, 0, xSize, ySize);

		int progress;

		if (tile.isBurning()) {
			progress = tile.getBurnTimeRemainingScaled(12);
			this.blit(x + 66, y + 38 + 10 - progress, 210, 10 - progress, 21, progress + 2);
		}

		progress = tile.getCookProgressScaled(24);
		this.blit(x + 88, y + 35, 210, 14, progress, 17);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.font.drawString(I18n.format("pe.rmfurnace.shortname"), 76, 5, 4210752);
		this.font.drawString(I18n.format("container.inventory"), 76, ySize - 96 + 2, 4210752);
	}
}