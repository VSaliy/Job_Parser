package vo;

/**
 *
 */
public class Vacancy {
    private String title;               //name of vacancy
    private String city;                //city
    private String companyName;         //name of company
    private String siteName;            //name of site
    private String url;                 //link to vacancy

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (!title.equals(vacancy.title)) return false;
        if (!city.equals(vacancy.city)) return false;
        if (!companyName.equals(vacancy.companyName)) return false;
        if (!siteName.equals(vacancy.siteName)) return false;
        return url.equals(vacancy.url);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + companyName.hashCode();
        result = 31 * result + siteName.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nVacancy{\n" +
                "title='" + title + '\'' + "\n" +
                "city='" + city + '\'' + "\n" +
                "companyName='" + companyName + '\'' + "\n" +
                "siteName='" + siteName + '\'' + "\n" +
                "url='" + url + '\'' +
                '}' + "\n";
    }
}
