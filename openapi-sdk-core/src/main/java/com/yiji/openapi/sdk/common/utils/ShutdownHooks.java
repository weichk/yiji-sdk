/**
 * create by zhike@yiji.com
 * date:2015年3月12日
 */
package com.yiji.openapi.sdk.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * shutdown回收资源hook
 * 
 * copy from com.yjf.common.util
 * 
 * @author zhike@yiji.com
 * 
 *
 */
public class ShutdownHooks {
	private static final Log logger = LogFactory.getLog(ShutdownHooks.class);
	public static List<TaskWrapper> tasks = new ArrayList<TaskWrapper>();
	private static AtomicInteger index = new AtomicInteger();

	public ShutdownHooks() {
	}

	public static void addShutdownHook(Runnable runnable, String hookName) {
		if (runnable != null) {
			TaskWrapper taskwrapper = new TaskWrapper(runnable, hookName);
			Thread thread = new Thread(taskwrapper, "YIJIShutdownHook" + index.incrementAndGet());
			taskwrapper.setShutdownhook(thread);
			tasks.add(taskwrapper);
			Runtime.getRuntime().addShutdownHook(thread);
		}

	}

	public static void shutdownAll() {
		Iterator<TaskWrapper> var0 = tasks.iterator();
		while (var0.hasNext()) {
			TaskWrapper task = var0.next();
			task.run();

			try {
				Runtime.getRuntime().removeShutdownHook(task.getShutdownhook());
			} catch (Exception var3) {
			}
		}

		tasks.clear();
	}

	private static class TaskWrapper implements Runnable {
		private Runnable runnable;
		private String hookName;
		private boolean isRunned = false;
		private Thread shutdownhook;

		public TaskWrapper(Runnable runnable, String hookName) {
			this.runnable = runnable;
			this.hookName = hookName;
		}

		public void run() {
			synchronized (this) {
				if (!this.isRunned) {
					ShutdownHooks.logger.info("[SHUTDOWNHOOK-"+this.hookName+"]开始执行");
					this.isRunned = true;

					try {
						this.runnable.run();
						ShutdownHooks.logger.info("[SHUTDOWNHOOK-"+this.hookName+"]执行结束");
					} catch (Exception var4) {
						ShutdownHooks.logger.error("[SHUTDOWNHOOK-"+this.hookName+"]执行失败",var4);
					}
				}

			}
		}

		public Thread getShutdownhook() {
			return this.shutdownhook;
		}

		public void setShutdownhook(Thread shutdownhook) {
			this.shutdownhook = shutdownhook;
		}
	}
}