package fun.gengzi.baselog.test;

import fun.gengzi.baselog.LoggerInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyLogger extends LoggerInfo {

    private String test;
}
