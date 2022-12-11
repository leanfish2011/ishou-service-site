package com.tim.ishou.site.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：tim
 * @date：2022-12-11 11:35 AM
 * @description：
 */
public class ThreadPoolUtil {

  private static int corePoolSize = 4;
  private static int maximumPoolSize = 6;
  private static long keepAliveTime = 2;
  private static TimeUnit unit = TimeUnit.SECONDS;
  private static int QueueSize = 5;

  public static ThreadPoolExecutor build() {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        unit,
        new ArrayBlockingQueue<>(QueueSize),
        new ThreadPoolExecutor.CallerRunsPolicy()
    );

    return executor;
  }
}
