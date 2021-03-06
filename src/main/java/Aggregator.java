import controller.Controller;
import model.HHStrategy;
import model.Model;
import model.Provider;
import model.RabotaUAStrategy;
import view.HtmlView;

/**
 *
 * */
public class Aggregator {


    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Model model = new Model(view, new Provider(new RabotaUAStrategy(), new HHStrategy()));
        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod("kiev");


    }
}
