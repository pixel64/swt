import java.util.ArrayList;

/**
 * Created by pixel on 05.09.2016.
 */
public class ObjectController {
    private static ObjectController instance;
    private ArrayList<BaseEntity> objects;
    public static ObjectController getInstance(){
        if(ObjectController.instance == null){
            ObjectController.instance = new ObjectController();
        }
        return ObjectController.instance;
    }
    private ObjectController(){
        this.objects = new ArrayList<>();
    }
    public void add(BaseEntity baseEntity){
        this.objects.add(baseEntity);
    }
    public void update(){
        for(BaseEntity m: this.objects){
            m.onUpdate();
        }
    }
}
