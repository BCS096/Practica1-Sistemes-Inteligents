package interfaces;

import Data.Habitacio;
import java.util.ArrayList;
import java.util.HashMap;

public interface Notify {

    public void notify(EventEnum event, Habitacio h, ArrayList<Habitacio> path, HashMap<String, Habitacio> bc);
}