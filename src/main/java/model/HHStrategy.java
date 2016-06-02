package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for "http://hh.ua/"
 */
public class HHStrategy implements Strategy {

    private final String URL_FORMAT = "https://hh.ua/search/vacancy?text=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString){
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
            Document document;
            while(true) {
                document = getDocument(searchString, page++);
                if(document == null) break;

                Elements elements = document.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;

                for(Element element : elements) {
                    Element title = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String titleString = title.text();

                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();
                    String siteName = "http://hh.ua/";
                    String url = title.attr("href");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(titleString);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    private Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/50.0.2661.102 Safari/537.36")
                .referrer("https://hh.ua/search/vacancy?enable_snippets=true&area=115&text=%22%20+%20%22" +
                        "java+kiev&currency_code=UAH&clusters=true&page=0")
                .get();
        return document;
    }
}
