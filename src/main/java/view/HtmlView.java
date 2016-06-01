package view;

import controller.Controller;
import vo.Vacancy;

import java.util.List;

public class HtmlView implements View {

    private Controller controller;

    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("odessa");
    }
}
