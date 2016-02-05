package com.saeed.akka.tcp.server;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.io.Tcp.ConnectionClosed;
import akka.io.Tcp.Received;
import akka.io.TcpMessage;
import akka.util.ByteString;

/**
 * Created by saeed on 15/February/15 AD.
 */
public class SimplisticHandlerActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Received) {
            final String data = ((Received) msg).data().utf8String();
            log.info("In SimplisticHandlerActor - Received message: " + data);
            getSender().tell(TcpMessage.write(ByteString.fromArray(("echo "+data).getBytes())), getSelf());
        } else if (msg instanceof ConnectionClosed) {
        	log.info("In SimplisticHandlerActor - Connection close: " + msg);
            getContext().stop(getSelf());
        }
    }
}