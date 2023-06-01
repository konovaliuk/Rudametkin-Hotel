package com.rudametkin.hotelsystem.services;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UtilService {

    public Timestamp parseDateTime(String datetime) {
        Date dateUtil = null;
        Timestamp timestamp = null;
        try {
            dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
            timestamp = new Timestamp(dateUtil.getTime());
        } catch (Exception ignore) {}

        return timestamp;
    }

}
