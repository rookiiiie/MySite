import com.MySiteTest.pojo.Quote;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/6/29
 */
public class test {
    @Test
    public void test(){
        String content="三人行，必有我师焉。   ---孔子  ---https://baike.baidu.com/item/%E4%B8%89%E4%BA%BA%E8%A1%8C%EF%BC%8C%E5%BF%85%E6%9C%89%E6%88%91%E5%B8%88/10967919?fr=aladdin\n" +
                "|\n" +
                "既然我已经踏上这条道路，那么，任何东西都不应妨碍我沿着这条路走下去。---康德";
        String[] ones=content.split("\\|");
        List<Quote> list=new ArrayList<>();
        for(int i=0;i<ones.length;i++){
            String[] entry=ones[i].split("---");
            String href=entry.length<=2?"/":entry[2].trim();
            String Content=entry[0].trim();
            String author=entry[1].trim();
            Quote quote=new Quote(Content,author,href);
            list.add(quote);
        }
    }
}
