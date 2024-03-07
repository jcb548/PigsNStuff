package net.chuck.chucksmod.entity.ai;

import net.chuck.chucksmod.entity.custom.TriafianPigEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;

public class TriafianPigAttackGoal extends MeleeAttackGoal {
    private final TriafianPigEntity entity;
    private int attackDelay = TriafianPigEntity.ATTACK_WINDUP;
    private int ticksUntilNextAttack = TriafianPigEntity.ATTACK_WINDUP;
    private boolean shouldCountUntilNextAttack = false;
    public TriafianPigAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = (TriafianPigEntity) mob;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = TriafianPigEntity.ATTACK_WINDUP;
        ticksUntilNextAttack = TriafianPigEntity.ATTACK_WINDUP;
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountUntilNextAttack){
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack-1, 0);
        }
    }
    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy){
        return entity.distanceTo(pEnemy) <= 3f;
    }
    private void resetAttackCooldown(){ticksUntilNextAttack = getTickCount(TriafianPigEntity.ANIMATION_LENGTH);}
    private boolean isTimeToStartAttackAnimation(){return ticksUntilNextAttack <= attackDelay;}
    private boolean isTimeToAttack(){return ticksUntilNextAttack <= 0;}
    private void performAttack(LivingEntity pEnemy){
        resetAttackCooldown();
        entity.swingHand(Hand.MAIN_HAND);
        entity.tryAttack(pEnemy);
    }

    @Override
    protected void attack(LivingEntity target) {
        if(isEnemyWithinAttackDistance(target)){
            shouldCountUntilNextAttack = true;
            if(isTimeToStartAttackAnimation()){
                entity.setAttacking(true);
            }
            if(isTimeToAttack()){
                this.entity.getLookControl().lookAt(target.getX(), target.getEyeY(), target.getZ());
                performAttack(target);
            }
        } else {
            resetAttackCooldown();
            shouldCountUntilNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationCooldown = 0;
        }
    }
}
