package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Strategu for "http://rabota.ua/"
 */
public class RabotaUAStrategy implements Strategy {
    private final String URL_FORMAT = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=Java&pg=%d";

    private Document getDocument(int page) throws IOException {
        String url = String.format(URL_FORMAT, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/50.0.2661.102 Safari/537.36")
                .referrer("http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=Java")
                .get();
        return document;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return null;
    }
}
