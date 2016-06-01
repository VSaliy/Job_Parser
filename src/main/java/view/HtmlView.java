package view;

import controller.Controller;
import vo.Vacancy;

import java.util.List;

public class HtmlView implements View {

    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "vacancies.html";

    public void update(List<Vacancy> vacancies) {

        System.out.println(vacancies);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("lvov");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        //TODO
        return null;
    }

    private void updateFile(String fileBody) {
        //TODO
    }
}
