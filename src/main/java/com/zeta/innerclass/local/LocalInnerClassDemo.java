package com.zeta.innerclass.local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class LocalInnerClassDemo {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000);
        clock.start(true);
        // keep program running until user selects "Ok"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;

    TalkingClock(int interval) {
        this.interval = interval;
    }

    void start(final boolean beep) {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                System.out.println("At the tone, the time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }
}

