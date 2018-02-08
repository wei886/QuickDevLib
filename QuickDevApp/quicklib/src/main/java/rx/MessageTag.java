package pica.nd.com.kotlintest.rx;

enum MessageTag {
    ONNEXT(0), ONERROR(1), ONCOMPLETE(2);
    private int what;

    MessageTag(int what) {

        this.what = what;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }
}