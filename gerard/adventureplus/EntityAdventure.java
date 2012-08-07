package gerard.adventureplus;

import gerard.plugins.adventurePlus;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIAvoidEntity;
import net.minecraft.src.EntityAIBreakDoor;
import net.minecraft.src.EntityAIFollowGolem;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIMoveIndoors;
import net.minecraft.src.EntityAIMoveThroughVillage;
import net.minecraft.src.EntityAIMoveTwardsRestriction;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIPlay;
import net.minecraft.src.EntityAIRestrictOpenDoor;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIVillagerMate;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityAIWatchClosest2;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IMob;
import net.minecraft.src.Item;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Potion;
import net.minecraft.src.World;

public class EntityAdventure extends EntityCreature implements IMob {
	protected int attackStrength;
	
	public EntityAdventure(World world)
    {
            super(world);
            texture = "/mob/char.png";
            moveSpeed = 0.4f;
            stepHeight = 1;
            attackStrength = 2;
            
            getNavigator().setBreakDoors(true);
            tasks.addTask(0, new EntityAISwimming(this));
            tasks.addTask(1, new EntityAdventureAIAttack(this, net.minecraft.src.EntityPlayer.class, moveSpeed, false));
            tasks.addTask(2, new EntityAIMoveTwardsRestriction(this, moveSpeed));
            tasks.addTask(3, new EntityAIMoveThroughVillage(this, moveSpeed, false));
            tasks.addTask(4, new EntityAIWander(this, moveSpeed));
            tasks.addTask(5, new EntityAIRestrictOpenDoor(this));
            tasks.addTask(6, new EntityAIWatchClosest2(this, net.minecraft.src.EntityPlayer.class, 3F, 1.0F));
            tasks.addTask(6, new EntityAIWatchClosest2(this, net.minecraft.src.EntityVillager.class, 5F, 0.02F));
            tasks.addTask(6, new EntityAIWander(this, 0.3F));
            tasks.addTask(7, new EntityAIMoveIndoors(this));
            targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
            targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityPlayer.class, 16F, 0, true));
    }
	
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (super.attackEntityFrom(par1DamageSource, par2))
        {
            Entity entity = par1DamageSource.getEntity();

            if (riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }

            if (entity != this)
            {
                entityToAttack = entity;
                if(adventurePlus.getWarning() == 0)
                	adventurePlus.setWarning(1);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        int i = attackStrength;

        if (isPotionActive(Potion.damageBoost))
        {
            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (isPotionActive(Potion.weakness))
        {
            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            attackEntityAsMob(par1Entity);
        }
    }

    public int getMaxHealth()
    {
            return 20;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }
    
    protected String getHurtSound()
    {
    	return "damage.hurtflesh";
    }

    protected String getDeathSound()
    {
    	return "damage.hurtflesh";
    }

    protected int getDropItemId()
    {
    	return Item.ingotIron.shiftedIndex;
    }
    
    protected boolean canDespawn()
    {
    	return true;
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if(adventurePlus.getWarning() > 0)
    		func_70942_a("smoke");
    	
    	EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16D);
    	if(entityplayer != null && adventurePlus.getWarning() > 0)
    		setSprinting(entityplayer.isSprinting());
    }
    
    protected void func_70942_a(String par1Str)
    {
    	double d = rand.nextGaussian() * 0.02D;
        double d1 = rand.nextGaussian() * 0.02D;
        double d2 = rand.nextGaussian() * 0.02D;
        worldObj.spawnParticle(par1Str, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
    }
}
