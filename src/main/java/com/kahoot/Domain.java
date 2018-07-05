package com.kahoot;

public class Domain {

    private String domain;
    private long count;

    public Domain(String domain, long count) {
        this.domain = domain;
        this.count = count;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
