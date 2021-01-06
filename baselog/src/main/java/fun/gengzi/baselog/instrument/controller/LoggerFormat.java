package fun.gengzi.baselog.instrument.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

/**
 * <h1>logger 内容格式化</h1>
 */
@Data
@AllArgsConstructor
public class LoggerFormat {

    // log format
    private String format;
    // log content
    private ArrayList<Object> logContent = new ArrayList<Object>();


}
