package com.toniprada.pfc;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.Inspector;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.network.NetworkPortrayal2D;
import sim.portrayal.network.SimpleEdgePortrayal2D;
import sim.portrayal.network.SpatialNetwork2D;
import sim.portrayal.simple.CircledPortrayal2D;
import sim.portrayal.simple.LabelledPortrayal2D;
import sim.portrayal.simple.MovablePortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

import javax.swing.*;
import java.awt.*;

public class BotsWithUI extends GUIState {

    public Display2D display;
    public JFrame displayFrame;
    ContinuousPortrayal2D yardPortrayal = new ContinuousPortrayal2D();
    NetworkPortrayal2D botsPortrayal = new NetworkPortrayal2D();

    public static void main(String[] args) {
        BotsWithUI vid = new BotsWithUI();
        Console c = new Console(vid);
        c.setVisible(true);
    }

    public BotsWithUI() {
        super(new BotsState(System.currentTimeMillis()));
    }

    public BotsWithUI(SimState state){
        super(state);
    }

    public static String getName(){
        return"Twitter Bots Simulation";
    }

    @Override
    public Object getSimulationInspectedObject() {
        return state;
    }

    @Override
    public Inspector getInspector() {
        Inspector i = super.getInspector();
        i.setVolatile(true);
        return i;
    }

    @Override
    public void start() {
        super.start();
        setupPortrayals();
    }

    @Override
    public void load(SimState state) {
        super.load(state);
        setupPortrayals();
    }

    public void setupPortrayals()
    {
        BotsState bots = (BotsState) state;
        // tell the portrayals what to portray and how to portray them
        yardPortrayal.setField(bots.yard );
        yardPortrayal.setPortrayalForAll(
                new MovablePortrayal2D(
                        new CircledPortrayal2D(
                                new LabelledPortrayal2D(
                                        new OvalPortrayal2D() {
                                            @Override
                                            public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
                                                Bot bot = (Bot) object;
                                                if (bot.isHuman()) {
                                                    paint = new Color(0, 255, 0);
                                                } else {
                                                    paint = new Color(255, 0, 0);
                                                }

                                                super.draw(object, graphics, info);
                                            }
                                        },
                                        5.0, null, Color.black, true
                                ),
                                0, 5.0, Color.green, true
                        )
                )
        );


        botsPortrayal.setField(new SpatialNetwork2D(bots.yard, bots.network));
        botsPortrayal.setPortrayalForAll(new SimpleEdgePortrayal2D());

        // reschedule the displayer
        display.reset();
        display.setBackdrop(Color.white);
        // redraw the display display.repaint();
    }

    @Override
    public void init(Controller c) {
        super.init(c);
        display = new Display2D(800,800,this);
        display.setClipping(false);
        displayFrame = display.createFrame();
        displayFrame.setTitle("Bots Display");
        c.registerFrame(displayFrame); // so the frame appears in the "Display" list
        displayFrame.setVisible(true);
        display.attach(botsPortrayal, "Bots");
        display.attach(yardPortrayal, "Yard" );
    }

    @Override
    public void quit() {
        super.quit();
        if (displayFrame!=null) displayFrame.dispose();
        displayFrame = null;
        display = null;
    }
}