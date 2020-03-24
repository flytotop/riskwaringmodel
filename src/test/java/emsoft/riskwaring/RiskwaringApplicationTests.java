package emsoft.riskwaring;

import com.emsoft.riskwaring.RiskwaringApplication;
import com.emsoft.riskwaring.config.EsBlog;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import io.searchbox.action.BulkableAction;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import io.searchbox.params.Parameters;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RiskwaringApplicationTests.class , RiskwaringApplication.class})
public class RiskwaringApplicationTests {

    @Autowired
     JestClient jestClient;




    @Test
    public void contextLoads() {

        //1.给es中索引（保存）一个文档
        EsBlog esBlog=new EsBlog();
        esBlog.setBlogId(10001l);
        esBlog.setId("10001");
        esBlog.setContent("content");
        esBlog.setSummary("summary");
        esBlog.setTitle("title");
        //构建一个索引功能
        Index index=new Index.Builder(esBlog).index("thblog").type("blog").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void search() {
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"title\" : \"tit\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("thblog").addType("blog").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }
