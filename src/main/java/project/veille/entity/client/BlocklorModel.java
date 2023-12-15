package project.veille.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import project.veille.entity.animation.ModAnimations;
import project.veille.entity.custom.BlocklorEntity;

public class BlocklorModel<T extends BlocklorEntity> extends SinglePartEntityModel<T> {
	private final ModelPart brocklor;
	private final ModelPart head;
	public BlocklorModel(ModelPart root) {
		this.brocklor = root.getChild("Brocklor");
		this.head = brocklor.getChild("Head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Brocklor = modelPartData.addChild("Brocklor", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Head = Brocklor.addChild("Head", ModelPartBuilder.create().uv(52, 20).cuboid(-5.0F, -5.0F, -6.0F, 10.0F, 9.0F, 12.0F, new Dilation(0.0F))
				.uv(48, 86).cuboid(-4.0F, 2.0F, -1.0F, 8.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, -12.0F));

		ModelPartData Mouth = Head.addChild("Mouth", ModelPartBuilder.create().uv(82, 62).cuboid(-5.0F, 4.0F, -6.0F, 10.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Upperbody = Brocklor.addChild("Upperbody", ModelPartBuilder.create().uv(0, 32).cuboid(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
				.uv(0, 64).cuboid(-4.0F, 7.0F, -5.0F, 8.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -10.0F, 0.0F));

		ModelPartData LeftArm = Upperbody.addChild("LeftArm", ModelPartBuilder.create().uv(54, 54).cuboid(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 10.0F, new Dilation(-0.005F))
				.uv(79, 78).cuboid(-3.0F, 2.0F, -4.0F, 6.0F, 8.0F, 8.0F, new Dilation(-0.005F))
				.uv(74, 0).cuboid(-2.0F, 10.0F, -3.0F, 4.0F, 1.0F, 6.0F, new Dilation(-0.005F)), ModelTransform.pivot(12.0F, -5.0F, 0.0F));

		ModelPartData lowerArmL = LeftArm.addChild("lowerArmL", ModelPartBuilder.create().uv(80, 50).cuboid(-4.0F, 7.0105F, -4.4798F, 8.0F, 3.0F, 8.0F, new Dilation(-0.005F))
				.uv(0, 0).cuboid(-1.0F, 0.0105F, -3.4798F, 2.0F, 8.0F, 6.0F, new Dilation(-0.005F)), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		ModelPartData RightArm = Upperbody.addChild("RightArm", ModelPartBuilder.create().uv(48, 0).cuboid(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 10.0F, new Dilation(-0.005F))
				.uv(0, 78).cuboid(-3.0F, 2.0F, -4.0F, 6.0F, 8.0F, 8.0F, new Dilation(-0.005F))
				.uv(48, 41).cuboid(-2.0F, 10.0F, -3.0F, 4.0F, 1.0F, 6.0F, new Dilation(-0.005F)), ModelTransform.pivot(-12.0F, -5.0F, 0.0F));

		ModelPartData lowerArm = RightArm.addChild("lowerArm", ModelPartBuilder.create().uv(76, 8).cuboid(-4.0F, 8.0F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(-0.005F))
				.uv(0, 32).cuboid(-1.0F, 1.0F, -3.0F, 2.0F, 8.0F, 6.0F, new Dilation(-0.005F)), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

		ModelPartData Lowerbody = Brocklor.addChild("Lowerbody", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -10.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

		ModelPartData Rleg = Lowerbody.addChild("Rleg", ModelPartBuilder.create().uv(27, 69).cuboid(-7.0F, -7.0F, -5.0F, 6.0F, 7.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 13.0F, 0.0F));

		ModelPartData Lleg = Lowerbody.addChild("Lleg", ModelPartBuilder.create().uv(57, 70).cuboid(1.0F, -7.0F, -5.0F, 6.0F, 7.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(BlocklorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);
		this.animateMovement(ModAnimations.BLOCKLOR_WALK,limbSwing,limbSwingAmount, 2f, 2.5f);

		this.updateAnimation(entity.idleAnimationState, ModAnimations.BLOCKLOR_TRANSFORMATIOSTILL, ageInTicks, 1f);
		this.updateAnimation(entity.transformationState, ModAnimations.BLOCKLOR_DETRANSFORME, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		brocklor.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return brocklor;
	}
}