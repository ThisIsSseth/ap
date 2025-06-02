package ap.exercises.ex5.scraper.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StringCounter {

    private List<StringCountHolder> stringCountHolder = new ArrayList<>();

    public void add(String string) {
        for (StringCountHolder s : stringCountHolder) {
            if (Objects.equals(string, s.getString())) {
                s.increaseCount();
                return;
            }
        }
        stringCountHolder.add(new StringCountHolder(string));
    }

    public List<StringCountHolder> getTop(int k) {
        return stringCountHolder.stream()
                .sorted(Comparator.comparingLong(StringCountHolder::getCount))
                .limit(k)
                .toList();

    }
}

/**
 * a lil class object to hold count of the strings
 */
class StringCountHolder {
    private final String string;
    private long count;

    public StringCountHolder(String string) {
        this.string = string;
        this.count = 1;
    }

    public String getString() {
        return string;
    }

    public long getCount() {
        return count;
    }

    public void increaseCount() {
        count++;
    }

}
