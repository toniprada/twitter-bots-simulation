package com.toniprada.pfc;

import com.toniprada.pfc.classifier.Classifier;
import com.toniprada.pfc.twitter.Account;
import com.toniprada.pfc.twitter.Relation;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.continuous.Continuous2D;
import sim.util.Bag;
import sim.util.Double2D;
import sim.util.MutableDouble2D;

public class Student implements Steppable {

    private static final long serialVersionUID = 1L;

    private Classifier classifier;
    private boolean isHuman;
    private Account account;


    public Student() {
        super();
        this.classifier = new Classifier();

        this.account = new Account();

    }

    public void step(SimState state) {
        StudentsState studentsState = (StudentsState) state;
        Continuous2D yard = studentsState.yard;
        Double2D me = studentsState.yard.getObjectLocation(this);
        MutableDouble2D nextLocation = new MutableDouble2D();

        // Go through my network and determine how much I want to be near them
        MutableDouble2D forceVector = new MutableDouble2D();
        Bag out = studentsState.network.getEdges(this, null);
        int len = out.size();
        /*for(int buddy = 0 ; buddy < len; buddy++) {
            Edge e = (Edge)(out.get(buddy));
            double buddiness = ((Double)(e.info)).doubleValue();
            // I could be in the to() end or the from() end.  getOtherNode is a cute function
            // which grabs the guy at the opposite end from me.
            Double2D him = studentsState.yard.getObjectLocation(e.getOtherNode(this));
            if (buddiness >= 0)  {// the further I am from him the more I want to go to him
                if ((him.x- me.x) > 8) {
                    forceVector.setTo((him.x - me.x) * buddiness, (him.y - me.y) * buddiness);
                    nextLocation.addIn(forceVector);
                }
            }

        };*/

        if (studentsState.schedule.getSteps()%1000 == 0) {
            this.isHuman = classifier.classify(account);
        }

        Bag students = studentsState.network.getAllNodes();
        if (account.shouldFollow()) {
            Object studentB = students.get(studentsState.getRandom().nextInt(studentsState.numStudents));
            studentsState.network.addEdge(this, studentB, 0.01);

            Student otherStudent = ((Student)studentB);
            account.addFriend(new Relation(otherStudent.getAccount()));
            otherStudent.getAccount().addFollower(new Relation(account));
        }

        if (account.shouldTweet()) {
            account.tweet();
        }
    }



    @Override
    public String toString() {
        //return "[" + System.identityHashCode(this) + "] + name:" + account.getProfile().getName();
        return "@" + account.getProfile().getScreenName() + " " + account.getProfile().getFriendsCount() + "|" + account.getProfile().getFollowersCount();
    }

    public Account getAccount() {
        return account;
    }


    public boolean isHuman() {
        return isHuman;
    }



}
