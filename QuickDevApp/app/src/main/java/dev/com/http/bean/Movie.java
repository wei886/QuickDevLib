package dev.com.http.bean;

import java.util.List;

/**
 * author: midVictor
 * date: on 2016/11/24
 * description:
 */

public class Movie {


    /**
     * id : 1220562
     * alt : https://book.douban.com/book/1220562
     * rating : {"max":10,"average":"7.0","numRaters":282,"min":0}
     * author : [{"name":"片山恭一"},{"name":"豫人"}]
     * alt_title :
     * image : https://img3.doubanio.com/spic/s1747553.jpg
     * title : 满月之夜白鲸现
     * mobile_link : https://m.douban.com/book/subject/1220562/
     * summary : 那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。
     * attrs : {"publisher":["青岛出版社"],"pubdate":["2005-01-01"],"author":["片山恭一","豫人"],"price":["18.00元"],"title":["满月之夜白鲸现"],"binding":["平装(无盘)"],"translator":["豫人"],"pages":["180"]}
     * tags : [{"count":106,"name":"片山恭一"},{"count":50,"name":"日本"},{"count":42,"name":"日本文学"},{"count":30,"name":"满月之夜白鲸现"},{"count":28,"name":"小说"},{"count":10,"name":"爱情"},{"count":7,"name":"純愛"},{"count":6,"name":"外国文学"}]
     */

    private String id;
    private String alt;
    private RatingBean rating;
    private String alt_title;
    private String image;
    private String title;
    private String mobile_link;
    private String summary;
    private AttrsBean attrs;
    private List<AuthorBean> author;
    private List<TagsBean> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public AttrsBean getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrsBean attrs) {
        this.attrs = attrs;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.0
         * numRaters : 282
         * min : 0
         */

        private int max;
        private String average;
        private int numRaters;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AttrsBean {
        private List<String> publisher;
        private List<String> pubdate;
        private List<String> author;
        private List<String> price;
        private List<String> title;
        private List<String> binding;
        private List<String> translator;
        private List<String> pages;

        public List<String> getPublisher() {
            return publisher;
        }

        public void setPublisher(List<String> publisher) {
            this.publisher = publisher;
        }

        public List<String> getPubdate() {
            return pubdate;
        }

        public void setPubdate(List<String> pubdate) {
            this.pubdate = pubdate;
        }

        public List<String> getAuthor() {
            return author;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public List<String> getPrice() {
            return price;
        }

        public void setPrice(List<String> price) {
            this.price = price;
        }

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public List<String> getBinding() {
            return binding;
        }

        public void setBinding(List<String> binding) {
            this.binding = binding;
        }

        public List<String> getTranslator() {
            return translator;
        }

        public void setTranslator(List<String> translator) {
            this.translator = translator;
        }

        public List<String> getPages() {
            return pages;
        }

        public void setPages(List<String> pages) {
            this.pages = pages;
        }
    }

    public static class AuthorBean {
        /**
         * name : 片山恭一
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        /**
         * count : 106
         * name : 片山恭一
         */

        private int count;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
