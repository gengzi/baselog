package fun.gengzi.baselog.instrument.controller;

import fun.gengzi.baselog.LoggerInfo;

public class LoggerHolder {

    private static final ThreadLocal<LoggerInfo> LOGGER_HOLDER = new ThreadLocal<>();

    private LoggerHolder(){
    }

    static void set(LoggerInfo loggerInfo){
        LOGGER_HOLDER.set(loggerInfo);
    }

    static LoggerInfo get(){
        return LOGGER_HOLDER.get();
    }

    static void remove(){
        LOGGER_HOLDER.remove();
    }



}
