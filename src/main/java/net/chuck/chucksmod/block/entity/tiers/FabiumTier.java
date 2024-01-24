package net.chuck.chucksmod.block.entity.tiers;

public interface FabiumTier {
    int WIRE_TRANSFER_RATE = 2048;
    int MAX_INSERT_EXTRACT = 1024;
    int LOW_USAGE = 128;
    int MEDIUM_USAGE = 256;
    int HIGH_USAGE = 512;
    int GENERATION = MAX_INSERT_EXTRACT;
    int GEN_MAX_EXTRACT = GENERATION;
    int MACHINE_ENERGY_STORAGE = 160000;
    int ENERGY_STORAGE = 5*MACHINE_ENERGY_STORAGE;
    int ENERGY_STORAGE_INSERT_EXTRACT = 2*MAX_INSERT_EXTRACT;
    int SPEED = 125;
    int QUARRY_INV_SIZE = 25;
    int QUARRY_SIZE = 11;
    int QUARRY_SPEED = SPEED / 10;
    int FLUID_TANK_BUCKETS = 32;
    int MACHINE_BUCKET_STORAGE = 8;
    int XP_DRAIN_RATE = 200;
    int PUMP_SIZE = 2*QUARRY_SIZE-1;
    int PUMP_SPEED = SPEED/15;
    int FLUID_PIPE_TRANSFER_RATE = 400;
}
