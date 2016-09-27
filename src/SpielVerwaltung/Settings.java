package SpielVerwaltung;

import jdk.internal.util.xml.XMLStreamException;
import org.newdawn.slick.openal.SoundStore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Kenanja on 21.09.2016.
 */
public class Settings {
    private boolean fullscreen;
    private int volume;
    private Document doc;
    public Settings(){
        fullscreen = false;
        volume= 100;
        try {
            File inputFile = new File("xml/settings.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Element settings = doc.getDocumentElement();
            volume = Integer.parseInt(settings.getElementsByTagName("volume").item(0).getTextContent());
            fullscreen = Boolean.parseBoolean(settings.getElementsByTagName("fullscreen").item(0).getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getVolume(){return volume;}

    public void setVolume(int v){
        volume = v;
        SoundStore.get().setMusicVolume(((float)v)/100);
        SoundStore.get().setSoundVolume(((float)v)/100);
        doc.getDocumentElement().getElementsByTagName("volume").item(0).setTextContent(String.valueOf(volume));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("xml/settings.xml"));
            Source input = new DOMSource(doc);

            transformer.transform(input, output);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void setFullscreen(boolean fullscreen){
        this.fullscreen = fullscreen;
        doc.getDocumentElement().getElementsByTagName("fullscreen").item(0).setTextContent(String.valueOf(this.fullscreen));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("xml/settings.xml"));
            Source input = new DOMSource(doc);

            transformer.transform(input, output);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public boolean isFullscreen() {
        return fullscreen;
    }
}
