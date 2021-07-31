import com.component.extract.ExtractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/ApplicationContext.xml"})
public class MainTest {
    @Autowired
    private ExtractService extractService;

    @Test
    public void run(){
        extractService.createContents();
    }
}
