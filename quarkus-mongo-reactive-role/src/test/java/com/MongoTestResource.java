package com;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.jboss.logging.Logger;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class MongoTestResource implements QuarkusTestResourceLifecycleManager{

    private static MongodExecutable MONGO;
    private static final Logger LOGGER = Logger.getLogger(MongoTestResource.class);


    @Override
    public Map<String, String> start() {
        try{

            Version.Main version = Version.Main.V5_0;
            MongodConfig config = MongodConfig.builder()
            .version(version)
            .net(new Net())
            .build();

            MONGO = MongodStarter.getDefaultInstance().prepare(config);
            MongodProcess mongodProcess = MONGO.start();
            int port = mongodProcess.getConfig().net().getPort();
            LOGGER.infof("Started embedded mongo %s on port %s", version, port);
            System.setProperty("EMBEDDED_MONGO_PORT", String.valueOf(port));



        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        if(MONGO != null){
            MONGO.stop();
        }
        
    }

    
}
