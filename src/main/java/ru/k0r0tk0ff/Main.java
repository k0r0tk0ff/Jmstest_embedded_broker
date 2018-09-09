package ru.k0r0tk0ff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Session;

public class Main {

    private static final Logger LOG  = LoggerFactory.getLogger(Main.class);

    private static final boolean IS_PERSISTANT = true;
    private static final boolean IS_TRANSACTED = false;
    private static final String URL = "tcp://localhost:61616";
    //private static final String URL = "vm://broker";
    private static final String QUEUENAME = "queue";

    //private static final int MODE = Session.SESSION_TRANSACTED;
    //private static final int MODE = Session.AUTO_ACKNOWLEDGE;
    //private static final int MODE = Session.DUPS_OK_ACKNOWLEDGE;
    private static final int MODE = Session.CLIENT_ACKNOWLEDGE;


    public static void main(String[]args){

        LOG.info("Start application jmstest ..............");
        LOG.error("MODE = Session.CLIENT_ACKNOWLEDGE");
        LOG.error("IS_TRANSACTED = " + IS_TRANSACTED);
        LOG.error("IS_PERSISTANT = " + IS_PERSISTANT);

        //System.out.println(Thread.currentThread().toString());
        Sender sender = new Sender(QUEUENAME, MODE, IS_TRANSACTED, URL);
        LOG.info("Success create sender object........");
        Receiver receiver = new Receiver(QUEUENAME, MODE, IS_TRANSACTED, URL);
        LOG.info("Success create receiver object ........");

        Broker broker = new Broker(IS_PERSISTANT, IS_TRANSACTED, URL, QUEUENAME);
        broker.run();


        try {
            sender.start();
            sender.join();


            receiver.start();
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
