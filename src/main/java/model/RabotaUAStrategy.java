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
 * Strategy for "http://rabota.ua/"
 */
public class RabotaUAStrategy implements Strategy {
    private final String URL_FORMAT = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=Java&pg=%d";


    private Document getDocument(int page) throws IOException {

        String url = String.format(URL_FORMAT, ++page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/50.0.2661.102 Safari/537.36")
                .referrer("http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=Java")
                .get();
        return document;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 1;
            Document document;
            while (true) {
                document = getDocument(page++);
                if(document == null) break;

                Elements elements = document.select("[class=v]");
                if(elements.size() == 0) break;

                for(Element element: elements) {
                    Element title = element.select("[class=t]").first();
                    String titleString = title.text();

                    String city = element.select("[class=b]").first().text();
                    String companyName = element.select("[class=s]").first().text();
                    String siteName = "http://rabota.ua/";
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
}
