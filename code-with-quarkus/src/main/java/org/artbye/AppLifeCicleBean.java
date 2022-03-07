package org.artbye;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class AppLifeCicleBean {

    private static final Logger LOGGER = Logger.getLogger("Listener bean");

    void onStart(@Observes StartupEvent event){
        LOGGER.info("The application is starting");
    }

    void onStop(@Observes ShutdownEvent event){
        LOGGER.info("The application is stopping");
    }
}
