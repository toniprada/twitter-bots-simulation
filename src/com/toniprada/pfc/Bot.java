package com.toniprada.pfc;

import com.toniprada.pfc.classifier.Classifier;
import com.toniprada.pfc.twitter.Account;
import com.toniprada.pfc.twitter.Relation;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;

public class Bot implements Steppable {

    private static final long serialVersionUID = 1L;
    private static final int CHECK_INTERVAL_STEPS = 1000;

    private Classifier classifier;
    private Account account;
    private boolean isHuman;

    public Bot() {
        super();
        this.classifier = new Classifier();
        this.account = new Account();
        this.isHuman = false;
    }

    @Override
    public void step(SimState state) {
        BotsState botsState = (BotsState) state;

        if (botsState.schedule.getSteps()%CHECK_INTERVAL_STEPS == 0) {
            this.isHuman = classifier.classify(account);
        }

        Bag bots = botsState.network.getAllNodes();
        if (account.shouldFollow()) {
            Object botTwo = bots.get(botsState.getRandom().nextInt(botsState.NUM_BOTS));
            botsState.network.addEdge(this, botTwo, 0.01);

            Bot otherBot = ((Bot)botTwo);
            account.addFriend(new Relation(otherBot.getAccount()));
            otherBot.getAccount().addFollower(new Relation(account));
        }

        if (account.shouldTweet()) {
            account.tweet();
        }
    }

    @Override
    public String toString() {
        return "@" + account.getProfile().getScreenName() + " " + account.getProfile().getFriendsCount() + "|"
                + account.getProfile().getFollowersCount();
    }

    public Account getAccount() {
        return account;
    }

    public boolean isHuman() {
        return isHuman;
    }

}
