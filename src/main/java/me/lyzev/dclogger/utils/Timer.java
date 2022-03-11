package me.lyzev.dclogger.utils;

/**
 * This file is part of DcLogger.
 * Copyright (c) 2021 Lyzev.
 *
 * @author Lyzev
 * @date 3/11/2022
 * @project DcLogger
 * @package me.lyzev.dclogger.utils
 */
public class Timer {

    private long lastMs = System.currentTimeMillis(); // The last time when the timer was reset (It's System.currentTimeMillis() when initializing)

    /**
     * This method is used to check whether the specified time has elapsed until the last reset.
     *
     * @param time  the elapsed time
     * @param reset if it should reset (only resets if time has elapsed)
     * @return if time has elapsed
     */
    public boolean hasTimeElapsed(long time, boolean reset) {
        boolean elapsed = System.currentTimeMillis() - lastMs > time; // If the difference between last reset and now is greater than the specified time
        if (elapsed && reset) // Checks if the time has elapsed and if it should reset
            reset(); // Resets the timer
        return elapsed; // Return if time has elapsed
    }

    /**
     * Sets {@link Timer#lastMs} to System.currentTimeMillis() (Used to reset the timer)
     */
    public void reset() {
        lastMs = System.currentTimeMillis();
    }

}
