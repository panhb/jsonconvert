package org.example.clog;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.clog.model.CLog;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hongbo.pan
 * @date 2020/11/6
 */
@Slf4j
public class CLogQueue {

    private static final int MAX_SIZE = 100;

    /**
     * 普通队列
     */
    private static ArrayBlockingQueue<CLog> cLogs = Queues.newArrayBlockingQueue(MAX_SIZE);

    /**
     * 死信队列
     */
    private static ArrayBlockingQueue<CLog> deadCLogs = Queues.newArrayBlockingQueue(MAX_SIZE / 2);

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    private static final DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

    public static void push(CLog cLog) {
        if (!cLogs.offer(cLog)) {
            if (!deadCLogs.offer(cLog)) {
//                log.warn("日志丢弃" + cLog);
            }
        }
    }

    public static List<CLog> pop(int size) {
        List<CLog> cLogList = Lists.newArrayList();
        if (cLogs.size() <= size) {
            cLogList.addAll(cLogs);
            cLogs.clear();
        } else {
            while (size > 0) {
                CLog cLog = cLogs.poll();
                if (Objects.isNull(cLog)) {
                    cLog = deadCLogs.poll();
                    if (Objects.isNull(cLog)) {
                        break;
                    } else {
                        size--;
                    }
                } else {
                    size--;
                }
                cLogList.add(cLog);
            }
        }
        return cLogList;
    }

    public static String getLevel() {
        if (cLogs.size() > (MAX_SIZE * 2 / 3)) {
            return "HIGH";
        } else {
            return "LOW";
        }
    }

    @SneakyThrows
    private static boolean writeFile(List<CLog> cLogList) {
        //TODO
//        if (!CollectionUtils.isEmpty(cLogList)) {
//            FileUtils.writeLines(getFile(), cLogList, true);
//            cLogList.clear();
//        }
        return true;
    }

    @SneakyThrows
    private static File getFile() {
        StringBuilder builder = new StringBuilder(LocalDateTime.now().format(formatter)).append(".clog");
        String fileName = builder.toString();
        File file = new File("d://" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    @SneakyThrows
    public static void main(String[] args) {
        consumer();
        for (int i = 0; i < 10000; i++) {
            CLog cLog = new CLog();
            cLog.setIp(i + "");
            push(cLog);
            if (i % 100 ==0) {
                Thread.sleep(50);
            }
        }
    }

    private static void consumer() {
        @SuppressWarnings("AlibabaThreadPoolCreation") ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(CLogQueue::run);
    }

    /**
     * 弹出队列的大小
     * @param logSize 队列大小
     * @param zoneSize 分组大小
     * @return
     */
    private static int getPopSize(int logSize, int zoneSize) {
        return logSize > zoneSize ? logSize / zoneSize : logSize;
    }

    @SneakyThrows
    private static void run() {
        while (true) {
            if (!cLogs.isEmpty()) {
                int logSize = cLogs.size();
                if ("HIGH".equals(CLogQueue.getLevel())) {
                    List<Future<Boolean>> futureList = Lists.newArrayList();
                    for (int i = 0; i < 3; i++) {
                        Future<Boolean> future = executor.submit(() -> writeFile(CLogQueue.pop(getPopSize(logSize, 3))));
                        futureList.add(future);
                    }
                    for (Future<Boolean> future : futureList) {
                        future.get();
                    }
                } else {
                    Future<Boolean> future = executor.submit(() -> writeFile(CLogQueue.pop(getPopSize(logSize, 10))));
                    future.get();
                }
            }
        }
    }
}
