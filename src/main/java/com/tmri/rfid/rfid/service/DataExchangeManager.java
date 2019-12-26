package com.tmri.rfid.rfid.service;

import com.tmri.rfid.rfid.bean.FryCommands;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by stone on 2018/12/19.
 */
public interface DataExchangeManager {

    public List<FryCommands> getDataExchange(String sjbbh);
    public String getExchangeJSON(String sjbbh) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    public boolean exchangeData(String sjbbh) throws Exception;
    public boolean decodeData(String sjbbh,ApplicationContext ctx) throws Exception;

}
