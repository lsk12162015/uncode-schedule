package cn.uncode.schedule;

import cn.uncode.schedule.zk.TaskDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ConsoleManager {

    protected static transient Logger log = LoggerFactory.getLogger(ConsoleManager.class);

    private static ZKScheduleManager scheduleManager;

    public static ZKScheduleManager getScheduleManager() throws Exception {
        if (null == ConsoleManager.scheduleManager) {
            ConsoleManager.scheduleManager = ZKScheduleManager.getApplicationcontext().getBean(ZKScheduleManager.class);
        }
        return ConsoleManager.scheduleManager;
    }

    public static void addScheduleTask(TaskDefine taskDefine) {
        try {
            ConsoleManager.scheduleManager.getScheduleDataManager().addTask(taskDefine);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void delScheduleTask(String targetBean, String targetMethod) {
        try {
            ConsoleManager.scheduleManager.getScheduleDataManager().delTask(targetBean, targetMethod);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static List<TaskDefine> queryScheduleTask() {
        List<TaskDefine> taskDefines = new ArrayList<TaskDefine>();
        try {
            List<TaskDefine> tasks = ConsoleManager.scheduleManager.getScheduleDataManager().selectTask();
            taskDefines.addAll(tasks);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return taskDefines;
    }

}
