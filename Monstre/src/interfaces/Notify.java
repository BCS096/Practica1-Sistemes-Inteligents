package interfaces;

import Data.Habitacio;
import java.util.ArrayList;

public interface Notify {

    public void notify(EventEnum event, Habitacio h, ArrayList<Habitacio> path);
}