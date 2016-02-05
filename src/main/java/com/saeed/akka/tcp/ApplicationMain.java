package com.saeed.akka.tcp;

import java.net.InetSocketAddress;

import com.saeed.akka.tcp.client.ClientActor;
import com.saeed.akka.tcp.server.ServerActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class ApplicationMain {

    public static void main(String[] args) {
        ActorSystem serverActorSystem = ActorSystem.create("ServerActorSystem");

        ActorRef serverActor = serverActorSystem.actorOf(ServerActor.props(null), "serverActor");

        ActorSystem clientActorSystem = ActorSystem.create("ClientActorSystem");

        ActorRef clientActor = clientActorSystem.actorOf(ClientActor.props(
                new InetSocketAddress("localhost", 9090), null), "clientActor");

        serverActorSystem.awaitTermination();
        clientActorSystem.awaitTermination();


    }

}