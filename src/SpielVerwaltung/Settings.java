package SpielVerwaltung;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Settings {
    private int volume;
    public Settings(){
        //TODO settings von der festplatte laden
        volume = 0;
    }

    public int getVolume(){return volume;}

    public void setVolume(int v){
        volume = v;
        //TODO auf festplatte schreiben
    }
}
