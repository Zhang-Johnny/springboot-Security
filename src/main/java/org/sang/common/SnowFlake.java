package org.sang.common;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SnowFlake {

    private final static Map<String, SnowFlake> snowFlakeMap = new ConcurrentHashMap<>();
    private static Object lock = new Object();

    private static SnowFlake getInstance(String module){
        if (snowFlakeMap.containsKey(module)){
            return snowFlakeMap.get(module);
        }
        synchronized (lock){
            if (!snowFlakeMap.containsKey(module)){
                SnowFlake snowFlake = new SnowFlake(1,1);
                snowFlakeMap.put(module,snowFlake);
            }
        }
        if (snowFlakeMap.containsKey(module)){
            return snowFlakeMap.get(module);
        }
        return null;
    }

    private final static long START_STMP = 1534838552000L;

    private final static long SEQUENCE_BIT = 5L;
    private final static long MACHINE_BIT = 5L;
    private final static long DATACENTER_BIT = 5L;

    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastStmp = -1L;

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static Long getSnowFlakeId(String module){
        if (snowFlakeMap.containsKey(module)){
            return snowFlakeMap.get(module).nextId();
        }
        Date date=new Date();
        String sNum=Utils.toString(date.getTime());
        sNum.substring(4,sNum.length()-1);
        sNum+=Utils.toString(Math.random()*1000);
       // return getInstance(module).nextId();
        return Utils.toLong(sNum);
    }

}