/**
 * Created by pixel on 05.09.2016.
 */
public class BaseEntity {
    public BaseEntity() {
        ObjectController controller = ObjectController.getInstance();
        controller.add(this);
    }
    public void onUpdate(){

    }
}
