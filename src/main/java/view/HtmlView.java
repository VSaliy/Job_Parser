package view;

import controller.Controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import vo.Vacancy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;


public class HtmlView implements View {

    private Controller controller;
    private final String filePath = "./home/alex/IdeaProjects/Job Parser/src/main/resources/vacancies.html";

    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("lvov");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent = null;
        try {
            Document document = getDocument();
            Element templateElement = document.select(".vacancy template").first();
            Element patternElement = templateElement.clone();
            patternElement.removeAttr("style");
            patternElement.removeClass("template");

            for (Vacancy vacancy : vacancies) {
                Element newVacancyElement = patternElement.clone();
                newVacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                newVacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                Element link = newVacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateElement.before(newVacancyElement.outerHtml());
            }
            fileContent = document.html();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO exception!");
        }
        return fileContent;
    }

    private void updateFile(String fileBody) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileBody);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        Document document = Jsoup.parse(filePath, "UTF-8");
        return document;
    }
}
