package dk.sdu.mmmi.cbse.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    //I know what you're thinking... "Could not autowire. No beans of 'ImplementationLocator' type found."
    //Seems like something here is wrong, right? Well.... Spring-magic.java is managing the lifecycles of all beans
    //So when it scans all configuration and component classes it finds GameConfig and locates its game factory method
    //it tries to resolve the parameter of the factory method, which is an ImplementationLocator.
    //It then checks its internal registry of a bean and finds the component("implementationLocator") from its previous scan.
    //It then creates an instance of ImplementationLocator and injects it into the game factory method.
    //The game factory method then returns a Game instance which is then registered as a bean in the registry.
    //The error comes from the fact that the ImplementationLocator might not be found in the static scan of the classes...
    @Bean
    public Game game(ImplementationLocator implementationLocator) {
        Game gameInstance = new Game();
        gameInstance.setImplementationLocator(implementationLocator);
        return gameInstance;
    }
}