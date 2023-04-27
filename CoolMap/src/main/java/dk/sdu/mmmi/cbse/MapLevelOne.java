package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.services.MapService;

public class MapLevelOne implements MapService{
    @Override
    public String getMap() {
        System.out.println("in map component");
        return "assets/asteroidsbackground.jpg";
    }

    public String getSoundTrack(){
        return "assets/lady-of-the-80x27s-128379.mp3";
    }
}
