package project.veille.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.veille.Veille;

import java.util.List;
import java.util.Objects;


public class BlocklorEntity extends HostileEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState transformationState = new AnimationState();

    private PlayerEntity targetPlayer;
    public static final Logger LOGGER = LoggerFactory.getLogger(Veille.MOD_ID);
    public final AnimationState stillState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public BlocklorEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f,1.0f) : 0.0F;
        this.limbAnimator.updateLimbs(f,0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient){
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0,new TemptGoal(this,1.0D, Ingredient.ofItems(Items.COAL),false));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, false));
        this.goalSelector.add(3, new MeleeAttackGoal(this,1,false));
    }

    public static DefaultAttributeContainer.Builder createBlocklorAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16);
    }

    private void setupAnimationStates(){
        if (this.hasPositionTarget()){
            this.transformationState.start(this.age);
        }else {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.age);
            } else {
                --this.idleAnimationTimeout;
            }
        }
    }

    // si le blocklor recoit des dégats d'une pioche en or il prend des dégats sinon il n'en prend pas
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() != null){
            if (((PlayerEntity) source.getAttacker()).getMainHandStack().getItem() == Items.GOLDEN_PICKAXE){
                return super.damage(source, amount);
            }
        }
        return false;
    }

    // arrete de bouger quand le blocklor est dans le champ de vision du joueur
    @Override
    public boolean canTarget(LivingEntity target) {
        if (target instanceof PlayerEntity) {
            return isInPlayerLineOfSight((PlayerEntity) target);
        }
        return false; // Or handle other cases accordingly
    }

    private boolean isInPlayerLineOfSight(PlayerEntity player) {
        Vec3d playerLookVec = player.getRotationVec(1.0F).normalize();
        Vec3d entityToPlayerVec = player.getCameraPosVec(1.0F).subtract(getCameraPosVec(1.0F)).normalize();

        double dotProduct = playerLookVec.dotProduct(entityToPlayerVec);

        // Adjust the threshold as needed
        return dotProduct > 0.5; // If the dot product is greater than 0.5, the player is facing the entity
    }





    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }
}
