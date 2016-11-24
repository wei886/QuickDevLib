package quickdev.com.http.bean;

/**
 * author: midVictor
 * date: on 2016/11/24
 * description:
 */

public class MovieItem {


    private String start;
    private String count;
    private String total;
    private String query;
    private String tag;
    private SimpleSubject  subjects ;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public SimpleSubject getSubjects() {
        return subjects;
    }

    public void setSubjects(SimpleSubject subjects) {
        this.subjects = subjects;
    }
}
