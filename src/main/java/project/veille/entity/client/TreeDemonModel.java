package project.veille.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import project.veille.entity.animation.ModAnimations;
import project.veille.entity.custom.TreeDemonEntity;

// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TreeDemonModel<T extends TreeDemonEntity> extends SinglePartEntityModel<T> {
	private final ModelPart tree_demon;
	public TreeDemonModel(ModelPart root) {
		this.tree_demon = root.getChild("tree_demon");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData tree_demon = modelPartData.addChild("tree_demon", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData left_leg = tree_demon.addChild("left_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -8.0F, 0.0F));

		ModelPartData right_leg = tree_demon.addChild("right_leg", ModelPartBuilder.create().uv(22, 7).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -8.0F, 0.0F));

		ModelPartData body = tree_demon.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create().uv(0, 11).cuboid(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -13.0F, 0.0F));

		ModelPartData rigth_arm = body.addChild("rigth_arm", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -8.0F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -14.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(18, 18).cuboid(-2.0F, -17.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(13, 0).cuboid(-4.0F, -6.0F, -3.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -13.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		tree_demon.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return tree_demon;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		setHeadAngles(headYaw, headPitch);
		this.animateMovement(ModAnimations.TREE_DEMON_WALK,limbAngle,limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.TREE_DEMON_IDLE, animationProgress, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

	}
}