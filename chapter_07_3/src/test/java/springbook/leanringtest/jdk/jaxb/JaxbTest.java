package springbook.leanringtest.jdk.jaxb;

import org.junit.Test;
import springbook.user.sqlService.jaxb.SqlType;
import springbook.user.sqlService.jaxb.Sqlmap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JaxbTest {

    @Test
    public void readSqlMap() throws JAXBException {
        String contextPath = Sqlmap.class.getPackage().getName();

        JAXBContext context = JAXBContext.newInstance(contextPath); // 바인딩용 클래스들 위치로 JAXB 컨텍스트 생성

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("/sqlmap.xml"));

        List<SqlType> sqlList = sqlmap.getSql();

        assertThat(sqlList.size(), is(3));
        assertThat(sqlList.get(0).getKey(), is("add"));
        assertThat(sqlList.get(0).getValue(), is("insert"));
        assertThat(sqlList.get(1).getKey(), is("get"));
        assertThat(sqlList.get(1).getValue(), is("select"));
        assertThat(sqlList.get(2).getKey(), is("delete"));
        assertThat(sqlList.get(2).getValue(), is("delete"));
    }
}
