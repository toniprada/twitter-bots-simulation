package com.toniprada.pfc;


import ec.util.MersenneTwisterFast;
import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.field.network.Network;
import sim.util.Double2D;

public class BotsState extends SimState {

    public static final int NUM_BOTS = 100;

    private static final long serialVersionUID = 1L;

    public Continuous2D yard = new Continuous2D(1.0, 100, 100);
    public Network network = new Network(true);

    public BotsState(long seed) {
        super(seed);
    }

    public void start() {
        super.start();
        // clear the yard
        yard.clear();
        // clear the network
        network.clear();
        // add some bpts to the yard at random positions
        for(int i = 0; i < NUM_BOTS; i++) {
            Bot bot = new Bot();
            yard.setObjectLocation(bot, new Double2D(yard.getWidth()*random.nextDouble(),
                    yard.getHeight()*random.nextDouble()));
            network.addNode(bot);
            schedule.scheduleRepeating(bot);
        }
    }

    public MersenneTwisterFast getRandom() {
        return random;
    }

    public static void main(String[] args) {
        doLoop(BotsState.class, args);
        System.exit(0);
    }

}